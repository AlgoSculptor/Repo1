<%@ page import="cn.shivam.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Orders</title>
    <%@ include file="includes/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="admin.css">
</head>
<body>
    <%@ include file="includes/admin-navbar.jsp" %>
    <div class="container">
        <h1>Manage Orders</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Date</th>
                    <th>Delivery Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (orders != null && !orders.isEmpty()) {
                    for (Order order : orders) {
            %>
                <tr>
                    <td><%= order.getOrderId() %></td>
                    <td><%= order.getuId() %></td>
                    <td><%= order.getId() %></td>
                    <td><%= order.getName() %></td>
                    <td><%= order.getCategory() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td><%= order.getPrice() %></td>
                    <td><%= order.getDate() %></td>
                    <td><%= order.getDeliveryAddress() %></td>
                    <td>
                        <a href="cancel-order?id=<%= order.getOrderId() %>&origin=admin" class="btn btn-danger">Cancel</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="10">No orders found.</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
