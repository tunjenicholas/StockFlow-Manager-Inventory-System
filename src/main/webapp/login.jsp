<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login|BizStock Kenya</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg,#0d6efd,#6c63ff);
            height: 100vh;
        }

        .card {
            border-radius: 15px;
        }
    </style>
</head>

<body class="d-flex align-items-center justify-content-center">

<div class="card shadow-lg p-4" style="width:380px;">

    <h3 class="text-center text-primary mb-3">StockFlow Manager</h3>

    <form action="login" method="post" id="loginForm">

        <input class="form-control mb-3" type="text" name="username" placeholder="Username" required>

        <input class="form-control mb-3" type="password" name="password" placeholder="Password" required>

        <button class="btn btn-primary w-100" id="loginBtn">
            <span id="btnText">Login</span>
            <span class="spinner-border spinner-border-sm d-none" id="spinner"></span>
        </button>

        <% if("1".equals(request.getParameter("error"))) { %>
            <div class="alert alert-danger mt-3">Invalid credentials</div>
        <% } %>

    </form>
</div>

<script>
document.getElementById("loginForm").addEventListener("submit", function () {
    document.getElementById("loginBtn").disabled = true;
    document.getElementById("spinner").classList.remove("d-none");
    document.getElementById("btnText").innerText = "Logging in...";
});
</script>

</body>
</html>