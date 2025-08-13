import axios from "axios";

const BASE_URL = "/api/sales-return-items";

export const getAllSalesReturnItems = async () => {
  return await axios.get(`${BASE_URL}/all`);
};

export const saveSalesReturnItem = async (payload) => {
  return await axios.post(`${BASE_URL}/save`, payload);
};

export const deleteSalesReturnItem = async (id) => {
  return await axios.delete(`${BASE_URL}/${id}`);
};

export const getSalesReturnItemsBySalesReturnId = async (salesReturnId) => {
  return await axios.get(`${BASE_URL}/by-sales-return/${salesReturnId}`);
};
