import React, { useEffect, useState } from "react";
import {
  createSale,
  holdSale,
  resumeSale,
  listSales,
} from "../../services/SaleService";

export default function SalePage() {
  const [sales, setSales] = useState([]);
  const [form, setForm] = useState({
    invoiceNumber: "",
    customerId: "",
  });

  useEffect(() => {
    loadSales();
  }, []);

  const loadSales = async () => {
    const res = await listSales();
    setSales(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newSale = {
      invoiceNumber: form.invoiceNumber,
      customerId: form.customerId,
      items: [],
    };
    await createSale(newSale);
    setForm({
      invoiceNumber: "",
      customerId: "",
    });
    loadSales();
  };

  const handleHold = async (id) => {
    await holdSale(id);
    loadSales();
  };

  const handleResume = async (id) => {
    await resumeSale(id);
    loadSales();
  };

  return (
    <div className="container mt-5 sale-page">
      <style>
        {`
          .sale-page {
            font-family: 'Segoe UI', 'Roboto', sans-serif;
          }

          .sale-page h3 {
            color: #5e72e4;
            font-weight: 600;
            margin-bottom: 1rem;
          }

          .sale-page .form-label {
            font-weight: 500;
          }

          .sale-page input.form-control,
          .sale-page select.form-select {
            border-radius: 4px;
          }

          .sale-page button.btn-primary {
            background-color: #5e72e4;
            border: none;
          }

          .sale-page .table {
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
          }

          .sale-page table thead {
            background: #f6f9fc;
          }

          .sale-page table tbody tr:hover {
            background: #f0f8ff;
          }

          .sale-page .btn-warning {
            background-color: #fb6340;
            border: none;
          }

          .sale-page .btn-success {
            background-color: #2dce89;
            border: none;
          }

          .sale-page .btn-sm {
            border-radius: 4px;
          }

          .sale-page .me-2 {
            margin-right: 0.5rem;
          }

          .sale-page .text-center {
            text-align: center;
          }
        `}
      </style>

      <h3>Sales Management</h3>

      <form onSubmit={handleSubmit} className="row g-3 mb-4">
        <div className="col-md-4">
          <label className="form-label">Invoice Number</label>
          <input
            className="form-control"
            value={form.invoiceNumber}
            onChange={(e) =>
              setForm({ ...form, invoiceNumber: e.target.value })
            }
            required
          />
        </div>
        <div className="col-md-4">
          <label className="form-label">Customer ID</label>
          <input
            className="form-control"
            value={form.customerId}
            onChange={(e) => setForm({ ...form, customerId: e.target.value })}
            required
          />
        </div>
        <div className="col-md-4 d-flex align-items-end">
          <button className="btn btn-primary w-100" type="submit">
            Create Sale
          </button>
        </div>
      </form>

      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <th>ID</th>
            <th>Invoice</th>
            <th>Date</th>
            <th>Customer</th>
            <th>Status</th>
            <th>Grand Total</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {sales.map((s) => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.invoiceNumber}</td>
              <td>{s.saleDate}</td>
              <td>{s.customer ? s.customer.name : "-"}</td>
              <td>{s.status}</td>
              <td>{s.grandTotal}</td>
              <td>
                {s.status === "PENDING" && (
                  <button
                    className="btn btn-sm btn-warning me-2"
                    onClick={() => handleHold(s.id)}
                  >
                    Hold
                  </button>
                )}
                {s.status === "HOLD" && (
                  <button
                    className="btn btn-sm btn-success me-2"
                    onClick={() => handleResume(s.id)}
                  >
                    Resume
                  </button>
                )}
              </td>
            </tr>
          ))}
          {sales.length === 0 && (
            <tr>
              <td colSpan="7" className="text-center">
                No sales found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
