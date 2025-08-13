import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Container,
  Row,
  Col,
  Card,
  CardHeader,
  CardBody,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Table,
} from "reactstrap";

export default function SalesReturnPage() {
  const [salesReturns, setSalesReturns] = useState([]);
  const [returnInvoice, setReturnInvoice] = useState("");
  const [returnDate, setReturnDate] = useState("");
  const [amount, setAmount] = useState("");
  const [originalSaleId, setOriginalSaleId] = useState("");
  const [from, setFrom] = useState("");
  const [to, setTo] = useState("");

  const fetchAll = async () => {
    const res = await axios.get("/api/sales-return/all");
    setSalesReturns(res.data);
  };

  useEffect(() => {
    fetchAll();
  }, []);

  const saveSalesReturn = async (e) => {
    e.preventDefault();
    const payload = {
      returnInvoice,
      returnDate: returnDate + "T00:00:00",
      amount,
      originalSale: { id: originalSaleId },
      items: [],
    };
    await axios.post("/api/sales-return/save", payload);
    fetchAll();
    setReturnInvoice("");
    setReturnDate("");
    setAmount("");
    setOriginalSaleId("");
  };

  const searchBetween = async () => {
    const res = await axios.get(
      `/api/sales-return/between?from=${from}&to=${to}`
    );
    setSalesReturns(res.data);
  };

  return (
    <Container className="mt-5 sales-return-container">
      {/* INTERNAL CSS */}
      <style>{`
        .sales-return-container {
          margin-top: 2rem;
        }
        .sales-return-card {
          box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
          border-radius: 0.5rem;
        }
        .sales-return-card-header {
          background: #f6f9fc;
          border-bottom: 1px solid #e9ecef;
        }
        .sales-return-card-header h4 {
          margin: 0;
          font-weight: 600;
          color: #32325d;
        }
        .sales-return-form label {
          font-weight: 500;
          color: #525f7f;
        }
        .sales-return-table th {
          background: #f6f9fc;
          color: #32325d;
        }
        .sales-return-table td {
          vertical-align: middle;
        }
        .btn-primary,
        .btn-info {
          border-radius: 20px;
          padding: 8px 20px;
        }
        .btn-primary:hover,
        .btn-info:hover {
          opacity: 0.9;
        }
      `}</style>

      <Row>
        <Col md="6">
          <Card className="sales-return-card">
            <CardHeader className="sales-return-card-header">
              <h4>Create Sales Return</h4>
            </CardHeader>
            <CardBody>
              <Form onSubmit={saveSalesReturn} className="sales-return-form">
                <FormGroup>
                  <Label>Return Invoice</Label>
                  <Input
                    type="text"
                    value={returnInvoice}
                    onChange={(e) => setReturnInvoice(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>Return Date</Label>
                  <Input
                    type="date"
                    value={returnDate}
                    onChange={(e) => setReturnDate(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>Amount</Label>
                  <Input
                    type="number"
                    step="0.01"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>Original Sale ID</Label>
                  <Input
                    type="number"
                    value={originalSaleId}
                    onChange={(e) => setOriginalSaleId(e.target.value)}
                    required
                  />
                </FormGroup>
                <Button color="primary" type="submit">
                  Save
                </Button>
              </Form>
            </CardBody>
          </Card>
        </Col>

        <Col md="6">
          <Card className="sales-return-card">
            <CardHeader className="sales-return-card-header">
              <h4>Filter Sales Returns</h4>
            </CardHeader>
            <CardBody>
              <Form inline>
                <FormGroup className="mr-2">
                  <Label className="mr-2">From</Label>
                  <Input
                    type="date"
                    value={from}
                    onChange={(e) => setFrom(e.target.value)}
                  />
                </FormGroup>
                <FormGroup className="mr-2">
                  <Label className="mr-2">To</Label>
                  <Input
                    type="date"
                    value={to}
                    onChange={(e) => setTo(e.target.value)}
                  />
                </FormGroup>
                <Button color="info" onClick={searchBetween}>
                  Search
                </Button>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>

      <Row className="mt-4">
        <Col>
          <Card>
            <CardHeader className="sales-return-card-header">
              <h4>Sales Returns List</h4>
            </CardHeader>
            <CardBody>
              <Table striped responsive className="sales-return-table">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Return Invoice</th>
                    <th>Return Date</th>
                    <th>Amount</th>
                    <th>Original Sale ID</th>
                  </tr>
                </thead>
                <tbody>
                  {salesReturns.map((s) => (
                    <tr key={s.id}>
                      <td>{s.id}</td>
                      <td>{s.returnInvoice}</td>
                      <td>{s.returnDate}</td>
                      <td>{s.amount}</td>
                      <td>{s.originalSale ? s.originalSale.id : "-"}</td>
                    </tr>
                  ))}
                  {salesReturns.length === 0 && (
                    <tr>
                      <td colSpan="5" className="text-center">
                        No Records Found
                      </td>
                    </tr>
                  )}
                </tbody>
              </Table>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
