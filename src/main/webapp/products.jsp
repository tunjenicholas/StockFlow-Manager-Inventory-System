<%@ page import="java.util.*, com.inventory.model.Product, com.inventory.model.User" %>

<%
User user = (User) session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}

String link = "#";
String role = (user != null) ? user.getRole() : "";

if ("CLERK".equals(user.getRole())) {
    link = "clerkDashboard.jsp";
} 
else if ("ADMIN".equals(user.getRole())) {
    link = "adminDashboard.jsp";
}
%>

<%
List<Product> products = (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
    background:#f4f6f9;
}

.card{
    border:none;
    border-radius:15px;
}

.low-stock{
    background:#fff5f5;
}

.page-title{
    font-weight:700;
}

</style>

</head>

<body>

	<%
	String msg = request.getParameter("msg");
	
	if ("updated".equals(msg)) {
	%>
	<div class="alert alert-success">
	    Product updated successfully!
	</div>
	<%
	} else if ("update_failed".equals(msg)) {
	%>
	<div class="alert alert-danger">
	    Failed to update product!
	</div>
	<%
	} else if ("deleted".equals(msg)) {
	%>
	<div class="alert alert-success">
	    Product deleted successfully!
	</div>
	<%
	} else if ("blocked".equals(msg)) {
	%>
	<div class="alert alert-warning">
	    Cannot delete product: It is used in sales records!
	</div>
	<%
	}
	%>

<div class="container mt-4">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2 class="page-title">
            Product Management
        </h2>

        <a href="<%= link %>" class="btn btn-secondary">
            Dashboard
        </a>

    </div>

    <!-- ADD PRODUCT CARD -->

    <div class="card shadow-sm mb-4">

	<% if("ADMIN".equals(role)) { %>
        <div class="card-body">

            <h5 class="mb-3">
                Add New Product
            </h5>
            <form action="products" method="post">

                <input type="hidden" name="action" value="add">

                <div class="row g-3">

                    <div class="col-md-4">
                        <input
                            type="text"
                            name="name"
                            class="form-control"
                            placeholder="Product Name"
                            required>
                    </div>

                    <div class="col-md-3">
                        <input
                            type="number"
                            name="quantity"
                            class="form-control"
                            placeholder="Quantity"
                            required>
                    </div>

                    <div class="col-md-3">
                        <input
                            type="number"
                            step="0.01"
                            name="price"
                            class="form-control"
                            placeholder="Price"
                            required>
                    </div>

                    <div class="col-md-2">
                        <button class="btn btn-success w-100">
                            Add Product
                        </button>
                    </div>

                </div>

            </form>
         <% } %>

        </div>

    </div>

    <!-- PRODUCT TABLE -->

    <div class="card shadow-sm m-4">

        <div class="card-body">

            <h5 class="mb-3">
                Product List
            </h5>

            <table class="table table-hover align-middle">

                <thead class="table-dark">

                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th width="180">Actions</th>
                </tr>

                </thead>

                <tbody>

                <%

                if(products != null && !products.isEmpty()){

                    for(Product p : products){

                %>

                <tr class="<%= (p.getQuantity() < 10 ? "low-stock" : "") %>">

                    <td>
                        <%= p.getProductId() %>
                    </td>

                    <td>
                        <%= p.getName() %>
                    </td>

                    <td>

                        <span class="badge bg-<%= (p.getQuantity() < 10 ? "danger" : "success") %>">

                            <%= p.getQuantity() %>

                        </span>

                    </td>

                    <td>
                        KES <%= p.getPrice() %>
                    </td>

					<td>
						<% if("ADMIN".equals(role)) { %>
						
						    <a class="btn btn-danger btn-sm"
						       href="products?action=delete&id=<%= p.getProductId() %>">
						       Delete
						    </a>
						
						    <a class="btn btn-primary btn-sm"
						       href="products?action=edit&id=<%= p.getProductId() %>">
						       Edit
						    </a>
						
						<% } else { %>
						
						    <span class="text-muted">No actions allowed</span>
						
						<% } %>
					
					</td>

                </tr>

                <%
                    }
                }
                else{
                %>

                <tr>

                    <td colspan="5" class="text-center text-muted">

                        No products available

                    </td>

                </tr>

                <%
                }
                %>

                </tbody>

            </table>

        </div>

    </div>

</div>

</body>
</html>