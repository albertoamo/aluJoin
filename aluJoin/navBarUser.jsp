<script type="text/javascript">
$(document).ready(function() {
    $(".menu").click(function(event) {
        $('#contentWall').load('Content',{content: $(this).attr('id')});
        });
});
</script>

<header id="wrap">

	<div class="logo">
		<a href="#"><img alt="" id="index.jsp" src="images/alu_logo2.png"></a>
	</div>

	<nav class="nav-left">
		<ul id="nav">
			<li><a class="menu" id="loginOk.jsp" href=#>Home</a></li>
			<li><a class="menu" id="edit.jsp" href=#>Profile</a></li>
			<li><a class="menu" id="alltweets.jsp" href=#>Users Tweets</a></li>
			<% HttpSession sesion = request.getSession();
			String isAdmin = (String)sesion.getAttribute("admin");
			   if(isAdmin.equals("1")){%>
			   <li><a class="menu" id="alluserstweets.jsp" href=#>All Users Tweets</a></li>
			    <%}%>
			<li><a href="#Contact">Contact</a></li>
		</ul>
	</nav>

	<nav class="nav-right">
		<ul id="nav">
			<li><a href="#Faq">F.A.Q</a></li>
			<li><a class="menu" id="logout.jsp" href=#>Out</a></li>
		</ul>
	</nav>

</header>