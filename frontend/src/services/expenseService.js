// src/services/expenseService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8087/api/expenses';

export const getAllExpenses = () => axios.get(BASE_URL);

export const addExpense = (dto) => axios.post(BASE_URL, dto);

export const filterExpenses = (from, to) =>
  axios.get(`${BASE_URL}/filter`, { params: { from, to } });

export const getCategories = () => axios.get(`${BASE_URL}/category`);

export const exportExcel = () => window.open(`${BASE_URL}/export/excel`, '_blank');

export const exportPDF = () => window.open(`${BASE_URL}/export/pdf`, '_blank');

export const getMonthlyTrends = () => axios.get(`${BASE_URL}/trends/monthly`);

export const getWeeklyTrends = () => axios.get(`${BASE_URL}/trends/weekly`);
