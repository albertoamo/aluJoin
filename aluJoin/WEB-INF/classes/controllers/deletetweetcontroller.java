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


public class deletetweetcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletetweetcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Bamos a vorrar un tweet");
		// TODO Auto-generated method stub
		 
		try {
			
			DAO database = new DAO();
					
			HttpSession session = request.getSession();
			
			String tweetid = (String)request.getParameter("tweetid");
			String userid = (String)session.getAttribute("id");
			
			String comproveOrigen = "select t.tweetid from tweet t where t.usernameid = " + userid + " and t.tweetid = " + tweetid + ";";
			System.out.println("Origen: " + comproveOrigen);
			ResultSet originSQL = database.executeSQL(comproveOrigen);
			
			if(originSQL.next()){
			String SQL = "DELETE FROM Tweet WHERE tweetid='" + tweetid + "'";
			System.out.println(SQL);
			database.executeUpdate(SQL);
			
			Map<Integer, String[]> tweets = (Map)session.getAttribute("tweetsMap");
			
			tweets.remove(Integer.parseInt(tweetid));
			
			session.setAttribute("tweetsMap", tweets);
			
			}
			else{
				System.out.println("Estas intentando borrar algo que no es tuyo pillin?");
			}
			
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
