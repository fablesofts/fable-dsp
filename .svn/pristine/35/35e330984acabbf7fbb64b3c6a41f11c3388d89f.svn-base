#!/bin/bash
#installsql.sh
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
echo "Your User is ${USER},Please use User:oracle to login for execute " |tee -a ${runpath}/log/installsql.log && exit 1
fi
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
while [ ! "${username}" ]
do
echo "Please input your database ${Dbtype}'s user name" |tee -a ${runpath}/log/installsql.log
echo -e "Please Input: \c"
read username
quitprogram ${username}
if [ ! "${username}" ];then
echo "You have not input user name Please input correct user name" |tee -a ${runpath}/log/installsql.log
fi
done
echo "You has input your database ${Dbtype} user name [${username}]" |tee -a ${runpath}/log/installsql.log
while [ ! "${password}" ]
do
echo "Please input your database ${Dbtype}'s user  ${username}'s password" |tee -a ${runpath}/log/installsql.log
echo -e "Please Input: \c"
read password
quitprogram ${password}
if [ ! "${password}" ];then
echo "You have not input database ${Dbtype}'s user  ${username}'s password, Please input correct password" |tee -a ${runpath}/log/installsql.log
fi
done
echo "You has input your database ${Dbtype} user name ${username}'s Password [${password}]" |tee -a ${runpath}/log/installsql.log
while [ ! "${oraclesid}" ]
do
echo "Please input your database ${Dbtype}'s sid" |tee -a ${runpath}/log/installsql.log
echo -e "Please Input: \c"
read oraclesid
quitprogram ${oraclesid}
if [ ! "${oraclesid}" ];then
echo "You have not input database ${Dbtype}'s sid, Please input correct sid." |tee -a ${runpath}/log/installsql.log
fi
done
echo "You has input your database ${Dbtype} sid [${oraclesid}]" |tee -a ${runpath}/log/installsql.log
oraclesqlexec ${username} ${password} ${oraclesid}
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