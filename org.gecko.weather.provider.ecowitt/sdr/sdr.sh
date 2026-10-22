#!/usr/bin/env bash
# The radio side of org.gecko.weather.provider.ecowitt: rtl_433 on an RTL-SDR (DVB-T) stick,
# decoding the station's 868 MHz telegrams and serving them as a JSON line stream over HTTP.
#
#   sdr.sh start     start rtl_433 in the background (no-op if it already runs)
#   sdr.sh stop      stop the rtl_433 this script started
#   sdr.sh restart
#   sdr.sh status    running? stick present? stream answering? last telegram?
#   sdr.sh log       follow the log
#   sdr.sh check     one-off: decode for 30 s in the foreground, nothing started
#   sdr.sh install [--service]
#                    apt packages rtl-433 and rtl-sdr, blacklist and unload the DVB-T kernel driver
#                    (asks for sudo where needed); --service also installs and starts a systemd
#                    user unit gecko-weather-sdr that runs this script at login
#   sdr.sh uninstall [--purge]
#                    remove the user unit and the blacklist; --purge also removes the packages
#
# Settings, overridable in the environment or in sdr.env next to this script:
#   SDR_FREQ=868.3M   SDR_GAIN=19.2   SDR_RATE=1024k   SDR_PORT=8433   SDR_HOST=127.0.0.1
#   SDR_EXTRA=""      further rtl_433 arguments, e.g. "-R 244" to decode only the WS90
#   SDR_RUN_DIR       pid and log; default ~/.local/state/gecko-weather-sdr
#
# Fixed gain matters: with auto gain a cheap FC0012 tuner, which reaches 868 MHz only at the
# edge of its range, decodes nothing. -M level adds RSSI and SNR to every telegram.
set -euo pipefail

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
[ -f "$HERE/sdr.env" ] && . "$HERE/sdr.env"

SDR_FREQ="${SDR_FREQ:-868.3M}"
SDR_GAIN="${SDR_GAIN:-19.2}"
SDR_RATE="${SDR_RATE:-1024k}"
SDR_PORT="${SDR_PORT:-8433}"
SDR_HOST="${SDR_HOST:-127.0.0.1}"
SDR_EXTRA="${SDR_EXTRA:-}"
SDR_RUN_DIR="${SDR_RUN_DIR:-${XDG_STATE_HOME:-$HOME/.local/state}/gecko-weather-sdr}"

PIDFILE="$SDR_RUN_DIR/rtl_433.pid"
LOGFILE="$SDR_RUN_DIR/rtl_433.log"
STREAM="http://$SDR_HOST:$SDR_PORT/stream"

# shellcheck disable=SC2206
ARGS=(-f "$SDR_FREQ" -g "$SDR_GAIN" -s "$SDR_RATE" -M level -M time:utc $SDR_EXTRA)

die() { echo "sdr: $*" >&2; exit 1; }

need_tools() {
	command -v rtl_433 >/dev/null || die "rtl_433 is not installed (sudo apt install rtl-433 rtl-sdr)"
}

stick_present() {
	lsusb 2>/dev/null | grep -q -i -E "RTL2838|RTL2832|0bda:2838|0bda:2832"
}

running_pid() {
	[ -f "$PIDFILE" ] || return 1
	local pid
	pid="$(cat "$PIDFILE" 2>/dev/null)" || return 1
	[ -n "$pid" ] && kill -0 "$pid" 2>/dev/null && grep -q rtl_433 "/proc/$pid/comm" 2>/dev/null && echo "$pid"
}

start() {
	need_tools
	if pid="$(running_pid)"; then
		echo "sdr: already running (pid $pid), stream $STREAM"
		return 0
	fi
	stick_present || die "no RTL-SDR stick on USB (lsusb shows no RTL2838/RTL2832)"
	if lsmod 2>/dev/null | grep -q '^dvb_usb_rtl28xxu'; then
		echo "sdr: note: the DVB-T kernel driver is loaded; librtlsdr detaches it per run. To keep it off:" >&2
		echo "     echo 'blacklist dvb_usb_rtl28xxu' | sudo tee /etc/modprobe.d/blacklist-rtlsdr.conf" >&2
	fi
	if other="$(pgrep -x rtl_433 | head -1)"; then
		die "another rtl_433 (pid $other) holds the stick; stop it first"
	fi
	mkdir -p "$SDR_RUN_DIR"
	echo "sdr: starting rtl_433 ${ARGS[*]} -F http:$SDR_HOST:$SDR_PORT" >> "$LOGFILE"
	nohup rtl_433 "${ARGS[@]}" -F "http:$SDR_HOST:$SDR_PORT" -F json:/dev/null >> "$LOGFILE" 2>&1 &
	echo $! > "$PIDFILE"
	sleep 2
	if pid="$(running_pid)"; then
		echo "sdr: rtl_433 running (pid $pid), stream $STREAM, log $LOGFILE"
	else
		rm -f "$PIDFILE"
		echo "--- last log lines ---" >&2
		tail -n 8 "$LOGFILE" >&2
		die "rtl_433 did not stay up"
	fi
}

stop() {
	if pid="$(running_pid)"; then
		kill "$pid"
		for _ in 1 2 3 4 5 6 7 8 9 10; do
			kill -0 "$pid" 2>/dev/null || break
			sleep 0.5
		done
		kill -0 "$pid" 2>/dev/null && kill -9 "$pid"
		rm -f "$PIDFILE"
		echo "sdr: stopped rtl_433 (pid $pid)"
	else
		rm -f "$PIDFILE"
		echo "sdr: not running"
	fi
}

