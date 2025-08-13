import React, { useState, useEffect } from "react";
import {
  Container, Row, Col, Card, CardHeader, CardBody,
  Form, FormGroup, Label, Input, Button, Table
} from "reactstrap";

// ✅ Use your WarehouseService instead of raw axios
import {
  getAllWarehouses,
  createWarehouse,
  deleteWarehouse,
  transferStock
} from "../../services/WarehouseService";

export default function WarehousePage() {
  const [warehouses, setWarehouses] = useState([]);
  const [name, setName] = useState("");
  const [location, setLocation] = useState("");
  const [fromId, setFromId] = useState("");
  const [toId, setToId] = useState("");
  const [productId, setProductId] = useState("");
  const [quantity, setQuantity] = useState("");

  useEffect(() => {
    loadWarehouses();
  }, []);

  const loadWarehouses = async () => {
    const res = await getAllWarehouses();
    setWarehouses(res.data);
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    const payload = { name, location, active: true };
    await createWarehouse(payload);
    alert("Warehouse created!");
    setName("");
    setLocation("");
    loadWarehouses();
  };

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure to delete this warehouse?")) {
      await deleteWarehouse(id);
      alert("Deleted");
      loadWarehouses();
    }
  };

  const handleTransfer = async () => {
    if (!fromId || !toId || !productId || !quantity) {
      alert("All fields are required for transfer");
      return;
    }

    const dto = {
      fromWarehouseId: fromId,
      toWarehouseId: toId,
      productId: productId,
      quantity: quantity
    };

    await transferStock(dto);
    alert("Stock transferred successfully");
    setFromId("");
    setToId("");
    setProductId("");
    setQuantity("");
  };

  return (
    <Container className="mt-5 warehouse-page">
      <style>{`
        .warehouse-page .card {
          box-shadow: 0 0 20px rgba(0,0,0,0.05);
          border-radius: 0.5rem;
        }

        .warehouse-page .card-header {
          background: #f6f9fc;
          border-bottom: 1px solid #e9ecef;
        }

        .warehouse-page .card-header h4 {
          margin: 0;
          font-weight: 600;
          color: #32325d;
        }

        .warehouse-page label {
          font-weight: 500;
          color: #525f7f;
        }

        .warehouse-page button {
          border-radius: 20px;
          padding: 8px 20px;
        }

        .warehouse-page table th {
          background: #f6f9fc;
        }

        .warehouse-page table td,
        .warehouse-page table th {
          vertical-align: middle !important;
        }
      `}</style>

      <Row>
        <Col md="6">
          <Card className="mb-4">
            <CardHeader><h4>Add Warehouse</h4></CardHeader>
            <CardBody>
              <Form onSubmit={handleCreate}>
                <FormGroup>
                  <Label>Name</Label>
                  <Input value={name} onChange={(e) => setName(e.target.value)} required />
                </FormGroup>
                <FormGroup>
                  <Label>Location</Label>
                  <Input value={location} onChange={(e) => setLocation(e.target.value)} required />
                </FormGroup>
                <Button color="primary" type="submit">Add</Button>
              </Form>
            </CardBody>
          </Card>
        </Col>

        <Col md="6">
          <Card className="mb-4">
            <CardHeader><h4>Transfer Stock</h4></CardHeader>
            <CardBody>
              <Form>
                <FormGroup>
                  <Label>From Warehouse ID</Label>
                  <Input type="number" value={fromId} onChange={(e) => setFromId(e.target.value)} required />
                </FormGroup>
                <FormGroup>
                  <Label>To Warehouse ID</Label>
                  <Input type="number" value={toId} onChange={(e) => setToId(e.target.value)} required />
                </FormGroup>
                <FormGroup>
                  <Label>Product ID</Label>
                  <Input type="number" value={productId} onChange={(e) => setProductId(e.target.value)} required />
                </FormGroup>
                <FormGroup>
                  <Label>Quantity</Label>
                  <Input type="number" value={quantity} onChange={(e) => setQuantity(e.target.value)} required />
                </FormGroup>
                <Button color="success" type="button" onClick={handleTransfer}>Transfer</Button>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>

      <Row>
        <Col>
          <Card>
            <CardHeader><h4>Warehouses</h4></CardHeader>
            <CardBody>
              <Table striped responsive>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Active</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {warehouses.map(w => (
                    <tr key={w.id}>
                      <td>{w.id}</td>
                      <td>{w.name}</td>
                      <td>{w.location}</td>
                      <td>{w.active ? "Yes" : "No"}</td>
                      <td>
                        <Button color="danger" size="sm" onClick={() => handleDelete(w.id)}>Delete</Button>
                      </td>
                    </tr>
                  ))}
                  {warehouses.length === 0 && <tr><td colSpan="5" className="text-center">No Warehouses Found</td></tr>}
                </tbody>
              </Table>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
