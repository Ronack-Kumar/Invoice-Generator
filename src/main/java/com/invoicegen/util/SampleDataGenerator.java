package com.invoicegen.util;

import com.invoicegen.model.*;

import java.time.LocalDate;

public class SampleDataGenerator {
    
    public static Invoice createSampleInvoice() {
        // Create sample company
        Company company = new Company(
            "Sai Hardware",
            "456 Hardware Street",
            "Mumbai",
            "MH",
            "400001",
            "info@saihardware.com",
            "+91-98765-43210",
            "www.saihardware.com",
            "27AABCS1234F1Z5"
        );
        
        // Create sample customer
        Customer customer = new Customer(
            "Sharma Construction Pvt Ltd",
            "789 Builder's Lane",
            "Pune",
            "MH",
            "411001",
            "purchase@sharmaconstruction.com",
            "+91-98123-45678"
        );
        
        // Create invoice
        Invoice invoice = new Invoice("INV-2025-001", customer, company);
        invoice.setInvoiceDate(LocalDate.now());
        
        // Add sample hardware items
        invoice.addItem(new InvoiceItem("Phillips Head Screwdriver Set", 5, 150.00));
        invoice.addItem(new InvoiceItem("Hammer (500g)", 3, 250.00));
        invoice.addItem(new InvoiceItem("Drill Machine 13mm", 2, 1500.00));
        invoice.addItem(new InvoiceItem("Steel Bolts M8x50 (Pack of 50)", 4, 180.00));
        invoice.addItem(new InvoiceItem("Measuring Tape 5m", 6, 120.00));
        invoice.addItem(new InvoiceItem("Safety Gloves (Pair)", 10, 45.00));
        invoice.addItem(new InvoiceItem("LED Bulb 9W", 20, 85.00));
        
        // Set tax rate
        invoice.setTaxRate(0.18); // 18% GST
        
        // Add notes
        invoice.setNotes("Thank you for choosing Sai Hardware. " +
                        "GST @ 18% included as per Indian tax regulations. " +
                        "All items come with manufacturer warranty. " +
                        "Returns accepted within 7 days with original receipt. " +
                        "Please remit payment within 30 days.");
        
        return invoice;
    }
    
    public static Company createSampleCompany() {
        return new Company(
            "Sai Hardware",
            "456 Hardware Street",
            "Mumbai",
            "MH",
            "400001",
            "info@saihardware.com",
            "+91-98765-43210",
            "www.saihardware.com",
            "27AABCS1234F1Z5"
        );
    }
    
    public static Customer createSampleCustomer() {
        return new Customer(
            "Sharma Construction Pvt Ltd",
            "789 Builder's Lane",
            "Pune",
            "MH",
            "411001",
            "purchase@sharmaconstruction.com",
            "+91-98123-45678"
        );
    }
    
    /**
     * Creates a comprehensive list of hardware items for different categories
     */
    public static Invoice createHardwareInvoiceWithCategory(String category, Customer customer, Company company) {
        Invoice invoice = new Invoice("HW-" + category.toUpperCase() + "-001", customer, company);
        invoice.setInvoiceDate(LocalDate.now());
        
        switch (category.toLowerCase()) {
            case "tools":
                invoice.addItem(new InvoiceItem("Cordless Drill 18V", 1, 2500.00));
                invoice.addItem(new InvoiceItem("Angle Grinder 4-inch", 1, 1800.00));
                invoice.addItem(new InvoiceItem("Circular Saw 7-inch", 1, 3200.00));
                invoice.addItem(new InvoiceItem("Jigsaw Electric", 1, 1600.00));
                invoice.addItem(new InvoiceItem("Impact Driver Set", 1, 2800.00));
                break;
                
            case "fasteners":
                invoice.addItem(new InvoiceItem("Hex Bolts M10x40 (Box of 100)", 5, 320.00));
                invoice.addItem(new InvoiceItem("Wood Screws 4x50mm (Pack of 200)", 8, 180.00));
                invoice.addItem(new InvoiceItem("Anchor Bolts M8 (Pack of 50)", 6, 250.00));
                invoice.addItem(new InvoiceItem("Washers Assorted (Box of 500)", 3, 150.00));
                invoice.addItem(new InvoiceItem("Nuts & Bolts Combo Set", 2, 450.00));
                break;
                
            case "electrical":
                invoice.addItem(new InvoiceItem("LED Bulb 12W Cool White", 25, 95.00));
                invoice.addItem(new InvoiceItem("Extension Cord 10m Heavy Duty", 3, 680.00));
                invoice.addItem(new InvoiceItem("Switch Socket Combo", 15, 125.00));
                invoice.addItem(new InvoiceItem("Electrical Tape Black (Roll)", 20, 35.00));
                invoice.addItem(new InvoiceItem("Wire Connectors Assorted (Pack)", 10, 85.00));
                break;
                
            case "plumbing":
                invoice.addItem(new InvoiceItem("PVC Pipe 4-inch (6ft)", 8, 280.00));
                invoice.addItem(new InvoiceItem("Elbow Joints 90° (Pack of 10)", 5, 120.00));
                invoice.addItem(new InvoiceItem("Ball Valve 1-inch", 4, 350.00));
                invoice.addItem(new InvoiceItem("Pipe Wrench 12-inch", 2, 580.00));
                invoice.addItem(new InvoiceItem("Teflon Tape (Roll)", 15, 25.00));
                break;
                
            case "safety":
                invoice.addItem(new InvoiceItem("Safety Helmet Hard Hat", 10, 180.00));
                invoice.addItem(new InvoiceItem("Safety Goggles Clear", 15, 95.00));
                invoice.addItem(new InvoiceItem("Work Gloves Cut-Resistant", 20, 75.00));
                invoice.addItem(new InvoiceItem("Safety Vest High-Visibility", 8, 220.00));
                invoice.addItem(new InvoiceItem("Ear Plugs (Box of 50 pairs)", 5, 180.00));
                break;
                
            default: // general hardware
                invoice.addItem(new InvoiceItem("Measuring Tape Steel 8m", 4, 180.00));
                invoice.addItem(new InvoiceItem("Spirit Level 24-inch", 2, 420.00));
                invoice.addItem(new InvoiceItem("Utility Knife Retractable", 6, 85.00));
                invoice.addItem(new InvoiceItem("Toolbox Plastic 16-inch", 3, 350.00));
                invoice.addItem(new InvoiceItem("Screwdriver Set Phillips + Flat", 5, 280.00));
        }
        
        invoice.setTaxRate(0.18); // 18% GST for hardware items in India
        invoice.setNotes("Sai Hardware - Your trusted partner for quality tools and materials. " +
                        "GST included. Warranty terms vary by product. " +
                        "Bulk orders welcome with special discounts.");
        
        return invoice;
    }
}
