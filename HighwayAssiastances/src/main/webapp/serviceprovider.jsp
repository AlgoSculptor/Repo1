
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>service provider login</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
	<div class="main">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container-fluide">
			<marquee><h1>Highway Assistance</h1></marquee>
				<div class="signin-content">
				
								
				
				
					<div class="signin-image">
						<figure>
							<img src="images/signup.png" alt="sing up image">
						</figure>
						<a href="registration.jsp" class="signup-image-link">Create an user
							account</a>
							<a href="serviceprovidersignup.jsp" class="signup-image-link">Create an service provider account</a>
							
					</div>

					<div class="signin-form">
						<h2 class="form-title">Service provider Login</h2>
						<form method="post" action="serviceprovider" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="username"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="serviceusername" id="username"
									placeholder="Your Email" required="required" />
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="servicepassword" id="password"
									placeholder="Password" required="required"/>
							</div>
							<div class="form-group">
								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> <label for="remember-me"
									class="label-agree-term"><span><span></span></span>Remember
									me</label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value=" Service provider Log in" />
									

									
									

							</div>
							

						</form>
						<br>
						<div>

							<a href="adminlogin.jsp" style="color: black; padding: 5px">
								Admin login</a> <a href="login.jsp"
								style="color: black; padding: 5px">User login</a>

						</div>
						
						
						
											</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
	var status=document.getElementById("status").value;
	if(status=="failed")
		{
		swal("sorry","please enter valid username and password","error");
		}
	if(status=="invalidemail")
	{
	swal("sorry","please enter valid email","error");
	}
	if(status=="invalidpassword")
	{
	swal("sorry","please entr valid password","error");
	}

	
	</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>