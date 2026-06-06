package com.inventory.controller;

import com.inventory.dao.SalesDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/sales")
public class SalesServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5790156622111127390L;
	SalesDAO dao = new SalesDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        boolean success = dao.recordSale(productId, quantity);
        
        if (success) {
            response.sendRedirect("sales.jsp?msg=success");
        } else {
            response.sendRedirect("sales.jsp?msg=outofstock");
        }
    }
}