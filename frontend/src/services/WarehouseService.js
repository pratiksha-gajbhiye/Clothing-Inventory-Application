// src/services/WarehouseService.js

import axios from "axios";

const BASE_URL = "/api/warehouses";

// Get all warehouses
export const getAllWarehouses = () => {
  return axios.get(BASE_URL);
};

// Create a warehouse
export const createWarehouse = (warehouse) => {
  return axios.post(BASE_URL, warehouse);
};

// Delete a warehouse by ID
export const deleteWarehouse = (id) => {
  return axios.delete(`${BASE_URL}/${id}`);
};

// Transfer stock between warehouses
export const transferStock = (transferDTO) => {
  return axios.post(`${BASE_URL}/transfer`, transferDTO);
};
