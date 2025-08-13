import React, { useState, useEffect } from "react";

// ✅ Import your service functions:
import {
  getAllSalesReturnItems,
  saveSalesReturnItem,
  deleteSalesReturnItem,
  getSalesReturnItemsBySalesReturnId,
} from "../../services/SalesReturnItemService";

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

export default function SalesReturnItemPage() {
  const [items, setItems] = useState([]);
  const [salesReturnId, setSalesReturnId] = useState("");
  const [itemId, setItemId] = useState("");
  const [quantity, setQuantity] = useState("");
  const [refundAmount, setRefundAmount] = useState("");
  const [filterSalesReturnId, setFilterSalesReturnId] = useState("");

  // ✅ Use service function here
  const fetchAll = async () => {
    const res = await getAllSalesReturnItems();
    setItems(res.data);
  };

  useEffect(() => {
    fetchAll();
  }, []);

  // ✅ Use service function here
  const saveItem = async (e) => {
    e.preventDefault();
    const payload = {
      salesReturn: { id: salesReturnId },
      item: { id: itemId },
      quantity,
      refundAmount,
    };
    await saveSalesReturnItem(payload);
    fetchAll();
    setSalesReturnId("");
    setItemId("");
    setQuantity("");
    setRefundAmount("");
  };

  // ✅ Use service function here
  const filterBySalesReturnId = async () => {
    if (filterSalesReturnId) {
      const res = await getSalesReturnItemsBySalesReturnId(filterSalesReturnId);
      setItems(res.data);
    } else {
      fetchAll();
    }
  };

  // ✅ Use service function here
  const deleteItem = async (id) => {
    await deleteSalesReturnItem(id);
    fetchAll();
  };

  // ✅ Same styles & JSX
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
  };

  const tableHeaderStyle = {
    background: "#f6f9fc",
    color: "#32325d",
  };

  return (
    <Container className="mt-5">
      <Row>
        <Col md="6">
          <Card
            className="mb-4"
            style={{
              boxShadow: "0 0 20px rgba(0,0,0,0.05)",
              borderRadius: "0.5rem",
            }}
          >
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Add Sales Return Item</h4>
            </CardHeader>
            <CardBody>
              <Form onSubmit={saveItem}>
                <FormGroup>
                  <Label style={labelStyle}>Sales Return ID</Label>
                  <Input
                    type="number"
                    value={salesReturnId}
                    onChange={(e) => setSalesReturnId(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Item ID</Label>
                  <Input
                    type="number"
                    value={itemId}
                    onChange={(e) => setItemId(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Quantity</Label>
                  <Input
                    type="number"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                    required
                  />
                </FormGroup>
                <FormGroup>
                  <Label style={labelStyle}>Refund Amount</Label>
                  <Input
                    type="number"
                    step="0.01"
                    value={refundAmount}
                    onChange={(e) => setRefundAmount(e.target.value)}
                    required
                  />
                </FormGroup>
                <Button color="primary" type="submit" style={buttonStyle}>
                  Save
                </Button>
              </Form>
            </CardBody>
          </Card>
        </Col>

        <Col md="6">
          <Card
            className="mb-4"
            style={{
              boxShadow: "0 0 20px rgba(0,0,0,0.05)",
              borderRadius: "0.5rem",
            }}
          >
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Filter Items by Sales Return ID</h4>
            </CardHeader>
            <CardBody>
              <Form inline>
                <FormGroup className="mr-2">
                  <Label
                    style={{ ...labelStyle, marginRight: "8px" }}
                  >
                    Sales Return ID
                  </Label>
                  <Input
                    type="number"
                    value={filterSalesReturnId}
                    onChange={(e) =>
                      setFilterSalesReturnId(e.target.value)
                    }
                  />
                </FormGroup>
                <Button
                  color="info"
                  onClick={filterBySalesReturnId}
                  style={buttonStyle}
                >
                  Search
                </Button>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>

      <Row>
        <Col>
          <Card
            style={{
              boxShadow: "0 0 20px rgba(0,0,0,0.05)",
              borderRadius: "0.5rem",
            }}
          >
            <CardHeader style={cardHeaderStyle}>
              <h4 style={headerTitleStyle}>Sales Return Items</h4>
            </CardHeader>
            <CardBody>
              <Table striped responsive>
                <thead>
                  <tr>
                    <th style={tableHeaderStyle}>ID</th>
                    <th style={tableHeaderStyle}>Sales Return ID</th>
                    <th style={tableHeaderStyle}>Item ID</th>
                    <th style={tableHeaderStyle}>Quantity</th>
                    <th style={tableHeaderStyle}>Refund Amount</th>
                    <th style={tableHeaderStyle}>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {items.map((i) => (
                    <tr key={i.id}>
                      <td>{i.id}</td>
                      <td>{i.salesReturn ? i.salesReturn.id : "-"}</td>
                      <td>{i.item ? i.item.id : "-"}</td>
                      <td>{i.quantity}</td>
                      <td>{i.refundAmount}</td>
                      <td>
                        <Button
                          color="danger"
                          size="sm"
                          style={{
                            borderRadius: "12px",
                            padding: "4px 12px",
                          }}
                          onClick={() => deleteItem(i.id)}
                        >
                          Delete
                        </Button>
                      </td>
                    </tr>
                  ))}
                  {items.length === 0 && (
                    <tr>
                      <td colSpan="6" className="text-center">
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
