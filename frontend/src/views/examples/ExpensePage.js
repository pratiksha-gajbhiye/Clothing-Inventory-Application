import React, { useEffect, useState } from 'react';
import {
  getAllExpenses,
  addExpense,
  filterExpenses,
  getCategories,
  exportExcel,
  exportPDF,
  getMonthlyTrends,
  getWeeklyTrends,
} from '../../services/expenseService';

import { Bar } from 'react-chartjs-2';

// ✅ Use chart.js/auto — no manual register needed!
import 'chart.js/auto';

export default function ExpensePage() {
  const [expenses, setExpenses] = useState([]);
  const [categories, setCategories] = useState([]);
  const [form, setForm] = useState({ categoryId: '', description: '', amount: '', date: '' });
  const [from, setFrom] = useState('');
  const [to, setTo] = useState('');
  const [monthly, setMonthly] = useState({});
  const [weekly, setWeekly] = useState({});

  const defaultCategories = [
    { id: '1', name: 'Rent' },
    { id: '2', name: 'Utilities' },
    { id: '3', name: 'Salary' },
    { id: '4', name: 'Stationery' },
    { id: '5', name: 'Marketing' },
  ];

  useEffect(() => {
    fetchAll();
  }, []);

  const fetchAll = async () => {
    const res = await getAllExpenses();
    setExpenses(res.data);

    try {
      const cat = await getCategories();
      const backendCategories = cat.data || [];
      const merged = [...defaultCategories];

      backendCategories.forEach(item => {
        if (!merged.some(c => String(c.id) === String(item.id))) {
          merged.push({ ...item, id: String(item.id) });
        }
      });

      setCategories(merged);
    } catch (err) {
      console.error('Category fetch failed:', err);
      setCategories(defaultCategories);
    }

    const mon = await getMonthlyTrends();
    setMonthly(mon.data);

    const week = await getWeeklyTrends();
    setWeekly(week.data);
  };

  const handleInput = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleAdd = async (e) => {
    e.preventDefault();
    await addExpense({ ...form, categoryId: String(form.categoryId) });
    setForm({ categoryId: '', description: '', amount: '', date: '' });
    fetchAll();
  };

  const handleFilter = async () => {
    const res = await filterExpenses(from, to);
    setExpenses(res.data);
  };

  return (
    <>
      <style>{`
        body {
          font-family: 'Inter', sans-serif;
          background: #f9fafb;
        }
        .container {
          max-width: 1200px;
          margin: auto;
          padding: 2rem;
        }
        h1 {
          font-size: 1.75rem;
          font-weight: bold;
          margin-bottom: 1rem;
          color: #111827;
        }
        form, .controls, .export {
          margin-bottom: 1rem;
        }
        select, input {
          border: 1px solid #d1d5db;
          border-radius: 0.375rem;
          padding: 0.5rem;
          margin-right: 0.5rem;
        }
        button {
          padding: 0.5rem 1rem;
          border-radius: 0.375rem;
          font-weight: 500;
          cursor: pointer;
        }
        .btn-primary {
          background: #3b82f6;
          color: #fff;
          border: none;
        }
        .btn-primary:hover {
          background: #2563eb;
        }
        .btn-outline {
          border: 1px solid #3b82f6;
          background: transparent;
          color: #3b82f6;
        }
        .btn-outline:hover {
          background: #eff6ff;
        }
        table {
          width: 100%;
          border-collapse: collapse;
        }
        th, td {
          border: 1px solid #e5e7eb;
          padding: 0.75rem;
          text-align: left;
        }
        th {
          background: #f1f5f9;
        }
        .chart-container {
          margin-top: 2rem;
        }
      `}</style>

      <div className="container">
        <h1>📊 Expense Tracker</h1>

        <form onSubmit={handleAdd}>
          <select
            name="categoryId"
            value={form.categoryId}
            onChange={handleInput}
            required
          >
            <option value="">Select Category</option>
            {categories.length === 0 ? (
              <option disabled>No categories found</option>
            ) : (
              categories.map((c) => (
                <option key={c.id} value={String(c.id)}>{c.name}</option>
              ))
            )}
          </select>

          <input
            name="description"
            placeholder="Description"
            value={form.description}
            onChange={handleInput}
            required
          />

          <input
            name="amount"
            type="number"
            placeholder="Amount"
            value={form.amount}
            onChange={handleInput}
            required
          />

          <input
            name="date"
            type="date"
            value={form.date}
            onChange={handleInput}
            required
          />

          <button type="submit" className="btn-primary">➕ Add</button>
        </form>

        <div className="controls">
          <input type="date" value={from} onChange={(e) => setFrom(e.target.value)} />
          <input type="date" value={to} onChange={(e) => setTo(e.target.value)} />
          <button onClick={handleFilter} className="btn-outline">🔍 Filter</button>
          <button onClick={fetchAll} className="btn-outline">🔄 Reset</button>
        </div>

        <div className="export">
          <button onClick={exportExcel} className="btn-outline">📄 Excel</button>
          <button onClick={exportPDF} className="btn-outline">📰 PDF</button>
        </div>

        <table>
          <thead>
            <tr>
              <th>#</th>
              <th>Category</th>
              <th>Description</th>
              <th>Amount</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {expenses.map((e, i) => (
              <tr key={i}>
                <td>{i + 1}</td>
                <td>{e.category?.name || '-'}</td>
                <td>{e.description}</td>
                <td>₹{e.amount}</td>
                <td>{e.date}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <div className="chart-container">
          <h2>📅 Monthly Trends</h2>
          <Bar
            data={{
              labels: Object.keys(monthly),
              datasets: [
                {
                  label: 'Monthly Expense',
                  data: Object.values(monthly),
                  backgroundColor: '#3b82f6',
                },
              ],
            }}
          />
        </div>

        <div className="chart-container">
          <h2>📅 Weekly Trends</h2>
          <Bar
            data={{
              labels: Object.keys(weekly),
              datasets: [
                {
                  label: 'Weekly Expense',
                  data: Object.values(weekly),
                  backgroundColor: '#10b981',
                },
              ],
            }}
          />
        </div>
      </div>
    </>
  );
}
