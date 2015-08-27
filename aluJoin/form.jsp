<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exemple Formulari</title>
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script>
	$(document).ready(function() {
		$('#navbar').load('navBarDefault.jsp');
		$("#registerForm").validate({
	    	submitHandler: function(form) {
	    		$('#contentmenu').load('formcontroller',$("#registerForm").serialize());
	    	},
			rules : {
				user : {
					required : true,
					maxlength : 16,
					minlength : 4
				},
				name : {
					required : true,
					maxlength : 16					
				},
				mail : {
					required : true,
					email : true, 
					maxlength : 25
				},
				password : {
					required : true, 
					minlength : 4, 
					maxlength : 16
				},
				confirm : {
					equalTo : "#password"
				},
				day : {
					required : true
				},
				month : {
					required : true
				},
				year : {
					required : true
				}
					
			}
		});
	});
</script>
</head>
<body id="wrap">

	<%
		BeanUser user = null;
		if (request.getAttribute("user") != null) {
			user = (BeanUser) request.getAttribute("user");
		} else {
			user = new BeanUser();
		}

		String[] Meses;
		Meses = new String[12];
		Meses[0] = "Enero";
		Meses[1] = "Febrero";
		Meses[2] = "Marzo";
		Meses[3] = "Abril";
		Meses[4] = "Mayo";
		Meses[5] = "Junio";
		Meses[6] = "Julio";
		Meses[7] = "Agosto";
		Meses[8] = "Septiembre";
		Meses[9] = "Octubre";
		Meses[10] = "Noviembre";
		Meses[11] = "Diciembre";
	%>

	<div id="navbar"></div>

	<section class="containermid">
	<div class="login">
		<h1>Sign Up</h1>

		<form action="" method="POST" id=registerForm>

			<p>
				<input type="text" name="user" value="<%=user.getUser()%>" id="user"
					minlength="5" placeholder="Username" />
				<% if (user.getError()[0] == 1) { %>
				<td class="error">The username already exists in our DB!</td>
				<% } %>
			</p>

			<p>
				<input type="text" name="name" value="<%=user.getName()%>" id="name"
					placeholder="Name" />
			</p>

			<p>
				<input type="text" name="surname" value="<%=user.getSurname()%>"
					id="surname" placeholder="Surname" />
			</p>

			<p>
				<input type="text" name="mail" value="<%=user.getMail()%>" id="mail"
					placeholder="Email" />
				<% if (user.getError()[1] == 1) { %>
				<td class="error">The mail is already registered in our DB!</td>
				<% } %>
			</p>

			<p>
				<input type="password" name="password" value="" id="password"
					placeholder="Password" />
			</p>

			<p>
				<input type="password" name="confirm" value="" id="confirm"
					placeholder="Confirm Password" />
			</p>


			<br>
			<div id="choices">
				Birth Date <select type="styled" name="day">
					<% int i;
					for (i = 1; i <= 31; i++) { %>
					<option value="<%=i%>"><%=i%></option>
					<% } user.setDay("1");%>
					<option selected value="<%=user.getDay()%>"><%=user.getDay()%></option>
				</select> <select type="styled" name="month">
					<% for (i = 0; i < 12; i++) { %>
					<option value="<%=Meses[i]%>"><%=Meses[i]%></option>
					<% } user.setMonth(Meses[0]);%>
					<option selected value="<%=user.getMonth()%>"><%=user.getMonth()%></option>
				</select> <select type="styled" name="year">
					<% for (i = 1930; i < 2015; i++) { %>
					<option value="<%=i%>"><%=i%></option>
					<% } user.setYear("1930");%>
					<option selected value="<%=user.getYear()%>"><%=user.getYear()%></option>
				</select>
			</div>

			<p>
			<div id="choices">
				Gender <select type="styled" name="gender"
					value="<%=user.getGender()%>">
					<option value="male">Male</option>
					<option value="female">Female</option>
				</select>
				</p>
			</div>

			<p>
				<textarea name="description" rows="10" cols="30" maxlength="255"
					value="" placeholder="Insert a brief description."></textarea>
			</p>

			<p class="submit">
				<input type="submit" name="commit" value="Enviar">
			</p>

		</form>
		<!-- AQUI ACABA EL FORMULARIO -->

	</div>
	</section>

	</div>

	<span class="footer">
		<div>Universitat Pompeu Fabra: 2014-2015</div>
		<div>Ingenieria del software para aplicaciones web</div>
	</span>
</body>
</html>