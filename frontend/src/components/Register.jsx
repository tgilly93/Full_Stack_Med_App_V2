import React, { forwardRef } from "react";
import { Container, Row, Col } from "react-bootstrap";

const Register = forwardRef((props, ref) => {
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
