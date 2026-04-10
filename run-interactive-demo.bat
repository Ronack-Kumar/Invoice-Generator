@echo off
chcp 65001 >nul
cls
echo.
echo ================================================================
echo                    INTERACTIVE INVOICE DEMO
echo ================================================================
echo.
echo Starting interactive invoice generation...
echo.

cd /d "%~dp0"

REM Compile the project (excluding Excel generator to avoid Apache POI dependencies)
echo Compiling...
javac -d target/classes src/main/java/com/invoicegen/model/*.java src/main/java/com/invoicegen/generator/InvoiceGenerator.java src/main/java/com/invoicegen/generator/TextInvoiceGenerator.java
javac -d target/classes -cp target/classes src/main/java/com/invoicegen/InteractiveDemo.java

if errorlevel 1 (
    echo.
    echo ❌ Compilation failed!
    pause
    exit /b 1
)

echo ✅ Compilation successful!
echo.

REM Run the interactive demo
echo Starting Interactive Demo...
echo.
java -cp target/classes com.invoicegen.InteractiveDemo

echo.
echo Demo completed!
pause
