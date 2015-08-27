package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import models.BeanUser;
import utils.BeanUtilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.DAO;
import utils.TweetMapGenerator;

public class votecontroller extends HttpServlet {
	// Atributo
	private static final long serialVersionUID = 1L;

	// Constructor
	public votecontroller() {
		super();
	}

	// En doGet comprobamos si el user está completo y si es así lo añadimos a
	// la BDD. En caso negativo, devolvemos el mismo formulario.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			DAO database = new DAO();
					
			HttpSession session = request.getSession();
			String userid = (String)session.getAttribute("id");
			String username = (String)session.getAttribute("user");
			String firstname = (String)session.getAttribute("name");
			String firstsurname = (String)session.getAttribute("surname");
			String tid = (String)request.getParameter("tweetid");
			String tlike = (String)request.getParameter("like");
			String tdislike = (String)request.getParameter("dislike");
			
			String check = "select v.usernameid from Votes v where v.usernameid = "+ userid + " and v.tweetid = "+ tid + ";";
			ResultSet checkQuery = database.executeSQL(check);
			
			if(!checkQuery.next()){
				
				String update1 = "UPDATE Tweet "
					+ "SET likes='" + tlike + "' "
					+ "WHERE tweetid= " + tid + ";";
				database.executeUpdate(update1);
			
				String update2 = "UPDATE Tweet "
					+ "SET dislikes='" + tdislike + "' "
					+ "WHERE tweetid= " + tid + ";";
				database.executeUpdate(update2);
			
				String insert = "INSERT INTO Votes values('" + userid + "','" + tid + "');";
				database.executeUpdate(insert);
	
				String tweetsQuery = "select u.username, u.firstname, u.firstsurname, " +
					"t.tweetid, t.usernameid, t.textt, t.likes, t.dislikes, t.datet, t.fathertweet " +
					"from `User` u, tweet t where u.usernameid = " + userid + " and t.usernameid = " + userid + ";";
			
				TweetMapGenerator TweetsG = new TweetMapGenerator();
				TweetsG.MapActualizer(tweetsQuery);
				session.setAttribute("tweetsMap", TweetsG.getMap());
				
				System.out.println("User has voted!!");
			
			}
			else{
				//Aqui deberia pasar algo, una response hacia ajax/jquery
				System.out.println("Ya le has dado like");
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

	// El método doPost sencillamente llama al doGet
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void doVote(DAO database, HttpSession session){
		
	}

}