import axios from "axios";

const BASE_URL = "/api/payments";

export const createPayment = (paymentDTO) => {
  return axios.post(BASE_URL, paymentDTO);
};

export const filterPayments = (type, start, end) => {
  return axios.get(`${BASE_URL}/filter`, {
    params: { type, start, end },
  });
};

export const exportCustomerLedgerExcel = (customerId) => {
  return axios.get(`${BASE_URL}/ledger/export/excel/customer/${customerId}`, {
    responseType: "blob",
  });
};

export const exportCustomerLedgerPdf = (customerId) => {
  return axios.get(`${BASE_URL}/ledger/export/pdf/customer/${customerId}`, {
    responseType: "blob",
  });
};

export const exportSupplierLedgerExcel = (supplierId) => {
  return axios.get(`${BASE_URL}/ledger/export/excel/supplier/${supplierId}`, {
    responseType: "blob",
  });
};

export const exportSupplierLedgerPdf = (supplierId) => {
  return axios.get(`${BASE_URL}/ledger/export/pdf/supplier/${supplierId}`, {
    responseType: "blob",
  });
};
