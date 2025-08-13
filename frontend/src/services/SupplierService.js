import axios from "axios";

const BASE_URL = "http://localhost:8087/api/suppliers";

// Create new supplier
export const createSupplier = (supplierDTO) => {
  return axios.post(BASE_URL, supplierDTO);
};

// Get all suppliers
export const
 getAllSuppliers = () => {
  return axios.get(BASE_URL);
};

// Import suppliers from Excelz
export const importSuppliersExcel = (file) => {
  const formData = new FormData();
  formData.append("file", file);
  return axios.post(`${BASE_URL}/import-excel`, formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

// Export suppliers as Excel
export const exportSuppliersExcel = () => {
  return axios.get(`${BASE_URL}/export-excel`, {
    responseType: "blob",
  });
};

// Download supplier template Excel
export const downloadSupplierTemplate = () => {
  return axios.get(`${BASE_URL}/supplier-template`, {
    responseType: "blob",
  });
};

// Upload supplier photo
export const uploadSupplierPhoto = (supplierId, file) => {
  const formData = new FormData();
  formData.append("file", file);
  return axios.post(`${BASE_URL}/upload-photo/${supplierId}`, formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

// Get suppliers with due payments
export const getSuppliersWithDuePayments = () => {
  return axios.get(`${BASE_URL}/due-payments`);
};
