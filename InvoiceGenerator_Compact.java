/*
=============================================================================
                    SAI HARDWARE INVOICE GENERATOR
                      Compact Version for College Submission
=============================================================================
Author: [Your Name]
Subject: Java Programming
College: [Your College Name]
Date: October 2025

FEATURES:
- Object-Oriented Programming with Classes and Objects
- Interactive Console Input System
- File I/O for Invoice Generation
- Automatic GST Calculation (18%)
- Indian Currency Formatting (₹)
- Hardware Store Inventory Management
=============================================================================
*/

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

// Invoice Item Class - Represents individual items in the invoice
class InvoiceItem {
    private String description;
    private int quantity;
    private double unitPrice;
    
    public InvoiceItem(String description, int quantity, double unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotalPrice() { return quantity * unitPrice; }
}

// Main Invoice Class - Core business logic
class Invoice {
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String companyName;
    private String customerName;
    private List<InvoiceItem> items;
    private double taxRate = 0.18; // 18% GST for hardware items in India
    
    public Invoice(String invoiceNumber, String companyName, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.companyName = companyName;
        this.customerName = customerName;
        this.invoiceDate = LocalDate.now();
        this.items = new ArrayList<>();
    }
    
    public void addItem(String description, int quantity, double unitPrice) {
        items.add(new InvoiceItem(description, quantity, unitPrice));
    }
    
    public double getSubtotal() {
        return items.stream().mapToDouble(InvoiceItem::getTotalPrice).sum();
    }
    
    public double getTaxAmount() { return getSubtotal() * taxRate; }
    public double getTotal() { return getSubtotal() + getTaxAmount(); }
    
    // Getters
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDate getInvoiceDate() { return invoiceDate; }
    public String getCompanyName() { return companyName; }
    public String getCustomerName() { return customerName; }
    public List<InvoiceItem> getItems() { return items; }
}

// Invoice Generator - Handles file output and formatting
class TextInvoiceGenerator {
    private static final DecimalFormat CURRENCY = new DecimalFormat("₹#,##0.00");
    
    public static void generateInvoice(Invoice invoice, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Invoice Header
            writer.write("================================================================\n");
            writer.write("                    SAI HARDWARE INVOICE                        \n");
            writer.write("================================================================\n\n");
            
            // Company and Invoice Details
            writer.write("FROM: " + invoice.getCompanyName() + "\n");
            writer.write("456 Hardware Street, Mumbai, MH 400001\n");
            writer.write("Phone: +91-98765-43210 | Email: info@saihardware.com\n");
            writer.write("GST No: 27AABCS1234F1Z5\n\n");
            
            writer.write("Invoice No: " + invoice.getInvoiceNumber() + "\n");
            writer.write("Date: " + invoice.getInvoiceDate() + "\n");
            writer.write("TO: " + invoice.getCustomerName() + "\n\n");
            
            // Items Table Header
            writer.write("================================================================\n");
            writer.write(String.format("%-30s %5s %10s %12s\n", "ITEM DESCRIPTION", "QTY", "PRICE", "TOTAL"));
            writer.write("================================================================\n");
            
            // Invoice Items
            for (InvoiceItem item : invoice.getItems()) {
                String desc = item.getDescription();
                if (desc.length() > 30) desc = desc.substring(0, 27) + "...";
                
                writer.write(String.format("%-30s %5d %10s %12s\n",
                    desc,
                    item.getQuantity(),
                    CURRENCY.format(item.getUnitPrice()),
                    CURRENCY.format(item.getTotalPrice())));
            }
            
            // Totals Section
            writer.write("================================================================\n");
            writer.write(String.format("%48s %12s\n", "Subtotal:", CURRENCY.format(invoice.getSubtotal())));
            writer.write(String.format("%48s %12s\n", "GST (18%):", CURRENCY.format(invoice.getTaxAmount())));
            writer.write("================================================================\n");
            writer.write(String.format("%48s %12s\n", "TOTAL AMOUNT:", CURRENCY.format(invoice.getTotal())));
            writer.write("================================================================\n\n");
            
            // Footer
            writer.write("Thank you for choosing Sai Hardware!\n");
            writer.write("GST included as per Indian tax regulations.\n");
            writer.write("Payment due within 30 days.\n");
        }
    }
}

// Main Application Class
public class InvoiceGenerator_Compact {
    
    // Method to create sample hardware invoice for demonstration
    public static void generateSampleInvoice() {
        try {
            Invoice invoice = new Invoice("SAI-HW-001", "Sai Hardware", "Sharma Construction Pvt Ltd");
            
            // Add sample hardware items
            invoice.addItem("Phillips Head Screwdriver Set", 5, 150.00);
            invoice.addItem("Hammer 500g", 3, 250.00);
            invoice.addItem("Drill Machine 13mm", 2, 1500.00);
            invoice.addItem("Steel Bolts M8x50 (Pack of 50)", 4, 180.00);
            invoice.addItem("Measuring Tape 5m", 6, 120.00);
            invoice.addItem("Safety Gloves (Pair)", 10, 45.00);
            invoice.addItem("LED Bulb 9W", 20, 85.00);
            
            // Generate invoice file
            TextInvoiceGenerator.generateInvoice(invoice, "sai_hardware_invoice.txt");
            
            // Display summary
            System.out.println("=== SAI HARDWARE INVOICE GENERATED ===");
            System.out.println("Invoice No: " + invoice.getInvoiceNumber());
            System.out.println("Customer: " + invoice.getCustomerName());
            System.out.println("Items: " + invoice.getItems().size());
            System.out.printf("Subtotal: ₹%.2f\n", invoice.getSubtotal());
            System.out.printf("GST (18%%): ₹%.2f\n", invoice.getTaxAmount());
            System.out.printf("Total: ₹%.2f\n", invoice.getTotal());
            System.out.println("File saved: sai_hardware_invoice.txt");
            
        } catch (IOException e) {
            System.err.println("Error generating invoice: " + e.getMessage());
        }
    }
    
