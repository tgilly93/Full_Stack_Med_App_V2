import React from "react";
import { Routes, Route } from "react-router-dom";
import Welcome from "./pages/Welcome";
import NavBar from "./components/NavBar";
import Footer from "./components/Footer";
 

function App() {
  return (
    <div className="App d-flex flex-column min-vh-100">
      <NavBar />
      <Routes>
        <Route path="/" element={<Welcome />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App
