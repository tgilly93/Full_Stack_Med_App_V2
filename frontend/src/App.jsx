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

        <Route
          path="/dashboard-admin"
          element={
            <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
              <AdminDashboard />
            </ProtectedRoute>
          }
        />

        <Route
          path="/dashboard-clin"
          element={
            <ProtectedRoute allowedRoles={["ROLE_CLINICIAN"]}>
              <ClinDashboard />
            </ProtectedRoute>
          }
        />

        <Route
          path="/dashboard-staff"
          element={
            <ProtectedRoute allowedRoles={["ROLE_STAFF"]}>
              <StaffDashboard />
            </ProtectedRoute>
          }
        />

        <Route
          path="/dashboard-patient"
          element={
            <ProtectedRoute allowedRoles={["ROLE_PATIENT"]}>
              <PatientDashboard />
            </ProtectedRoute>
          }
        />

        <Route
          path="/dashboard-receptionist"
          element={
            <ProtectedRoute allowedRoles={["ROLE_RECEPTIONIST"]}>
              <ReceptionistDashboard />
            </ProtectedRoute>
          }
        />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
