<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanLogin"%>

<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script>
	$(document).ready(function() {
		$("#registerForm").validate({submitHandler : function(form) {
		$('#contentmenu').load('logincontroller',$("#registerForm").serialize());},
							rules : {
								user : {
									required : true,
									minlength : 4
								},
								password : {
									required : true,
									minlength : 4
								}
							}
						});
			});
</script>
</head>
<body>

	<%
		BeanLogin login = null;
		if (request.getAttribute("login") != null) {
			login = (BeanLogin) request.getAttribute("login");
		} else {
			login = new BeanLogin();
		}
	%>

	<br>
	<div class="login">
		<h1>Login</h1>
		<form id=registerForm method="POST" action="" >
			<p>
				<input type="text" name="user" value="<%=login.getUser()%>"
					id="user" placeholder="Username">
				<% if (login.getError()[0] == 1) { %>
				<td class="error">Este usuario no existe</td>
				<% } %>
			</p>
			<p>
				<input type="password" name="password" value="" id="password "
					placeholder="Password">
				<% if (login.getError()[1] == 1) { %>
				<td class="error">Contraseña incorrecta</td>
				<% } %>
			</p>
			<p class="remember_me">
				<label> <input type="checkbox" name="remember_me"
					id="remember_me"> Remember me on this computer
				</label>
			</p>
			<p class="submit">
				<input class="menu" type="submit" name="submit" value="Login"
					id="loginbox">
			</p>
		</form>
	</div>
</body>
