# Sai Hardware Invoice Generator - Compact Version
## College Submission Format

### Overview
This is a complete Invoice Generator application for Sai Hardware store implemented in Java. The program demonstrates core Object-Oriented Programming concepts and file I/O operations.

### File Structure
- **Single File**: `InvoiceGenerator_Compact.java` (Complete application)
- **Output**: Generated invoice files in `.txt` format

### Key Features
1. **Object-Oriented Design**
   - `InvoiceItem` class for individual items
   - `Invoice` class for complete invoices
   - `TextInvoiceGenerator` class for file generation

2. **Business Logic**
   - Hardware store inventory management
   - Automatic GST calculation (18%)
   - Indian currency formatting (₹)
   - Professional invoice layout

3. **User Interface**
   - Interactive console input
   - Menu-driven options
   - Error handling and validation

### Technical Concepts
- Classes and Objects
- Encapsulation and Access Modifiers
- Collections (ArrayList)
- File I/O (FileWriter)
- Exception Handling
- Lambda Expressions
- Stream API
- Date/Time API
- String Formatting

### How to Run

#### Option 1: Generate Sample Invoice
```bash
javac InvoiceGenerator_Compact.java
java InvoiceGenerator_Compact sample
```

#### Option 2: Interactive Mode
```bash
javac InvoiceGenerator_Compact.java
java InvoiceGenerator_Compact
```

### Sample Output
The program generates professional invoices with:
- Company letterhead (Sai Hardware)
- GST number and Indian address
- Itemized billing with quantities and prices
- Automatic tax calculations
- Professional formatting

### Hardware Items Database
The application includes various hardware categories:
- Tools (Drills, Hammers, Saws)
- Fasteners (Bolts, Screws, Nuts)
- Electrical Items (Bulbs, Wires, Switches)
- Safety Equipment (Gloves, Helmets)
- Measuring Tools (Tapes, Levels)

### Code Size: ~300 lines (Perfect for college submission)

---
**Author**: [Your Name]  
**Subject**: Java Programming  
**College**: [Your College Name]  
**Date**: October 2025
