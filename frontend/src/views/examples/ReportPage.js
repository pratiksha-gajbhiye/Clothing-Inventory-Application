// src/pages/ReportPage.js
import React, { useState } from "react";
import {
  fetchProfitLoss,
  fetchStockReport,
  exportProfitLossPdf,
  exportStockExcel,
} from "../../services/ReportService";

export default function ReportPage() {
  const [from, setFrom] = useState("");
  const [to, setTo] = useState("");
  const [profitLoss, setProfitLoss] = useState(null);
  const [stockReport, setStockReport] = useState([]);

  const handleProfitLoss = async () => {
    const res = await fetchProfitLoss(from, to);
    setProfitLoss(res.data);
  };

  const handleStockReport = async () => {
    const res = await fetchStockReport();
    setStockReport(res.data);
  };

  const handleExportProfitLossPdf = async () => {
    const res = await exportProfitLossPdf(from, to);
    const blob = new Blob([res.data], { type: "application/pdf" });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
  };

  const handleExportStockExcel = async () => {
    const res = await exportStockExcel();
    const url = window.URL.createObjectURL(new Blob([res.data]));
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download", "stock_report.xlsx");
    document.body.appendChild(link);
    link.click();
  };

  return (
    <div className="p-6 space-y-6">
      <style>{`
        h2 {
          font-size: 1.5rem;
          font-weight: 700;
          margin-bottom: 1rem;
        }
        .form-section label {
          display: flex;
          align-items: center;
          gap: 0.5rem;
          font-weight: 500;
        }
        .form-section input {
          border: 1px solid #ccc;
          padding: 0.25rem 0.5rem;
          border-radius: 4px;
        }
        .report-box {
          background-color: #f9fafb;
          padding: 1rem;
          border-radius: 0.5rem;
          border: 1px solid #e5e7eb;
          margin-top: 1rem;
        }
        .report-box h3 {
          font-weight: 600;
          margin-bottom: 0.5rem;
        }
        .report-box p {
          margin: 0.25rem 0;
        }
        table {
          width: 100%;
          border-collapse: collapse;
          margin-top: 1rem;
        }
        th, td {
          border: 1px solid #d1d5db;
          padding: 0.5rem 1rem;
          text-align: left;
        }
        thead {
          background-color: #e5e7eb;
        }
        .btn {
          padding: 0.5rem 1rem;
          border-radius: 4px;
          font-weight: 500;
          color: white;
        }
        .btn-blue {
          background-color: #2563eb;
        }
        .btn-green {
          background-color: #16a34a;
        }
        .btn + .btn {
          margin-left: 0.75rem;
        }
      `}</style>

      <h2>Reports</h2>

      <div className="form-section flex gap-4 items-center">
        <label>
          From:
          <input type="date" value={from} onChange={(e) => setFrom(e.target.value)} />
        </label>
        <label>
          To:
          <input type="date" value={to} onChange={(e) => setTo(e.target.value)} />
        </label>
        <button onClick={handleProfitLoss} className="btn btn-blue">Get Profit & Loss</button>
        <button onClick={handleExportProfitLossPdf} className="btn btn-green">Export PDF</button>
      </div>

      {profitLoss && (
        <div className="report-box">
          <h3>Profit & Loss Report</h3>
          <p><strong>Total Sales:</strong> ₹{profitLoss.totalSales}</p>
          <p><strong>Total Purchase:</strong> ₹{profitLoss.totalPurchase}</p>
          <p><strong>Profit:</strong> ₹{profitLoss.profit}</p>
        </div>
      )}

      <div className="form-section mt-8">
        <button onClick={handleStockReport} className="btn btn-blue">Get Stock Report</button>
        <button onClick={handleExportStockExcel} className="btn btn-green">Export Stock Excel</button>
      </div>

      {stockReport.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>Item</th>
              <th>Available Qty</th>
              <th>Unit Price</th>
            </tr>
          </thead>
          <tbody>
            {stockReport.map((item, index) => (
              <tr key={index}>
                <td>{item.itemName}</td>
                <td>{item.quantity}</td>
                <td>₹{item.unitPrice}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
