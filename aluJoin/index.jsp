<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>aluJoin Main</title>
<meta name="description" content="" />
<meta name="author" content="" />

<!-- ======================= CSS =========================== -->
<link rel="shortcut icon" href="images/icon/favlogo.ico" />
<link rel="stylesheet" type="text/css" href="css/axl_design.css" />
<link rel="stylesheet" type="text/css" href="css/axl_navbar.css" />
<link rel="stylesheet" href="css/axl_wall.css">

	<!-- ====================== Jquery ========================= -->
	<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>

	<!-- =================== Implicit Jquery =================== -->

	<script type="text/javascript">
		$(document).ready(function() {

			$('#welText').load('welText.jsp');
			$('#footer').load('footer.html');

		});
	</script>
</head>

<body id="wrap">

	<div id="faux">
		<div id="contentmenu">
			<%
				HttpSession sesion = request.getSession();
				if (sesion != null && session.getAttribute("user") != null) {
			%>

			<jsp:include page="loginOk.jsp" />
			<%
				} else {
			%>

			<!-- CARGAMOS EL TEXTO DE BIENVENIDA -->
			<div id="welText" class="weltext"></div>
			<div> <jsp:include page="menu3.jsp" /> </div>
			<%
				}
			%>
					<div id = "footer"></div>
		</div>
	</div>
</body>
</html>