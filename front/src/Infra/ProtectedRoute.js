import React from "react";
import { Navigate } from "react-router-dom";

const ProtectedRoute = ({ allowedRoles, children }) => {
    console.log(localStorage.getItem("userRole"));
    
  const userRole = localStorage.getItem("userRole"); // Recupera o role do localStorage

  if (!userRole || !allowedRoles.includes(userRole)) {
    if(userRole && (userRole === "ADMIN" || userRole === "STOKIST"))
        return <Navigate to="/back-home" replace />;

    return <Navigate to="/pagina-principal" replace />;
  }

  return children;
};

export default ProtectedRoute;
