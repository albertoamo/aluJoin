
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ page import="models.BeanLogin"%>
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>

<script type="text/javascript">
$('#navbar').load('navBarDefault.jsp');
$(document).ready(function() {
    $(".menu").click(function(event) {
        $('#contentmenu').load('Content',{content: $(this).attr('id')});
        });
});
</script>

<div id = "navbar"></div>

<br>
<section class="container">

	<jsp:include page="login.jsp" />

	<div class="login-help">
		<p>
			Forgot your password? <a class="menu" id="form.jsp" href=#>Click
				here to reset it</a>.
		</p>
	</div>

	<div class="login">
		<h1>Sign Up</h1>
		<form method="post" action="">
			<p class="remember_me">
				<label><span name="remember_me" id="remember_me" /> I'm new
					in here, I want to join!!</label>
			</p>
			<p class="submit">
				<input class="menu" type="button" name="commit" value="Register"
					id="form.jsp" href=#>
			</p>
		</form>
	</div>
	
		<p>
		</p>

	<jsp:include page="lasttweetsform.jsp" />
	
</section>