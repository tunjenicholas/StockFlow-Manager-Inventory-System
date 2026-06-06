package com.inventory.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        String loginURI = req.getContextPath() + "/login.jsp";
        String loginServlet = req.getContextPath() + "/login";

        boolean loginRequest =
                req.getRequestURI().equals(loginURI) ||
                req.getRequestURI().equals(loginServlet);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}