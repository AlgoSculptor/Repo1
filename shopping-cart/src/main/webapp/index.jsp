<%@ page import="cn.shivam.connection.DBCon" %>
<%@ page import="cn.shivam.model.*" %>
<%@ page import="cn.shivam.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.shivam.model.User" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%
    User auth = (User)request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }
    
    ProductDao pd = new ProductDao(DBCon.getConnection());
    List<Product> products = pd.getAllProducts();
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
    	request.setAttribute("cart_list",cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Shopping Cart</title>
    <%@ include file="includes/head.jsp" %>
    <link rel = "stylesheet" href = "index.css">
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>
    <div class="container">
        <div class="card-header my-3"> All Products</div>
        <div class="row">
        
        <%
        if(!products.isEmpty()) {
            for(Product p : products) {
        %>
                <div class="col-md-3 my-3"> <!-- Add my-3 class here -->
                    <div class="card-w-100 border" style="width: 100%;">
                        <!-- Image container with fixed dimensions -->
                        <div class="product-image-container">
                            <!-- Add the CSS class to the image tag -->
                            <img class="card-img-top product-image" src="product-images/<%= p.getImage() %>" alt="Card image cap">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getName() %></h5>
                            <h6 class="price"> Price <%= p.getPrice() %></h6>
                            <h6 class="Category">Category : <%= p.getCategory() %></h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="add-to-cart?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart</a>
                                <a href="order.jsp?id=<%=p.getId()%>" class="btn btn-primary">Order Now</a>


                            </div>
                        </div>
                    </div>
                </div>
        <%  
            }
        }
        %>
        </div>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>

