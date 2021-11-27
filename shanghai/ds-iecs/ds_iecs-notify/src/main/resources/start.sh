echo start
pkill -f ds-iecs-notify.jar
sleep 1
nohup java -jar $(dirname $(readlink -f "$0"))/ds-iecs-notify.jar >/dev/null 2>&1&
echo end

