<%@ page import="com.inventory.model.User" %>
<%
User user = (User) session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}

if(!"ADMIN".equals(user.getRole())){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="bg-light">

<nav class="navbar navbar-dark bg-dark px-3">
    <span class="navbar-brand">StockFlow Manager: Admin</span>
    <a href="logout" class="btn btn-danger btn-sm">Logout</a>
</nav>

<div class="container mt-4">

<h4>Welcome StockFlow Manager, <%= user.getUsername() %></h4>

<div class="row mt-3">

    <div class="col-md-4">
        <div class="card text-white bg-success shadow">
            <div class="card-body">
                <h5>Products</h5>
                <p>Manage inventory</p>
                <a href="products" class="btn btn-light btn-sm">Open</a>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card text-white bg-primary shadow">
            <div class="card-body">
                <h5>Sales</h5>
                <p>Record transactions</p>
                <a href="sales.jsp" class="btn btn-light btn-sm">Open</a>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card text-white bg-warning shadow">
            <div class="card-body">
                <h5>Reports</h5>
                <p>Analytics dashboard</p>
                <a href="report" class="btn btn-light btn-sm">Open</a>
            </div>
        </div>
    </div>

</div>

</div>

</body>
</html>