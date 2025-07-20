import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import NavBar from "../components/Navbar";
import EditProfile from "../components/EditProfile";
import Appointment from "../components/Appointment";
import MyAppointments from "../components/MyAppointments";

function AdminDashboard() {
  return (
    <>
      <NavBar />

    <Container
      fluid
      className="min-vh-100 bg-light p-4"
    >
      <h1 className="text-center mb-4">Welcome to Your Dashboard!</h1>
      <Row>
        <Col md={6} className="mb-4">
          <EditProfile />
        </Col>
        <Col md={6} className="mb-4">
          <Appointment />
        </Col>
      </Row>

      <Row>
        <Col>
          <MyAppointments />
        </Col>
      </Row>
    </Container>
    </>
  );
}

export default AdminDashboard;
