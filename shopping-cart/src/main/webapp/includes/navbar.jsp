<%@ page import="cn.shivam.model.User" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">E-commerce Shopping Cart</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger px-1">${cart_list.size()}</span></a>
            </li>
            <% 
                User authenticatedUser = (User) request.getSession().getAttribute("auth");
                if(authenticatedUser != null) {
            %>
                <% if(authenticatedUser.getUserType().equals("Merchant")) { // Check if user is of type "Merchant" %>
                <!-- Navigation for authenticated users -->
                <li class="nav-item">
                    <a class="nav-link" href="order.jsp">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="addProduct.jsp">Upload Product</a>
                </li>
                <% } %>
                <% if(authenticatedUser.getUserType().equals("Admin")) { // Check if user is of type "Admin" %>
                <!-- Navigation for admin users -->
                <li class="nav-item">
                    <a class="nav-link" href="admin_dashboard.jsp">Admin Dashboard</a>
                </li>
                <% } %>
                <li class="nav-item">
                    <a class="nav-link" href="log-out">Logout</a>
                </li>
            <% } else { %>
                <!-- Navigation for non-authenticated users -->
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
            <% } %> 
        </ul>
       <form class="form-inline my-2 my-lg-0" action="search" method="GET"> <!-- Change action to point to the SearchServlet -->
    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="query"> <!-- Add name="query" to input field -->
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
</form>

    </div>
</nav>
