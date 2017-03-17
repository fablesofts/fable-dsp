#!/bin/bash
#installcheck.sh
kernelname=`uname -s`
kernelversion=`uname -r`
nodename=`uname -n`
machinename=`uname -m`
processor=`uname -p`
hardware=`uname -i`
operatingsystem=`uname -o`
port="1099 1199"
checklinux()
{
if [ "${kernelname}" != "Linux" ];then
echo "Check fail ,your kernel is ${kernelname}" |tee -a installcheck.log && exit 1
else 
echo "Check pass,your kernel is ${kernelname}" |tee -a installcheck.log
echo "[kernelname]=${kernelname}
[kernelversion]=${kernelversion}
[nodename]=${nodename}
[machinename]=${machinename}
[processor]=${processor}
[hardware]=${hardware}
[operatingsystem]=${operatingsystem}" |tee -a installcheck.log
fi
if [ "$SHELL" != "/bin/bash" ];then
echo "Check fail ,your SHELL is $SHELL" |tee -a installcheck.log && exit 1
else
echo "[SHELL]=$SHELL" |tee -a installcheck.log
fi
if [ "$USER" != "root" ];then
echo "Check fail ,your USER is $USER" |tee -a installcheck.log && exit 1
else
echo "[USER]=$USER" |tee -a installcheck.log
fi
}
checkhost()
{
for j in `ifconfig ${i}  | sed -n '/inet addr:/ s/inet addr://pg' | awk -F" " '{print $1}'`
do
ipaddr=`grep "${j}" /etc/hosts|awk '{print $1}'`
hostname=`grep "${j}" /etc/hosts|awk '{print $2}'`
if [ "${j}" = "${ipaddr}" ] && [ "X${hostname}" != "X" ];then
echo "[IPADDR]=${ipaddr}
[HOST]=${hostname}
[NETWORK]=${i}" |tee -a installcheck.log
else
echo "Check fail,your ${i} IPADDR is ${j} and HOST is not found " |tee -a installcheck.log && exit 1
fi
done
}
checknetwork()
{
for i in `ifconfig -a |grep HWaddr |awk '{print $1}'`
do
if [ ! ${i} ];then
echo "Check fail ,Read Network fail,no network was found" |tee -a installcheck.log && exit 1
fi
checkhost
if [ -f "/etc/sysconfig/network-scripts/ifcfg-${i}" ];then
echo "Check pass ,network ${i}'s config file ifcfg-${i} was found" |tee -a installcheck.log
else
echo "Check fail ,network ${i}'s config file ifcfg-${i} was not found,Please check " |tee -a installcheck.log && exit 1
fi
networkcard="${networkcard},${i}"
quantity=`expr ${quantity} + 1`
done
if [ ${quantity} -lt 2 ];then
echo "Check fail ,network 's total is ${quantity}, Please check" |tee -a installcheck.log && exit 1
fi
echo "total:${quantity}${networkcard}" |tee -a installcheck.log
}
checkjdk()
{
java -version
if [ $? -eq 0 ];then
echo "Check pass,JDK was found" |tee -a installcheck.log
else
echo "Check fail,java jdk was not fount" |tee -a installcheck.log && exit 1
fi
if [ "X${JAVA_HOME}" != "X" ] && [ -d "${JAVA_HOME}" ];then
echo "Check pass,JAVA_HOME is ${JAVA_HOME}" |tee -a installcheck.log
else
echo "Check fail,JAVA_HOME was not set or set JAVA_HOME error" |tee -a installcheck.log && exit 1
fi
}
checkport()
{
for portnum in ${port}
do
netstat -an|grep ${portnum}
if [ $? -ne 0 ];then
echo "Check port ${portnum} pass" |tee -a installcheck.log
else
echo "Check port ${portnum} fail,port ${portnum} has in use,Please check." |tee -a installcheck.log && exit 1
fi
done
}
echo "Install check is starting,Please wait..."
echo "[`date -d now +%Y-%m-%d-%T`]Install check is starting,Please wait..." >> installcheck.log
echo "Checking operatingsystem,Please wait..." |tee -a installcheck.log
checklinux
echo "Checking port,Please wait..." |tee -a installcheck.log
checkport
echo "Checking network,Please wait..." |tee -a installcheck.log
checknetwork
echo "Checking java jdk..." |tee -a installcheck.log
checkjdk