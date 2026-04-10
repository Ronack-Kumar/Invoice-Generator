# Quick Start Guide

## Immediate Usage (No Setup Required)

1. **Generate a Sample Invoice**
   - Double-click `run-sample-simple.bat`
   - Check the `output` folder for the generated text file

2. **Create Your Own Invoice**
   - Double-click `run-simple.bat`
   - Follow the prompts to enter your company and customer information
   - Add invoice items
   - The invoice will be saved to the `output` folder

## Sample Output

The generated invoice will look like this:

```
================================================================================
                                 INVOICE                                        
================================================================================

FROM:
Sai Hardware
456 Hardware Street, Mumbai, MH 400001
Phone: +91-98765-43210
Email: info@saihardware.com

Invoice Number: INV-2025-001
Invoice Date: Oct 03, 2025

BILL TO:
Sharma Construction Pvt Ltd
789 Builder's Lane, Pune, MH 411001
Phone: +91-98123-45678
Email: purchase@sharmaconstruction.com

================================================================================
DESCRIPTION                                   QTY   UNIT PRICE           TOTAL
================================================================================
Phillips Head Screwdriver Set                   5      ₹150.00         ₹750.00
Hammer (500g)                                   3      ₹250.00         ₹750.00
Drill Machine 13mm                              2    ₹1,500.00       ₹3,000.00
================================================================================

                                                        Subtotal:       ₹8,090.00
                                                      Tax (8.0%):         ₹647.20
================================================================================
                                                           TOTAL:       ₹8,737.20
================================================================================
```

## For Excel Support

1. Install Maven
2. Run: `mvn exec:java -Dexec.mainClass="com.invoicegen.InvoiceGeneratorApp"`
3. Choose "Excel" or "Both" when prompted for format

## Troubleshooting

- **"Java not found"**: Install Java 8 or higher
- **Compilation errors**: Make sure you're in the project directory
- **No output folder**: The script automatically creates it
- **Permission errors**: Run as administrator if needed

## File Locations

- **Generated invoices**: `output/` folder
- **Source code**: `src/main/java/` folder
- **Compiled classes**: `classes/` folder (auto-created)
