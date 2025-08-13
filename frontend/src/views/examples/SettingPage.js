import React, { useState, useEffect } from "react";
import {
  Container, Row, Col, Card, CardHeader, CardBody,
  Form, FormGroup, Label, Input, Button, Table
} from "reactstrap";

import {
  getProfile,
  saveProfile,
  getAllTaxes,
  saveTax,
  deleteTax,
  getAllPaymentTypes,
  changePassword
} from "../../services/SettingService";

export default function SettingPage() {
  const [profile, setProfile] = useState({
    name: "",
    address: "",
    phone: "",
    email: ""
  });
  const [taxes, setTaxes] = useState([]);
  const [paymentTypes, setPaymentTypes] = useState([]);
  const [taxName, setTaxName] = useState("");
  const [taxRate, setTaxRate] = useState("");
  const [oldPass, setOldPass] = useState("");
  const [newPass, setNewPass] = useState("");

  useEffect(() => {
    loadProfile();
    loadTaxes();
    loadPaymentTypes();
  }, []);

  const loadProfile = async () => {
    const res = await getProfile();
    setProfile(res.data);
  };

  const saveProfileHandler = async (e) => {
    e.preventDefault();
    await saveProfile(profile);
    alert("Company profile saved!");
  };

  const loadTaxes = async () => {
    const res = await getAllTaxes();
    setTaxes(res.data);
  };

  const saveTaxHandler = async (e) => {
    e.preventDefault();
    await saveTax({ name: taxName, rate: taxRate });
    alert("Tax saved!");
    setTaxName("");
    setTaxRate("");
    loadTaxes();
  };

  const deleteTaxHandler = async (id) => {
    if (window.confirm("Delete this tax?")) {
      await deleteTax(id);
      alert("Deleted!");
      loadTaxes();
    }
  };

  const loadPaymentTypes = async () => {
    const res = await getAllPaymentTypes();
    setPaymentTypes(res.data);
  };

  const changePasswordHandler = async (e) => {
    e.preventDefault();
    await changePassword({
      email: profile.email,
      oldPassword: oldPass,
      newPassword: newPass
    });
    alert("Password changed!");
    setOldPass("");
    setNewPass("");
  };

  return (
    <Container className="mt-5 setting-page">
      <style>{`
        .setting-page .card {
          box-shadow: 0 0 20px rgba(0,0,0,0.05);
          border-radius: 0.5rem;
          margin-bottom: 1.5rem;
        }

        .setting-page .card-header {
          background: #f6f9fc;
          border-bottom: 1px solid #e9ecef;
        }

        .setting-page .card-header h4 {
          margin: 0;
          font-weight: 600;
          color: #32325d;
        }

        .setting-page label {
          font-weight: 500;
          color: #525f7f;
        }

        .setting-page button {
          border-radius: 20px;
          padding: 8px 20px;
        }

        .setting-page table th {
          background: #f6f9fc;
        }

        .setting-page table td,
        .setting-page table th {
          vertical-align: middle !important;
        }

        .setting-page table {
          margin-top: 1rem;
        }
      `}</style>

      <Row>
        <Col md="6">
          <Card>
            <CardHeader><h4>Company Profile</h4></CardHeader>
            <CardBody>
              <Form onSubmit={saveProfileHandler}>
                <FormGroup>
                  <Label>Name</Label>
                  <Input
                    value={profile.name}
                    onChange={e => setProfile({ ...profile, name: e.target.value })}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>Address</Label>
                  <Input
                    value={profile.address}
                    onChange={e => setProfile({ ...profile, address: e.target.value })}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>Phone</Label>
                  <Input
                    value={profile.phone}
                    onChange={e => setProfile({ ...profile, phone: e.target.value })}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>Email</Label>
                  <Input
                    type="email"
                    value={profile.email}
                    onChange={e => setProfile({ ...profile, email: e.target.value })}
                    required
                  />
                </FormGroup>
                <Button color="primary" type="submit">Save</Button>
              </Form>
            </CardBody>
          </Card>
        </Col>

        <Col md="6">
          <Card>
            <CardHeader><h4>Change Password</h4></CardHeader>
            <CardBody>
              <Form onSubmit={changePasswordHandler}>
                <FormGroup>
                  <Label>Old Password</Label>
                  <Input
                    type="password"
                    value={oldPass}
                    onChange={e => setOldPass(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>New Password</Label>
                  <Input
                    type="password"
                    value={newPass}
                    onChange={e => setNewPass(e.target.value)}
                    required
                  />
                </FormGroup>
                <Button color="warning" type="submit">Change Password</Button>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>

      <Row>
        <Col md="6">
          <Card>
            <CardHeader><h4>Manage Taxes</h4></CardHeader>
            <CardBody>
              <Form onSubmit={saveTaxHandler}>
                <FormGroup>
                  <Label>Tax Name</Label>
                  <Input
                    value={taxName}
                    onChange={e => setTaxName(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label>Tax Rate (%)</Label>
                  <Input
                    type="number"
                    value={taxRate}
                    onChange={e => setTaxRate(e.target.value)}
                    required
                  />
                </FormGroup>
                <Button color="info" type="submit">Add Tax</Button>
              </Form>

              <Table striped responsive className="mt-3">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Rate (%)</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {taxes.map(tax => (
                    <tr key={tax.id}>
                      <td>{tax.id}</td>
                      <td>{tax.name}</td>
                      <td>{tax.rate}</td>
                      <td>
                        <Button
                          color="danger"
                          size="sm"
                          onClick={() => deleteTaxHandler(tax.id)}
                        >
                          Delete
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </CardBody>
          </Card>
        </Col>

        <Col md="6">
          <Card>
            <CardHeader><h4>Payment Types</h4></CardHeader>
            <CardBody>
              <Table striped responsive>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                  </tr>
                </thead>
                <tbody>
                  {paymentTypes.map(pt => (
                    <tr key={pt.id}>
                      <td>{pt.id}</td>
                      <td>{pt.name}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
