package com.invoicegen.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private Customer customer;
    private Company company;
    private List<InvoiceItem> items;
    private double taxRate;
    private String notes;

    public Invoice() {
        this.items = new ArrayList<>();
        this.taxRate = 0.1; // Default 10% tax
        this.invoiceDate = LocalDate.now();
    }

    public Invoice(String invoiceNumber, Customer customer, Company company) {
        this();
        this.invoiceNumber = invoiceNumber;
        this.customer = customer;
        this.company = company;
    }

    // Getters and Setters
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public void addItem(InvoiceItem item) {
        this.items.add(item);
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Calculated fields
    public double getSubtotal() {
        return items.stream()
                .mapToDouble(InvoiceItem::getTotalPrice)
                .sum();
    }

    public double getTaxAmount() {
        return getSubtotal() * taxRate;
    }

    public double getTotal() {
        return getSubtotal() + getTaxAmount();
    }
}
