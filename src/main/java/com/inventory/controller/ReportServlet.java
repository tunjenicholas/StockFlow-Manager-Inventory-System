package com.inventory.controller;

import com.inventory.dao.ReportDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ReportDAO dao = new ReportDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("totalSales", dao.getTotalSales());
        request.setAttribute("todaySales", dao.getTodaySales());
        request.setAttribute("lowStockCount", dao.getLowStockCount());
        request.setAttribute("lowStockList", dao.getLowStockProducts());

        request.getRequestDispatcher("report.jsp")
               .forward(request, response);
    }
}