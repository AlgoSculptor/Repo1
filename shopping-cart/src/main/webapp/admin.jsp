<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="cn.shivam.connection.DBCon" %>
<%@ page import="cn.shivam.model.*" %>
<%@ page import="cn.shivam.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.shivam.model.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
</head>
<body>
    <h1>Welcome to Admin Panel</h1>
    
    <h2>Orders</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>User ID</th>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Date</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            if (orders != null && !orders.isEmpty()) {
                for (Order order : orders) {
            %>
            <tr>
                <td><%= order.getOrderId() %></td>
                <td><%= order.getuId() %></td>
                <td><%= order.getId() %></td>
                <td><%= order.getName() %></td>
                <td><%= order.getCategory() %></td>
                <td><%= order.getPrice() %></td>
                <td><%= order.getQuantity() %></td>
                <td><%= order.getDate() %></td>
                <td>
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                        <input type="submit" value="Cancel Order">
                    </form>
                </td>
            </tr>
            <% 
                } // end for loop
            } else {
            %>
            <tr>
                <td colspan="9">No orders found.</td>
            </tr>
            <% } // end if-else block
            %>
        </tbody>
    </table>
    
    <h2>Users</h2>
    <table border="1">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td>
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="userId" value="<%= user.getId() %>">
                        <input type="submit" value="Remove User">
                    </form>
                </td>
            </tr>
            <% 
                } // end for loop
            } else {
            %>
            <tr>
                <td colspan="4">No users found.</td>
            </tr>
            <% } // end if-else block
            %>
        </tbody>
    </table>
</body>
</html>
