<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <%@ include file="includes/head.jsp" %>
    <!-- Add any additional CSS files here -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Registration</div>
        <div class="card-body">
            <form action="register" method="post">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter your Name" required>
                </div>
                <div class="form-group">
    <label for="userType">User Type</label>
    <select class="form-control" id="userType" name="userType">
        <option value="Merchant">Merchant</option>
        <option value="Customer" selected>Customer</option>
    </select>
</div>
                
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter your Email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your Password" required>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
            <p class="mt-3">Already have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
</body>
</html>