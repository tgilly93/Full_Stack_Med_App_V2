import React from "react";
import { Routes, Route } from "react-router-dom";
import Welcome from "./pages/Welcome";
import Footer from "./components/Footer";
import Dashboard from "./pages/Dashboard";
 

function App() {
  return (
    <div className="App d-flex flex-column min-vh-100">
      <Routes>
        <Route path="/" element={<Welcome />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App
