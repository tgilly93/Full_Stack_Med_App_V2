import React, { forwardRef, useState } from "react";
import { Container, Row, Col, Form, Button, Alert } from "react-bootstrap";
import { loginUser } from "../services/authService";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { login } from "../store/authSlice";

const Login = forwardRef((props, ref) => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const [message, setMessage] = useState("");
  const [error, setError] = useState(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError(false);

    try {
      const { token, user } = await loginUser(formData);
      dispatch(login({ token, user }));

      switch (user.role) {
        case "ROLE_ADMIN":
          navigate("/dashboard-admin");
          break;
        case "ROLE_CLINICIAN":
          navigate("/dashboard-clin");
          break;
        case "ROLE_STAFF":
          navigate("/dashboard-staff");
          break;
        case "ROLE_PATIENT":
          navigate("/dashboard-patient");
          break;
        case "ROLE_RECEPTIONIST":
          navigate("/dashboard-receptionist");
          break;
        default:
          navigate("/");
      }
    } catch (err) {
      console.error("Login failed:", err);
      setError(true);
      setMessage("Login failed. Invalid username or password.");
    }
  };

  return (
    <section id="login" className="vh-100 bg-light" ref={ref}>
      <Container className="d-flex justify-content-center align-items-center h-100">
        <Row>
          <Col className="text-center">
            <h1>Login</h1>

            {message && (
              <Alert variant={error ? "danger" : "success"}>{message}</Alert>
            )}

            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter Username"
                  name="username"
                  value={formData.username}
                  onChange={handleChange}
                  required
                />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  required
                />
              </Form.Group>

              <Button variant="primary" type="submit" className="w-100">
                Submit
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </section>
  );
});

export default Login;
