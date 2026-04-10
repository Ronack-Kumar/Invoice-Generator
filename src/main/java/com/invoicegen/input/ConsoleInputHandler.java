package com.invoicegen.input;

import com.invoicegen.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleInputHandler {
    private Scanner scanner;

    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public Invoice createInvoice() {
        System.out.println("=== Invoice Generator ===\n");

        // Get invoice basic info
        System.out.print("Enter Invoice Number: ");
        String invoiceNumber = scanner.nextLine().trim();

        System.out.print("Enter Invoice Date (MM/dd/yyyy) or press Enter for today: ");
        String dateInput = scanner.nextLine().trim();
        LocalDate invoiceDate = LocalDate.now();
        if (!dateInput.isEmpty()) {
            try {
                invoiceDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Using today's date.");
            }
        }

        // Create company info
        Company company = createCompany();
        
        // Create customer info
        Customer customer = createCustomer();

        // Create invoice
        Invoice invoice = new Invoice(invoiceNumber, customer, company);
        invoice.setInvoiceDate(invoiceDate);

        // Add items
        addItems(invoice);

        // Set tax rate
        System.out.print("Enter tax rate (e.g., 0.1 for 10%) [default: 0.1]: ");
        String taxRateInput = scanner.nextLine().trim();
        if (!taxRateInput.isEmpty()) {
            try {
                double taxRate = Double.parseDouble(taxRateInput);
                invoice.setTaxRate(taxRate);
            } catch (NumberFormatException e) {
                System.out.println("Invalid tax rate. Using default 10%.");
            }
        }

        // Add notes
        System.out.print("Enter any notes (optional): ");
        String notes = scanner.nextLine().trim();
        if (!notes.isEmpty()) {
            invoice.setNotes(notes);
        }

        return invoice;
    }

    private Company createCompany() {
        System.out.println("\n=== Company Information ===");
        System.out.print("Company Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Address: ");
        String address = scanner.nextLine().trim();
        
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        
        System.out.print("State: ");
        String state = scanner.nextLine().trim();
        
        System.out.print("ZIP Code: ");
        String zipCode = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        
        System.out.print("Website (optional): ");
        String website = scanner.nextLine().trim();
        if (website.isEmpty()) website = null;
        
        System.out.print("Tax ID (optional): ");
        String taxId = scanner.nextLine().trim();
        if (taxId.isEmpty()) taxId = null;

        return new Company(name, address, city, state, zipCode, email, phone, website, taxId);
    }

    private Customer createCustomer() {
        System.out.println("\n=== Customer Information ===");
        System.out.print("Customer Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Address: ");
        String address = scanner.nextLine().trim();
        
        System.out.print("City: ");
        String city = scanner.nextLine().trim();
        
        System.out.print("State: ");
        String state = scanner.nextLine().trim();
        
        System.out.print("ZIP Code: ");
        String zipCode = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();

        return new Customer(name, address, city, state, zipCode, email, phone);
    }

    private void addItems(Invoice invoice) {
        System.out.println("\n=== Invoice Items ===");
        System.out.println("Enter invoice items (press Enter with empty description to finish):");

        int itemNumber = 1;
        while (true) {
            System.out.println("\nItem #" + itemNumber + ":");
            System.out.print("Description: ");
            String description = scanner.nextLine().trim();
            
            if (description.isEmpty()) {
                break;
            }

            System.out.print("Quantity: ");
            int quantity = 1;
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Using 1.");
            }

            System.out.print("Unit Price: ₹");
            double unitPrice = 0.0;
            try {
                unitPrice = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Using ₹0.00.");
            }

            InvoiceItem item = new InvoiceItem(description, quantity, unitPrice);
            invoice.addItem(item);
            
            System.out.println("Added: " + item.getDescription() + " - " + 
                             item.getQuantity() + " x ₹" + item.getUnitPrice() + 
                             " = ₹" + item.getTotalPrice());
            itemNumber++;
        }

        if (invoice.getItems().isEmpty()) {
            System.out.println("No items added. Adding a sample item.");
            invoice.addItem(new InvoiceItem("Sample Service", 1, 100.00));
        }
    }

    public String getOutputFormat() {
        System.out.println("\n=== Output Format ===");
        System.out.println("1. Text file (.txt)");
        System.out.println("2. Excel file (.xlsx)");
        System.out.println("3. Both");
        System.out.print("Choose format (1-3): ");
        
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": return "text";
            case "2": return "excel";
            case "3": return "both";
            default: 
                System.out.println("Invalid choice. Using both formats.");
                return "both";
        }
    }

    public String getOutputPath() {
        System.out.print("Enter output directory path (or press Enter for current directory): ");
        String path = scanner.nextLine().trim();
        return path.isEmpty() ? "." : path;
    }

    public void close() {
        scanner.close();
    }
}
