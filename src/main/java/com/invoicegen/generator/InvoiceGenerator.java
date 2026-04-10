package com.invoicegen.generator;

import com.invoicegen.model.Invoice;

public interface InvoiceGenerator {
    void generateInvoice(Invoice invoice, String outputPath) throws Exception;
}
