# Interactive Invoice Generator - User Input Guide

## How to Create Your Own Invoice

### Step 1: Run the Interactive Generator
Double-click: `create-custom-invoice.bat`

OR run in terminal:
```cmd
java -cp classes com.invoicegen.InvoiceGeneratorTextApp
```

### Step 2: Follow the Prompts

The system will ask you for the following information:

#### 🏢 **Invoice Details**
```
Enter Invoice Number: [Example: SAI-2025-001]
Enter Invoice Date (MM/dd/yyyy) or press Enter for today: [Example: 10/03/2025 or just press Enter]
```

#### 🏪 **Company Information** (Your Business)
```
Company Name: [Example: Sai Hardware]
Address: [Example: 456 Hardware Street]
City: [Example: Mumbai]
State: [Example: MH]
ZIP Code: [Example: 400001]
Email: [Example: info@saihardware.com]
Phone: [Example: +91-98765-43210]
Website (optional): [Example: www.saihardware.com or press Enter to skip]
Tax ID (optional): [Example: 27AABCS1234F1Z5 or press Enter to skip]
```

#### 👤 **Customer Information** (Bill To)
```
Customer Name: [Example: ABC Construction Ltd]
Address: [Example: 123 Builder Road]
City: [Example: Pune]
State: [Example: MH]
ZIP Code: [Example: 411001]
Email: [Example: accounts@abcconstruction.com]
Phone: [Example: +91-98123-45678]
```

#### 📦 **Invoice Items** (Products/Services)
For each item, enter:
```
Item #1:
Description: [Example: Phillips Head Screwdriver Set]
Quantity: [Example: 5]
Unit Price: ₹[Example: 150]

Item #2:
Description: [Example: Hammer 500g]
Quantity: [Example: 3]
Unit Price: ₹[Example: 250]

... continue adding items ...

When finished, just press Enter with empty description to stop adding items.
```

#### 💰 **Additional Settings**
```
Enter tax rate (e.g., 0.1 for 10%) [default: 0.1]: [Example: 0.18 for 18% GST]
Enter any notes (optional): [Example: Thank you for your business. Payment due in 30 days.]
```

#### 📁 **Output Settings**
```
Enter output directory path (or press Enter for current directory): [Example: ./invoices or just press Enter]
```

### Step 3: Generated Files

The system will create:
- Text invoice file: `invoice_[number]_[timestamp].txt`
- Professional formatting with company letterhead
- Itemized billing with calculations
- GST/Tax calculations
- Payment terms and notes

## Sample Input Session

```
=== Invoice Generator (Text Format) ===

Enter Invoice Number: SAI-HW-001
Enter Invoice Date (MM/dd/yyyy) or press Enter for today: [Press Enter]

=== Company Information ===
Company Name: Sai Hardware
Address: 456 Hardware Street
City: Mumbai
State: MH
ZIP Code: 400001
Email: info@saihardware.com
Phone: +91-98765-43210
Website (optional): www.saihardware.com
Tax ID (optional): 27AABCS1234F1Z5

=== Customer Information ===
Customer Name: Sharma Construction
Address: 789 Builder Lane
City: Pune
State: MH
ZIP Code: 411001
Email: accounts@sharma.com
Phone: +91-98123-45678

=== Invoice Items ===
Enter invoice items (press Enter with empty description to finish):

Item #1:
Description: Drill Machine 13mm
Quantity: 2
Unit Price: ₹1500
Added: Drill Machine 13mm - 2 x ₹1500.0 = ₹3000.0

Item #2:
Description: Safety Gloves
Quantity: 10
Unit Price: ₹45
Added: Safety Gloves - 10 x ₹45.0 = ₹450.0

Item #3:
Description: [Press Enter to finish]

Enter tax rate (e.g., 0.1 for 10%) [default: 0.1]: 0.18
Enter any notes (optional): GST @ 18% included. Payment due in 30 days.
Enter output directory path (or press Enter for current directory): [Press Enter]

Text invoice generated: invoice_SAI-HW-001_20251003_235900.txt

=== Invoice Summary ===
Invoice Number: SAI-HW-001
Date: 2025-10-03
Customer: Sharma Construction
Items: 2
Subtotal: ₹3450.00
Tax: ₹621.00
Total: ₹4071.00
```

## Quick Start Options

### Option 1: Full Interactive Mode
```cmd
create-custom-invoice.bat
```

### Option 2: Command Line
```cmd
java -cp classes com.invoicegen.InvoiceGeneratorTextApp
```

### Option 3: Compact Version
```cmd
java InvoiceGenerator_Compact
```

## Tips for Data Entry

1. **Invoice Numbers**: Use format like SAI-2025-001, HW-001, etc.
2. **Dates**: MM/dd/yyyy format or just press Enter for today
3. **Phone Numbers**: Use Indian format +91-xxxxx-xxxxx
4. **GST Rate**: 0.18 for 18%, 0.12 for 12%, etc.
5. **Item Descriptions**: Be specific (e.g., "Drill Machine 13mm" not just "Drill")
6. **Quantities**: Whole numbers only
7. **Prices**: Enter amount without ₹ symbol (e.g., 150 not ₹150)

## Generated Invoice Features

✅ Professional company letterhead  
✅ Customer billing details  
✅ Itemized product list with quantities  
✅ Automatic subtotal calculation  
✅ GST/Tax calculation and display  
✅ Grand total calculation  
✅ Payment terms and notes  
✅ Indian currency formatting (₹)  
✅ Proper business formatting  
✅ Timestamped filename  

The generated invoice will be saved as a text file in your specified directory and can be printed or emailed to customers.
