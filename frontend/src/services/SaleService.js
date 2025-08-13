import axios from "axios";

const API = "http://localhost:8080/api/sales"; // change if needed

export const createSale = (dto) => axios.post(API, dto);
export const holdSale = (id) => axios.patch(`${API}/${id}/hold`);
export const resumeSale = (id) => axios.patch(`${API}/${id}/resume`);
export const listSales = () => axios.get(API);
