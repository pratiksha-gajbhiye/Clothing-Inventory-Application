// ✅ This is your clean ItemService.js — place this in /services/ItemService.js

import axios from "axios";

const API = "http://localhost:8087/api/items";

export const getAllItems = () => axios.get(API);

export const createItem = (item) => axios.post(API, item);

export const updateItem = (id, item) => axios.put(`${API}/${id}`, item);

export const deleteItem = (id) => axios.delete(`${API}/${id}`);

export const importItems = (formData) =>
  axios.post(`${API}/items/import`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });

// ✅ Note: Use `deleteItem` not `deleteItemById` in your import
