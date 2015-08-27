   <body>
   
   <%String getAdmin = (String) session.getAttribute("admin");
   	System.out.println("Anonimous: " + getAdmin);
   	if(getAdmin == null){getAdmin = "";}
   %>
   
   <div id = "contentWall">
   		<div class="wall" id="back">
   			
   			<div class="boxing_top" id="actions">
   				<h2 id="topic">Top Trending Topics</h2><hr id="mesage">
   				<ul>
            		<li><a href="#Information">#Primer Topic</a></li>
            		<li><a href="#About">#Segundo Topic</a></li>
            		<li><a href="#Contact">#Tercer Topic</a></li>
            		<li><a href="#Information">#Cuarto Topic</a></li>
            		<li><a href="#About">#Quinto Topic</a></li>
            		<li><a href="#Contact">#Sexto Topic</a></li>
            		<li><a href="#Information">#Septimo Topic</a></li>
            		<li><a href="#About">#Octavo Topic</a></li>
         		</ul>
   			</div>
   
			<div class="boxing_top" id = "messages">
			<% if(getAdmin.equals("1")){ %>
   				<jsp:include page="tweets.jsp" />
   				<%}else{ %>
   				<jsp:include page="otherTweets.jsp" />
   				<%} %>
   			</div>
  	
  		</div>
	</div>

</body>