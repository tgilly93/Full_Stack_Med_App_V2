import React, { forwardRef, useState } from "react";
import { Container, Row, Col, Form, Button, Alert } from "react-bootstrap";

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

    const payload = {
      ...formData,
      primaryOffice: parseInt(formData.primaryOffice),
    };

    try {
      const response = await fetch("http://localhost:9000/register", {
  return (
    <section id="register" className="vh-100 bg-white" ref={ref}>
      <Container className="d-flex justify-content-center align-items-center h-100">
        <Row>
          <Col className="text-center">
            <h1>Create an Account</h1>
            <p>This is the register component!</p>
          </Col>
        </Row>
      </Container>
    </section>
  );
});

export default Register;
