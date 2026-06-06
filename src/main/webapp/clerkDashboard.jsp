<%@ page import="com.inventory.model.User" %>

<%
User user = (User) session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}

if(!"CLERK".equals(user.getRole())){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Clerk Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body {
    background: #f4f6f9;
}

.card {
    border: none;
    border-radius: 15px;
    transition: 0.2s;
}

.card:hover {
    transform: translateY(-3px);
    box-shadow: 0 10px 20px rgba(0,0,0,0.1);
}

.header {
    background: #0d6efd;
    color: white;
    padding: 20px;
    border-radius: 10px;
}

.role-badge {
    background: #198754;
    padding: 5px 10px;
    border-radius: 20px;
    font-size: 12px;
}

</style>

</head>

<body>

<div class="container mt-4">

    <!-- HEADER -->
    <div class="d-flex justify-content-between header mb-4">
	    <div>
	    	<h3>StockFlow Manager: Clerk</h3>
	
	        <p class="d-flex flex-column">
	            <span>Welcome  StockFlow Manager, <b><%= user.getUsername() %></b></span>
	            <span class=""><b>Role: </b> <%= user.getRole() %></span>
	        </p>
	    </div>

        <div class="text-top">

        	<a href="logout" class="btn btn-danger">
            	Logout
        	</a>

    	</div>

    </div>

    <!-- DASHBOARD CARDS -->
    <div class="row g-4">

        <div class="col-md-4">

            <div class="card shadow-sm p-3">

                <h5>Products</h5>
                <p>View available stock</p>

                <a href="products" class="btn btn-primary">
                    Open
                </a>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card shadow-sm p-3">

                <h5>Sales</h5>
                <p>Record new transactions</p>

                <a href="sales.jsp" class="btn btn-success">
                    Open
                </a>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card shadow-sm p-3">

                <h5>Inventory Status</h5>
                <p>Check stock levels</p>

                <a href="products" class="btn btn-warning">
                    View
                </a>

            </div>

        </div>

    </div>


</div>

</body>
</html>