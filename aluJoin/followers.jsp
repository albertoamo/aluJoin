<script type="text/javascript">
$(document).ready(function() {
    $(".menu").click(function(event) {
        $('#content').load('Content',{content: $(this).attr('id')});
        });
});
</script>
<%
String followers = (String) session.getAttribute("followers");
String following = (String) session.getAttribute("following");
%>


<a href="#" class="myButton">Follow</a><hr id="mesage">
<p><a href=#>0 Messages</a></p><hr id="follow">
<p><a href=#><%= following %> Following</a></p><hr id="follow">
<p><a href=#><%= followers %> Followers</a></p><hr id="follow">