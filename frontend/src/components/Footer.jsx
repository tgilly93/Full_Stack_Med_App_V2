import React from "react";
import { Container, Row, Col } from "react-bootstrap";

function Footer() {
  return (
    <footer
      className="footer mt-auto py-3 bg-body-tertiary"
      style={{ position: "relative", bottom: 0, width: "100%" }}
    >
      <Container>
        <Row className="text-center">
          <Col>
            <p className="mb-0 text-body-secondary py-2">© 2025 Terry Gilmore Jr. | All Rights Reserved.</p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
}


export default Footer;
