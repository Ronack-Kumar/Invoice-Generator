@echo off
chcp 65001 >nul
echo ================================================================
echo                 SAI HARDWARE - INTERACTIVE INVOICE
echo ================================================================
echo.
echo This will create a custom invoice based on your input.
echo Follow the prompts to enter your details.
echo.
pause
echo.

echo Starting Interactive Invoice Generator...
echo.
java -cp classes com.invoicegen.InvoiceGeneratorTextApp

echo.
echo Invoice generation completed!
echo Check the output folder for your generated invoice.
echo.
pause
