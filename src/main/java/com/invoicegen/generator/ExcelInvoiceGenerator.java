package com.invoicegen.generator;

import com.invoicegen.model.Invoice;
import com.invoicegen.model.InvoiceItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ExcelInvoiceGenerator implements InvoiceGenerator {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    @Override
    public void generateInvoice(Invoice invoice, String outputPath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Invoice");
            
            // Create styles
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle titleStyle = createTitleStyle(workbook);
            CellStyle currencyStyle = createCurrencyStyle(workbook);
            CellStyle boldStyle = createBoldStyle(workbook);
            CellStyle borderStyle = createBorderStyle(workbook);

            int rowNum = 0;

            // Title
            Row titleRow = sheet.createRow(rowNum++);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("INVOICE");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            
            rowNum++; // Empty row

            // Company Information
            rowNum = addCompanyInfo(sheet, invoice, rowNum, boldStyle);
            
            rowNum++; // Empty row

            // Invoice Details and Customer Info side by side
            Row detailsRow = sheet.createRow(rowNum++);
            detailsRow.createCell(0).setCellValue("Invoice Number:");
            detailsRow.getCell(0).setCellStyle(boldStyle);
            detailsRow.createCell(1).setCellValue(invoice.getInvoiceNumber());
            
            detailsRow.createCell(3).setCellValue("BILL TO:");
            detailsRow.getCell(3).setCellStyle(boldStyle);

            Row dateRow = sheet.createRow(rowNum++);
            dateRow.createCell(0).setCellValue("Invoice Date:");
            dateRow.getCell(0).setCellStyle(boldStyle);
            dateRow.createCell(1).setCellValue(invoice.getInvoiceDate().format(DATE_FORMAT));
            
            dateRow.createCell(3).setCellValue(invoice.getCustomer().getName());

            rowNum = addCustomerInfo(sheet, invoice, rowNum - 1, 3);
            
            rowNum += 2; // Empty rows

            // Items header
            Row itemHeaderRow = sheet.createRow(rowNum++);
            String[] headers = {"DESCRIPTION", "QTY", "UNIT PRICE", "TOTAL"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = itemHeaderRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Items
            for (InvoiceItem item : invoice.getItems()) {
                Row itemRow = sheet.createRow(rowNum++);
                itemRow.createCell(0).setCellValue(item.getDescription());
                itemRow.createCell(1).setCellValue(item.getQuantity());
                
                Cell unitPriceCell = itemRow.createCell(2);
                unitPriceCell.setCellValue(item.getUnitPrice());
                unitPriceCell.setCellStyle(currencyStyle);
                
                Cell totalCell = itemRow.createCell(3);
                totalCell.setCellValue(item.getTotalPrice());
                totalCell.setCellStyle(currencyStyle);
                
                // Apply border style to all cells in the row
                for (int i = 0; i < 4; i++) {
                    if (itemRow.getCell(i) != null) {
                        CellStyle cellStyle = workbook.createCellStyle();
                        if (i >= 2) {
                            cellStyle.cloneStyleFrom(currencyStyle);
                        } else {
                            cellStyle.cloneStyleFrom(borderStyle);
                        }
                        setBorders(cellStyle);
                        itemRow.getCell(i).setCellStyle(cellStyle);
                    }
                }
            }

            rowNum++; // Empty row

            // Totals
            Row subtotalRow = sheet.createRow(rowNum++);
            subtotalRow.createCell(2).setCellValue("Subtotal:");
            subtotalRow.getCell(2).setCellStyle(boldStyle);
            Cell subtotalCell = subtotalRow.createCell(3);
            subtotalCell.setCellValue(invoice.getSubtotal());
            subtotalCell.setCellStyle(currencyStyle);

            Row taxRow = sheet.createRow(rowNum++);
            taxRow.createCell(2).setCellValue("Tax (" + (invoice.getTaxRate() * 100) + "%):");
            taxRow.getCell(2).setCellStyle(boldStyle);
            Cell taxCell = taxRow.createCell(3);
            taxCell.setCellValue(invoice.getTaxAmount());
            taxCell.setCellStyle(currencyStyle);

            Row totalRow = sheet.createRow(rowNum++);
            totalRow.createCell(2).setCellValue("TOTAL:");
            totalRow.getCell(2).setCellStyle(boldStyle);
            Cell totalCell = totalRow.createCell(3);
            totalCell.setCellValue(invoice.getTotal());
            CellStyle totalCellStyle = workbook.createCellStyle();
            totalCellStyle.cloneStyleFrom(currencyStyle);
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            totalCellStyle.setFont(boldFont);
            totalCell.setCellStyle(totalCellStyle);

            // Notes
            if (invoice.getNotes() != null && !invoice.getNotes().trim().isEmpty()) {
                rowNum += 2;
                Row notesLabelRow = sheet.createRow(rowNum++);
                notesLabelRow.createCell(0).setCellValue("NOTES:");
                notesLabelRow.getCell(0).setCellStyle(boldStyle);
                
                Row notesRow = sheet.createRow(rowNum++);
                notesRow.createCell(0).setCellValue(invoice.getNotes());
                sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 3));
            }

            // Footer
            rowNum += 2;
            Row footerRow1 = sheet.createRow(rowNum++);
            footerRow1.createCell(0).setCellValue("Thank you for your business!");
            
            Row footerRow2 = sheet.createRow(rowNum++);
            footerRow2.createCell(0).setCellValue("Payment is due within 30 days of invoice date.");

            // Auto-size columns
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
                workbook.write(fileOut);
            }
        }
    }

    private int addCompanyInfo(Sheet sheet, Invoice invoice, int startRow, CellStyle boldStyle) {
        int rowNum = startRow;
        
        Row companyRow = sheet.createRow(rowNum++);
        companyRow.createCell(0).setCellValue("FROM:");
        companyRow.getCell(0).setCellStyle(boldStyle);
        
        Row nameRow = sheet.createRow(rowNum++);
        nameRow.createCell(0).setCellValue(invoice.getCompany().getName());
        
        Row addressRow = sheet.createRow(rowNum++);
        addressRow.createCell(0).setCellValue(invoice.getCompany().getFullAddress());
        
        Row phoneRow = sheet.createRow(rowNum++);
        phoneRow.createCell(0).setCellValue("Phone: " + invoice.getCompany().getPhone());
        
        Row emailRow = sheet.createRow(rowNum++);
        emailRow.createCell(0).setCellValue("Email: " + invoice.getCompany().getEmail());
        
        if (invoice.getCompany().getWebsite() != null) {
            Row websiteRow = sheet.createRow(rowNum++);
            websiteRow.createCell(0).setCellValue("Website: " + invoice.getCompany().getWebsite());
        }
        
        if (invoice.getCompany().getTaxId() != null) {
            Row taxIdRow = sheet.createRow(rowNum++);
            taxIdRow.createCell(0).setCellValue("Tax ID: " + invoice.getCompany().getTaxId());
        }
        
        return rowNum;
    }

    private int addCustomerInfo(Sheet sheet, Invoice invoice, int startRow, int column) {
        int rowNum = startRow + 1;
        
        Row addressRow = sheet.getRow(rowNum);
        if (addressRow == null) addressRow = sheet.createRow(rowNum);
        addressRow.createCell(column).setCellValue(invoice.getCustomer().getFullAddress());
        rowNum++;
        
        Row phoneRow = sheet.getRow(rowNum);
        if (phoneRow == null) phoneRow = sheet.createRow(rowNum);
        phoneRow.createCell(column).setCellValue("Phone: " + invoice.getCustomer().getPhone());
        rowNum++;
        
        Row emailRow = sheet.getRow(rowNum);
        if (emailRow == null) emailRow = sheet.createRow(rowNum);
        emailRow.createCell(column).setCellValue("Email: " + invoice.getCustomer().getEmail());
        
        return rowNum + 1;
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        setBorders(style);
        return style;
    }

    private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 18);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createCurrencyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("₹#,##0.00"));
        return style;
    }

    private CellStyle createBoldStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private CellStyle createBorderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorders(style);
        return style;
    }

    private void setBorders(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}
