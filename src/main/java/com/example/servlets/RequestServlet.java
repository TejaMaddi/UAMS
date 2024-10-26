package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/requestAccess")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from session
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve form parameters
        String softwareIdStr = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        // Validate input
        if (softwareIdStr == null || accessType == null || reason == null) {
            response.sendRedirect("requestAccess.jsp?error=InvalidInput");
            return;
        }

        int softwareId;
        try {
            softwareId = Integer.parseInt(softwareIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("requestAccess.jsp?error=InvalidSoftwareId");
            return;
        }

        // Database connection and insert request
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/your_db", "your_user", "your_password")) {
            String sql = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, softwareId);
                stmt.setString(3, accessType);
                stmt.setString(4, reason);
                stmt.executeUpdate();
            }
            response.sendRedirect("requestAccess.jsp?success=RequestSubmitted");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("requestAccess.jsp?error=DatabaseError");
        }
    }
}
