import React from 'react';
import { Plus, Trash2 } from 'lucide-react';

const InvoiceForm = ({ invoiceData, setInvoiceData }) => {
  
  const handleCompanyChange = (e) => {
    setInvoiceData({
      ...invoiceData,
      company: { ...invoiceData.company, [e.target.name]: e.target.value }
    });
  };

  const handleCustomerChange = (e) => {
    setInvoiceData({
      ...invoiceData,
      customer: { ...invoiceData.customer, [e.target.name]: e.target.value }
    });
  };

  const handleMetaChange = (e) => {
    setInvoiceData({
      ...invoiceData,
      [e.target.name]: e.target.value
    });
  };

  const handleItemChange = (index, e) => {
    const newItems = [...invoiceData.items];
    newItems[index][e.target.name] = e.target.value;
    setInvoiceData({ ...invoiceData, items: newItems });
  };

  const addItem = () => {
    setInvoiceData({
      ...invoiceData,
      items: [...invoiceData.items, { description: '', quantity: 1, unitPrice: 0 }]
    });
  };

  const removeItem = (index) => {
    const newItems = invoiceData.items.filter((_, i) => i !== index);
    setInvoiceData({ ...invoiceData, items: newItems });
  };

  return (
    <div className="invoice-form">
      <h2>Invoice Details</h2>
      
      <div className="glass-panel">
        <h3>Meta Information</h3>
        <div className="form-row">
          <div className="form-group">
            <label>Invoice Number</label>
            <input type="text" name="invoiceNumber" value={invoiceData.invoiceNumber} onChange={handleMetaChange} />
          </div>
          <div className="form-group">
            <label>Date</label>
            <input type="date" name="invoiceDate" value={invoiceData.invoiceDate} onChange={handleMetaChange} />
          </div>
        </div>
        <div className="form-row">
          <div className="form-group">
            <label>Tax Rate (%)</label>
            <input type="number" name="taxRate" value={invoiceData.taxRate} onChange={handleMetaChange} step="0.1" />
          </div>
        </div>
      </div>

      <div className="glass-panel">
        <h3>From (Your Company)</h3>
        <div className="form-group">
          <label>Company Name</label>
          <input type="text" name="name" value={invoiceData.company.name} onChange={handleCompanyChange} />
        </div>
        <div className="form-group">
          <label>Address</label>
          <textarea name="address" value={invoiceData.company.address} onChange={handleCompanyChange} rows="2"></textarea>
        </div>
        <div className="form-row">
          <div className="form-group">
            <label>Phone</label>
            <input type="text" name="phone" value={invoiceData.company.phone} onChange={handleCompanyChange} />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input type="email" name="email" value={invoiceData.company.email} onChange={handleCompanyChange} />
          </div>
        </div>
        <div className="form-row">
          <div className="form-group">
            <label>Tax ID / GST</label>
            <input type="text" name="taxId" value={invoiceData.company.taxId} onChange={handleCompanyChange} />
          </div>
        </div>
      </div>

      <div className="glass-panel">
        <h3>Bill To (Customer)</h3>
        <div className="form-group">
          <label>Customer Name</label>
          <input type="text" name="name" value={invoiceData.customer.name} onChange={handleCustomerChange} />
        </div>
        <div className="form-group">
          <label>Address</label>
          <textarea name="address" value={invoiceData.customer.address} onChange={handleCustomerChange} rows="2"></textarea>
        </div>
        <div className="form-row">
          <div className="form-group">
            <label>Phone</label>
            <input type="text" name="phone" value={invoiceData.customer.phone} onChange={handleCustomerChange} />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input type="email" name="email" value={invoiceData.customer.email} onChange={handleCustomerChange} />
          </div>
        </div>
      </div>

      <div className="glass-panel">
        <h3>Invoice Items</h3>
        {invoiceData.items.map((item, index) => (
          <div key={index} className="form-row item-row-animate" style={{ alignItems: 'flex-end' }}>
            <div className="form-group" style={{ flex: 2 }}>
              <label>Description</label>
              <input type="text" name="description" value={item.description} onChange={(e) => handleItemChange(index, e)} />
            </div>
            <div className="form-group" style={{ flex: 1 }}>
              <label>Qty</label>
              <input type="number" name="quantity" value={item.quantity} onChange={(e) => handleItemChange(index, e)} min="1" />
            </div>
            <div className="form-group" style={{ flex: 1 }}>
              <label>Price</label>
              <input type="number" name="unitPrice" value={item.unitPrice} onChange={(e) => handleItemChange(index, e)} step="0.01" />
            </div>
            <div className="form-group" style={{ flex: 0 }}>
              <button className="btn-danger btn-icon" onClick={() => removeItem(index)} title="Remove Item">
                <Trash2 size={18} />
              </button>
            </div>
          </div>
        ))}
        <button className="btn-secondary" onClick={addItem} style={{ width: '100%', marginTop: '1rem' }}>
          <Plus size={18} /> Add Item
        </button>
      </div>

      <div className="glass-panel">
        <h3>Notes</h3>
        <textarea name="notes" value={invoiceData.notes} onChange={handleMetaChange} rows="3" placeholder="Thank you for your business!"></textarea>
      </div>

    </div>
  );
};

export default InvoiceForm;
