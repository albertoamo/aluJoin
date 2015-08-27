package controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.DAO;
import utils.TweetMapGenerator;


public class updatetweetcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatetweetcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Tweet editado!");
		// TODO Auto-generated method stub
		 
		try {
			
			DAO database = new DAO();
					
			HttpSession session = request.getSession();
			String userid = (String)session.getAttribute("id");
			String username = (String)session.getAttribute("user");
			String firstname = (String)session.getAttribute("name");
			String firstsurname = (String)session.getAttribute("surname");
			String text = "'"+(String)request.getParameter("tweetupdate") + "'" ;
			
			String tupdate = (String)request.getParameter("tweetupdate");
			String tid = (String)request.getParameter("tweetid");
			
			//tweetupdate
			//tweetid
			
			System.out.println("UPDATE Tweet "
					+ "SET textt='" + tupdate + "' "
					+ "WHERE tweetid= " + tid + ";");
			
			String update = "UPDATE Tweet "
					+ "SET textt='" + tupdate + "' "
					+ "WHERE tweetid= " + tid + ";";
			database.executeUpdate(update);
	
			String tweetsQuery = "select u.username, u.firstname, u.firstsurname, " +
					"t.tweetid, t.usernameid, t.textt, t.likes, t.dislikes, t.datet, t.fathertweet " +
					"from `User` u, tweet t where u.usernameid = " + userid + " and t.usernameid = " + userid + ";";
			
			TweetMapGenerator TweetsG = new TweetMapGenerator();
			TweetsG.MapActualizer(tweetsQuery);
			session.setAttribute("tweetsMap", TweetsG.getMap());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginOk.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
