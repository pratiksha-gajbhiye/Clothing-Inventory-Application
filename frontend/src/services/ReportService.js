// src/services/ReportService.js

import axios from "axios";

const API_URL = "http://localhost:8087/api/reports";

// Fetch Profit and Loss report
export const fetchProfitLoss = (from, to) => {
  return axios.get(`${API_URL}/profit-loss`, {
    params: { from, to },
  });
};

// Export Profit and Loss report as PDF
export const exportProfitLossPdf = (from, to) => {
  return axios.get(`${API_URL}/profit-loss/export-pdf`, {
    params: { from, to },
    responseType: "blob",
  });
};

// Fetch Stock Report
export const fetchStockReport = () => {
  return axios.get(`${API_URL}/stock`);
};

// Export Stock Report as Excel
export const exportStockExcel = () => {
  return axios.get(`${API_URL}/stock/export-excel`, {
    responseType: "blob",
  });
};

// Fetch Expense Report (if needed later)
export const fetchExpenseReport = (from, to) => {
  return axios.get(`${API_URL}/expenses`, {
    params: { from, to },
  });
};

// Fetch Item Sales Report (if needed later)
export const fetchItemSales = (from, to) => {
  return axios.get(`${API_URL}/item-sales`, {
    params: { from, to },
  });
};
