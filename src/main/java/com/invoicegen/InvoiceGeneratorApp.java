package com.invoicegen;

import com.invoicegen.generator.ExcelInvoiceGenerator;
import com.invoicegen.generator.InvoiceGenerator;
import com.invoicegen.generator.TextInvoiceGenerator;
import com.invoicegen.input.ConsoleInputHandler;
import com.invoicegen.model.Invoice;
import com.invoicegen.util.SampleDataGenerator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceGeneratorApp {
    
    public static void main(String[] args) {
        System.out.println("Welcome to Invoice Generator!");
        System.out.println("=============================");
        
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
        
        // Generate both formats
        InvoiceGenerator textGenerator = new TextInvoiceGenerator();
        String textPath = "output/" + baseFileName + ".txt";
        
        textGenerator.generateInvoice(sampleInvoice, textPath);
        System.out.println("Sample invoice generated successfully!");
        System.out.println("Text file: " + textPath);
        
        try {
            InvoiceGenerator excelGenerator = new ExcelInvoiceGenerator();
            String excelPath = "output/" + baseFileName + ".xlsx";
            excelGenerator.generateInvoice(sampleInvoice, excelPath);
            System.out.println("Excel file: " + excelPath);
        } catch (NoClassDefFoundError e) {
            System.out.println("Excel generation not available (Apache POI library missing)");
            System.out.println("Use Maven to build with full dependencies for Excel support");
        }
        
        // Display sample invoice summary
        displayInvoiceSummary(sampleInvoice);
    }
    
    private static void generateCustomInvoice() throws Exception {
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        
        try {
            // Get invoice data from user
            Invoice invoice = inputHandler.createInvoice();
            
            // Get output preferences
            String format = inputHandler.getOutputFormat();
            String outputPath = inputHandler.getOutputPath();
            
            // Create output directory if it doesn't exist
            File outputDir = new File(outputPath);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String baseFileName = "invoice_" + invoice.getInvoiceNumber().replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp;
            
            // Generate invoices based on format choice
            if (format.equals("text") || format.equals("both")) {
                InvoiceGenerator textGenerator = new TextInvoiceGenerator();
                String textPath = outputPath + "/" + baseFileName + ".txt";
                textGenerator.generateInvoice(invoice, textPath);
                System.out.println("\nText invoice generated: " + textPath);
            }
            
            if (format.equals("excel") || format.equals("both")) {
                try {
                    InvoiceGenerator excelGenerator = new ExcelInvoiceGenerator();
                    String excelPath = outputPath + "/" + baseFileName + ".xlsx";
                    excelGenerator.generateInvoice(invoice, excelPath);
                    System.out.println("Excel invoice generated: " + excelPath);
                } catch (NoClassDefFoundError e) {
                    System.out.println("Excel generation not available (Apache POI library missing)");
                    System.out.println("Please use Maven to build the project with all dependencies");
                    if (format.equals("excel")) {
                        System.out.println("Falling back to text format...");
                        InvoiceGenerator textGenerator = new TextInvoiceGenerator();
                        String textPath = outputPath + "/" + baseFileName + ".txt";
                        textGenerator.generateInvoice(invoice, textPath);
                        System.out.println("Text invoice generated: " + textPath);
                    }
                }
            }
            
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