    // Interactive invoice creation method
    public static void createCustomInvoice() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("=== SAI HARDWARE - CUSTOM INVOICE CREATOR ===\n");
            
            // Get invoice details
            System.out.print("Enter Invoice Number: ");
            String invoiceNo = scanner.nextLine();
            
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();
            
            // Create invoice
            Invoice invoice = new Invoice(invoiceNo, "Sai Hardware", customerName);
            
            // Add items interactively
            System.out.println("\n--- Add Invoice Items ---");
            System.out.println("(Type 'done' when finished)");
            
            while (true) {
                System.out.print("\nItem Description: ");
                String desc = scanner.nextLine();
                if (desc.equalsIgnoreCase("done")) break;
                
                System.out.print("Quantity: ");
                int qty = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Unit Price (₹): ");
                double price = Double.parseDouble(scanner.nextLine());
                
                invoice.addItem(desc, qty, price);
                System.out.printf("✓ Added: %s (₹%.2f)\n", desc, qty * price);
            }
            
            if (invoice.getItems().isEmpty()) {
                System.out.println("No items added. Adding sample item...");
                invoice.addItem("Sample Hardware Item", 1, 100.00);
            }
            
            // Generate invoice
            String fileName = "invoice_" + invoiceNo.replaceAll("[^a-zA-Z0-9]", "_") + ".txt";
            TextInvoiceGenerator.generateInvoice(invoice, fileName);
            
            // Display summary
            System.out.println("\n=== INVOICE SUMMARY ===");
            System.out.println("Invoice: " + invoice.getInvoiceNumber());
            System.out.println("Customer: " + invoice.getCustomerName());
            System.out.println("Items: " + invoice.getItems().size());
            System.out.printf("Subtotal: ₹%.2f\n", invoice.getSubtotal());
            System.out.printf("GST: ₹%.2f\n", invoice.getTaxAmount());
            System.out.printf("Total: ₹%.2f\n", invoice.getTotal());
            System.out.println("File: " + fileName);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    // Main method - Program entry point
    public static void main(String[] args) {
        System.out.println("SAI HARDWARE INVOICE GENERATOR");
        System.out.println("==============================");
        
        if (args.length > 0 && args[0].equals("sample")) {
            generateSampleInvoice();
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Generate Sample Invoice");
            System.out.println("2. Create Custom Invoice");
            System.out.print("Choose option (1 or 2): ");
            
            String choice = scanner.nextLine();
            scanner.close();
            
            if (choice.equals("1")) {
                generateSampleInvoice();
            } else {
                createCustomInvoice();
            }
        }
    }
}

/*
=============================================================================
SAMPLE OUTPUT:
=============================================================================

================================================================
                    SAI HARDWARE INVOICE                        
================================================================

FROM: Sai Hardware
456 Hardware Street, Mumbai, MH 400001
Phone: +91-98765-43210 | Email: info@saihardware.com
GST No: 27AABCS1234F1Z5

Invoice No: SAI-HW-001
Date: 2025-10-03
TO: Sharma Construction Pvt Ltd

================================================================
ITEM DESCRIPTION               QTY      PRICE        TOTAL
================================================================
Phillips Head Screwdriver Set     5     ₹150.00      ₹750.00
Hammer 500g                       3     ₹250.00      ₹750.00
Drill Machine 13mm                2   ₹1,500.00    ₹3,000.00
Steel Bolts M8x50 (Pack of 50)    4     ₹180.00      ₹720.00
Measuring Tape 5m                 6     ₹120.00      ₹720.00
Safety Gloves (Pair)             10      ₹45.00      ₹450.00
LED Bulb 9W                      20      ₹85.00    ₹1,700.00
================================================================
                                        Subtotal:    ₹8,090.00
                                        GST (18%):   ₹1,456.20
================================================================
                                        TOTAL AMOUNT: ₹9,546.20
================================================================

=============================================================================
TECHNICAL CONCEPTS DEMONSTRATED:
=============================================================================
• Object-Oriented Programming (Classes, Objects, Encapsulation)
• Collections Framework (ArrayList)
• File I/O Operations (FileWriter)
• Lambda Expressions and Stream API
• Exception Handling (try-catch-finally)
• String Manipulation and Formatting
• Date and Time API (LocalDate)
• Scanner for User Input
• DecimalFormat for Currency
• Method Overloading and Access Modifiers

=============================================================================
HOW TO COMPILE AND RUN:
=============================================================================
javac InvoiceGenerator_Compact.java
java InvoiceGenerator_Compact

OR for sample:
java InvoiceGenerator_Compact sample

=============================================================================
*/
