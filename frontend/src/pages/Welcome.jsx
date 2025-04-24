import React, { useRef } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";
import Register from "../components/Register"; 
import Login from "../components/Login";

function Welcome() {
  const registerRef = useRef(null);
  const loginRef = useRef(null);

  const scrollToSection = (ref) => {
    ref.current?.scrollIntoView({ behavior: "smooth" });
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
            <Button variant="primary" onClick={() => scrollToSection(registerRef)}>
              Create an Account
            </Button>

            <div className="my-3" />

            <p>Returning User?</p>
            <Button variant="secondary" onClick={() => scrollToSection(loginRef)}>
              Login
            </Button>

          </Col>
        </Row>
      </Container>

      <Register ref={registerRef} />
      <Login ref={loginRef} />
      </>
    );  
}   

export default Welcome;