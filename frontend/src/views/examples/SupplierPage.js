import React, { useState, useEffect } from "react";
import {
  Container, Row, Col, Card, CardHeader, CardBody,
  Form, FormGroup, Label, Input, Button, Table
} from "reactstrap";

import {
  createSupplier,
  getAllSuppliers,
  importSuppliersExcel,
  exportSuppliersExcel,
  downloadSupplierTemplate,
  uploadSupplierPhoto,
  getSuppliersWithDuePayments
} from "../../services/SupplierService";

export default function SupplierPage() {
  const [suppliers, setSuppliers] = useState([]);
  const [dueSuppliers, setDueSuppliers] = useState([]);

  const [name, setName] = useState("");
  const [mobile, setMobile] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [gstNumber, setGstNumber] = useState("");
  const [taxNumber, setTaxNumber] = useState("");
  const [country, setCountry] = useState("");
  const [stateVal, setStateVal] = useState("");
  const [city, setCity] = useState("");
  const [postcode, setPostcode] = useState("");
  const [address, setAddress] = useState("");
  const [openingBalance, setOpeningBalance] = useState("");

  const [importFile, setImportFile] = useState(null);
  const [photoFile, setPhotoFile] = useState(null);
  const [photoSupplierId, setPhotoSupplierId] = useState("");

  useEffect(() => {
    loadSuppliers();
    loadDueSuppliers();
  }, []);

  const loadSuppliers = async () => {
    const res = await getAllSuppliers();
    setSuppliers(res.data);
  };

  const loadDueSuppliers = async () => {
    const res = await getSuppliersWithDuePayments();
    setDueSuppliers(res.data);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const dto = {
      name, mobile, email, phone, gstNumber, taxNumber,
      country, state: stateVal, city, postcode, address,
      openingBalance
    };
    await createSupplier(dto);
    alert("Supplier created!");
    loadSuppliers();
    loadDueSuppliers();
  };

  const handleImport = async () => {
    if (!importFile) return alert("Please select a file!");
    await importSuppliersExcel(importFile);
    alert("Import successful!");
    loadSuppliers();
    loadDueSuppliers();
  };

  const handleExport = async () => {
    const res = await exportSuppliersExcel();
    const url = window.URL.createObjectURL(new Blob([res.data]));
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download", "suppliers.xlsx");
    document.body.appendChild(link);
    link.click();
  };

  const handleDownloadTemplate = async () => {
    const res = await downloadSupplierTemplate();
    const url = window.URL.createObjectURL(new Blob([res.data]));
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download", "supplier_template.xlsx");
    document.body.appendChild(link);
    link.click();
  };

  const handleUploadPhoto = async () => {
    if (!photoFile || !photoSupplierId) {
      alert("Supplier ID & file are required!");
      return;
    }
    await uploadSupplierPhoto(photoSupplierId, photoFile);
    alert("Photo uploaded!");
  };

  // ✅ Internal CSS
  const cardHeaderStyle = {
    background: "#f6f9fc",
    borderBottom: "1px solid #e9ecef",
  };
  const headerTitleStyle = {
    margin: 0,
    fontWeight: 600,
    color: "#32325d",
  };
  const cardStyle = {
    boxShadow: "0 0 20px rgba(0,0,0,0.05)",
    borderRadius: "0.5rem",
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
          <Card className="mb-4" style={cardStyle}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Add Supplier</h4>
            </CardHeader>
            <CardBody>
              <Form onSubmit={handleSave}>
                {[
                  { label: "Name", value: name, setter: setName },
                  { label: "Mobile", value: mobile, setter: setMobile },
                  { label: "Email", value: email, setter: setEmail },
                  { label: "Phone", value: phone, setter: setPhone },
                  { label: "GST", value: gstNumber, setter: setGstNumber },
                  { label: "TAX", value: taxNumber, setter: setTaxNumber },
                  { label: "Country", value: country, setter: setCountry },
                  { label: "State", value: stateVal, setter: setStateVal },
                  { label: "City", value: city, setter: setCity },
                  { label: "Postcode", value: postcode, setter: setPostcode },
                  { label: "Address", value: address, setter: setAddress },
                  { label: "Opening Balance", value: openingBalance, setter: setOpeningBalance },
                ].map((f, idx) => (
                  <FormGroup key={idx}>
                    <Label style={labelStyle}>{f.label}</Label>
                    <Input
                      value={f.value}
                      onChange={(e) => f.setter(e.target.value)}
                      required={f.label === "Name"}
                    />
                  </FormGroup>
                ))}
                <Button color="primary" type="submit" style={buttonStyle}>Save</Button>
              </Form>
            </CardBody>
          </Card>
        </Col>

        <Col md="6">
          <Card className="mb-4" style={cardStyle}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Import/Export</h4>
            </CardHeader>
            <CardBody>
              <FormGroup>
                <Label style={labelStyle}>Excel File</Label>
                <Input type="file" onChange={(e) => setImportFile(e.target.files[0])} />
              </FormGroup>
              <Button color="info" onClick={handleImport} style={buttonStyle}>Import Excel</Button>
              <Button color="success" onClick={handleExport} style={buttonStyle}>Export Excel</Button>
              <Button color="secondary" onClick={handleDownloadTemplate} style={buttonStyle}>Download Template</Button>
            </CardBody>
          </Card>

          <Card style={cardStyle}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Upload Supplier Photo</h4>
            </CardHeader>
            <CardBody>
              <FormGroup>
                <Label style={labelStyle}>Supplier ID</Label>
                <Input type="number" value={photoSupplierId} onChange={(e) => setPhotoSupplierId(e.target.value)} />
              </FormGroup>
              <FormGroup>
                <Label style={labelStyle}>Photo File</Label>
                <Input type="file" onChange={(e) => setPhotoFile(e.target.files[0])} />
              </FormGroup>
              <Button color="warning" onClick={handleUploadPhoto} style={buttonStyle}>Upload Photo</Button>
            </CardBody>
          </Card>
        </Col>
      </Row>

      <Row>
        <Col>
          <Card style={cardStyle}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>All Suppliers</h4>
            </CardHeader>
            <CardBody>
              <Table striped responsive>
                <thead>
                  <tr>
                    <th style={tableHeaderStyle}>ID</th>
                    <th style={tableHeaderStyle}>Name</th>
                    <th style={tableHeaderStyle}>Mobile</th>
                    <th style={tableHeaderStyle}>Email</th>
                    <th style={tableHeaderStyle}>GST</th>
                    <th style={tableHeaderStyle}>Opening Balance</th>
                  </tr>
                </thead>
                <tbody>
                  {suppliers.map(s => (
                    <tr key={s.id}>
                      <td>{s.id}</td>
                      <td>{s.name}</td>
                      <td>{s.mobile}</td>
                      <td>{s.email}</td>
                      <td>{s.gstNumber}</td>
                      <td>{s.openingBalance}</td>
                    </tr>
                  ))}
                  {suppliers.length === 0 && (
                    <tr><td colSpan="6" className="text-center">No Suppliers Found</td></tr>
                  )}
                </tbody>
              </Table>
            </CardBody>
          </Card>
        </Col>
      </Row>

      <Row>
        <Col>
          <Card style={cardStyle}>
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Suppliers With Due Payments</h4>
            </CardHeader>
            <CardBody>
              <Table striped responsive>
                <thead>
                  <tr>
                    <th style={tableHeaderStyle}>ID</th>
                    <th style={tableHeaderStyle}>Name</th>
                    <th style={tableHeaderStyle}>Mobile</th>
                    <th style={tableHeaderStyle}>Email</th>
                    <th style={tableHeaderStyle}>GST</th>
                    <th style={tableHeaderStyle}>Opening Balance</th>
                  </tr>
                </thead>
                <tbody>
                  {dueSuppliers.map(s => (
                    <tr key={s.id}>
                      <td>{s.id}</td>
                      <td>{s.name}</td>
                      <td>{s.mobile}</td>
                      <td>{s.email}</td>
                      <td>{s.gstNumber}</td>
                      <td>{s.openingBalance}</td>
                    </tr>
                  ))}
                  {dueSuppliers.length === 0 && (
                    <tr><td colSpan="6" className="text-center">No Due Payments</td></tr>
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
