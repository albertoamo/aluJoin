<head>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<script type="text/javascript">
$(document).ready(function() {
	$("#tweetform").validate({submitHandler: function(form) {
		$('#contentmenu').load('sharetweetcontroller',$("#tweetform").serialize());
		}
	});
	
    $(".auser").click(function(event) {
    	$('#contentWall').load('userWallController',{username: $(this).attr('name')});
	});

});
</script>

<% 
	ArrayList<String> ListF = new ArrayList<String>();
	ListF = (ArrayList) session.getAttribute("followingList");
%>

</head>


<h2 id="topic">Following</h2>
<hr id="mesage">
<ul>
<%for(int i = 0; i<ListF.size(); i++){%>
	<li id="userlink"><a class="auser" name="<%= ListF.get(i) %>" id = "userlink" href="#">@<%= ListF.get(i) %></a></li>
<%}%>
</ul>