import React from "react";
import { Container, Row, Col } from "react-bootstrap";

function Welcome() {
    return (
      <Container
        fluid
        className="vh-100 d-flex justify-content-center align-items-center bg-light"
      >
        <Row>
          <Col className="text-center">
            <h1>This is the Welcome Page!</h1>
            <p>Welcome to my fullstack application!</p>
          </Col>
        </Row>
      </Container>
    );  
}   

export default Welcome;