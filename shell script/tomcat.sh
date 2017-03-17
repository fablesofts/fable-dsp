#!/bin/bash
#tomcat.sh
TOMCAT_HOME=/home/apache-tomcat-6.0.20
service=apache-tomcat-6.0.20
servicename=tomcatservice
PIDS=`ps -ef | grep "${service}" | grep -v grep | awk '{print $2}'`
case $1 in
  'start')
  	if [ "X$PIDS" != "X" ]; then
		echo "${servicename} has been started, the PID = "${PIDS}""
	else
	echo "${servicename} is startting..."
    ${TOMCAT_HOME}/bin/startup.sh
	fi ;;
  'restart') 
  	if [ "X$PIDS" != "X" ]; then
		kill -9 $PIDS
		echo "${servicename} has been killed, PID="${PIDS}"!"
	fi
	echo "${servicename} is startting..."
    ${TOMCAT_HOME}/bin/startup.sh ;;
  'status') 
  	if [ "X$PIDS" != "X" ]; then
		echo "${servicename} has been started, the PID = "${PIDS}""
	else 
	   echo "${servicename} has not been started " 
        fi ;;
  'stop') 
  	if [ "X$PIDS" != "X" ]; then
		kill -9 $PIDS
		echo "${servicename} has been killed, PID="${PIDS}"!"
	else
		echo "${servicename} has not been started, the stop command do nothing."
	fi  ;;
  *) echo "Call parameters error"
     echo "Usage:service tomcat | start | restart | stop | status"
     exit 1 ;;
esac
