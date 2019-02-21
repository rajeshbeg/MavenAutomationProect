@ECHO OFF
SET projectdir="D:\Autom@tix\Automation_Project"
REM Example file
:LOOP    
IF NOT EXIST %projectdir%"\automationrunner\run\sanityrun.txt" GOTO SANITYEND
REM All this gets done if the file exists...

CLS
echo "WAITING 30 MINUTES FOR THE SERVER To RESTART"
timeout /t 20

call %projectdir%\automationrunner\sanityrunner.bat
call %projectdir%\automationrunner\SendSanityReport.vbs
del %projectdir%"\automationrunner\run\sanityrun.txt"

:SANITYEND
IF NOT EXIST %projectdir%"\automationrunner\run\adhocrun.txt" GOTO ADHOCEND
REM All this gets done if the file exists...

CLS
echo "Initializing..."
timeout /t 10

call %projectdir%\automationrunner\adhocrunner.bat
del %projectdir%"\automationrunner\run\adhocrun.txt"

:ADHOCEND
IF EXIST %projectdir%"\automationrunner\run\adhocrun.txt" GOTO ADHOCQUEUEEND

CLS
echo "Checking for the Queued Automation Runs...."
timeout /t 10
cd %projectdir%
call mvn exec:java@RunPNBTestNGXML

:ADHOCQUEUEEND
REM Crafty 1 minute delay...
timeout /t 40
GOTO LOOP