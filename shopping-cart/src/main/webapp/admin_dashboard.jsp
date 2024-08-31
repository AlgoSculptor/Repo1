<%@ page import="cn.shivam.model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <%@ include file="includes/head.jsp" %>
    <link rel="stylesheet" href="admin.css">
</head>
<body>
    <%@ include file="includes/admin-navbar.jsp" %>
    <div class="container">
        <h1>Welcome to Admin Dashboard</h1>
        <div class="row">
            <div class="col-md-4">
                <a href="admin-manage-products" class="btn btn-primary">Manage Products</a>
            </div>
            <div class="col-md-4">
                <a href="admin-manage-users" class="btn btn-primary">Manage Users</a>
            </div>
            <div class="col-md-4">
                <a href="admin-manage-orders" class="btn btn-primary">Manage Orders</a>
            </div>
        </div>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
