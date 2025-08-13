// src/services/PurchaseReturnService.js

import axios from "axios";

const API_URL = "http://localhost:8087/api/purchase-returns"; // ✅ Backend endpoint

export const getAllReturns = () => axios.get(API_URL);
export const getReturnById = (id) => axios.get(`${API_URL}/${id}`);
export const createReturn = (data) => axios.post(API_URL, data);
export const deleteReturn = (id) => axios.delete(`${API_URL}/${id}`);
