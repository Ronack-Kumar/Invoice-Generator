@echo off
echo Generating Sample Invoice...
echo.

REM Check if Maven is installed
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

REM Compile and run the application with sample data
echo Compiling project...
mvn clean compile -q
if errorlevel 1 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

echo.
echo Generating sample invoice...
echo.
mvn exec:java -Dexec.mainClass="com.invoicegen.InvoiceGeneratorApp" -Dexec.args="--sample" -q

echo.
echo Sample invoice generation completed.
pause
