import React, { forwardRef, useState } from "react";
import { Container, Row, Col, Form, Button, Alert } from "react-bootstrap";
import { registerUser } from "../services/authService";

const Register = forwardRef((props, ref) => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    confirmPassword: "",
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    phoneNumber: "",
    address: "",
    city: "",
    stateCode: "",
    ZIP: "",
    primaryOffice: "",
    role: "ROLE_PATIENT", // add dynamic role selection later
  });

  const [message, setMessage] = useState("");
  const [error, setError] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(false);
    setMessage("");

    const payload = {
      ...formData,
      primaryOffice: parseInt(formData.primaryOffice),
    };

    try {
      await registerUser(payload);
      setMessage("Registration successful! You can now log in.");
      setFormData({
        username: "",
        password: "",
        confirmPassword: "",
        firstName: "",
        lastName: "",
        dateOfBirth: "",
        phoneNumber: "",
        address: "",
        city: "",
        stateCode: "",
        ZIP: "",
        primaryOffice: "",
        role: "ROLE_PATIENT",
      });
    } catch (err) {
      console.error("Registration error:", err);
      setError(true);
      setMessage("Registration failed. Please try again.");
    }
  };

  return (
    <section id="register" className="vh-100 bg-white" ref={ref}>
      <Container className="d-flex justify-content-center align-items-center h-100">
        <Row>
          <Col className="text-center">
            <h2 className="text-center mb-4">Create an Account</h2>

            {message && (
              <Alert variant={error ? "danger" : "success"}>
                {message}
              </Alert>
            )}

            <Form onSubmit={handleSubmit}>
              <Row>
                <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="text"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Phone Number</Form.Label>
                  <Form.Control
                    type="text"
                    name="phoneNumber"
                    value={formData.phoneNumber}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
            </Row>

            <Row>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Confirm Password</Form.Label>
                  <Form.Control
                    type="password"
                    name="confirmpassword"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
            </Row>

            <Row>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>First Name</Form.Label>
                  <Form.Control
                    type="text"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Last Name</Form.Label>
                  <Form.Control
                    type="text"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col> 
            </Row>

            <Form.Group className="mb-3">
              <Form.Label>Date of Birth</Form.Label>
              <Form.Control
                type="date"
                name="dateOfBirth"
                value={formData.dateOfBirth}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Address</Form.Label>
              <Form.Control
                type="text"
                name="address"
                value={formData.address}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Row>
              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>City</Form.Label>
                  <Form.Control
                    type="text"
                    name="city"
                    value={formData.city}
                    onChange={handleChange}
                    required
                    />
                </Form.Group>
              </Col>

              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>State</Form.Label>
                  <Form.Control
                    type="text"
                    name="stateCode"
                    value={formData.stateCode}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>

              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>ZIP</Form.Label>
                  <Form.Control
                    type="text"
                    name="ZIP"
                    value={formData.ZIP}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
            </Row>

            <Form.Group className="mb-3">
              <Form.Label>Primary Office</Form.Label>
              <Form.Control
                type="number"
                name="primaryOffice"
                value={formData.primaryOffice}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Button
              type="submit"
              variant="primary"
              className="w-100">
              Register
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  </section>
);
});

export default Register;
