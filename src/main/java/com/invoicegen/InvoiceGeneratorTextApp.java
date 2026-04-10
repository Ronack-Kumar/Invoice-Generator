package com.invoicegen;

import com.invoicegen.generator.InvoiceGenerator;
import com.invoicegen.generator.TextInvoiceGenerator;
import com.invoicegen.input.TextOnlyInputHandler;
import com.invoicegen.model.Invoice;
import com.invoicegen.util.SampleDataGenerator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceGeneratorTextApp {
    
    public static void main(String[] args) {
        System.out.println("Welcome to Invoice Generator (Text Format)!");
        System.out.println("==========================================");
        System.out.println("Note: This version generates text invoices only.");
        System.out.println("For Excel support, use Maven to build with dependencies.");
        System.out.println();
        
        try {
            if (args.length > 0 && args[0].equals("--sample")) {
                generateSampleInvoice();
            } else {
                generateCustomInvoice();
            }
        } catch (Exception e) {
            System.err.println("Error generating invoice: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void generateSampleInvoice() throws Exception {
        System.out.println("Generating sample invoice...\n");
        
        Invoice sampleInvoice = SampleDataGenerator.createSampleInvoice();
        
        // Create output directory if it doesn't exist
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String baseFileName = "sample_invoice_" + timestamp;
        
        // Generate text format
        InvoiceGenerator textGenerator = new TextInvoiceGenerator();
        String textPath = "output/" + baseFileName + ".txt";
        textGenerator.generateInvoice(sampleInvoice, textPath);
        
        System.out.println("Sample invoice generated successfully!");
        System.out.println("Text file: " + textPath);
        
        // Display sample invoice summary
        displayInvoiceSummary(sampleInvoice);
    }
    
    private static void generateCustomInvoice() throws Exception {
        TextOnlyInputHandler inputHandler = new TextOnlyInputHandler();
        
        try {
            // Get invoice data from user
            Invoice invoice = inputHandler.createInvoice();
            
            // Get output path
            String outputPath = inputHandler.getOutputPath();
            
            // Create output directory if it doesn't exist
            File outputDir = new File(outputPath);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String baseFileName = "invoice_" + invoice.getInvoiceNumber().replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp;
            
            // Generate text invoice
            InvoiceGenerator textGenerator = new TextInvoiceGenerator();
            String textPath = outputPath + "/" + baseFileName + ".txt";
            textGenerator.generateInvoice(invoice, textPath);
            System.out.println("\nText invoice generated: " + textPath);
            
            System.out.println("\nInvoice generation completed successfully!");
            
            // Display invoice summary
            displayInvoiceSummary(invoice);
            
        } finally {
            inputHandler.close();
        }
    }
    
    private static void displayInvoiceSummary(Invoice invoice) {
        System.out.println("\n=== Invoice Summary ===");
        System.out.println("Invoice Number: " + invoice.getInvoiceNumber());
        System.out.println("Date: " + invoice.getInvoiceDate());
        System.out.println("Customer: " + invoice.getCustomer().getName());
        System.out.println("Items: " + invoice.getItems().size());
        System.out.printf("Subtotal: ₹%.2f%n", invoice.getSubtotal());
        System.out.printf("Tax: ₹%.2f%n", invoice.getTaxAmount());
        System.out.printf("Total: ₹%.2f%n", invoice.getTotal());
        System.out.println("========================");
    }
}
