import React from "react";
import { Container, Row, Col } from "react-bootstrap";

function Register() {
  return (
    <section id="register" className="vh-100 bg-white">
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
}

export default Register;
