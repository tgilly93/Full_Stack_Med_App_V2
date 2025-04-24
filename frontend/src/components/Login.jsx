import React, { forwardRef } from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";

const Login = forwardRef((props, ref) => {
    return (
        <section id="login" className="vh-100 bg-light" ref={ref}>
            <Container className="d-flex justify-content-center align-items-center h-100">
                <Row>
                    <Col className="text-center">
                        <h1>Login</h1>
                        <Form>
                            <Form.Group controlId="formBasicEmail">
                                <Form.Label>Email address</Form.Label>
                                <Form.Control type="email" placeholder="Enter email" />
                            </Form.Group>

                            <Form.Group controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Password" />
                            </Form.Group>

                            <Button variant="primary" type="submit">
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