#!/bin/bash
#networkmodify.sh
#errorcode1,Read Network fail
#errorcode2,Netwok start fail
#errorcode3,Call parameters error
runpath=`dirname $0`
if [ ! -d "${runpath}/log" ];then
mkdir -p ${runpath}/log
fi
tishi()
{
if [ $? -ne 0 ];then
echo "errorcode:2,Network $1 fail"
echo "[`date -d now +%Y-%m-%d-%T`] Network $1 fail" >> ${runpath}/log/networkmodify.log && exit 1
else
echo "errorcode:0,Network $1 success"
echo "[`date -d now +%Y-%m-%d-%T`] Network $1 success" >> ${runpath}/log/networkmodify.log
fi
}
modifyhosts()
{
ipaddres=`ifconfig $1  | sed -n '/inet addr:/ s/inet addr://pg' | awk -F" " '{print $1}'`
ipaddr=`grep "${ipaddres}" /etc/hosts|awk '{print $1}'`
ipaddr1=`grep "$2" /etc/hosts|awk '{print $1}'`
namehost1=`grep "${ipaddres}" /etc/hosts|awk '{print $2}'`
if [ "X${ipaddres}" != "X" ] && [ "X${ipaddr}" != "X" ] && [ "${ipaddres}" = "${ipaddr}" ] && [ "X${namehost1}" != "X" ] && [ "X${ipaddr1}" = "X" ] ;then
ipaddres=$2
namehost=`echo ${ipaddres##*.}`
echo "${ipaddr} ${namehost1} has modify to $2 fable${namehost}" |tee -a ${runpath}/log/networkmodify.log
sed -i "s/${ipaddr}/$2/g" /etc/hosts
sed -i "s/${namehost1}/fable${namehost}/g" /etc/hosts
elif [ "X${ipaddr1}" != "X" ];then
echo "$2 has in hosts,do not to modify" |tee -a ${runpath}/log/networkmodify.log
elif [ "X${ipaddr1}" = "X" ] && [ "X${ipaddr}" = "X" ];then
ipaddres=$2
namehost=`echo ${ipaddres##*.}`
echo "Add ${ipaddres} fable${namehost} to hosts" |tee -a ${runpath}/log/networkmodify.log
echo "${ipaddres} fable${namehost}" >> /etc/hosts
else
echo "unkonow error" |tee -a ${runpath}/log/networkmodify.log
fi
}
checkipaddres()
{
ipaddres=`ifconfig $1  | sed -n '/inet addr:/ s/inet addr://pg' | awk -F" " '{print $1}'`
ifconfig |grep $2 >> ${runpath}/log/networkmodify.log
if [ $? -eq 0 ] && [ "${ipaddres}" != "$2" ];then
echo "Ipaddres $2 has exist,Please check" |tee -a ${runpath}/log/networkmodify.log && exit 1
fi
}
modifynetwork()
{
checkipaddres $1 $2
modifyhosts $1 $2
ifup $1
hwaddr=`ifconfig -a |grep HWaddr |grep $1 |awk '{print $5}'`
echo "modify $1 ${hwaddr} $2 $3 $4"
echo "[`date -d now +%Y-%m-%d-%T`] modify $1 ${hwaddr} $2 $3 $4" >> ${runpath}/log/networkmodify.log
echo "DEVICE=$1
HWADDR=$hwaddr
TYPE=Ethernet
ONBOOT=yes
NM_CONTROLLED=yes
BOOTPROTO=static
IPADDR=$2
NETMASK=$3" > /etc/sysconfig/network-scripts/ifcfg-$1
if [ "$4" != "null" ];then
echo "GATEWAY=$4" >> /etc/sysconfig/network-scripts/ifcfg-$1
fi
#ifconfig $1 down
ifdown $1
tishi shutdown
#ifconfig $1 up
ifup $1
tishi startup
}
readnetwork()
{
for i in `ifconfig -a |grep HWaddr |awk '{print $1}'`
do
if [ ! ${i} ];then
echo "errorcode:1,Read Network fail,no network was found"
echo "[`date -d now +%Y-%m-%d-%T`] Read Network fail,no eth0 network" >> ${runpath}/log/networkmodify.log && exit 1
fi
netconfigs=`ifconfig|grep ${i} -A 3|sed -e '/^$/d'| awk '{if(NR%2!=0)ORS=" ";else ORS="\n";print}'|grep ${i}|awk '{print $1,$5,$7,$9}'|sed -n "s/${i} /${i}\:/gp"`
gateway=`route|grep ${i}|awk '{print $2}'|grep -v \*`
echo "${netconfigs},gateway:${gateway}"|sed -n "s/ /\,/gp" |tee -a ${runpath}/log/networkmodify.log
done
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
[NETWORK]=${i}" |tee -a ${runpath}/log/networkmodify.log
else
echo "Check fail,your ${i} IPADDR is ${j} and HOST is not found" |tee -a ${runpath}/log/networkmodify.log && exit 1
fi
done
}
checknetwork()
{
for i in `ifconfig -a |grep HWaddr |awk '{print $1}'`
do
if [ ! ${i} ];then
echo "Check fail ,Read Network fail,no network was found" |tee -a ${runpath}/log/networkmodify.log && exit 1
fi
checkhost
if [ -f "/etc/sysconfig/network-scripts/ifcfg-${i}" ];then
echo "Check pass ,network ${i}'s config file ifcfg-${i} was found" |tee -a ${runpath}/log/networkmodify.log
else
echo "Check fail ,network ${i}'s config file ifcfg-${i} was not found,Please check" |tee -a ${runpath}/log/networkmodify.log && exit 1
fi
networkcard="${networkcard},${i}"
quantity=`expr ${quantity} + 1`
done
if [ ${quantity} -lt 2 ];then
echo "Check fail ,network 's total is ${quantity}, Please check" |tee -a ${runpath}/log/networkmodify.log && exit 1
fi
echo "total:${quantity}${networkcard}" |tee -a ${runpath}/log/networkmodify.log
}
echo "[`date -d now +%Y-%m-%d-%T`]Your Request:$0 $*" >> ${runpath}/log/networkmodify.log
case $1 in
  'read') readnetwork ;;
  'ipset') modifynetwork $2 $3 $4 $5 ;;
  'check') checknetwork ;;
  *) echo "errorcode:3,Call parameters error"
     echo "[`date -d now +%Y-%m-%d-%T`] Call parameters error" >> ${runpath}/log/networkmodify.log && exit 1 ;;
esac