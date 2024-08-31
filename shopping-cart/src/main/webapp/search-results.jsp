<%@ page import="java.util.*" %>
<%@ page import="cn.shivam.model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <%@ include file="includes/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="index.css">
    <style>
        .card-img-container {
            height: 200px; /* Set a fixed height for the image container */
            overflow: hidden; /* Ensure that larger images are clipped */
        }
        .card-img-container img {
            object-fit: contain; /* Ensure that the entire image fits within the container */
            width: 100%; /* Ensure that the image fills the container width */
            height: 100%; /* Ensure that the image fills the container height */
        }
    </style>
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>
    <div class="container">
        <h2>Search Results</h2>
        <div class="row">
            <% 
            List<Product> searchResults = (List<Product>)request.getAttribute("searchResults");
            if(searchResults != null && !searchResults.isEmpty()) {
                for(Product p : searchResults) {
            %>
                <div class="col-md-3 my-3">
                    <!-- Product card here -->
                    <div class="card">
                        <div class="card-img-container"> <!-- Image container with fixed dimensions -->
                            <img src="product-images/<%= p.getImage() %>" class="card-img-top" alt="Product Image">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getName() %></h5>
                            <p class="card-text">Category: <%= p.getCategory() %></p>
                            <p class="card-text">Price: <%= p.getPrice() %></p>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="add-to-cart?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart</a>
                                <a href="order-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Buy Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            <% 
                }
            } else {
            %>
                <p>No results found.</p>
            <% } %>
        </div>
    </div>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
