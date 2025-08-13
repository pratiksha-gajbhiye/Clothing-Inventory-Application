import React, { useEffect, useState } from "react";
import {
  getAllReturns,
  getReturnById,
  createReturn,
  deleteReturn,
} from "../../services/PurchaseReturnservice";
import { getAllSuppliers } from "../../services/SupplierService";
import { getAllItems } from "../../services/ItemService";

export default function PurchaseReturnPage() {
  const [returns, setReturns] = useState([]);
  const [form, setForm] = useState({
    referenceNumber: "",
    returnDate: "",
    returnReason: "",
    paymentStatus: "UNPAID",
    tax: 0,
    netAmount: 0,
    attachmentPath: "",
    supplierId: "",
    items: [],
  });
  const [suppliers, setSuppliers] = useState([]);
  const [items, setItems] = useState([]);
  const [itemInputs, setItemInputs] = useState([{ itemId: "", quantity: 0, unitPrice: 0 }]);

  const [viewedReturn, setViewedReturn] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    const res = await getAllReturns();
    const supplierRes = await getAllSuppliers();
    const itemRes = await getAllItems();
    setReturns(res.data);
    setSuppliers(supplierRes.data);
    setItems(itemRes.data);
  };

  const handleAddItemInput = () => {
    setItemInputs([...itemInputs, { itemId: "", quantity: 0, unitPrice: 0 }]);
  };

  const handleItemChange = (index, field, value) => {
    const newItems = [...itemInputs];
    newItems[index][field] = field === "quantity" || field === "unitPrice" ? parseFloat(value) : value;
    setItemInputs(newItems);
  };

  const calculateNetAmount = () => {
    const total = itemInputs.reduce((acc, curr) => acc + curr.quantity * curr.unitPrice, 0);
    return total + parseFloat(form.tax || 0);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      ...form,
      items: itemInputs,
      netAmount: calculateNetAmount(),
    };
    await createReturn(payload);
    await fetchData();
    alert("Purchase return created!");
    setForm({ ...form, referenceNumber: "", returnDate: "", returnReason: "", attachmentPath: "", tax: 0 });
    setItemInputs([{ itemId: "", quantity: 0, unitPrice: 0 }]);
  };

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this return?")) {
      await deleteReturn(id);
      fetchData();
    }
  };

  const handleView = async (id) => {
    const res = await getReturnById(id);
    setViewedReturn(res.data);
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
    setViewedReturn(null);
  };

  return (
    <>
      <style>{`
        .form-container {
          border: 1px solid #e2e8f0;
          padding: 1.5rem;
          border-radius: 0.5rem;
          background: #ffffff;
          box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .input-row {
          display: grid;
          grid-template-columns: 1fr 1fr;
          gap: 1rem;
          margin-bottom: 1rem;
        }

        .item-row {
          display: flex;
          gap: 0.5rem;
          margin-bottom: 0.5rem;
        }

        input, select {
          padding: 0.5rem;
          border: 1px solid #cbd5e0;
          border-radius: 4px;
          width: 100%;
        }

        button {
          padding: 0.5rem 1rem;
          border: none;
          border-radius: 4px;
          cursor: pointer;
        }

        .submit-btn {
          background-color: #2563eb;
          color: white;
          margin-top: 1rem;
        }

        .add-btn {
          background: none;
          color: #2563eb;
          border: none;
          text-decoration: underline;
          cursor: pointer;
        }

        table {
          width: 100%;
          border-collapse: collapse;
          margin-top: 1.5rem;
        }

        th, td {
          padding: 0.75rem;
          border: 1px solid #e2e8f0;
          text-align: left;
        }

        th {
          background-color: #f7fafc;
        }

        .delete-btn {
          color: #dc2626;
          cursor: pointer;
        }

        .net-amount {
          margin-top: 1rem;
          font-weight: bold;
        }

        .modal {
          position: fixed;
          top: 0;
          left: 0;
          right: 0;
          bottom: 0;
          background: rgba(0, 0, 0, 0.4);
          display: flex;
          align-items: center;
          justify-content: center;
          z-index: 1000;
        }

        .modal-content {
          background: white;
          padding: 1.5rem;
          border-radius: 8px;
          width: 500px;
          max-height: 90vh;
          overflow-y: auto;
        }

        .modal-header {
          display: flex;
          justify-content: space-between;
          margin-bottom: 1rem;
        }

        .close-btn {
          background: none;
          color: #dc2626;
          font-size: 1.25rem;
          cursor: pointer;
        }

        .view-label {
          font-weight: bold;
        }
      `}</style>

      <div className="p-4">
        <h2 className="text-xl font-semibold mb-4">Purchase Returns</h2>

        <form onSubmit={handleSubmit} className="form-container">
          <div className="input-row">
            <input
              type="text"
              placeholder="Reference Number"
              value={form.referenceNumber}
              onChange={(e) => setForm({ ...form, referenceNumber: e.target.value })}
              required
            />
            <input
              type="date"
              value={form.returnDate}
              onChange={(e) => setForm({ ...form, returnDate: e.target.value })}
              required
            />
            <input
              type="text"
              placeholder="Return Reason"
              value={form.returnReason}
              onChange={(e) => setForm({ ...form, returnReason: e.target.value })}
            />
            <select
              value={form.paymentStatus}
              onChange={(e) => setForm({ ...form, paymentStatus: e.target.value })}
            >
              <option value="PAID">Paid</option>
              <option value="UNPAID">Unpaid</option>
              <option value="PARTIAL">Partial</option>
            </select>
            <input
              type="number"
              placeholder="Tax"
              value={form.tax}
              onChange={(e) => setForm({ ...form, tax: e.target.value })}
            />
            <select
              value={form.supplierId}
              onChange={(e) => setForm({ ...form, supplierId: e.target.value })}
              required
            >
              <option value="">Select Supplier</option>
              {suppliers.map((s) => (
                <option key={s.id} value={s.id}>
                  {s.name}
                </option>
              ))}
            </select>
            <input
              type="text"
              placeholder="Attachment Path"
              value={form.attachmentPath}
              onChange={(e) => setForm({ ...form, attachmentPath: e.target.value })}
            />
          </div>

          <h4 className="font-bold mt-4">Return Items</h4>
          {itemInputs.map((item, idx) => (
            <div key={idx} className="item-row">
              <select
                value={item.itemId}
                onChange={(e) => handleItemChange(idx, "itemId", e.target.value)}
                required
              >
                <option value="">Select Item</option>
                {items.map((i) => (
                  <option key={i.id} value={i.id}>
                    {i.name}
                  </option>
                ))}
              </select>
              <input
                type="number"
                placeholder="Quantity"
                value={item.quantity}
                onChange={(e) => handleItemChange(idx, "quantity", e.target.value)}
                required
              />
              <input
                type="number"
                placeholder="Unit Price"
                value={item.unitPrice}
                onChange={(e) => handleItemChange(idx, "unitPrice", e.target.value)}
                required
              />
            </div>
          ))}
          <button type="button" onClick={handleAddItemInput} className="add-btn">
            + Add Item
          </button>

          <div className="net-amount">
            Net Amount: ₹{calculateNetAmount().toFixed(2)}
          </div>

          <button type="submit" className="submit-btn">
            Submit Return
          </button>
        </form>

        <div className="mt-8">
          <h3 className="text-lg font-bold mb-2">Returns List</h3>
          <table>
            <thead>
              <tr>
                <th>Ref No</th>
                <th>Date</th>
                <th>Supplier</th>
                <th>Amount</th>
                <th>Tax</th>
                <th>Net</th>
                <th>Status</th>
                <th>Attachment</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {returns.map((r) => (
                <tr key={r.id}>
                  <td>{r.referenceNumber}</td>
                  <td>{r.returnDate}</td>
                  <td>{r.supplier?.name}</td>
                  <td>₹{r.totalAmount}</td>
                  <td>₹{r.tax}</td>
                  <td>₹{r.netAmount}</td>
                  <td>{r.paymentStatus}</td>
                  <td>
                    {r.attachmentPath && (
                      <a href={r.attachmentPath} target="_blank" rel="noreferrer" className="text-blue-500 underline">
                        View
                      </a>
                    )}
                  </td>
                  <td>
                    <button onClick={() => handleView(r.id)} className="text-blue-600 mr-2">View</button>
                    <button onClick={() => handleDelete(r.id)} className="delete-btn">Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Modal */}
        {modalOpen && viewedReturn && (
          <div className="modal" onClick={closeModal}>
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
              <div className="modal-header">
                <h3>Return Details</h3>
                <button className="close-btn" onClick={closeModal}>×</button>
              </div>
              <p><span className="view-label">Reference:</span> {viewedReturn.referenceNumber}</p>
              <p><span className="view-label">Date:</span> {viewedReturn.returnDate}</p>
              <p><span className="view-label">Supplier:</span> {viewedReturn.supplier?.name}</p>
              <p><span className="view-label">Reason:</span> {viewedReturn.returnReason}</p>
              <p><span className="view-label">Status:</span> {viewedReturn.paymentStatus}</p>
              <p><span className="view-label">Tax:</span> ₹{viewedReturn.tax}</p>
              <p><span className="view-label">Net Amount:</span> ₹{viewedReturn.netAmount}</p>
              <p><span className="view-label">Attachment:</span> <a href={viewedReturn.attachmentPath} target="_blank" rel="noreferrer">Open</a></p>

              <h4 className="mt-2">Items:</h4>
              <ul>
                {viewedReturn.items.map((item, idx) => (
                  <li key={idx}>
                    {item.item.name} - Qty: {item.quantity} × ₹{item.unitPrice}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        )}
      </div>
    </>
  );
}
