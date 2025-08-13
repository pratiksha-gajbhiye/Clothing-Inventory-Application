import React, { useState, useEffect } from "react";
import {
  createCustomer,
  getAllCustomers,
  getCustomerById,
  importCustomers,
  getCustomersWithDueBills,
} from "../../services/customerService.js";

export default function CustomerList() {
  const [showModal, setShowModal] = useState(false);
  const [customers, setCustomers] = useState([]);
  const [searchId, setSearchId] = useState("");

  const initialCustomer = {
    name: "",
    mobile: "",
    email: "",
    phone: "",
    gstNumber: "",
    taxNumber: "",
    country: "",
    state: "",
    city: "",
    postcode: "",
    address: "",
    previousDue: 0,
  };

  const [newCustomer, setNewCustomer] = useState(initialCustomer);

  useEffect(() => {
    loadCustomers();
  }, []);

  const loadCustomers = async () => {
    try {
      const response = await getAllCustomers();
      setCustomers(response.data);
    } catch (err) {
      console.error("Failed to fetch customers:", err);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "previousDue") {
      setNewCustomer({ ...newCustomer, [name]: parseFloat(value) || 0 });
    } else {
      setNewCustomer({ ...newCustomer, [name]: value });
    }
  };

  const handleAddCustomer = async (e) => {
    e.preventDefault();
    try {
      const payload = {
        ...newCustomer,
        previousDue: parseFloat(newCustomer.previousDue) || 0,
      };
      await createCustomer(payload);
      setShowModal(false);
      setNewCustomer(initialCustomer);
      loadCustomers();
      alert("✅ Customer saved!");
    } catch (err) {
      console.error("Error creating customer:", err);
      alert("❌ Failed to save customer");
    }
  };

  const handleImport = async () => {
    try {
      await importCustomers();
      loadCustomers();
    } catch (err) {
      console.error("Error importing customers:", err);
    }
  };

  const handleSearchById = async () => {
    if (!searchId) return;
    try {
      const response = await getCustomerById(searchId);
      if (response.data) {
        setCustomers([response.data]);
      } else {
        alert("No customer found!");
      }
    } catch (err) {
      console.error("Not found:", err);
      alert("No customer found!");
    }
  };

  const handleGetDueBills = async () => {
    try {
      const response = await getCustomersWithDueBills();
      setCustomers(response.data);
    } catch (err) {
      console.error("Error fetching due bills:", err);
    }
  };

  return (
    <div className="max-w-full mx-auto p-6">
      <style>{`
        .btn { padding: 0.5rem 1rem; border-radius: 0.375rem; font-size: 0.875rem; cursor: pointer; transition: all 0.2s; }
        .btn-outline { border: 1px solid #2563eb; background: transparent; color: #2563eb; }
        .btn-outline:hover { background: #2563eb; color: #fff; }
        .btn-primary { background: #2563eb; color: #fff; border: none; }
        .btn-primary:hover { background: #1d4ed8; }
        .table-container { overflow-x: auto; }
        .custom-table { width: 100%; border-collapse: collapse; font-size: 0.875rem; }
        .custom-table th, .custom-table td { border: 1px solid #e5e7eb; padding: 0.75rem; text-align: left; }
        .custom-table th { background: #f9fafb; font-weight: 600; text-transform: uppercase; font-size: 0.75rem; }
        .custom-table tbody tr:nth-child(even) { background: #f9fafb; }
        .modal { position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: rgba(0, 0, 0, 0.5); display: flex; align-items: center; justify-content: center; z-index: 50; }
        .modal-content { background: #fff; padding: 2rem; border-radius: 0.5rem; width: 100%; max-width: 500px; }
        .modal-content input { display: block; width: 100%; padding: 0.5rem; margin-bottom: 0.75rem; border: 1px solid #e5e7eb; border-radius: 0.375rem; font-size: 0.875rem; }
        .badge { display: inline-block; padding: 0.25rem 0.5rem; border-radius: 0.25rem; background: #d1fae5; color: #065f46; font-size: 0.75rem; }
        .search-input { border: 1px solid #e5e7eb; border-radius: 0.375rem; padding: 0.5rem; font-size: 0.875rem; }
      `}</style>

      <div className="flex flex-col md:flex-row md:justify-between md:items-center mb-6 gap-4">
        <h1 className="text-2xl font-bold text-gray-800">🧵 Clothing Customer List</h1>
        <div className="flex gap-2 flex-wrap">
          <button className="btn btn-outline" onClick={handleImport}>Import</button>
          <button className="btn btn-outline" onClick={handleGetDueBills}>View Due Bills</button>
          <button className="btn btn-primary" onClick={() => setShowModal(true)}>➕ Add Customer</button>
        </div>
      </div>

      <div className="flex flex-wrap items-center gap-2 mb-4">
        <input type="text" placeholder="Search by ID" value={searchId} onChange={(e) => setSearchId(e.target.value)} className="search-input" />
        <button className="btn btn-outline" onClick={handleSearchById}>Search ID</button>
      </div>

      <div className="table-container">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Name</th><th>Mobile</th><th>Phone</th><th>Email</th><th>GST No.</th><th>Tax No.</th><th>Country</th><th>State</th><th>City</th><th>Postcode</th><th>Address</th><th>Prev. Due</th>
            </tr>
          </thead>
          <tbody>
            {customers.map((c, idx) => (
              <tr key={idx}>
                <td>{c.name}</td><td>{c.mobile}</td><td>{c.phone}</td><td>{c.email}</td><td>{c.gstNumber}</td><td>{c.taxNumber}</td><td>{c.country}</td><td>{c.state}</td><td>{c.city}</td><td>{c.postcode}</td><td>{c.address}</td><td>₹{Number(c.previousDue).toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <h2 className="text-xl font-bold mb-4">Add Customer</h2>
            <form onSubmit={handleAddCustomer}>
              {["name","mobile","email","phone","gstNumber","taxNumber","country","state","city","postcode","address","previousDue"].map((field) => (
                <input key={field} name={field} placeholder={field} type={field === "previousDue" ? "number" : "text"} value={newCustomer[field]} onChange={handleInputChange} />
              ))}
              <div className="flex gap-2">
                <button type="submit" className="btn btn-primary">Save</button>
                <button type="button" className="btn btn-outline" onClick={() => setShowModal(false)}>Cancel</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
