import React, { useState, useEffect } from "react";
import {
  getAllEntries,
  createEntry,
  deleteEntry,
  getEntryById,
} from "../../services/CashBankEntryService.js";

export default function CashBankEntryPage() {
  const [entries, setEntries] = useState([]);
  const [form, setForm] = useState({
    id: "",
    date: "",
    type: "CASH",
    transactionType: "INCOME",
    amount: "",
    description: "",
    reference: "",
  });

  useEffect(() => {
    loadEntries();
  }, []);

  const loadEntries = async () => {
    try {
      const res = await getAllEntries();
      setEntries(res.data);
    } catch (err) {
      console.error("Error loading:", err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createEntry({
        ...form,
        amount: parseFloat(form.amount),
      });
      setForm({
        id: "",
        date: "",
        type: "CASH",
        transactionType: "INCOME",
        amount: "",
        description: "",
        reference: "",
      });
      loadEntries();
    } catch (err) {
      console.error("Error saving:", err);
    }
  };

  const handleEdit = async (id) => {
    const res = await getEntryById(id);
    setForm({
      ...res.data,
      date: res.data.date,
      amount: res.data.amount,
    });
  };

  const handleDelete = async (id) => {
    if (window.confirm("Delete this entry?")) {
      await deleteEntry(id);
      loadEntries();
    }
  };

  return (
    <div className="container cashbank-page">
      <style>
        {`
          .cashbank-page {
            font-family: 'Segoe UI', sans-serif;
            max-width: 1100px;
            margin: 40px auto;
          }
          .cashbank-page h3 {
            color: #5e72e4;
            margin-bottom: 20px;
          }
          .cashbank-page form {
            background: #f9f9f9;
            border-radius: 8px;
            padding: 20px;
          }
          .cashbank-page label {
            font-weight: 600;
            font-size: 0.9rem;
          }
          .cashbank-page .form-control, .cashbank-page .form-select {
            border-radius: 4px;
          }
          .cashbank-page button.btn-primary {
            background: #5e72e4;
            border: none;
          }
          .cashbank-page button.btn-info {
            background: #5e72e4;
            border: none;
          }
          .cashbank-page button.btn-danger {
            background: #dc3545;
            border: none;
          }
          .cashbank-page table {
            margin-top: 20px;
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
          }
          .cashbank-page thead {
            background: #e9ecef;
          }
          .cashbank-page tbody tr:hover {
            background: #f0f8ff;
          }
          .cashbank-page .table th, .cashbank-page .table td {
            vertical-align: middle;
          }
        `}
      </style>

      <h3>Cash / Bank Entry</h3>
      <form onSubmit={handleSubmit} className="row g-3 mb-4">
        <div className="col-md-2">
          <label className="form-label">Date</label>
          <input
            type="date"
            className="form-control"
            value={form.date}
            onChange={(e) => setForm({ ...form, date: e.target.value })}
            required
          />
        </div>
        <div className="col-md-2">
          <label className="form-label">Type</label>
          <select
            className="form-select"
            value={form.type}
            onChange={(e) => setForm({ ...form, type: e.target.value })}
          >
            <option value="CASH">CASH</option>
            <option value="BANK">BANK</option>
          </select>
        </div>
        <div className="col-md-2">
          <label className="form-label">Transaction</label>
          <select
            className="form-select"
            value={form.transactionType}
            onChange={(e) =>
              setForm({ ...form, transactionType: e.target.value })
            }
          >
            <option value="INCOME">INCOME</option>
            <option value="EXPENSE">EXPENSE</option>
          </select>
        </div>
        <div className="col-md-2">
          <label className="form-label">Amount</label>
          <input
            type="number"
            className="form-control"
            value={form.amount}
            onChange={(e) => setForm({ ...form, amount: e.target.value })}
            required
          />
        </div>
        <div className="col-md-2">
          <label className="form-label">Reference</label>
          <input
            type="text"
            className="form-control"
            value={form.reference}
            onChange={(e) => setForm({ ...form, reference: e.target.value })}
          />
        </div>
        <div className="col-md-2">
          <label className="form-label">Description</label>
          <input
            type="text"
            className="form-control"
            value={form.description}
            onChange={(e) => setForm({ ...form, description: e.target.value })}
          />
        </div>
        <div className="col-12">
          <button className="btn btn-primary" type="submit">
            {form.id ? "Update" : "Add"}
          </button>
        </div>
      </form>

      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Type</th>
            <th>Transaction</th>
            <th>Amount</th>
            <th>Reference</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {entries.map((e) => (
            <tr key={e.id}>
              <td>{e.id}</td>
              <td>{e.date}</td>
              <td>{e.type}</td>
              <td>{e.transactionType}</td>
              <td>{e.amount}</td>
              <td>{e.reference}</td>
              <td>{e.description}</td>
              <td>
                <button
                  className="btn btn-sm btn-info me-2"
                  onClick={() => handleEdit(e.id)}
                >
                  Edit
                </button>
                <button
                  className="btn btn-sm btn-danger"
                  onClick={() => handleDelete(e.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
          {entries.length === 0 && (
            <tr>
              <td colSpan="8" className="text-center">
                No entries found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
