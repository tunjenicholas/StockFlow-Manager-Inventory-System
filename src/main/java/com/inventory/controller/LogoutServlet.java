package com.inventory.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3309031742448355995L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
        	// destroy session
            session.invalidate();
        }

        response.sendRedirect("login.jsp");
    }
}