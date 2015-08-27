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
	$('#navbarA').load('navBarDefault.jsp');
	$('#navbarU').load('navBarUser.jsp');
	$(document).ready(function() {
		$("#editForm").validate({
	    	submitHandler: function(form) {
	    		$('#contentmenu').load('editcontroller',$("#editForm").serialize());
	    	},
			rules : {
				
				oldpassword: {
					required : true, 
					minlength : 4, 
					maxlength : 16
			},
				name : {
					maxlength : 16,
					required : true
				},
				password : {
					minlength : 4, 
					maxlength : 16
				},
				confirm : {
					equalTo : "#password"
				}
					
			}
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#navigation').load('menu.jsp');
	});
</script>


<%
	HttpSession sesion = request.getSession();
	if (sesion != null && session.getAttribute("user") != null) {
%>
<div id="navbarU"></div>

<%
	} else {
%>
<div id="navbarA"></div>
<%
	}
%>



</head>
<body>

	<%
		
		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");
		String gender = (String) session.getAttribute("gender");
		String day = (String) session.getAttribute("day");
		int dayInt = Integer.parseInt(day);
		String month = (String) session.getAttribute("month");
		String year = (String) session.getAttribute("year");
		String description = (String) session.getAttribute("description");
		String errorStr = (String) request.getAttribute("error") ;

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
<div id = "contentForm">

	<section id="navigation">
		
	</section>

 	<section>
   		<div class="wall" id="back">
   			<div class="boxing_top" id="lateral">
   				<nav class="avatar">
   					<img src="images/profile_user.png" class="avatar" alt="" style="height:180px;"><hr id="alma">
   					<p id="alma">Alma Mater: </p><img src="images/uni_upf.png" class="avatar" alt="" style="height:35px;">
   				</nav>
   			</div>

   			<div class="boxing_top" id="prof">
               <nav class="title">
                  <p id="name">Profile Settings</p><hr id="mesage">
                  <span id="alinfo">
                        <form action="" method="POST" id=editForm>
                     <table>
                     <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="<%=name%>" id="name" /></td>
                     </tr>
                     <tr>
                        <td>Surname</td>
                        <td><input type="text" name="surname" value="<%=surname%>" id="surname" /></td>
                     </tr>

                     <tr>
                        <td>Gender(*)</td>
                        <td><select name="gender" value="<%=gender%>">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                     </select></td>
                     <!-- RESTRICCIONES AQUI DENTRO EN JSP -->
                     </tr>

                     <tr>
                     <td>Birth Date(*)</td>
                     <td><select name="day">
                     <%    int i; for (i = 1; i <= 31; i++) { %>
                        <option selected="<%=day%>" value="<%=i%>"><%=i%></option>
                        <% } %>
                        <option selected value="<%=day%>" ><%=day%></option>
                     </select> 

                     <select name="month" >
                        <% for (i = 0; i < 12; i++) { %>
                        <option selected="<%=month%>" value="<%=Meses[i]%>"><%=Meses[i]%></option>
                        <% } %>
                        <option selected value="<%=month%>" ><%=month%></option>
                     </select>

                     <select name="year">
                        <% for (i = 1930; i < 2015; i++) { %>
                        <option value="<%=i%>" ><%=i%></option>
                        <% } %>
                        <option selected value="<%=year%>" ><%=year%></option>
                     </select></td>
                     <!-- RESTRICCIONES AQUI DENTRO EN JSP -->
                     </tr>

                     <tr>
                     <td>New Password(*)</td>
                     <td><input type="password" name="password" value="" id="password" /></td>
                     </tr>

                     <tr>
                     <td>Confirm Password(*)</td>
                     <td><input type="password" name="confirm" value="" id="confirm" /></td>
                     <!-- RESTRICCIONES AQUI DENTRO EN JSP -->
                     </tr>

                     <tr>
                     <td>Description</td>
                     <td><textarea name="description" rows="10" cols="30" maxlength="255" value=""><%=description%></textarea>
                     </td>
                     <!-- RESTRICCIONES AQUI DENTRO EN JSP -->
                     </tr>

                     <tr>
                     <td><input type="submit" value="Enviar"></td>
                     </tr>

                  </table>
               </form>
                  </span>
               </nav>
            </div>
   		</div>
   	</section>
 </div>
</body>
</html>