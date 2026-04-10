@echo off
echo Starting Invoice Generator...
echo.

REM Check if Maven is installed
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

REM Compile and run the application
echo Compiling project...
mvn clean compile -q
if errorlevel 1 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

echo.
echo Running Invoice Generator...
echo.
mvn exec:java -Dexec.mainClass="com.invoicegen.InvoiceGeneratorApp" -q

echo.
echo Invoice Generator finished.
pause
