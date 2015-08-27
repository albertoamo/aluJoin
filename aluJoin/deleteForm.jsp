<head>
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteform").validate({submitHandler: function(form) {
		$('#contentmenu').load('deleteUserController',$("#deleteform").serialize());}
		});
	});
</script>

<% String error = (String) request.getParameter("error");

	if(error.equals("null")){error = "toDelete";}
%>

</head>
<body>
	<form action="" method="POST" id="deleteform">
		<input type="text" name = "deletealias" value = ""  id = "name" placeholder="<%=error%>">
		<p class="submit" style="float:right;"><input type="submit" name="commit" value="detonate">
	</form>
</body>