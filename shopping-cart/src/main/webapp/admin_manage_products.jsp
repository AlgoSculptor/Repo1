<%@ page import="cn.shivam.model.Product" %>
<%@ page import="cn.shivam.dao.*" %>
<%@ page import="cn.shivam.connection.*" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manage Products</title>
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
            var id = row.find(".product-id").text();
            var name = row.find(".edit-name").val();
            var category = row.find(".edit-category").val();
            var price = row.find(".edit-price").val();

            // AJAX POST request to update the product data
            $.ajax({
                url: "update-product",
                method: "POST",
                data: {
                    id: id,
                    name: name,
                    category: category,
                    price: price
                },
                success: function(response) {
                    console.log("Product updated successfully");
                    // Refresh the page or update the UI as needed
                },
                error: function(xhr, status, error) {
                    console.error("Error updating product:");
                    console.error(xhr.responseText); // Log the complete error response from the server
                    // Handle the error, display a message, or take appropriate action based on the response
                }

            });
        });
    });
    </script>
</head>
<body>
    <%@ include file="includes/admin-navbar.jsp" %>
    <div class="container">
        <h1>Manage Products</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    ProductDao productDao = new ProductDao(DBCon.getConnection());
                    List<Product> productList = productDao.getAllProducts();
                    for (Product product : productList) { 
                %>
                <tr>
                    <td class="product-id"><%= product.getId() %></td>
                    <td>
                        <span class="view-mode view-name"><%= product.getName() %></span>
                        <input type="text" class="form-control edit-mode edit-name" value="<%= product.getName() %>" style="display:none;">
                    </td>
                    <td>
                        <span class="view-mode view-category"><%= product.getCategory() %></span>
                        <input type="text" class="form-control edit-mode edit-category" value="<%= product.getCategory() %>" style="display:none;">
                    </td>
                    <td>
                        <span class="view-mode view-price"><%= product.getPrice() %></span>
                        <input type="text" class="form-control edit-mode edit-price" value="<%= product.getPrice() %>" style="display:none;">
                    </td>
                    <td>
                        <button class="btn btn-primary edit-btn">Edit</button>
                        <button class="btn btn-success save-btn" style="display:none;">Save</button>
                        <button class="btn btn-secondary cancel-btn" style="display:none;">Cancel</button>
                        <!-- Add delete button or link as needed -->
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
