<%@ page import="cn.shivam.model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    User auth = (User)request.getSession().getAttribute("auth");
    if(auth == null || !auth.getUserType().equals("Merchant")){
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <%@ include file="includes/head.jsp" %>
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>
    <div class="container">
        <h2 class="my-3">Add Product</h2>
        <!-- Display error message if present -->
        <% String errorMessage = (String)request.getAttribute("errorMessage");
           if(errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
            </div>
        <% } %>
        
        <% if(auth != null && auth.getUserType().equals("Merchant")) { %>
         <form action="add-products2" method="post" enctype="multipart/form-data">
        <label for="productName">Product Name:</label><br>
        <input type="text" id="productName" name="productName"><br>
        
        <label for="productCategory">Category:</label><br>
        <input type="text" id="productCategory" name="productCategory"><br>
        
        <label for="productPrice">Price:</label><br>
        <input type="text" id="productPrice" name="productPrice"><br>
        
        <label for="productQuantity">Quantity:</label><br>
        <input type="text" id="productQuantity" name="productQuantity"><br>
        
        <label for="productImage">Image:</label><br>
        <input type="file" id="image" name="image"><br><br>
        
        <input type="submit" value="Submit">
    </form>
        <% } else { %>
            <div class="alert alert-danger" role="alert">
                You do not have permission to access this page.
            </div>
        <% } %>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
