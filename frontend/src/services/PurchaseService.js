import axios from "axios";

const API_URL = "http://localhost:8087/api/purchases";

const PurchaseService = {
  create(formData) {
    return axios.post(API_URL, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
  },

  update(id, formData) {
    return axios.put(`${API_URL}/${id}`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
  },

  getAll() {
    return axios.get(API_URL);
  },

  getById(id) {
    return axios.get(`${API_URL}/${id}`);
  },

  delete(id) {
    return axios.delete(`${API_URL}/${id}`);
  },

  filter(params) {
    return axios.get(`${API_URL}/filter`, { params });
  },

  getSummary() {
    return axios.get(`${API_URL}/summary`);
  },

  createReturn(purchase) {
    return axios.post(`${API_URL}/returns`, purchase);
  },
};

export default PurchaseService;
