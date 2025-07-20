import React from "react";
import { Routes, Route } from "react-router-dom";
import Welcome from "./pages/Welcome";
import Footer from "./components/Footer";
import AdminDashboard from "./pages/AdminDashboard";
 

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
