@echo off
chcp 65001 >nul
cls
echo.
echo ================================================================
echo                    INTERACTIVE INVOICE DEMO
echo ================================================================
echo.

cd /d "%~dp0"

REM Create target directory
if not exist "target\classes" mkdir "target\classes"

REM Compile step by step
echo Compiling model classes...
javac -d target/classes src/main/java/com/invoicegen/model/*.java
if errorlevel 1 goto :error

echo Compiling generators...
javac -d target/classes src/main/java/com/invoicegen/generator/InvoiceGenerator.java src/main/java/com/invoicegen/generator/TextInvoiceGenerator.java
if errorlevel 1 goto :error

echo Compiling demo app...
javac -d target/classes -cp target/classes src/main/java/com/invoicegen/InteractiveDemo.java
if errorlevel 1 goto :error

echo ✅ Compilation successful!
echo.
echo Starting Interactive Demo...
echo.

REM Run the demo
java -cp target/classes com.invoicegen.InteractiveDemo

echo.
echo Demo completed!
pause
exit /b 0

:error
echo.
echo ❌ Compilation failed!
pause
exit /b 1
