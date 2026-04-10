package com.invoicegen;

import com.invoicegen.generator.TextInvoiceGenerator;
import com.invoicegen.model.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 * Interactive Demo showing step-by-step user input for invoice generation
 */
public class InteractiveDemo {

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("           SAI HARDWARE - INTERACTIVE INVOICE DEMO");
        System.out.println("================================================================");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        try {
            // Create output directory
            File outputDir = new File("output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            System.out.println("🎯 This demo will guide you through creating a custom invoice");
            System.out.println("   based on your input. Let's start!\n");

            // Step 1: Invoice Basic Info
            System.out.println("STEP 1: Invoice Information");
            System.out.println("---------------------------");
            System.out.print("📄 Enter Invoice Number (e.g., SAI-2025-001): ");
            String invoiceNumber = scanner.nextLine().trim();
            if (invoiceNumber.isEmpty()) {
                invoiceNumber = "SAI-DEMO-001";
                System.out.println("   Using default: " + invoiceNumber);
            }

            // Step 2: Company Info
            System.out.println("\nSTEP 2: Your Company Information");
            System.out.println("---------------------------------");

            Company company = new Company();

            System.out.print("🏢 Company Name: ");
            String companyName = scanner.nextLine().trim();
            company.setName(companyName.isEmpty() ? "Sai Hardware" : companyName);

            System.out.print("📍 Address: ");
            String address = scanner.nextLine().trim();
            company.setAddress(address.isEmpty() ? "456 Hardware Street" : address);

            System.out.print("🏙️ City: ");
            String city = scanner.nextLine().trim();
            company.setCity(city.isEmpty() ? "Mumbai" : city);

            System.out.print("🏛️ State: ");
            String state = scanner.nextLine().trim();
            company.setState(state.isEmpty() ? "MH" : state);

            System.out.print("📮 ZIP Code: ");
            String zipCode = scanner.nextLine().trim();
            company.setZipCode(zipCode.isEmpty() ? "400001" : zipCode);

            System.out.print("📧 Email: ");
            String email = scanner.nextLine().trim();
            company.setEmail(email.isEmpty() ? "info@saihardware.com" : email);

            System.out.print("📞 Phone: ");
            String phone = scanner.nextLine().trim();
            company.setPhone(phone.isEmpty() ? "+91-98765-43210" : phone);

            System.out.print("🌐 Website (optional): ");
            String website = scanner.nextLine().trim();
            company.setWebsite(website.isEmpty() ? "www.saihardware.com" : website);

            System.out.print("🏛️ GST/Tax ID (optional): ");
            String taxId = scanner.nextLine().trim();
            company.setTaxId(taxId.isEmpty() ? "27AABCS1234F1Z5" : taxId);

            // Step 3: Customer Info
            System.out.println("\nSTEP 3: Customer Information (Bill To)");
            System.out.println("---------------------------------------");

            Customer customer = new Customer();

            System.out.print("👤 Customer Name: ");
            String customerName = scanner.nextLine().trim();
            customer.setName(customerName.isEmpty() ? "Demo Construction Ltd" : customerName);

            System.out.print("📍 Customer Address: ");
            String custAddress = scanner.nextLine().trim();
            customer.setAddress(custAddress.isEmpty() ? "123 Builder Road" : custAddress);

            System.out.print("🏙️ Customer City: ");
            String custCity = scanner.nextLine().trim();
            customer.setCity(custCity.isEmpty() ? "Pune" : custCity);

            System.out.print("🏛️ Customer State: ");
            String custState = scanner.nextLine().trim();
            customer.setState(custState.isEmpty() ? "MH" : custState);

            System.out.print("📮 Customer ZIP: ");
            String custZip = scanner.nextLine().trim();
            customer.setZipCode(custZip.isEmpty() ? "411001" : custZip);

            System.out.print("📧 Customer Email: ");
            String custEmail = scanner.nextLine().trim();
            customer.setEmail(custEmail.isEmpty() ? "accounts@demo.com" : custEmail);

            System.out.print("📞 Customer Phone: ");
            String custPhone = scanner.nextLine().trim();
            customer.setPhone(custPhone.isEmpty() ? "+91-98123-45678" : custPhone);

            // Step 4: Create Invoice and Add Items
            System.out.println("\nSTEP 4: Invoice Items");
            System.out.println("---------------------");

            Invoice invoice = new Invoice(invoiceNumber, customer, company);
            invoice.setInvoiceDate(LocalDate.now());

            System.out.println("💡 Add items to your invoice. Press Enter with empty description to finish.\n");

            int itemCount = 1;
            while (true) {
                System.out.println("📦 Item #" + itemCount + ":");
                System.out.print("   Description: ");
                String description = scanner.nextLine().trim();

                if (description.isEmpty()) {
                    if (invoice.getItems().isEmpty()) {
                        System.out.println("   No items added. Adding sample items...");
                        invoice.addItem(new InvoiceItem("Drill Machine 13mm", 2, 1500.00));
                        invoice.addItem(new InvoiceItem("Safety Gloves (Pair)", 5, 45.00));
                        invoice.addItem(new InvoiceItem("LED Bulb 9W", 10, 85.00));
                    }
                    break;
                }

                System.out.print("   Quantity: ");
                int quantity = 1;
                try {
                    String qtyInput = scanner.nextLine().trim();
                    if (!qtyInput.isEmpty()) {
                        quantity = Integer.parseInt(qtyInput);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("   Invalid quantity. Using 1.");
                }

                System.out.print("   Unit Price (₹): ");
                double unitPrice = 0.0;
                try {
                    String priceInput = scanner.nextLine().trim();
                    if (!priceInput.isEmpty()) {
                        unitPrice = Double.parseDouble(priceInput);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("   Invalid price. Using ₹0.00.");
                }

                InvoiceItem item = new InvoiceItem(description, quantity, unitPrice);
                invoice.addItem(item);

                System.out.printf("   ✅ Added: %s - %d x ₹%.2f = ₹%.2f\n\n",
                        description, quantity, unitPrice, item.getTotalPrice());

                itemCount++;
            }

            // Step 5: Tax Rate and Notes
            System.out.println("\nSTEP 5: Final Settings");
            System.out.println("-----------------------");

            System.out.print("💰 Enter GST/Tax rate (0.18 for 18%, default: 0.18): ");
            String taxRateInput = scanner.nextLine().trim();
            if (!taxRateInput.isEmpty()) {
                try {
                    double taxRate = Double.parseDouble(taxRateInput);
                    invoice.setTaxRate(taxRate);
                } catch (NumberFormatException e) {
                    System.out.println("   Invalid tax rate. Using default 18%.");
                }
            } else {
                invoice.setTaxRate(0.18); // 18% GST
            }

            System.out.print("📝 Enter notes (optional): ");
            String notes = scanner.nextLine().trim();
            if (!notes.isEmpty()) {
                invoice.setNotes(notes);
            } else {
                invoice.setNotes("Thank you for choosing " + company.getName() +
                        ". GST @ " + (int) (invoice.getTaxRate() * 100) +
                        "% included. Payment due within 30 days.");
            }

            // Step 6: Generate Invoice
            System.out.println("\nSTEP 6: Generating Invoice");
            System.out.println("--------------------------");

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "output/custom_invoice_" + timestamp + ".txt";

            TextInvoiceGenerator generator = new TextInvoiceGenerator();
            generator.generateInvoice(invoice, fileName);

            // Step 7: Display Results
            System.out.println("🎉 INVOICE GENERATED SUCCESSFULLY!");
            System.out.println("===================================");
            System.out.println("📄 Invoice Number: " + invoice.getInvoiceNumber());
            System.out.println("📅 Date: " + invoice.getInvoiceDate());
            System.out.println("🏢 Company: " + invoice.getCompany().getName());
            System.out.println("👤 Customer: " + invoice.getCustomer().getName());
            System.out.println("📦 Items: " + invoice.getItems().size());
            System.out.printf("💰 Subtotal: ₹%.2f\n", invoice.getSubtotal());
            System.out.printf("🏛️ GST (%d%%): ₹%.2f\n", (int) (invoice.getTaxRate() * 100), invoice.getTaxAmount());
            System.out.printf("💎 TOTAL: ₹%.2f\n", invoice.getTotal());
            System.out.println("📁 File: " + fileName);
            System.out.println();
            System.out.println("✨ Your professional invoice is ready!");
            System.out.println("   You can find it in the 'output' folder.");

        } catch (Exception e) {
            System.err.println("❌ Error generating invoice: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
