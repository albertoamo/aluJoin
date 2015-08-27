<head>
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#followform").validate({submitHandler: function(form) {
		$('#contentmenu').load('followController',$("#followform").serialize());}
		});
	});
</script>

<% String error = (String) request.getParameter("error");

	if(error.equals("null")){error = "toFollow";}
%>

</head>
<body>
	<form action="" method="POST" id="followform">
		<input type="text" name = "followalias" value = ""  id = "name"  placeholder="<%=error%>">
		<p class="submit" style="float:right;"><input type="submit" name="commit" value="follow">
	</form>
</body>