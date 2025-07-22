import React from "react";
import { Routes, Route } from "react-router-dom";
import Welcome from "./pages/Welcome";
import Footer from "./components/Footer";
import AdminDashboard from "./pages/AdminDashboard";
import ClinDashboard from "./pages/ClinDashboard";
import StaffDashboard from "./pages/StaffDashboard";
import PatientDashboard from "./pages/PatientDashboard";
import ReceptionistDashboard from "./pages/ReceptionistDashboard";
import ProtectedRoute from "./components/ProtectedRoute";
 

function App() {
  return (
    <div className="App d-flex flex-column min-vh-100">
      <Routes>
        <Route path="/" element={<Welcome />} />
        <Route path="/admindashboard" element={<AdminDashboard />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App
