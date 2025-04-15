import React from "react";
import { Routes, Route } from "react-router-dom";
import Welcome from "./pages/Welcome";
import NavBar from "./components/NavBar";
 



function App() {
  return (
    <div className="App">
      <NavBar />
      <Routes>
        <Route path="/" element={<Welcome />} />
      </Routes>
    </div>
  );
}

export default App
