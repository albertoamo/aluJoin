<script type="text/javascript">
$(document).ready(function() {
    $(".menu").click(function(event) {
        $('#contentmenu').load('Content',{content: $(this).attr('id')});
        });
});
</script>

<header id="wrap">

	<div class="logo">
		<a class = "menu" id="index.jsp" href="#" ><img alt="" id="index.jsp" src="images/alu_logo2.png"></a>
	</div>

	<nav class="nav-left">
		<ul id="nav">			
			<li><a href="#Information">Information</a></li>
			<li><a href="#About">About us</a></li>
			<li><a href="#Contact">Contact</a></li>
		</ul>
	</nav>
	<nav class="nav-right">
		<ul id="nav">
			<li><a href="#Faq">F.A.Q</a></li>
			<li><a href="#Language">Lang</a></li>
		</ul>
	</nav>
</header>