<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#navbar').load('navBarDefault.jsp');
    $(".menu").click(function(event) {
        $('#contentmenu').load('Content',{content: $(this).attr('id')});
        });
});
</script>
</head>
<body id="blocked">
	<div id="contentmenu">

	<div id = "navbar"></div>

		<section class="containermid">
		<div class="login">
			<h1>Sign Up</h1>

			<a class="menu" id="index.jsp" href=#> Welcome to aluJoin. </a>

		</div>
		</section>

	</div>
</body>
</html>