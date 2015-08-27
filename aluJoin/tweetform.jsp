<head>
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#tweetform").validate({submitHandler: function(form) {
		$('#contentmenu').load('sharetweetcontroller',$("#tweetform").serialize());}
		});
	});
</script>

</head>
<body>
	<form action="" method="POST" id="tweetform">
		<textarea name="tweettext" rows="8" cols="75" maxlength="255"
			value="" placeholder="What are you thinking?"></textarea>
		<p class="submit" style="float:right;"><input type="submit" name="commit" value="share">
	</form>
</body>