import React, { useState } from "react";
import {
  createPayment,
  filterPayments as filterPaymentsAPI,
  exportCustomerLedgerExcel,
  exportCustomerLedgerPdf,
  exportSupplierLedgerExcel,
  exportSupplierLedgerPdf,
} from "../../services/PaymentService"; // ✅ full import!

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

export default function PaymentPage() {
  const [payments, setPayments] = useState([]);

  const [type, setType] = useState("IN");
  const [paymentDate, setPaymentDate] = useState("");
  const [amount, setAmount] = useState("");
  const [paymentMode, setPaymentMode] = useState("");
  const [note, setNote] = useState("");
  const [customerId, setCustomerId] = useState("");
  const [supplierId, setSupplierId] = useState("");

  const [filterType, setFilterType] = useState("");
  const [start, setStart] = useState("");
  const [end, setEnd] = useState("");

  const savePayment = async (e) => {
    e.preventDefault();
    const payload = {
      type,
      paymentDate,
      amount,
      paymentMode,
      note,
      customer: customerId ? { id: customerId } : null,
      supplier: supplierId ? { id: supplierId } : null,
    };

    await createPayment(payload);
    alert("Payment saved!");
    clearForm();
  };

  const clearForm = () => {
    setType("IN");
    setPaymentDate("");
    setAmount("");
    setPaymentMode("");
    setNote("");
    setCustomerId("");
    setSupplierId("");
  };

  const filterPayments = async () => {
    const res = await filterPaymentsAPI(filterType || null, start, end);
    setPayments(res.data);
  };

  const downloadFile = (blob, filename) => {
    const url = window.URL.createObjectURL(new Blob([blob]));
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download", filename);
    document.body.appendChild(link);
    link.click();
    link.parentNode.removeChild(link);
  };

  const handleExportCustomerExcel = async () => {
    if (!customerId) {
      alert("Enter Customer ID first!");
      return;
    }
    const res = await exportCustomerLedgerExcel(customerId);
    downloadFile(res.data, `customer_ledger_${customerId}.xlsx`);
  };

  const handleExportCustomerPdf = async () => {
    if (!customerId) {
      alert("Enter Customer ID first!");
      return;
    }
    const res = await exportCustomerLedgerPdf(customerId);
    downloadFile(res.data, `customer_ledger_${customerId}.pdf`);
  };

  const handleExportSupplierExcel = async () => {
    if (!supplierId) {
      alert("Enter Supplier ID first!");
      return;
    }
    const res = await exportSupplierLedgerExcel(supplierId);
    downloadFile(res.data, `supplier_ledger_${supplierId}.xlsx`);
  };

  const handleExportSupplierPdf = async () => {
    if (!supplierId) {
      alert("Enter Supplier ID first!");
      return;
    }
    const res = await exportSupplierLedgerPdf(supplierId);
    downloadFile(res.data, `supplier_ledger_${supplierId}.pdf`);
  };

  const cardHeaderStyle = {
    background: "#f6f9fc",
    borderBottom: "1px solid #e9ecef",
  };

  const headerTitleStyle = {
    margin: 0,
    fontWeight: 600,
    color: "#32325d",
  };

  const labelStyle = {
    fontWeight: 500,
    color: "#525f7f",
  };

  const buttonStyle = {
    borderRadius: "20px",
    padding: "8px 20px",
    marginRight: "10px",
  };

  const tableHeaderStyle = {
    background: "#f6f9fc",
    color: "#32325d",
  };

  return (
    <Container className="mt-5">
      <Row>
        <Col md="6">
          <Card className="mb-4" style={{ boxShadow: "0 0 20px rgba(0,0,0,0.05)", borderRadius: "0.5rem" }}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Add Payment</h4>
            </CardHeader>
            <CardBody>
              <Form onSubmit={savePayment}>
                <FormGroup>
                  <Label style={labelStyle}>Type</Label>
                  <Input
                    type="select"
                    value={type}
                    onChange={(e) => setType(e.target.value)}
                    required
                  >
                    <option value="IN">IN</option>
                    <option value="OUT">OUT</option>
                  </Input>
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Payment Date</Label>
                  <Input
                    type="date"
                    value={paymentDate}
                    onChange={(e) => setPaymentDate(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Amount</Label>
                  <Input
                    type="number"
                    step="0.01"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Payment Mode</Label>
                  <Input
                    type="text"
                    value={paymentMode}
                    onChange={(e) => setPaymentMode(e.target.value)}
                    placeholder="Cash, UPI, Bank Transfer..."
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Note</Label>
                  <Input
                    type="text"
                    value={note}
                    onChange={(e) => setNote(e.target.value)}
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Customer ID</Label>
                  <Input
                    type="number"
                    value={customerId}
                    onChange={(e) => setCustomerId(e.target.value)}
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Supplier ID</Label>
                  <Input
                    type="number"
                    value={supplierId}
                    onChange={(e) => setSupplierId(e.target.value)}
                  />
                </FormGroup>

                <Button color="primary" type="submit" style={buttonStyle}>
                  Save Payment
                </Button>

                {/* ✅ Ledger Export Buttons */}
                <div style={{ marginTop: "15px" }}>
                  <Button color="success" style={buttonStyle} onClick={handleExportCustomerExcel}>
                    Export Customer Ledger (Excel)
                  </Button>
                  <Button color="danger" style={buttonStyle} onClick={handleExportCustomerPdf}>
                    Export Customer Ledger (PDF)
                  </Button>
                </div>
                <div style={{ marginTop: "10px" }}>
                  <Button color="success" style={buttonStyle} onClick={handleExportSupplierExcel}>
                    Export Supplier Ledger (Excel)
                  </Button>
                  <Button color="danger" style={buttonStyle} onClick={handleExportSupplierPdf}>
                    Export Supplier Ledger (PDF)
                  </Button>
                </div>
              </Form>
            </CardBody>
          </Card>
        </Col>

        <Col md="6">
          <Card className="mb-4" style={{ boxShadow: "0 0 20px rgba(0,0,0,0.05)", borderRadius: "0.5rem" }}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Filter Payments</h4>
            </CardHeader>
            <CardBody>
              <Form inline>
                <FormGroup className="mr-2">
                  <Label style={{ ...labelStyle, marginRight: "8px" }}>Type</Label>
                  <Input
                    type="select"
                    value={filterType}
                    onChange={(e) => setFilterType(e.target.value)}
                  >
                    <option value="">All</option>
                    <option value="IN">IN</option>
                    <option value="OUT">OUT</option>
                  </Input>
                </FormGroup>
                <FormGroup className="mr-2">
                  <Label style={{ ...labelStyle, marginRight: "8px" }}>Start</Label>
                  <Input
                    type="date"
                    value={start}
                    onChange={(e) => setStart(e.target.value)}
                  />
                </FormGroup>
                <FormGroup className="mr-2">
                  <Label style={{ ...labelStyle, marginRight: "8px" }}>End</Label>
                  <Input
                    type="date"
                    value={end}
                    onChange={(e) => setEnd(e.target.value)}
                  />
                </FormGroup>
                <Button color="info" onClick={filterPayments} style={buttonStyle}>
                  Search
                </Button>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>

      <Row>
        <Col>
          <Card style={{ boxShadow: "0 0 20px rgba(0,0,0,0.05)", borderRadius: "0.5rem" }}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Payments</h4>
            </CardHeader>
            <CardBody>
              <Table striped responsive>
                <thead>
                  <tr>
                    <th style={tableHeaderStyle}>ID</th>
                    <th style={tableHeaderStyle}>Type</th>
                    <th style={tableHeaderStyle}>Date</th>
                    <th style={tableHeaderStyle}>Amount</th>
                    <th style={tableHeaderStyle}>Mode</th>
                    <th style={tableHeaderStyle}>Note</th>
                    <th style={tableHeaderStyle}>Customer ID</th>
                    <th style={tableHeaderStyle}>Supplier ID</th>
                  </tr>
                </thead>
                <tbody>
                  {payments.map((p) => (
                    <tr key={p.id}>
                      <td>{p.id}</td>
                      <td>{p.type}</td>
                      <td>{p.paymentDate}</td>
                      <td>{p.amount}</td>
                      <td>{p.paymentMode}</td>
                      <td>{p.note}</td>
                      <td>{p.customer ? p.customer.id : "-"}</td>
                      <td>{p.supplier ? p.supplier.id : "-"}</td>
                    </tr>
                  ))}
                  {payments.length === 0 && (
                    <tr>
                      <td colSpan="8" className="text-center">
                        No Payments Found
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
