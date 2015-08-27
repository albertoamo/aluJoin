package controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


public class unfollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public unfollowController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("unFollowController!");
		String toDelete = (String) request.getParameter("todelete");
		System.out.println(toDelete);
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("id");
		
		String unfollowSQL = "delete from follower where usernameid = "+userid+ " and followid = "+toDelete+";";
		System.out.println(unfollowSQL);
		try {
			DAO database = new DAO();
			
			database.executeUpdate(unfollowSQL);
			
			String followingSQL = "select username from `User` where usernameid in (select followid from follower  where usernameid = " + userid + ");";
			
			ResultSet following = database.executeSQL(followingSQL);
			
			ArrayList<String> ListF = new ArrayList<String>();
			
			while(following.next()){
				ListF.add(following.getString(1));
			}
			
			session.setAttribute("followingList", ListF);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginOk.jsp");
		if (dispatcher != null)
			dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
