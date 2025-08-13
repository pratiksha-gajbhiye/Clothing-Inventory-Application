// src/services/AuthService.js

import axios from "axios";

const API_URL = "http://localhost:8087/api/auth"; // ✅ Make sure this matches your Spring Boot server port

export const signup = (signupRequest) => {
  return axios.post(`${API_URL}/signup`, signupRequest);
};

export const login = (loginRequest) => {
  return axios.post(`${API_URL}/login`, loginRequest);
};
