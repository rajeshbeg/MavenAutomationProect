set projectLocation=D:\Autom@tix\Automation_Project
cd %projectLocation%
call mvn -f adhoc_pom.xml clean install
call mvn exec:java@RunChangeExecutionStatus
call mvn exec:java@RunDumpReportToDB
call mvn exec:java@RunExcelReportGenerator
echo "Generating Report..."
timeout /t 5