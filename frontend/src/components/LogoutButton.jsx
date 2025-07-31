import React from "react";
import { useDispatch } from "react-redux";
import { logout } from "../store/authSlice";
import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";

const LogoutButton = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleLogout = () => {
        dispatch(logout());
        navigate("/");
    };

    return (
        <Button variant="outline-danger" onClick={handleLogout}>
            Logout
        </Button>
    );
};

export default LogoutButton;
