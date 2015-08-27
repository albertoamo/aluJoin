<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanLogin"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page buffer="16kb" %> 

<script type="text/javascript">
	$(document).ready(function() {
		$('#navbar').load('navBarUser.jsp');
		$('#navbarAdmin').load('navBarAdmin.jsp');
		$('#followers').load('followers.jsp');
		$('#messages').load('tweets.jsp');
		$('#newTweet').load('tweetform.jsp');
		$('#followlist').load('followinglist.jsp');
		$('#followformdiv').load('followForm.jsp');
	});
</script>

<html>

<body id="wrap">

	<%
	String user = (String) session.getAttribute("user");
	String name = (String) session.getAttribute("name");
	String surname = (String) session.getAttribute("surname");
	String gender = (String) session.getAttribute("gender");
	String mail = (String) session.getAttribute("mail");
	String day = (String) session.getAttribute("day");
	String month = (String) session.getAttribute("month");
	String year = (String) session.getAttribute("year");
	String description = (String) session.getAttribute("description");	
	String birthdate = day + "/" + month + "/"  + year;
	String admin = (String) session.getAttribute("admin");
	
	String error = (String) request.getAttribute("error");
	
	System.out.println("Error en jsp: " + error);
	
	%>
	
   <div id = "contentWall">
   		<div class="wall" id="back">
   			<div class="boxing_top" id="lateral">
   				<nav class="avatar">
   					<img src="images/profile_user.png" class="avatar" alt="" style="height:180px;"><hr id="alma">
   					<p id="alma">Alma Mater: </p><img src="images/uni_upf.png" class="avatar" alt="" style="height:35px;">
   				</nav>
   			</div>

   			<div class="boxing_top" id="main">
   				<nav class="title">
   					<%if(name != null){%>
   						<p id="name"><%=name%> <%=surname%></p>
					<% } %>
   					<% if(user != null){%>
   						<p id="username"> @<%=user%></p>
   					<% } %>
   					<hr id="mesage">
   					<span id="alinfo">
   						<%if(gender!= null){%>
   							<p>Gender: <%=gender%></p>
   						<% } %>
   						<%if(mail != null){%>
   							<p>Email: <%=mail%></p>
   						<% } %>
   						<%if(birthdate != null){%>
   							<p>Birthday: <%=birthdate%></p>
   						<% } %>
   						<%if(description != null){%>
   							<p>Description: <%=description%></p>
   						<%} %>
   					</span>
   				</nav>
   				<nav class="follow">
   				<!--  AQUI IMPRIMIMOS EL PANEL DE FOLLOWERS -->
   				<div id = "followers"> </div>
   				</nav>
   			</div>
   			
   			<div class="boxing_top" id="actions">
   				<h2 id="topic">Top Trending Topics</h2><hr id="mesage">
   				<ul>
            		<li><a href="#Information">#Primer Topic</a></li>
            		<li><a href="#About">#Segundo Topic</a></li>
            		<li><a href="#Contact">#Tercer Topic</a></li>
            		<li><a href="#Information">#Cuarto Topic</a></li>
            		<li><a href="#About">#Quinto Topic</a></li>
            		<li><a href="#Contact">#Sexto Topic</a></li>
            		<li><a href="#Information">#Septimo Topic</a></li>
            		<li><a href="#About">#Octavo Topic</a></li>
         		</ul>
   			</div>
   			
   			<!--  -->
   			<div class="boxing_top" id="newTweet">
			</div>
   			
   			<div class="boxing_top" id="actions">

				<div> <jsp:include page="followForm.jsp" >
					  <jsp:param name="error" value="<%= error %>"/>
					  </jsp:include>
				 </div>

				<%if(admin.equals("1")){ %>
				<!-- <div id ="deleteformdiv"></div>  -->
				<div> <jsp:include page="deleteForm.jsp" >
					  <jsp:param name="error" value="<%= error %>"/>
					  </jsp:include>
				 </div>
				<%} %>
   				<br><br><br>
   				<hr id="mesage">
   				<div id = "followlist"></div>
   			</div>
	
			<!--  AQUI IMPRIMIMOS LOS TWEETS -->
   			<div class="boxing_top" id = "messages"></div>
   			

		</div>
			<div>
		<FONT SIZE=1 class="sesiondate"><p>Sesión iniciada a las: <%=session.getAttribute("date")%><p></font>
			</div>
   </div>

</body>
</html>
