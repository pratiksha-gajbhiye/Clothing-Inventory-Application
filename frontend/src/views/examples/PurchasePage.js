import React, { useState, useEffect } from "react";
import PurchaseService from "../../services/PurchaseService.js";

export default function PurchasePage() {
  const [purchases, setPurchases] = useState([]);
  const [summary, setSummary] = useState(null);
  const [filter, setFilter] = useState({
    supplierName: "",
    startDate: "",
    endDate: "",
  });

  const [form, setForm] = useState({
    invoiceNumber: "",
    purchaseDate: "",
    supplierId: "",
    paymentType: "",
    invoicePhoto: "",
    totalAmount: 0,
    paidAmount: 0,
    items: [],
  });

  const [selectedId, setSelectedId] = useState(null);

  useEffect(() => {
    loadPurchases();
    loadSummary();
  }, []);

  const loadPurchases = () => {
    PurchaseService.getAll().then((res) => setPurchases(res.data));
  };

  const loadSummary = () => {
    PurchaseService.getSummary().then((res) => setSummary(res.data));
  };

  const handleSubmit = async (e) => {
  e.preventDefault();

  const total = parseFloat(form.totalAmount);
  const paid = parseFloat(form.paidAmount);

  const purchaseDTO = {
    invoiceNumber: form.invoiceNumber,
    purchaseDate: form.purchaseDate,
    supplierId: form.supplierId,
    paymentType: form.paymentType,
    totalAmount: total,
    paidAmount: paid,
    items: form.items,
  };

  // const formData = new FormData();
  // formData.append(
  //   "purchase",
  //   new Blob([JSON.stringify(purchaseDTO)], { type: "application/json" })
  // );

  if (form.invoicePhoto) {
    formData.append("invoicePhoto", form.invoicePhoto);
  }

  if (selectedId) {
    await PurchaseService.update(selectedId, formData);
  } else {
    await PurchaseService.create(formData);
  }

  loadPurchases();
  clearForm();
};


  const clearForm = () => {
    setForm({
      invoiceNumber: "",
      purchaseDate: "",
      supplierId: "",
      paymentType: "",
      invoicePhoto: "",
      totalAmount: 0,
      paidAmount: 0,
      items: [],
    });
    setSelectedId(null);
  };

  const handleDelete = (id) => {
    PurchaseService.delete(id).then(() => loadPurchases());
  };

  const handleFilter = () => {
    PurchaseService.filter(filter).then((res) => setPurchases(res.data));
  };

  const handleEdit = (id) => {
    PurchaseService.getById(id).then((res) => {
      const p = res.data;
      setForm({
        invoiceNumber: p.invoiceNumber,
        purchaseDate: p.purchaseDate,
        supplierId: p.supplier?.id || "",
        paymentType: p.paymentType,
        invoicePhoto: p.invoicePhoto,
        totalAmount: p.totalAmount,
        paidAmount: p.paidAmount,
        items: p.items || [],
      });
      setSelectedId(id);
    });
  };

  const handleReturn = (id) => {
    PurchaseService.getById(id).then((res) => {
      const p = res.data;
      const returnData = {
        ...p,
        id: null, // Force new
      };
      PurchaseService.createReturn(returnData).then(() => {
        alert("Return created successfully!");
        loadPurchases();
      });
    });
  };

  return (
    <div className="purchase-page">
      <style>{`
        .purchase-page {
          padding: 2rem;
          font-family: Arial, sans-serif;
        }
        .purchase-page h1 {
          font-size: 1.75rem;
          font-weight: bold;
          margin-bottom: 1rem;
        }
        .purchase-page form input {
          display: block;
          margin: 0.5rem 0;
          padding: 0.5rem;
          width: 100%;
          max-width: 400px;
          border: 1px solid #ccc;
          border-radius: 4px;
        }
        .purchase-page form button {
          background-color: #2563eb;
          color: white;
          padding: 0.5rem 1rem;
          border: none;
          border-radius: 4px;
          cursor: pointer;
        }
        .purchase-page form button:hover {
          background-color: #1d4ed8;
        }
        .purchase-page .mt-6 input {
          display: inline-block;
          margin: 0.5rem 0.5rem 0.5rem 0;
          padding: 0.5rem;
          border: 1px solid #ccc;
          border-radius: 4px;
        }
        .purchase-page .mt-6 button {
          background-color: #16a34a;
          color: white;
          padding: 0.5rem 1rem;
          border: none;
          border-radius: 4px;
          cursor: pointer;
        }
        .purchase-page .mt-6 button:hover {
          background-color: #166534;
        }
        .purchase-page .summary {
          background: #f3f4f6;
          padding: 1rem;
          border-radius: 0.5rem;
          margin-top: 2rem;
        }
        .purchase-page table {
          width: 100%;
          border-collapse: collapse;
          margin-top: 2rem;
        }
        .purchase-page th,
        .purchase-page td {
          border: 1px solid #ccc;
          padding: 0.5rem;
          text-align: left;
        }
        .purchase-page th {
          background: #f9fafb;
        }
        .purchase-page table button {
          background: #2563eb;
          color: white;
          border: none;
          padding: 0.25rem 0.75rem;
          margin-right: 0.3rem;
          border-radius: 4px;
          cursor: pointer;
        }
        .purchase-page table button:hover {
          background: #1d4ed8;
        }
        .purchase-page table .delete {
          background: #dc2626;
        }
        .purchase-page table .delete:hover {
          background: #991b1b;
        }
      `}</style>

      <h1>Purchases</h1>

      <form onSubmit={handleSubmit}>
        <input
          placeholder="Invoice Number"
          value={form.invoiceNumber}
          onChange={(e) => setForm({ ...form, invoiceNumber: e.target.value })}
        />
        <input
          placeholder="Purchase Date"
          type="date"
          value={form.purchaseDate}
          onChange={(e) => setForm({ ...form, purchaseDate: e.target.value })}
        />
        <input
          placeholder="Supplier ID"
          value={form.supplierId}
          onChange={(e) => setForm({ ...form, supplierId: e.target.value })}
        />
        <select
  value={form.paymentType}
  onChange={(e) => setForm({ ...form, paymentType: e.target.value })}
>
  <option value="">Select Payment Type</option>
  <option value="Cash">Cash</option>
  <option value="Card">Card</option>
  <option value="UPI">UPI</option>
  <option value="Credit">Credit</option>
</select>

       <input
  type="file"
  onChange={(e) => {
    const file = e.target.files[0];
    setForm({ ...form, invoicePhoto: file });
  }}
/>
        <input
          placeholder="Total Amount"
          type="number"
          value={form.totalAmount}
          onChange={(e) => setForm({ ...form, totalAmount: e.target.value })}
        />
        <input
          placeholder="Paid Amount"
          type="number"
          value={form.paidAmount}
          onChange={(e) => setForm({ ...form, paidAmount: e.target.value })}
        />
        <button type="submit">{selectedId ? "Update" : "Add"} Purchase</button>
      </form>

      <div className="mt-6">
        <h2>Filter</h2>
        <input
          placeholder="Supplier Name"
          value={filter.supplierName}
          onChange={(e) => setFilter({ ...filter, supplierName: e.target.value })}
        />
        <input
          placeholder="Start Date"
          type="date"
          value={filter.startDate}
          onChange={(e) => setFilter({ ...filter, startDate: e.target.value })}
        />
        <input
          placeholder="End Date"
          type="date"
          value={filter.endDate}
          onChange={(e) => setFilter({ ...filter, endDate: e.target.value })}
        />
        <button onClick={handleFilter}>Filter</button>
      </div>

      {summary && (
        <div className="summary">
          <h2>Summary</h2>
          <p>Total Spent: ₹{summary.totalSpent}</p>
          <p>Total Due: ₹{summary.totalDue}</p>
          <p>Total Purchases: {summary.totalPurchases}</p>
        </div>
      )}

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Invoice</th>
            <th>Supplier</th>
            <th>Date</th>
            <th>Amount</th>
            <th>Due</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {purchases.map((p) => (
            <tr key={p.id}>
              <td>{p.id}</td>
              <td>{p.invoiceNumber}</td>
              <td>{p.supplier?.id}</td>
              <td>{p.purchaseDate}</td>
              <td>₹{p.totalAmount}</td>
              <td>₹{p.dueAmount}</td>
              <td>
                <button onClick={() => handleEdit(p.id)}>Edit</button>
                <button className="delete" onClick={() => handleDelete(p.id)}>
                  Delete
                </button>
                <button onClick={() => handleReturn(p.id)}>Return</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}


