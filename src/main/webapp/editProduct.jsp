<%@ page import="com.inventory.model.Product" %>

<%
Product p = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">

    <div class="card shadow">

        <div class="card-body">

            <h3 class="mb-4">
                Edit Product
            </h3>

            <form action="products" method="post">

                <input type="hidden"
                       name="action"
                       value="update">

                <input type="hidden"
                       name="id"
                       value="<%= p.getProductId() %>">

                <div class="mb-3">

                    <label>Name</label>

                    <input
                        type="text"
                        name="name"
                        value="<%= p.getName() %>"
                        class="form-control">

                </div>

                <div class="mb-3">

                    <label>Quantity</label>

                    <input
                        type="number"
                        name="quantity"
                        value="<%= p.getQuantity() %>"
                        class="form-control">

                </div>

                <div class="mb-3">

                    <label>Price</label>

                    <input
                        type="number"
                        step="0.01"
                        name="price"
                        value="<%= p.getPrice() %>"
                        class="form-control">

                </div>

                <button class="btn btn-success">
                    Update Product
                </button>

                <a href="products"
                   class="btn btn-secondary">

                    Cancel

                </a>

            </form>

        </div>

    </div>

</div>

</body>
</html>