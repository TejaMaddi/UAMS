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

@WebServlet("/approveRequest")
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/uams_db", "postgres", "rudra5624");
            PreparedStatement stmt = conn.prepareStatement("UPDATE requests SET status=? WHERE id=?");
            stmt.setString(1, action.equals("approve") ? "Approved" : "Rejected");
            stmt.setInt(2, requestId);
            stmt.executeUpdate();
            response.sendRedirect("pendingRequests.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("pendingRequests.jsp");
        }
    }
}
