import axios from "axios";

const API_URL = "/api/sales-return";

export const saveSalesReturn = (data) => axios.post(`${API_URL}/save`, data);

export const getAllSalesReturns = () => axios.get(`${API_URL}/all`);

export const getSalesReturnsBetween = (from, to) =>
  axios.get(`${API_URL}/between`, { params: { from, to } });
