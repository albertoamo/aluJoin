<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanLogin"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page buffer="16kb"%>

<%@ page import="java.util.SortedSet"%>
<%@ page import="java.util.TreeSet"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#navbarA').load('navBarDefault.jsp');
		$('#navbarU').load('navBarUser.jsp');
		$('#followers').load('unfollowers.jsp');
		
		$(".retweet").click(function(event) {
	    	alert($(".key_grouper").attr('id'));
	        $('#contentWall').load('retweetController',{tweetid: $(".key_grouper").attr('id')});
		});
		
	    $(".myButton").click(function(event) {
	        $('#contentWall').load('unfollowController',{todelete: $(this).attr('id')});
	        });	
	    
	    $(".like").click(function(event) {
	    	var like_num = parseInt($(this).attr('id')) + 1;
	    	var likes = like_num.toString();
	    	$('#contentWall').load('votecontroller',{tweetid: $(".key_grouper").attr('id'),like: likes, dislike: $(".dislike").attr('id')});
		});
	    $(".dislike").click(function(event) {
	    	var dislike_num = parseInt($(this).attr('id')) + 1;
	    	var dislikes = dislike_num.toString();
	        $('#contentWall').load('votecontroller',{tweetid: $(".key_grouper").attr('id'),like: $(".like").attr('id'),dislike: dislikes});
		});
	});
</script>

<html>

<body id="wrap">

	<%
	
	String getAdmin = (String) session.getAttribute("admin");
	
	String id = (String) request.getAttribute("id");
	String user = (String) request.getAttribute("user");
	String name = (String) request.getAttribute("name");
	String surname = (String) request.getAttribute("surname");
	String gender = (String) request.getAttribute("gender");
	String mail = (String) request.getAttribute("mail");
	String day = (String) request.getAttribute("day");
	String month = (String) request.getAttribute("month");
	String year = (String) request.getAttribute("year");
	String description = (String) request.getAttribute("description");	
	String birthdate = day + "/" + month + "/"  + year;
	String admin = (String) request.getAttribute("admin");
	
	String followers = (String) request.getAttribute("followers");
	String following = (String) request.getAttribute("following");
	
	Map<Integer, String[]> tweets = new HashMap<Integer, String[]>();
	
	tweets = (Map) request.getAttribute("tweetsMap");

	%>

	<%
		if (session != null && session.getAttribute("user") != null) {
	%>
	<div id="navbarU"></div>

	<%
		} else {
	%>
	<div id="navbarA"></div>
	<%
		}
	%>

	<div id="contentWall">
		<div class="wall" id="back">
			<div class="boxing_top" id="lateral">
				<nav class="avatar">
					<img src="images/profile_user.png" class="avatar" alt=""
						style="height: 180px;">
					<hr id="alma">
					<p id="alma">Alma Mater:</p>
					<img src="images/uni_upf.png" class="avatar" alt=""
						style="height: 35px;">
				</nav>
			</div>

			<div class="boxing_top" id="main">
				<nav class="title">
					<%if(name != null){%>
					<p id="name"><%=name%>
						<%=surname%></p>
					<% } %>
					<% if(user != null){%>
					<p id="username">
						@<%=user%></p>
					<% } %>
					<hr id="mesage">
					<span id="alinfo"> <%if(gender!= null){%>
						<p>
							Gender:
							<%=gender%></p> <% } %> <%if(mail != null){%>
						<p>
							Email:
							<%=mail%></p> <% } %> <%if(birthdate != null){%>
						<p>
							Birthday:
							<%=birthdate%></p> <% } %> <%if(description != null){%>
						<p>
							Description:
							<%=description%></p> <%} %>
					</span>
				</nav>
				<nav class="follow">
					<a href="#" class="myButton" id="<%=id%>">Unfollow</a><hr id="<%=user%>">
						<p><a href=#>0 Messages</a></p><hr id="follow">
						<p><a href=#><%= following %> Following</a></p><hr id="follow">
						<p><a href=#><%= followers %> Followers</a></p><hr id="follow">
				</nav>
			</div>

			<div class="boxing_top" id="actions">
				<h2 id="topic">Top Trending Topics</h2>
				<hr id="mesage">
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
		<div class="boxing_top" id = "messages">
			<%
			TreeSet<Integer> keys = new TreeSet<Integer>(tweets.keySet());
			keys = (TreeSet)keys.descendingSet();
			
			for (Integer Key : keys) {
			%>
	
			<div class="msg_group">
				<nav class="mesag_av">
					<img src="images/profile_user.png" class="avatar" alt="">
				</nav>

				<nav class="mesag">

					<!-- Gestion de la cabecera del mensaje -->
					<p id="topic"><%=tweets.get(Key)[1]%>
						<%=tweets.get(Key)[2]%></p>
					<p id="topuser">
						@<%=tweets.get(Key)[0]%></p>
					<p id="topdate">
						<%=tweets.get(Key)[6]%></p>

					<!-- Gestion del texto del mensaje -->
					<section class="msg_send">
						<hr id="mesage">
						<p id="txtarea<%=Key%>"><%=tweets.get(Key)[3]%></p>

					</section>
					<br>
					<hr id="mesage">

				<section style="margin-top: -12px; padding-right: 10px; text-align: right;">
				<span class="key_grouper" id="<%=Key %>">
				<p id="topuser" style="padding-left: 30px;"><%=tweets.get(Key)[4]%></p><a class="like" id=<%=tweets.get(Key)[4]%> href="#"><p id="topuser">
					<img class="icon_msg" src="images/thumbs_up.png" alt="">Like</p></a>
				<p id="topuser"><%=tweets.get(Key)[5]%></p><a class="dislike" id=<%=tweets.get(Key)[5]%> href="#"><p id="topuser">
					<img class="icon_msg" src="images/thumbs_down.png" alt="">Dislike</p></a>
				<a class="retweet" name=<%=Key %> href="#"><p id="topuser">
					<img class="icon_msg" src="images/retweet.png" alt="">Retweet</p></a>
				<a class="edit" name=<%=Key %> href="#"><p id="topuser">
					<img class="icon_msg" src="images/edit.png" alt="">Edit</p></a>
				<a class="delete" id=<%=Key %> href=#><p id="topuser">
					<img class="icon_msg" src="images/bomb.png" alt="">Detonate</p></a>
			</span>
			</section>
				</nav>
				<%
				}
				%>
			</div>
		</div>
		</div>
</body>
</html>
