// src/services/CashBankEntryService.js

import axios from "axios";

const API_URL = "http://localhost:8087/api/cashbank";

export const getAllEntries = () => axios.get(API_URL);
export const createEntry = (entry) => axios.post(API_URL, entry);
export const deleteEntry = (id) => axios.delete(`${API_URL}/${id}`);
export const getEntryById = (id) => axios.get(`${API_URL}/${id}`);
