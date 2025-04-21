import React, { useRef } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";
import Register from "../components/Register"; // Import the Register component

function Welcome() {
  const registerRef = useRef(null);
  const scrollToRegister = () => {
    registerRef.current?.scrollIntoView({ behavior: "smooth" });
  };

    return (
      <>
      <Container
        fluid
        className="min-vh-100 d-flex justify-content-center align-items-center bg-light"
      >
        <Row>
          <Col className="text-center">
            <h1>This is the Welcome Page!</h1>
            <p>Welcome to my fullstack application!</p>
            <Button variant="primary" onClick={scrollToRegister}>
              Create an Account
            </Button>
          </Col>
        </Row>
      </Container>

      <div ref={registerRef}>
        <Register />
      </div>
      </>
    );  
}   

export default Welcome;