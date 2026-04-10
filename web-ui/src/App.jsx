import React, { useState, useRef } from 'react';
import html2pdf from 'html2pdf.js';
import * as XLSX from 'xlsx';
import { Download, FileSpreadsheet } from 'lucide-react';

import InvoiceForm from './components/InvoiceForm';
import InvoicePreview from './components/InvoicePreview';

function App() {
  const [invoiceData, setInvoiceData] = useState({
    invoiceNumber: `INV-${new Date().getFullYear()}-001`,
    invoiceDate: new Date().toISOString().split('T')[0],
    taxRate: 18,
    company: {
      name: '',
      address: '',
      phone: '',
      email: '',
      taxId: ''
    },
    customer: {
      name: '',
      address: '',
      phone: '',
      email: ''
    },
    items: [
      { description: '', quantity: 1, unitPrice: 0 }
    ],
    notes: ''
  });

  const invoiceRef = useRef();

  const calculateSubtotal = () => {
    return invoiceData.items.reduce((sum, item) => sum + (item.quantity * item.unitPrice), 0);
  };

  const calculateTax = () => {
    return calculateSubtotal() * (invoiceData.taxRate / 100);
  };

  const calculateTotal = () => {
    return calculateSubtotal() + calculateTax();
  };

  const exportPDF = () => {
    const element = invoiceRef.current;
    const opt = {
      margin:       0,
      filename:     `invoice_${invoiceData.invoiceNumber}.pdf`,
      image:        { type: 'jpeg', quality: 0.98 },
      html2canvas:  { scale: 2, useCORS: true },
      jsPDF:        { unit: 'in', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().set(opt).from(element).save();
  };

  const exportExcel = () => {
    // Flatten data for Excel
    const rows = [
      ['INVOICE'],
      [],
      ['Invoice Number', invoiceData.invoiceNumber],
      ['Date', invoiceData.invoiceDate],
      [],
      ['FROM', '', 'BILL TO'],
      [invoiceData.company.name, '', invoiceData.customer.name],
      [invoiceData.company.address, '', invoiceData.customer.address],
      [invoiceData.company.phone, '', invoiceData.customer.phone],
      [invoiceData.company.email, '', invoiceData.customer.email],
      [invoiceData.company.taxId, '', ''],
      [],
      ['DESCRIPTION', 'QTY', 'UNIT PRICE', 'TOTAL']
    ];

    invoiceData.items.forEach(item => {
      rows.push([
        item.description, 
        item.quantity, 
        item.unitPrice, 
        item.quantity * item.unitPrice
      ]);
    });

    rows.push([]);
    rows.push(['', '', 'Subtotal', calculateSubtotal()]);
    rows.push(['', '', `Tax (${invoiceData.taxRate}%)`, calculateTax()]);
    rows.push(['', '', 'TOTAL', calculateTotal()]);
    rows.push([]);
    rows.push(['NOTES']);
    rows.push([invoiceData.notes]);

    const worksheet = XLSX.utils.aoa_to_sheet(rows);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Invoice');
    
    // Auto-size columns slightly
    worksheet['!cols'] = [
      { wch: 30 },
      { wch: 10 },
      { wch: 15 },
      { wch: 15 }
    ];

    XLSX.writeFile(workbook, `invoice_${invoiceData.invoiceNumber}.xlsx`);
  };

  return (
    <div className="app-container">
      <div className="left-pane">
        <h1>Invoice Generator</h1>
        <InvoiceForm 
          invoiceData={invoiceData} 
          setInvoiceData={setInvoiceData} 
        />
      </div>
      <div className="right-pane">
        <div className="export-controls">
          <button className="btn-primary" onClick={exportPDF}>
            <Download size={18} /> Export PDF
          </button>
          <button className="btn-secondary" style={{ background: '#10b981', color: 'white', border: 'none' }} onClick={exportExcel}>
            <FileSpreadsheet size={18} /> Export Excel
          </button>
        </div>
        <div className="invoice-paper-container">
          <InvoicePreview 
            ref={invoiceRef}
            invoiceData={invoiceData}
            calculateSubtotal={calculateSubtotal}
            calculateTax={calculateTax}
            calculateTotal={calculateTotal}
          />
        </div>
      </div>
    </div>
  );
}

export default App;
