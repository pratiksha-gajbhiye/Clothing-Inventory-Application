import React, { useEffect, useState } from "react";
import ProfileService from "../../services/ProfileService";

import {
  Button,
  Card,
  CardHeader,
  CardBody,
  FormGroup,
  Form,
  Input,
  Container,
  Row,
  Col,
} from "reactstrap";

import UserHeader from "components/Headers/UserHeader.js";

const ProfilePage = () => {
  const [profile, setProfile] = useState({
    username: "",
    email: "",
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    country: "",
    postalCode: "",
    aboutMe: "",
  });

  const [editMode, setEditMode] = useState(false);
  const userId = 1; // TODO: Replace with real user ID or auth context

  useEffect(() => {
    ProfileService.getProfile(userId)
      .then((res) => {
        setProfile(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, [userId]);

  const handleChange = (e) => {
    setProfile({
      ...profile,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    ProfileService.updateProfile(userId, profile)
      .then(() => {
        alert("Profile updated successfully!");
        setEditMode(false);
      })
      .catch((err) => {
        console.error(err);
        alert("Update failed!");
      });
  };

  console.log("Edit mode:", editMode);

  return (
    <>
      <UserHeader />
      <Container className="mt--7" fluid>
        <Row>
          <Col className="order-xl-1" xl="8">
            <Card className="bg-secondary shadow">
              <CardHeader className="bg-white border-0">
                <Row className="align-items-center">
                  <Col xs="8">
                    <h3 className="mb-0">My account</h3>
                  </Col>
                  <Col className="text-right">
                    <Button
                      color={editMode ? "secondary" : "info"}
                      type="button"
                      onClick={() => {
                        console.log("Toggling edit mode");
                        setEditMode(!editMode);
                      }}
                    >
                      {editMode ? "Cancel" : "Edit"}
                    </Button>
                  </Col>
                </Row>
              </CardHeader>
              <CardBody>
                <Form onSubmit={handleSubmit}>
                  <h6 className="heading-small text-muted mb-4">
                    User information
                  </h6>
                  <div className="pl-lg-4">
                    <Row>
                      <Col lg="6">
                        <FormGroup>
                          <label htmlFor="input-username">Username</label>
                          <Input
                            name="username"
                            id="input-username"
                            placeholder="Username"
                            type="text"
                            value={profile.username}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                      <Col lg="6">
                        <FormGroup>
                          <label htmlFor="input-email">Email address</label>
                          <Input
                            name="email"
                            id="input-email"
                            placeholder="Email"
                            type="email"
                            value={profile.email}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="6">
                        <FormGroup>
                          <label htmlFor="input-first-name">First name</label>
                          <Input
                            name="firstName"
                            id="input-first-name"
                            placeholder="First name"
                            type="text"
                            value={profile.firstName}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                      <Col lg="6">
                        <FormGroup>
                          <label htmlFor="input-last-name">Last name</label>
                          <Input
                            name="lastName"
                            id="input-last-name"
                            placeholder="Last name"
                            type="text"
                            value={profile.lastName}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                  </div>

                  <hr className="my-4" />
                  <h6 className="heading-small text-muted mb-4">
                    Contact information
                  </h6>
                  <div className="pl-lg-4">
                    <Row>
                      <Col md="12">
                        <FormGroup>
                          <label htmlFor="input-address">Address</label>
                          <Input
                            name="address"
                            id="input-address"
                            placeholder="Home Address"
                            type="text"
                            value={profile.address}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                    <Row>
                      <Col lg="4">
                        <FormGroup>
                          <label htmlFor="input-city">City</label>
                          <Input
                            name="city"
                            id="input-city"
                            placeholder="City"
                            type="text"
                            value={profile.city}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                      <Col lg="4">
                        <FormGroup>
                          <label htmlFor="input-country">Country</label>
                          <Input
                            name="country"
                            id="input-country"
                            placeholder="Country"
                            type="text"
                            value={profile.country}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                      <Col lg="4">
                        <FormGroup>
                          <label htmlFor="input-postal-code">Postal code</label>
                          <Input
                            name="postalCode"
                            id="input-postal-code"
                            placeholder="Postal code"
                            type="text"
                            value={profile.postalCode}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </FormGroup>
                      </Col>
                    </Row>
                  </div>

                  <hr className="my-4" />
                  <h6 className="heading-small text-muted mb-4">About me</h6>
                  <div className="pl-lg-4">
                    <FormGroup>
                      <label>About Me</label>
                      <Input
                        name="aboutMe"
                        placeholder="A few words about you ..."
                        rows="4"
                        type="textarea"
                        value={profile.aboutMe}
                        onChange={handleChange}
                        disabled={!editMode}
                      />
                    </FormGroup>
                  </div>

                  {editMode && (
                    <Button color="primary" type="submit">
                      Save Profile
                    </Button>
                  )}
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default ProfilePage;
