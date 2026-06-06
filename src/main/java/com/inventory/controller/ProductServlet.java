package com.inventory.controller;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4393102521161143611L;
	ProductDAO dao = new ProductDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("products");
            return;
        }

        // ADD PRODUCT
        if (action.equals("add")) {

            String name = request.getParameter("name");
            int qty = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));

            Product p = new Product(name, qty, price);

            dao.addProduct(p);

            response.sendRedirect("products");
        }

        // UPDATE PRODUCT
        else if (action.equals("update")) {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int qty = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));

            Product p = new Product(name, qty, price);
            p.setProductId(id);

            boolean success = dao.updateProduct(p);

            if (success) {
                response.sendRedirect("products?msg=updated");
            } else {
                response.sendRedirect("products?msg=update_failed");
            }

            return;
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // EDIT PRODUCT
        if ("edit".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));

            Product p = dao.getProductById(id);

            request.setAttribute("product", p);

            request.getRequestDispatcher("editProduct.jsp")
                   .forward(request, response);
            return;
        }

        // DELETE PRODUCT
        if ("delete".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));

            String result = dao.deleteProduct(id);

            if ("SUCCESS".equals(result)) {
                response.sendRedirect("products?msg=deleted");
            } 
            else if ("CHILD_EXISTS".equals(result)) {
                response.sendRedirect("products?msg=blocked");
            } 
            else {
                response.sendRedirect("products?msg=error");
            }

            return; // IMPORTANT
        }

        // DEFAULT VIEW ALL PRODUCTS
        List<Product> list = dao.getAllProducts();
        request.setAttribute("products", list);

        request.getRequestDispatcher("products.jsp")
               .forward(request, response);
    }
    
    
}