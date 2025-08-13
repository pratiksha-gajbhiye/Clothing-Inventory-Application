import axios from "axios";

// Company Profile
export const getProfile = () => axios.get("/api/settings/profile");

export const saveProfile = (profile) =>
  axios.post("/api/settings/profile", profile);

// Taxes
export const getAllTaxes = () => axios.get("/api/settings/taxes");

export const saveTax = (tax) =>
  axios.post("/api/settings/taxes", tax);

export const deleteTax = (id) =>
  axios.delete(`/api/settings/taxes/${id}`);

// Payment Types
export const getAllPaymentTypes = () =>
  axios.get("/api/settings/payment-types");

// Change Password
export const changePassword = (data) =>
  axios.post("/api/settings/change-password", data);