status() {
	local rc=0
	if pid="$(running_pid)"; then
		echo "rtl_433:  running, pid $pid, since $(ps -o lstart= -p "$pid" | sed 's/^ *//')"
	else
		echo "rtl_433:  not running"
		rc=1
	fi
	if stick_present; then echo "stick:    present"; else echo "stick:    NOT on USB"; rc=1; fi
	if curl -s -o /dev/null --max-time 2 "http://$SDR_HOST:$SDR_PORT/"; then
		echo "stream:   $STREAM"
		local line
		line="$(curl -s -N --max-time 12 "$STREAM" | head -n 1 || true)"
		if [ -n "$line" ]; then
			echo "telegram: $line"
		else
			echo "telegram: none within 12 s (station out of range, wrong band or gain?)"
		fi
	else
		echo "stream:   not answering on $SDR_HOST:$SDR_PORT"
		rc=1
	fi
	return $rc
}

check() {
	need_tools
	if pid="$(running_pid)"; then
		die "rtl_433 runs already (pid $pid); use status, or stop first"
	fi
	echo "sdr: decoding on $SDR_FREQ for 30 s ..."
	rtl_433 "${ARGS[@]}" -T 30 -F json
}

BLACKLIST=/etc/modprobe.d/blacklist-rtlsdr.conf
UNIT_DIR="${XDG_CONFIG_HOME:-$HOME/.config}/systemd/user"
UNIT="$UNIT_DIR/gecko-weather-sdr.service"

install_packages() {
	if command -v rtl_433 >/dev/null && command -v rtl_test >/dev/null; then
		echo "sdr: packages rtl-433 and rtl-sdr are installed"
		return 0
	fi
	command -v apt-get >/dev/null || die "no apt-get; install rtl_433 and the rtl-sdr tools by hand"
	echo "sdr: installing rtl-433 and rtl-sdr (sudo)"
	sudo apt-get install -y rtl-433 rtl-sdr
}

install_blacklist() {
	if [ -f "$BLACKLIST" ] && grep -q '^blacklist dvb_usb_rtl28xxu' "$BLACKLIST"; then
		echo "sdr: $BLACKLIST is in place"
	else
		echo "sdr: blacklisting the DVB-T kernel driver in $BLACKLIST (sudo)"
		printf '# RTL-SDR sticks are used by rtl_433 (gecko weather), not as DVB-T receivers\nblacklist dvb_usb_rtl28xxu\n' | sudo tee "$BLACKLIST" >/dev/null
	fi
	if lsmod 2>/dev/null | grep -q '^dvb_usb_rtl28xxu'; then
		echo "sdr: unloading the DVB-T kernel driver (sudo)"
		sudo modprobe -r dvb_usb_rtl28xxu rtl2832_sdr rtl2832 2>/dev/null || sudo rmmod dvb_usb_rtl28xxu rtl2832_sdr rtl2832
	fi
}

install_service() {
	command -v systemctl >/dev/null || die "no systemctl; run '$0 start' from your own session instead"
	mkdir -p "$UNIT_DIR"
	cat > "$UNIT" <<UNITEOF
[Unit]
Description=rtl_433 for the Gecko Weather Ecowitt station (SDR)
After=network.target

[Service]
Type=forking
ExecStart=$HERE/sdr.sh start
ExecStop=$HERE/sdr.sh stop
PIDFile=$PIDFILE
Restart=on-failure
RestartSec=10

[Install]
WantedBy=default.target
UNITEOF
	systemctl --user daemon-reload
	systemctl --user enable gecko-weather-sdr.service >/dev/null 2>&1
	if pid="$(running_pid)"; then
		echo "sdr: rtl_433 already runs (pid $pid) outside the unit; stopping it so the unit takes over"
		stop
	fi
	systemctl --user start gecko-weather-sdr.service
	echo "sdr: user unit gecko-weather-sdr installed, enabled and started ($UNIT)"
	echo "     to run it without a login session: loginctl enable-linger $USER"
}

install_all() {
	install_packages
	install_blacklist
	stick_present || echo "sdr: note: no RTL-SDR stick on USB right now; plug it in before start" >&2
	if [ "${1:-}" = "--service" ]; then
		install_service
	else
		echo "sdr: done; '$0 start' runs rtl_433 now, '$0 install --service' makes it a user service"
	fi
}

uninstall_all() {
	if [ -f "$UNIT" ]; then
		systemctl --user disable --now gecko-weather-sdr.service >/dev/null 2>&1 || true
		rm -f "$UNIT"
		systemctl --user daemon-reload
		echo "sdr: user unit gecko-weather-sdr removed"
	fi
	stop
	if [ -f "$BLACKLIST" ]; then
		echo "sdr: removing $BLACKLIST (sudo); the DVB-T driver claims the stick again after a replug or reboot"
		sudo rm -f "$BLACKLIST"
	fi
	if [ "${1:-}" = "--purge" ]; then
		echo "sdr: removing the packages rtl-433 and rtl-sdr (sudo)"
		sudo apt-get remove -y rtl-433 rtl-sdr
	fi
	rm -rf "$SDR_RUN_DIR"
	echo "sdr: uninstalled"
}

case "${1:-}" in
	start) start ;;
	stop) stop ;;
	restart) stop; start ;;
	status) status ;;
	log) mkdir -p "$SDR_RUN_DIR"; touch "$LOGFILE"; tail -n 20 -f "$LOGFILE" ;;
	check) check ;;
	install) install_all "${2:-}" ;;
	uninstall) uninstall_all "${2:-}" ;;
	*) sed -n '2,/^set -e/{/^set -e/d;s/^# \{0,1\}//p}' "$0"; exit 2 ;;
esac
