<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#latesttweets").validate({submitHandler : function(form) {
		$('#contentmenu').load('tweetscontroller',$("#latesttweets").serialize());}
		});
	});
</script>
</head>
<body>

	<br>

	<form id=latesttweets method="POST" action="">
		<div class="login-help">
			<p>
				Quieres ver los Ultimos Tweets?
			<input class="menu" type="submit" name="submit" value="Show Me" id="loginbox">
			</p>
		</div>
	</form>
	</div>
</body>
