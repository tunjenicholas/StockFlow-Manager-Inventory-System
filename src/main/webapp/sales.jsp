<%@ page import="java.util.*, com.inventory.model.Product, com.inventory.dao.ProductDAO, com.inventory.model.User" %>

<%
User user = (User) session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}

String link = "#";

if ("CLERK".equals(user.getRole())) {
    link = "clerkDashboard.jsp";
} 
else if ("ADMIN".equals(user.getRole())) {
    link = "adminDashboard.jsp";
}
%>

<%
    ProductDAO dao = new ProductDAO();
    List<Product> products = dao.getAllProducts();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Record Sale</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f4f6f9;
        }

        .card-box {
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }

        .low-stock {
            color: red;
            font-weight: bold;
        }

        .header-title {
            font-weight: 700;
        }
    </style>
</head>

<body>

<div class="container mt-4">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="header-title">Record Sale</h3>
        <div class="">
	        <a href="products" class="btn btn-outline-primary">View Products</a>
	        <a href="<%= link %>" class="btn btn-secondary">
	            Dashboard
	        </a>
        </div>

    </div>

    <div class="row">

        <!-- FORM CARD -->
        <div class="col-md-5">

            <div class="card card-box p-3">

                <h5 class="mb-3">New Sale</h5>

                <form action="sales" method="post">

                    <div class="mb-3">
                        <label class="form-label">Select Product</label>

                        <select name="productId" class="form-select" required>
                            <option value="">-- Select Product --</option>

                            <%
                                if (products != null) {
                                    for (Product p : products) {
                            %>

                            <option value="<%= p.getProductId() %>">
                                <%= p.getName() %>
                                (Stock:
                                <span class="<%= (p.getQuantity() < 10 ? "low-stock" : "") %>">
                                    <%= p.getQuantity() %>
                                </span>)
                            </option>

                            <%
                                    }
                                }
                            %>

                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Quantity</label>
                        <input type="number" name="quantity" min="1" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-success w-100">
                        Submit Sale
                    </button>

                </form>

                <!-- ALERTS -->
                <div class="mt-3">

                    <%
                        String msg = request.getParameter("msg");

                        if ("success".equals(msg)) {
                    %>

                    <div class="alert alert-success">Sale recorded successfully!</div>

                    <%
                        } else if ("outofstock".equals(msg)) {
                    %>

                    <div class="alert alert-warning">Insufficient stock!</div>

                    <%
                        } else if ("failed".equals(msg)) {
                    %>

                    <div class="alert alert-danger">Sale failed!</div>

                    <%
                        }
                    %>

                </div>

            </div>

        </div>

        <!-- INFO CARD -->
        <div class="col-md-7">

            <div class="card card-box p-3">

                <h5>Sales Info Panel</h5>

                <p>This module allows you to:</p>

                <ul>
                    <li>Record product sales</li>
                    <li>Automatically reduce stock</li>
                    <li>Track inventory movement</li>
                </ul>

                <hr>

                <p><b>Tip:</b> Products with stock below 10 appear in red.</p>

            </div>

        </div>

    </div>

</div>

</body>
</html>