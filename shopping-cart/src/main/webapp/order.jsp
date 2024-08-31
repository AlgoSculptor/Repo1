<%@ page import="java.util.*"%>
<%@ page import="cn.shivam.model.*"%>
<%@ page import="cn.shivam.dao.*"%>
<%@ page import="cn.shivam.connection.*"%>
<%@ page import="java.text.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders = null;
if (auth != null) {
    request.setAttribute("auth", auth);
    orders = new OrderDao(DBCon.getConnection()).userOrders(auth.getId());
} else {
    response.sendRedirect("login.jsp");
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
}

// Retrieve product ID from URL parameter
String productId = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Order</title>
    <%@ include file="includes/head.jsp"%>
</head>
<body>
    <%@ include file="includes/navbar.jsp"%>
    <div class="container">
        <div class="d-flex py-3"> 
            <!-- <h3>Total Price: $ ${(total>0)?dcf.format(total):0}</h3> -->
            <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a>
        </div>
        <%-- Form to enter quantity and delivery address --%>
        <form action="order-now" method="post">
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" class="form-control" id="quantity" name="quantity" required>
            </div>
            <div class="form-group">
                <label for="address">Delivery Address:</label>
                <input type="text" class="form-control" id="address" name="address" required>
            </div>
            <div class="form-group">
                <%-- Hidden field to pass product ID dynamically --%>
                <input type="hidden" id="productId" name="id" value="<%= productId %>">
            </div>
            <!-- Add more input fields for city, state, postal code, etc. as needed -->
            <button type="submit" class="btn btn-primary">Place Order</button>
        </form>

        <%-- Displaying user's orders --%>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
                <% if(orders != null) { 
                    for(Order o : orders) { %>
                    <tr>
                        <td><%= o.getDate()%></td>
                        <td><%= o.getName()%></td>
                        <td><%= o.getCategory()%></td>
                        <td><%= dcf.format(o.getPrice())%></td>
                        <td><%= o.getQuantity()%></td>
                        <td> 
                            <a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel</a> 
                        </td>
                    </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
    <%@ include file="includes/footer.jsp"%>
</body>
</html>
