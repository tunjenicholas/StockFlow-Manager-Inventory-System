package com.inventory.controller;

import com.inventory.dao.UserDAO;
import com.inventory.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6642695399141327165L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.validateUser(username, password);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // ROLE-BASED REDIRECT
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("adminDashboard.jsp");
            } else {
                response.sendRedirect("clerkDashboard.jsp");
            }

        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}