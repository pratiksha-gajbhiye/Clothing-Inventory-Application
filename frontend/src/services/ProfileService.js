import axios from "axios";

const API_BASE_URL = "http://localhost:8087/api/user";

class ProfileService {
  getProfile(userId) {
    const token = localStorage.getItem("token");
    return axios.get(`${API_BASE_URL}/profile/${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  updateProfile(userId, profileData) {
    const token = localStorage.getItem("token");
    return axios.put(`${API_BASE_URL}/profile/${userId}`, profileData, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }
}

export default new ProfileService();
