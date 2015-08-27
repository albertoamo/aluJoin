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
import java.util.TreeMap;
import java.util.TreeSet;

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
public class sharetweetcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sharetweetcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			Date date2 = new Date(stamp.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat(
					"MM/dd/yyyy h:mm:ss a");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
			String formattedDate = sdf.format(date2);
			
			DAO database = new DAO();
					
			HttpSession session = request.getSession();
			String userid = (String)session.getAttribute("id");
			String text = (String)request.getParameter("tweettext") ;
			String SQL = "insert into tweet (usernameid, textt, likes, dislikes, datet, fathertweet) " + 
					"values(" + userid + ", '" + text+ "', " + 0+ ", " + 0+ ", " + "'" + formattedDate + "', " + "null" + ");";
			
			database.executeUpdate(SQL);

			String tweetsQuery = "select u.username, u.firstname, u.firstsurname, " +
					"t.tweetid, t.usernameid, t.textt, t.likes, t.dislikes, t.datet, t.fathertweet " +
					"from `User` u, tweet t where u.usernameid = " + userid + " and t.usernameid = " + userid + ";";
			
			String retweetsQuery = "select u.username, u.firstname, u.firstsurname, " +
					"t.tweetid, t.usernameid, t.textt, t.likes, t.dislikes, t.datet, t.fathertweet " +
					 "from `User` u, tweet t where t.usernameid = u.usernameid and " +
					 "t.tweetid in(select r.tweetid from retweet r where r.usernameid = " + userid +");";
			
			
			TweetMapGenerator TweetsG = new TweetMapGenerator();
			TweetsG.MapActualizer(tweetsQuery);
			TweetsG.MapActualizer(retweetsQuery);
			session.setAttribute("tweetsMap", TweetsG.getMap());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
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