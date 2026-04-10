package com.invoicegen;

import com.invoicegen.generator.InvoiceGenerator;
import com.invoicegen.generator.TextInvoiceGenerator;
import com.invoicegen.model.*;
import com.invoicegen.util.SampleDataGenerator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Demo class to showcase different hardware categories
 */
public class HardwareCatalogDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Sai Hardware - Product Catalog Demo ===");
        System.out.println();
        
        try {
            Company company = SampleDataGenerator.createSampleCompany();
            Customer customer = SampleDataGenerator.createSampleCustomer();
            
            // Create output directory
            File outputDir = new File("output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            InvoiceGenerator textGenerator = new TextInvoiceGenerator();
            
            // Generate invoices for different hardware categories
            String[] categories = {"tools", "fasteners", "electrical", "plumbing", "safety"};
            
            for (String category : categories) {
                System.out.println("Generating " + category.toUpperCase() + " catalog invoice...");
                
                Invoice invoice = SampleDataGenerator.createHardwareInvoiceWithCategory(category, customer, company);
                String filename = "output/hardware_" + category + "_" + timestamp + ".txt";
                
                textGenerator.generateInvoice(invoice, filename);
                System.out.println("✓ " + category.toUpperCase() + " invoice saved: " + filename);
                
                // Display summary
                System.out.printf("   Items: %d | Subtotal: ₹%.2f | GST: ₹%.2f | Total: ₹%.2f%n", 
                    invoice.getItems().size(), 
                    invoice.getSubtotal(), 
                    invoice.getTaxAmount(), 
                    invoice.getTotal());
                System.out.println();
            }
            
            System.out.println("=== All Hardware Catalogs Generated Successfully! ===");
            System.out.println();
            System.out.println("Categories available:");
            System.out.println("• TOOLS - Power tools and hand tools");
            System.out.println("• FASTENERS - Bolts, screws, nuts, washers");
            System.out.println("• ELECTRICAL - Wiring, switches, bulbs");
            System.out.println("• PLUMBING - Pipes, fittings, valves");
            System.out.println("• SAFETY - Protective equipment and gear");
            
        } catch (Exception e) {
            System.err.println("Error generating hardware catalogs: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
