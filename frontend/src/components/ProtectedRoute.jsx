import React from "react";
import { Navigate } from "react-router-dom";
import { getUser } from "../services/authService";

const ProtectedRoute = ({ allowedRoles, children }) => {
    const user = getUser();
    
    if (!user) {
        return <Navigate to="/" replace />;
    }

    if (!allowedRoles.includes(user.role)) {
        return <Navigate to="/" replace />;
    }

    return children;
};

export default ProtectedRoute;