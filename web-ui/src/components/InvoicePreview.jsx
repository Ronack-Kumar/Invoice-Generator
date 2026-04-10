import React from 'react';

const InvoicePreview = React.forwardRef(({ invoiceData, calculateSubtotal, calculateTax, calculateTotal }, ref) => {
  const { company, customer, items, invoiceNumber, invoiceDate, taxRate, notes } = invoiceData;

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('en-IN', {
      style: 'currency',
      currency: 'INR'
    }).format(amount);
  };

  const formatDate = (dateString) => {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', { year: 'numeric', month: 'short', day: 'numeric' });
  };

  return (
    <div className="invoice-paper" ref={ref} id="invoice-preview-element">
      <div className="inv-header">
        <div>
          <h1 className="inv-title">INVOICE</h1>
        </div>
        <div className="inv-meta">
          <p><strong>Invoice Number:</strong> {invoiceNumber}</p>
          <p><strong>Date:</strong> {formatDate(invoiceDate)}</p>
        </div>
      </div>

      <div className="inv-address-block">
        <div className="inv-address">
          <div className="inv-address-title">From:</div>
          <h3>{company.name || 'Your Company Name'}</h3>
          <p>{company.address}</p>
          <p>{company.phone}</p>
          <p>{company.email}</p>
          {company.taxId && <p><strong>Tax ID:</strong> {company.taxId}</p>}
        </div>

        <div className="inv-address text-right" style={{ textAlign: 'right' }}>
          <div className="inv-address-title">Bill To:</div>
          <h3>{customer.name || 'Customer Name'}</h3>
          <p>{customer.address}</p>
          <p>{customer.phone}</p>
          <p>{customer.email}</p>
        </div>
      </div>

      <table className="inv-table">
        <thead>
          <tr>
            <th>Description</th>
            <th className="text-right">Qty</th>
            <th className="text-right">Unit Price</th>
            <th className="text-right">Total</th>
          </tr>
        </thead>
        <tbody>
          {items.map((item, index) => (
            <tr key={index}>
              <td>{item.description || 'Item Description'}</td>
              <td className="text-right">{item.quantity}</td>
              <td className="text-right">{formatCurrency(item.unitPrice || 0)}</td>
              <td className="text-right">{formatCurrency((item.quantity || 0) * (item.unitPrice || 0))}</td>
            </tr>
          ))}
          {items.length === 0 && (
            <tr>
              <td colSpan="4" style={{ textAlign: 'center', color: '#9ca3af' }}>No items added yet.</td>
            </tr>
          )}
        </tbody>
      </table>

      <div className="inv-summary">
        <div className="inv-summary-row">
          <span>Subtotal:</span>
          <span>{formatCurrency(calculateSubtotal())}</span>
        </div>
        <div className="inv-summary-row">
          <span>Tax ({taxRate}%):</span>
          <span>{formatCurrency(calculateTax())}</span>
        </div>
        <div className="inv-summary-row total">
          <span>Total:</span>
          <span>{formatCurrency(calculateTotal())}</span>
        </div>
      </div>

      {notes && (
        <div className="inv-notes">
          <strong>Notes:</strong>
          <p style={{ marginTop: '0.5rem', whiteSpace: 'pre-line' }}>{notes}</p>
        </div>
      )}
    </div>
  );
});

export default InvoicePreview;
