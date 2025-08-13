import axios from "axios";

// Base URL for your backend
const API_URL = "http://localhost:8087/api/customers";

// ✔️ POST: Create one customer
export const createCustomer = (customerDTO) => {
  return axios.post(API_URL, customerDTO);
};

// ✔️ GET: Get all customers
export const getAllCustomers = () => {
  return axios.get(API_URL);
};

// ✔️ GET: Get one customer by ID
export const getCustomerById = (id) => {
  return axios.get(`${API_URL}/${id}`);
};

// ✔️ POST: Import multiple customers
export const importCustomers = (customerDTOs) => {
  return axios.post(`${API_URL}/import`, customerDTOs);
};

// ✔️ GET: Customers with due bills
export const getCustomersWithDueBills = () => {
  return axios.get(`${API_URL}/due-bills`);
};
