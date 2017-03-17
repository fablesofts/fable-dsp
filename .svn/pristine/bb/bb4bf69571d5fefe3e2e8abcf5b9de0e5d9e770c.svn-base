#!/bin/bash
#defaultinstallsql.sh
softversion="V3.0"
runpath=`dirname $0`
oraclefile="123.sql 345.sql 456.sql"
mysqlfile=""
sqlserverfile=""
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
echo 'Install program exit ' |tee -a ${runpath}/log/installsql.log
exit 0
fi
}
tips()
{
echo "Sorry,Not support ${Dbtype} on this version,Please wait for the next version for support" |tee -a ${runpath}/log/installsql.log && exit 1
}
checkuseroracle()
{
if [ "${USER}" != "oracle" ];then
echo "Your User is ${USER},Please use User:oracle to login for execute" |tee -a ${runpath}/log/installsql.log && exit 1
fi
}
yesorno()
{
while true
do
echo -e "$1[y/n] \c"
read request
quitprogram ${request}
case ${request} in
[Yy]) return 0 ;;
[Nn]) return 1 ;;
*) echo "Your choose ${request} is error,Please choose again" |tee -a ${runpath}/log/installsql.log
esac
done
}
oraclesqlexec()
{
sqlfile=""
for i in ${oraclefile}
do
sqlfile="@${i};\n${sqlfile}"
done
sqlfileexec=`echo -e "${sqlfile}"`
echo "Use database ${Dbtype}(SID:$3) user $1 to execute SQLFILE:${sqlfileexec}" |tee -a ${runpath}/log/installsql.log
sqlplus $1/$2@localhost:1521/$3 <<EOF
spool ${runpath}/log/installsqlexec.log
${sqlfileexec}
spool off
exit;
EOF
}
installoracle()
{
echo "You have select dbtype ${Dbtype}" |tee -a ${runpath}/log/installsql.log
databasetype=${Dbtype}
username=fable
password=fable
oraclesid=orcl
oracleaddress=localhost
echo "Databasetype=[${databasetype}]
Username=[${username}]
Password=[${password}]
${Dbtype}sid=[${oraclesid}]
${Dbtype}address=[${oracleaddress}]" |tee -a ${runpath}/log/installsql.log
if yesorno "Are you sure use this config to install sqlfile? ";then
echo "You have sure use this config to install sqlfile" |tee -a ${runpath}/log/installsql.log
oraclesqlexec ${username} ${password} ${oraclesid}
else
echo "You hava choose not to install,Program exit" |tee -a ${runpath}/log/installsql.log && exit 1
fi
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
  *) echo "Your choice ${num} is false,Please choose correct item" |tee -a ${runpath}/log/installsql.log
esac
}
done