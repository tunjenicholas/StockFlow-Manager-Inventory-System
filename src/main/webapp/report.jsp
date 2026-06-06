<%@ page import="java.util.*, com.inventory.model.Product" %>

<%
Double totalSales = (Double) request.getAttribute("totalSales");
Double todaySales = (Double) request.getAttribute("todaySales");
List<Product> lowStockList = (List<Product>) request.getAttribute("lowStockList");
%>

<!DOCTYPE html>
<html>
<head>
<title>Reports</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-4">

<div class="d-flex justify-content-between align-items-center mb-3">
	<h3>Sales Dashboard</h3>

    <a href="adminDashboard.jsp" class="btn btn-secondary">
     	Dashboard
    </a>
</div>

<div class="row">

<div class="col-md-4">
<div class="card text-white bg-success p-3">
<h5>Total Sales</h5>
<h3>KES <%= totalSales %></h3>
</div>
</div>

<div class="col-md-4">
<div class="card text-white bg-primary p-3">
<h5>Today Sales</h5>
<h3>KES <%= todaySales %></h3>
</div>
</div>

<div class="col-md-4">
<div class="card text-white bg-danger p-3">
<h5>Low Stock Items</h5>
<h3><%= (lowStockList != null ? lowStockList.size() : 0) %></h3>
</div>
</div>

</div>

<hr>

<h4>Low Stock Products</h4>

<table class="table table-striped table-hover bg-white">

<thead class="table-dark">
<tr>
<th>ID</th><th>Name</th><th>Qty</th><th>Price</th>
</tr>
</thead>

<tbody>

<%
if(lowStockList != null){
for(Product p : lowStockList){
%>

<tr style="color:<%= (p.getQuantity() < 5 ? "red" : "black") %>">
<td><%= p.getProductId() %></td>
<td><%= p.getName() %></td>
<td><%= p.getQuantity() %></td>
<td><%= p.getPrice() %></td>
</tr>

<% } } %>

</tbody>

</table>

</div>

</body>
</html>