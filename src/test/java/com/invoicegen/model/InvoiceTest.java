package com.invoicegen.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class InvoiceTest {

    @Test
    public void testInvoiceCalculations() {
        // Create test data
        Company company = new Company("Test Corp", "123 Test St", "Test City", "TS", "12345", 
                                    "test@test.com", "555-1234", null, null);
        Customer customer = new Customer("Test Customer", "456 Customer Ave", "Customer City", 
                                       "CS", "67890", "customer@test.com", "555-5678");
        
        Invoice invoice = new Invoice("TEST-001", customer, company);
        
        // Add test items
        invoice.addItem(new InvoiceItem("Item 1", 2, 10.00));
        invoice.addItem(new InvoiceItem("Item 2", 1, 20.00));
        
        // Test calculations
        assertEquals("Subtotal should be 40.00", 40.00, invoice.getSubtotal(), 0.01);
        assertEquals("Tax should be 4.00 (10%)", 4.00, invoice.getTaxAmount(), 0.01);
        assertEquals("Total should be 44.00", 44.00, invoice.getTotal(), 0.01);
    }

    @Test
    public void testInvoiceItemCalculation() {
        InvoiceItem item = new InvoiceItem("Test Item", 3, 15.50);
        assertEquals("Total price should be 46.50", 46.50, item.getTotalPrice(), 0.01);
    }

    @Test
    public void testCustomTaxRate() {
        Company company = new Company("Test Corp", "123 Test St", "Test City", "TS", "12345", 
                                    "test@test.com", "555-1234", null, null);
        Customer customer = new Customer("Test Customer", "456 Customer Ave", "Customer City", 
                                       "CS", "67890", "customer@test.com", "555-5678");
        
        Invoice invoice = new Invoice("TEST-002", customer, company);
        invoice.setTaxRate(0.08); // 8% tax
        invoice.addItem(new InvoiceItem("Service", 1, 100.00));
        
        assertEquals("Subtotal should be 100.00", 100.00, invoice.getSubtotal(), 0.01);
        assertEquals("Tax should be 8.00 (8%)", 8.00, invoice.getTaxAmount(), 0.01);
        assertEquals("Total should be 108.00", 108.00, invoice.getTotal(), 0.01);
    }

    @Test
    public void testInvoiceDefaults() {
        Invoice invoice = new Invoice();
        
        assertEquals("Default tax rate should be 0.1 (10%)", 0.1, invoice.getTaxRate(), 0.001);
        assertEquals("Invoice date should be today", LocalDate.now(), invoice.getInvoiceDate());
        assertNotNull("Items list should not be null", invoice.getItems());
        assertTrue("Items list should be empty", invoice.getItems().isEmpty());
    }
}
