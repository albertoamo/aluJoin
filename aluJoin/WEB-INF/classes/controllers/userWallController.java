package controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BeanLogin;
import utils.BeanUtilities;
import utils.DAO;
import utils.TweetMapGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;

/**
 * Servlet implementation class logincontroller
 */
public class userWallController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userWallController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		
		try {
			DAO database = new DAO();
			String userInfoQuery ="SELECT * FROM `User` where username = \""+ username + "\";";
			
			ResultSet userQuery = database.executeSQL(userInfoQuery);
			
			userQuery.next();
			String userid = userQuery.getString(1);
			String name = userQuery.getString(3);
			String surname = userQuery.getString(4);
			String gender = userQuery.getString(5);
			String mail = userQuery.getString(6);
			String date = userQuery.getString(7);
			String dateSplitted[] = new String[3];
			
			dateSplitted = date.split("/");
			
			String day = dateSplitted[0];
			String month = dateSplitted[1];
			String year = dateSplitted[2];
			
			String description = userQuery.getString(9);
			String admin = userQuery.getString(10);
			
			request.setAttribute("id", userid);
			request.setAttribute("user", username);
			request.setAttribute("name", name);
			request.setAttribute("surname", surname);
			request.setAttribute("gender", gender);
			request.setAttribute("mail", mail);
			request.setAttribute("day", day);
			request.setAttribute("month", month);
			request.setAttribute("year", year);
			request.setAttribute("description", description);
			request.setAttribute("admin", admin);
			
			ResultSet follow_query = database.executeSQL("select count(*) from Follower where followid = " + userid + ";");
			follow_query.next();
			request.setAttribute("followers", follow_query.getString(1));
			
			follow_query = database.executeSQL("select count(*) from Follower where usernameid = " + userid + ";");
			follow_query.next();
			request.setAttribute("following", follow_query.getString(1));
			
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
			request.setAttribute("tweetsMap", TweetsG.getMap());
			
			Map<Integer, String[]> tweets = TweetsG.getMap();
			
			TreeSet<Integer> keys = new TreeSet<Integer>(tweets.keySet());
			keys = (TreeSet)keys.descendingSet();
			
			for (Integer Key : keys) {
				System.out.println("Tweet: " + tweets.get(Key));
			}
			
			String followingSQL = "select username from `User` where usernameid in (select followid from follower  where usernameid = " + userid + ");";
			
			ResultSet following = database.executeSQL(followingSQL);
			
			ArrayList<String> ListF = new ArrayList<String>();
			
			while(following.next()){
				ListF.add(following.getString(1));
			}
			
			
			request.setAttribute("followingList", ListF);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("otherUser.jsp");
		if (dispatcher != null)
			dispatcher.forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}