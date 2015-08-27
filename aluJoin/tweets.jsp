<script type="text/javascript">
$(document).ready(function() {
	$('#navbarA').load('navBarDefault.jsp');
	$('#navbarU').load('navBarUser.jsp');
	//Jquery functions for message
    $(".delete").click(function(event) {
        $('#contentWall').load('deletetweetcontroller',{tweetid: $(this).attr('id')});
        });
	
    $(".like").click(function(event) {
    	var like_num = parseInt($(this).attr('id')) + 1;
    	var likes = like_num.toString();
    	$('.msg_group').load('votecontroller',{tweetid: $(".key_grouper").attr('id'),like: likes, dislike: $(".dislike").attr('id')});
	});
    $(".dislike").click(function(event) {
    	var dislike_num = parseInt($(this).attr('id')) + 1;
    	var dislikes = dislike_num.toString();
        $('.msg_group').load('votecontroller',{tweetid: $(".key_grouper").attr('id'),like: $(".like").attr('id'),dislike: dislikes});
	});
    
    $(".retweet").click(function(event) {
    	alert($(".key_grouper").attr('id'));
        $('#contentWall').load('retweetController',{tweetid: $(".key_grouper").attr('id')});
	});
	
    //Edit functions
    $(".edit").each(function(i) {
    	var nuevoid = $(this).attr('name');
    	$("#msgarea" + nuevoid).hide();
    });
	
    $(".edit").click(function(event) {
    	var nuevoid = $(this).attr('name')
    	$("#txtarea" + nuevoid).hide();
    	$("#msgarea" + nuevoid).show();
    	//alert('llega aqui dentro ^_^' + '#txtarea' + nuevoid);
    	
    	$('#msgarea' + nuevoid).keydown(function (e){
    	    if(e.keyCode == 13){
    	    	$("#txtarea" + nuevoid).show();
    	    	$("#msgarea" + nuevoid).hide();
    	        //alert('you pressed enter ^_^');
    	        
    	        var texto = $('textarea#msgarea' + nuevoid).val();
    	        $('#contentWall').load('updatetweetcontroller',{
    	        	tweetupdate: texto,
    	        	tweetid: nuevoid
    	        	});
    	    }
    	});
        });
});
</script>
<head>
<%
	if (session != null && session.getAttribute("user") != null) {
%>
<div id="navbarU"></div>

<%
	} else {
%>
<div id="navbarA"></div>
<%
	}
%>

</head>
<body>
<%@ page import="java.util.SortedSet"%>
<%@ page import="java.util.TreeSet"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>

<%
	Map<Integer, String[]> tweets = new HashMap<Integer, String[]>();
	
	tweets = (Map) request.getAttribute("tweetsMap");
	
	if (tweets == null) {%>
		<%
		tweets = (Map) session.getAttribute("tweetsMap");
	}
%>


	<%
		TreeSet<Integer> keys = new TreeSet<Integer>(tweets.keySet());
		keys = (TreeSet)keys.descendingSet();
		
		for (Integer Key : keys) {
	%>
   	
   	<div class="msg_group">
   		<nav class="mesag_av">
			<img src="images/profile_user.png" class="avatar" alt="">
		</nav>
		
		<nav class="mesag">
		
			<!-- Gestion de la cabecera del mensaje -->
			<p id="topic"><%=tweets.get(Key)[1]%>
				<%=tweets.get(Key)[2]%></p>
			<p id="topuser">
				@<%=tweets.get(Key)[0]%></p>
			<p id="topdate">
				<%=tweets.get(Key)[6]%></p>
			
			<!-- Gestion del texto del mensaje -->
			<section class="msg_send"><hr id="mesage">
				<p id="txtarea<%=Key%>"><%=tweets.get(Key)[3]%></p>
				<textarea style="margin-bottom: -24px;" id="msgarea<%=Key%>" rows="2" cols="60" maxlength="255"><%=tweets.get(Key)[3]%></textarea>
			</section>
			<br><hr id="mesage">
			
			<section style="margin-top: -12px; padding-right: 10px; text-align: right;">
			<span class="key_grouper" id="<%=Key %>">
				<p id="topuser" style="padding-left: 30px;"><%=tweets.get(Key)[4]%></p><a class="like" id=<%=tweets.get(Key)[4]%> href="#"><p id="topuser">
					<img class="icon_msg" src="images/thumbs_up.png" alt="">Like</p></a>
				<p id="topuser"><%=tweets.get(Key)[5]%></p><a class="dislike" id=<%=tweets.get(Key)[5]%> href="#"><p id="topuser">
					<img class="icon_msg" src="images/thumbs_down.png" alt="">Dislike</p></a>
				<a class="retweet" name=<%=Key %> href="#"><p id="topuser">
					<img class="icon_msg" src="images/retweet.png" alt="">Retweet</p></a>
				<a class="edit" name=<%=Key %> href="#"><p id="topuser">
					<img class="icon_msg" src="images/edit.png" alt="">Edit</p></a>
				<a class="delete" id=<%=Key %> href=#><p id="topuser">
					<img class="icon_msg" src="images/bomb.png" alt="">Detonate</p></a>
			</span>
			</section>
		</nav>
	<%
		}
	%>
	</div>
	
</body>