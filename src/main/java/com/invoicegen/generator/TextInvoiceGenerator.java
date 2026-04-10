package com.invoicegen.generator;

import com.invoicegen.model.Invoice;
import com.invoicegen.model.InvoiceItem;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class TextInvoiceGenerator implements InvoiceGenerator {

    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("₹#,##0.00");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    @Override
    public void generateInvoice(Invoice invoice, String outputPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputPath)) {
            // Header
            writer.write("================================================================================\n");
            writer.write("                                 INVOICE                                        \n");
            writer.write("================================================================================\n\n");

            // Company Information
            writer.write("FROM:\n");
            writer.write(invoice.getCompany().getName() + "\n");
            writer.write(invoice.getCompany().getFullAddress() + "\n");
            writer.write("Phone: " + invoice.getCompany().getPhone() + "\n");
            writer.write("Email: " + invoice.getCompany().getEmail() + "\n");
            if (invoice.getCompany().getWebsite() != null) {
                writer.write("Website: " + invoice.getCompany().getWebsite() + "\n");
            }
            if (invoice.getCompany().getTaxId() != null) {
                writer.write("Tax ID: " + invoice.getCompany().getTaxId() + "\n");
            }
            writer.write("\n");

            // Invoice Details
            writer.write("Invoice Number: " + invoice.getInvoiceNumber() + "\n");
            writer.write("Invoice Date: " + invoice.getInvoiceDate().format(DATE_FORMAT) + "\n\n");

            // Customer Information
            writer.write("BILL TO:\n");
            writer.write(invoice.getCustomer().getName() + "\n");
            writer.write(invoice.getCustomer().getFullAddress() + "\n");
            writer.write("Phone: " + invoice.getCustomer().getPhone() + "\n");
            writer.write("Email: " + invoice.getCustomer().getEmail() + "\n\n");

            // Items Header
            writer.write("================================================================================\n");
            writer.write(String.format("%-40s %8s %12s %15s\n", "DESCRIPTION", "QTY", "UNIT PRICE", "TOTAL"));
            writer.write("================================================================================\n");

            // Items
            for (InvoiceItem item : invoice.getItems()) {
                writer.write(String.format("%-40s %8d %12s %15s\n",
                        truncateString(item.getDescription(), 40),
                        item.getQuantity(),
                        CURRENCY_FORMAT.format(item.getUnitPrice()),
                        CURRENCY_FORMAT.format(item.getTotalPrice())));
            }

            writer.write("================================================================================\n\n");

            // Totals
            writer.write(String.format("%65s %15s\n", "Subtotal:", CURRENCY_FORMAT.format(invoice.getSubtotal())));
            writer.write(String.format("%65s %15s\n", "Tax (" + (invoice.getTaxRate() * 100) + "%):", CURRENCY_FORMAT.format(invoice.getTaxAmount())));
            writer.write("================================================================================\n");
            writer.write(String.format("%65s %15s\n", "TOTAL:", CURRENCY_FORMAT.format(invoice.getTotal())));
            writer.write("================================================================================\n\n");

            // Notes
            if (invoice.getNotes() != null && !invoice.getNotes().trim().isEmpty()) {
                writer.write("NOTES:\n");
                writer.write(invoice.getNotes() + "\n\n");
            }

            // Footer
            writer.write("Thank you for your business!\n");
            writer.write("Payment is due within 30 days of invoice date.\n");
        }
    }

    private String truncateString(String str, int maxLength) {
        if (str == null) return "";
        return str.length() > maxLength ? str.substring(0, maxLength - 3) + "..." : str;
    }
}
