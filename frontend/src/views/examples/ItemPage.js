import React, { useState, useEffect } from "react";
import {
  getAllItems,
  createItem,
  updateItem,
  deleteItem,
  importItems,
} from "../../services/ItemService";

export default function ItemPage() {
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({
    id: "",
    name: "",
    skuCode: "",
    barcode: "",
    size: "",
    color: "",
    stock: 0,
    costPrice: 0,
    sellingPrice: 0,
    gstPercentage: 0,
    brandId: "",
    categoryId: "",
  });
  const [file, setFile] = useState(null);
  const [brandId, setBrandId] = useState("");
  const [categoryId, setCategoryId] = useState("");

  useEffect(() => {
    loadItems();
  }, []);

  const loadItems = async () => {
    try {
      const res = await getAllItems();
      setItems(res.data);
    } catch (error) {
      console.error("Error loading items:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (form.id) {
        await updateItem(form.id, form);
      } else {
        await createItem(form);
      }
      loadItems();
      setForm({
        id: "",
        name: "",
        skuCode: "",
        barcode: "",
        size: "",
        color: "",
        stock: 0,
        costPrice: 0,
        sellingPrice: 0,
        gstPercentage: 0,
        brandId: "",
        categoryId: "",
      });
    } catch (error) {
      console.error("Error saving item:", error);
    }
  };

  const handleEdit = (item) => {
    setForm(item);
  };

  const handleDelete = async (id) => {
    if (window.confirm("Delete this item?")) {
      try {
        await deleteItem(id);
        loadItems();
      } catch (error) {
        console.error("Error deleting item:", error);
      }
    }
  };

  const handleFileUpload = async (e) => {
    e.preventDefault();
    if (!file || !brandId || !categoryId) {
      alert("Please select a file and IDs.");
      return;
    }
    const formData = new FormData();
    formData.append("file", file);
    formData.append("brandId", brandId);
    formData.append("categoryId", categoryId);

    try {
      await importItems(formData);
      alert("Import successful!");
      loadItems();
    } catch (err) {
      console.error(err);
      alert("Import failed.");
    }
  };

  return (
    <div className="container mt-5 item-page">
     <style>
  {`
    .item-page {
      font-family: 'Segoe UI', 'Roboto', sans-serif;
    }
    .item-page .container {
      max-width: 1200px;
    }
    .item-page .card {
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }
    .item-page .card-header {
      background: #5e72e4;
      color: #fff;
      padding: 1rem 1.25rem;
    }
    .item-page .card-body {
      background: #fff;
    }
    .item-page .btn {
      border-radius: 4px;
    }
    .item-page .btn-primary {
      background-color: #5e72e4;
      border: none;
    }
    .item-page .btn-info {
      background-color: #5e72e4;
      border: none;
    }
    .item-page .btn-danger {
      background-color: #dc3545;
      border: none;
    }
    .item-page .btn-success {
      background-color: #198754;
      border: none;
    }
    .item-page .table thead {
      background-color: #e9ecef;
    }
    .item-page .table tbody tr:hover {
      background-color: #f0f8ff;
    }
    .item-page .form-label {
      font-weight: 600;
    }
    .item-page input.form-control {
      border-radius: 4px;
    }
    .item-page h4 {
      margin: 0;
    }
    .item-page .mt-5 {
      margin-top: 3rem;
    }
    .item-page .mb-4 {
      margin-bottom: 2rem;
    }
    .item-page .form-card {
      max-width: 800px;
      margin-left: 0;  /* align left */
      margin-right: auto;
    }
  `}
</style>


      <div className="card mb-4 form-card">
        <div className="card-header">
          <h4 className="mb-0">Add / Edit Item</h4>
        </div>
        <div className="card-body">
          <form onSubmit={handleSubmit} className="row g-3">
            {[
              ["Name", "name"],
              ["SKU Code", "skuCode"],
              ["Barcode", "barcode"],
              ["Size", "size"],
              ["Color", "color"],
              ["Stock", "stock", "number"],
              ["Cost Price", "costPrice", "number"],
              ["Selling Price", "sellingPrice", "number"],
              ["GST %", "gstPercentage", "number"],
              ["Brand ID", "brandId"],
              ["Category ID", "categoryId"],
            ].map(([label, key, type = "text"], i) => (
              <div className="col-md-6" key={i}>
                <label className="form-label">{label}</label>
                <input
                  type={type}
                  className="form-control"
                  value={form[key]}
                  onChange={(e) =>
                    setForm({
                      ...form,
                      [key]:
                        type === "number"
                          ? Number(e.target.value)
                          : e.target.value,
                    })
                  }
                  required={key === "name"}
                />
              </div>
            ))}

            <div className="col-12">
              <button type="submit" className="btn btn-primary">
                {form.id ? "Update Item" : "Add Item"}
              </button>
            </div>
          </form>
        </div>
      </div>

      <div className="card mb-4 form-card">
        <div className="card-header">
          <h4 className="mb-0">Import Items (Excel)</h4>
        </div>
        <div className="card-body">
          <form onSubmit={handleFileUpload} className="row g-3">
            <div className="col-md-6">
              <input
                type="file"
                className="form-control"
                onChange={(e) => setFile(e.target.files[0])}
              />
            </div>
            <div className="col-md-3">
              <input
                placeholder="Brand ID"
                className="form-control"
                value={brandId}
                onChange={(e) => setBrandId(e.target.value)}
              />
            </div>
            <div className="col-md-3">
              <input
                placeholder="Category ID"
                className="form-control"
                value={categoryId}
                onChange={(e) => setCategoryId(e.target.value)}
              />
            </div>
            <div className="col-md-12">
              <button type="submit" className="btn btn-success w-100">
                Import
              </button>
            </div>
          </form>
        </div>
      </div>

      <div className="card">
        <div className="card-header">
          <h4 className="mb-0">Items List</h4>
        </div>
        <div className="card-body table-responsive">
          <table className="table table-hover align-middle">
            <thead className="table-light">
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>SKU</th>
                <th>Barcode</th>
                <th>Size</th>
                <th>Color</th>
                <th>Stock</th>
                <th>Cost</th>
                <th>Selling</th>
                <th>GST%</th>
                <th>Brand ID</th>
                <th>Category ID</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {items.map((i) => (
                <tr key={i.id}>
                  <td>{i.id}</td>
                  <td>{i.name}</td>
                  <td>{i.skuCode}</td>
                  <td>{i.barcode}</td>
                  <td>{i.size}</td>
                  <td>{i.color}</td>
                  <td>{i.stock}</td>
                  <td>{i.costPrice}</td>
                  <td>{i.sellingPrice}</td>
                  <td>{i.gstPercentage}</td>
                  <td>{i.brandId}</td>
                  <td>{i.categoryId}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-info me-2"
                      onClick={() => handleEdit(i)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-sm btn-danger"
                      onClick={() => handleDelete(i.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
              {items.length === 0 && (
                <tr>
                  <td colSpan="13" className="text-center">
                    No items found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
