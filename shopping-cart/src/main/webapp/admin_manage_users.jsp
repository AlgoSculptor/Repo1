<%@ page import="cn.shivam.model.User" %>
<%@ page import="cn.shivam.dao.*" %>
<%@ page import="cn.shivam.connection.*" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manage Users</title>
    <%@ include file="includes/head.jsp" %>
    <link rel="stylesheet" href="admin.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    $(document).ready(function() {
        $(".edit-btn").click(function() {
            console.log("Edit button clicked");
            var row = $(this).closest("tr");
            row.find(".view-mode").hide();
            row.find(".edit-mode").show();
            $(this).hide();
            row.find(".save-btn").show();
            row.find(".cancel-btn").show();
        });

        $(".cancel-btn").click(function() {
            console.log("Cancel button clicked");
            var row = $(this).closest("tr");
            row.find(".view-mode").show();
            row.find(".edit-mode").hide();
            row.find(".edit-btn").show();
            row.find(".save-btn").hide();
            row.find(".cancel-btn").hide();
        });

        $(".save-btn").click(function() {
            var row = $(this).closest("tr");
            var id = row.find(".user-id").text();
            var name = row.find(".edit-name").val();
            var email = row.find(".edit-email").val();
            var userType = row.find(".edit-userType").val();

            if (userType !== "Merchant" && userType !== "Customer") {
                alert("Invalid user type. Please select either 'Merchant' or 'Customer'.");
                return;
            }

            // AJAX POST request to update the user data
            $.post("update-user", {
                id: id,
                name: name,
                email: email,
                userType: userType
            }, function(response) {
                // Update the user data in the table row
                row.find(".view-name").text(name);
                row.find(".view-email").text(email);
                row.find(".view-userType").text(userType);
                row.find(".view-mode").show();
                row.find(".edit-mode").hide();
                row.find(".edit-btn").show();
                row.find(".save-btn").hide();
                row.find(".cancel-btn").hide();
            });
        });

    });
</script>

</head>
<body>
    <%@ include file="includes/admin-navbar.jsp" %>
    <div class="container">
        <h1>Manage Users</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>User Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    UserDao userDao = new UserDao(DBCon.getConnection());
                    List<User> userList = userDao.getAllUsers();
                    for (User user : userList) { 
                %>
                <tr>
                    <td class="user-id"><%= user.getId() %></td>
                    <td>
                        <span class="view-mode view-name"><%= user.getName() %></span>
                        <input type="text" class="form-control edit-mode edit-name" value="<%= user.getName() %>" style="display:none;">
                    </td>
                    <td>
                        <span class="view-mode view-email"><%= user.getEmail() %></span>
                        <input type="email" class="form-control edit-mode edit-email" value="<%= user.getEmail() %>" style="display:none;">
                    </td>
                    <td>
    					<span class="view-mode view-userType"><%= user.getUserType() %></span>
    					<select class="form-control edit-mode edit-userType" style="display:none;">
      						<option value="Merchant" <%= user.getUserType().equals("Merchant") ? "selected" : "" %>>Merchant</option>
        					<option value="Customer" <%= user.getUserType().equals("Customer") ? "selected" : "" %>>Customer</option>
    					</select>
					</td>

                    <td>
                        <button class="btn btn-primary edit-btn">Edit</button>
                        <button class="btn btn-success save-btn" style="display:none;">Save</button>
                        <button class="btn btn-secondary cancel-btn" style="display:none;">Cancel</button>
                        <a href="delete-user?id=<%= user.getId() %>" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
