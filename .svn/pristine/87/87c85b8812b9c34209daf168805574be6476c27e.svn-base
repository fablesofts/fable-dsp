#!/bin/bash
#installdatabase.sh
softversion="V3.0"
runpath=`dirname $0`
if [ ! -w "${runpath}" ];then
echo "Path:${runpath} do not hava permission to write,Please check" && exit 1
fi
if [ ! -d "${runpath}/log" ];then
mkdir -p ${runpath}/log
fi
echo '**********FABLE--SOFTWARE*******'
echo "Welcome to use Data Change DataBase Intall ${softversion}"
echo '**********FABLE--SOFTWARE*******'
quitprogram()
{
if [ "$1" = "quit" ];then
echo 'Install program exit ' |tee -a ${runpath}/log/installdatabase.log
exit 0
fi
}
tips()
{
echo "Sorry,Not support ${Dbtype} on this version,Please wait for the next version for support" |tee -a ${runpath}/log/installdatabase.log && exit 1
}
checkuseroracle()
{
if [ "${USER}" != "oracle" ];then
echo "Your User is ${USER},Please use User:oracle to login for execute" |tee -a ${runpath}/log/installdatabase.log && exit 1
fi
}
oraclesqlexec()
{
echo -e "Please Input your $1 datafile $2 SIZE:(Default:500M) \c"
read filesize
quitprogram ${filesize}
if [ ! "${filesize}" ];then
filesize=500M
fi
echo "Your $1 datafile $2 SIZE:${filesize}" |tee -a ${runpath}/log/installdatabase.log
createsql="CREATE TABLESPACE $3
    DATAFILE '${filepath}/$4'
    SIZE ${filesize} AUTOEXTEND ON NEXT 5M MAXSIZE UNLIMITED
    LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO;"
if [ "$5" = "temp" ];then
createsql="CREATE temporary TABLESPACE $3
    ${spacetype}FILE '${filepath}/$4'
    SIZE ${filesize} AUTOEXTEND ON NEXT 5M MAXSIZE UNLIMITED
    LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO;"
fi
echo "Use sysdba to execute $1 SQL:${createsql}" |tee -a ${runpath}/log/installdatabase.log
sqlplus / as sysdba <<EOF
spool ${runpath}/log/installdatabase$3.log
${createsql}
spool off
exit;
EOF
}
createoracleuser()
{
createsql="CREATE USER $1 PROFILE DEFAULT IDENTIFIED BY $2 DEFAULT TABLESPACE $3 ACCOUNT UNLOCK;
GRANT DBA TO $1;"
echo "Use sysdba to execute SQL:${createsql}" |tee -a ${runpath}/log/installdatabase.log
sqlplus / as sysdba <<EOF
spool ${runpath}/log/installdatabase$1.log
${createsql}
spool off
exit;
EOF
}
installoracle()
{
echo "You have select dbtype ${Dbtype}" |tee -a ${runpath}/log/installdatabase.log
while [ ! "${filepath}" ]
do
echo "Please input your database ${Dbtype} datafile install path" |tee -a ${runpath}/log/installdatabase.log
echo -e "Please Input: \c"
read filepath
quitprogram ${filepath}
if [ ! "${filepath}" ];then
echo "You have not input Install path,Please input correct Install Path" |tee -a ${runpath}/log/installdatabase.log
fi
done
echo "You has input your database ${Dbtype} datafile path:${filepath}" |tee -a ${runpath}/log/installdatabase.log
if [ ! -d "${filepath}" ];then
echo "path:${filepath} is not exist,Please check" |tee -a ${runpath}/log/installdatabase.log && exit 1
fi
if [ ! -w "${filepath}" ];then
echo "path:${filepath} do not hava permission to write,Please check" |tee -a ${runpath}/log/installdatabase.log && exit 1
fi
while [ "${spacetype}" != "data" ] && [ "${spacetype}" != "temp" ]
do
{
echo "Please choose your ${Dbtype} Tablespace type:"
echo "1:General Tablespace"
echo "2:Temporary(temp) Tablespace"
echo -e "Please Input: \c"
read Spacetype
quitprogram ${Spacetype}
case ${Spacetype} in
  1) spacetype=data ;;
  2) spacetype=temp ;;
  *) echo "Your choice ${Spacetype} is false,Please choose correct item" |tee -a ${runpath}/log/installdatabase.log
esac
}
done
echo "Tablespace type=[${Spacetype}]" |tee -a ${runpath}/log/installdatabase.log
echo -e "Please Input which your ${Dbtype} Tablespace's name to create:(Default Name:FABLE_${spacetype}) \c"
read tablespacename
quitprogram ${tablespacename}
if [ ! "${tablespacename}" ];then
tablespacename=FABLE_${spacetype}
fi
echo "Talblespacename=[${tablespacename}]" |tee -a ${runpath}/log/installdatabase.log
echo -e "Please Input which your ${Dbtype} Tablespace [${tablespacename}] datafile's name to create:(Default Name:FABLE_${spacetype}.ora) \c"
read filename
quitprogram ${filename}
if [ ! "${filename}" ];then
filename=FABLE_${spacetype}.ora
fi
echo "Talblespace's file name=[${filename}]" |tee -a ${runpath}/log/installdatabase.log
oraclesqlexec ${Dbtype} ${tablespacename} ${tablespacename} ${filename} ${spacetype}
if [ "${spacetype}" = "data" ];then
echo "Create database ${Dbtype} user..." |tee -a ${runpath}/log/installdatabase.log
else
exit 0
fi
while [ ! "${dbuser}" ]
do
echo "Please input your database ${Dbtype} which user name need to create" |tee -a ${runpath}/log/installdatabase.log
echo -e "Please Input: \c"
read dbuser
quitprogram ${dbuser}
if [ ! "${dbuser}" ];then
echo "You have not input user name,Please input correct user name" |tee -a ${runpath}/log/installdatabase.log
fi
done
echo "Your database ${Dbtype} user is ${dbuser}" |tee -a ${runpath}/log/installdatabase.log
while [ ! "${dbpassword}" ]
do
echo "Please input your database ${Dbtype} ${dbuser} which user password need to create" |tee -a ${runpath}/log/installdatabase.log
echo -e "Please Input: \c"
read dbpassword
quitprogram ${dbpassword}
if [ ! "${dbpassword}" ];then
echo "You have not input ${dbuser}'s password,Please input correct password" |tee -a ${runpath}/log/installdatabase.log
fi
done
echo "Your database ${Dbtype} user ${dbuser}'s Password is ${dbpassword}" |tee -a ${runpath}/log/installdatabase.log
createoracleuser ${dbuser} ${dbpassword} ${tablespacename}
}
while [ "${Dbtype}" != "ORACLE" ] && [ "${Dbtype}" != "MYSQL" ] && [ "${Dbtype}" != "SQLSERVER" ]
do
{
echo "Please choose your Dbtype:"
echo "1:Oracle database"
echo "2:MySQL database"
echo "3:SQLSERVER database"
echo -e "Please Input: \c"
read num
quitprogram ${num}
case ${num} in
  1) Dbtype=ORACLE && checkuseroracle && installoracle ;;
  2) Dbtype=MYSQL && tips ;;
  3) Dbtype=SQLSERVER && tips ;;
  *) echo "Your choice ${num} is false,Please choose correct item" |tee -a ${runpath}/log/installdatabase.log
esac
}
done