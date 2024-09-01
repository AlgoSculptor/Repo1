<!--navbar start-->


		<nav class="navbar navbar-expand-sm sticky-top navbar-light " style="background-color: #d0d2ce">
			<div class="container-fluid">
				<a class="navbar-brand pl-lg-5 " href="index.jsp">Highway_Assistance</a>
				<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
					data-target="#navbar1">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse text-center" id="navbar1">
					<ul class="navbar-nav">
						<li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="index.jsp#about">About</a>
						</li>


						<li class="nav-item"><a class="nav-link" href="emergency.jsp">Emergency</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="service.jsp">services</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="feedback.jsp">review&rating</a>
						</li>

						<li class="nav-item"><a class="nav-link" href="#contact">Contact us</a>
						</li>
					</ul>
					<ul class="navbar-nav ml-auto">


						<li class="nav-item active bg-danger"><a class="nav-link" href="index.jsp">
								<%=session.getAttribute("name")%>
							</a><i class="zmdi zmdi-account material-icons-name"></i></li>


						<li class="nav-item active"><a class="nav-link" href="login.jsp">Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<!--navbar end-->