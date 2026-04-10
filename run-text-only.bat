@echo off
echo Compiling Invoice Generator (Text Only Version)...
echo.

REM Create output directories
if not exist "classes" mkdir classes
if not exist "output" mkdir output

REM Compile Java files
echo Compiling source files...
javac -d classes -cp "src\main\java" src\main\java\com\invoicegen\model\*.java
if errorlevel 1 goto :error

javac -d classes -cp "src\main\java;classes" src\main\java\com\invoicegen\generator\InvoiceGenerator.java
if errorlevel 1 goto :error

javac -d classes -cp "src\main\java;classes" src\main\java\com\invoicegen\generator\TextInvoiceGenerator.java
if errorlevel 1 goto :error

javac -d classes -cp "src\main\java;classes" src\main\java\com\invoicegen\input\ConsoleInputHandler.java
if errorlevel 1 goto :error

javac -d classes -cp "src\main\java;classes" src\main\java\com\invoicegen\util\SampleDataGenerator.java
if errorlevel 1 goto :error

javac -d classes -cp "src\main\java;classes" src\main\java\com\invoicegen\InvoiceGeneratorApp.java
if errorlevel 1 goto :error

echo.
echo Compilation successful!
echo Running Invoice Generator (Text format only)...
echo.

REM Run the application
java -cp classes com.invoicegen.InvoiceGeneratorApp

goto :end

:error
echo.
echo ERROR: Compilation failed!
pause
exit /b 1

:end
echo.
echo Invoice Generator finished.
pause
