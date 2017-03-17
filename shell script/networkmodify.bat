@echo off
set runpath=echo %cd%
cd %~d0%~p0
if not exist log md log
echo [%date:~0,4%-%date:~5,2%-%date:~8,2%-%time%]Your Request:%0 %*>>.\log\networkmodify-%date:~0,4%-%date:~5,2%-%date:~8,2%.log
echo "modify %1 network to %1 %2 %3 %4" >>.\log\networkmodify-%date:~0,4%-%date:~5,2%-%date:~8,2%.log
rem %runpath%\log\
if "%4" == "" goto Nogateway
goto gateway
:Nogateway
netsh interface ip set address name=%1 source=static addr=%2 mask=%3
goto end
:gateway
netsh interface ip set address name=%1 source=static addr=%2 mask=%3 gateway=%4 1
:end
echo "response code:%errorlevel%" >>.\log\networkmodify-%date:~0,4%-%date:~5,2%-%date:~8,2%.log