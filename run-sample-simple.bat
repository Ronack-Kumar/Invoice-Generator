@echo off
chcp 65001 >nul
echo Generating Sample Invoice...
echo.

REM Check if classes directory exists, if not create it and compile
if not exist "classes" (
    echo Compiling Java files...
    mkdir classes
    javac -d classes src\main\java\com\invoicegen\model\*.java
    javac -d classes -cp classes src\main\java\com\invoicegen\generator\InvoiceGenerator.java
    javac -d classes -cp classes src\main\java\com\invoicegen\generator\TextInvoiceGenerator.java
    javac -d classes -cp classes src\main\java\com\invoicegen\util\*.java
    javac -d classes -cp classes src\main\java\com\invoicegen\input\TextOnlyInputHandler.java
    javac -d classes -cp classes src\main\java\com\invoicegen\InvoiceGeneratorTextApp.java
    echo Compilation complete!
    echo.
)

REM Create output directory
if not exist "output" mkdir output

echo Generating sample invoice...
echo.
java -cp classes com.invoicegen.InvoiceGeneratorTextApp --sample

echo.
echo Sample generation completed.
pause
