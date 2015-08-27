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
public class tweetscontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tweetscontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HOLAAA");
		try {
			DAO database = new DAO();
			ResultSet NumTweetQ = database.executeSQL("select max(tweetid) from tweet;");
			NumTweetQ.next();
			
			String NumTweetS = NumTweetQ.getString(1);
			int NumTweetsI = Integer.parseInt(NumTweetS);
			
			System.out.println(NumTweetsI);
			if(NumTweetsI>5){
				NumTweetsI -= 5;
			}
			else{
				NumTweetsI = 0;
			}
			
			String tweetsQuery = "select u.username, u.firstname, u.firstsurname, t.tweetid, t.usernameid, t.textt, t.likes, t.dislikes, t.datet, t.fathertweet" +
					 " from `User` u, tweet t where t.usernameid = u.usernameid and t.tweetid > "+ NumTweetsI +" ;";
			
			TweetMapGenerator TweetsG = new TweetMapGenerator();
			TweetsG.MapActualizer(tweetsQuery);
			request.setAttribute("tweetsMap", TweetsG.getMap());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
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