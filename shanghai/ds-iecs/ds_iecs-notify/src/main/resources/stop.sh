#!/bin/sh
echo stoping
pid=`ps -aux|grep ds-iecs-notify | grep -v grep | awk '{print $2}'`
echo $pid
kill -9 $pid
echo stoped
