package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.DAO;
import utils.TweetMapGenerator;

/**
 * Servlet implementation class logoutcontroller
 */
public class alltweetscontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alltweetscontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			HttpSession session = request.getSession();
			String userid = (String)session.getAttribute("id");
			
			String tweetsQuery = "select u.username, u.firstname, u.firstsurname, " +
					"t.tweetid, t.usernameid, t.textt, t.likes, t.dislikes, t.datet, t.fathertweet " +
					"from `User` u, tweet t where u.usernameid = t.usernameid and u.usernameid in "+
					"(select followid from follower  where usernameid = " + userid + ");";
			
			TweetMapGenerator TweetsG = new TweetMapGenerator();
			TweetsG.MapActualizer(tweetsQuery);
			request.setAttribute("tweetsMap", TweetsG.getMap());	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("anonymous.jsp");
	    if (dispatcher != null) dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}