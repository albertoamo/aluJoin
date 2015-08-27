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

public class editcontroller extends HttpServlet {
	// Atributo
	private static final long serialVersionUID = 1L;

	// Constructor
	public editcontroller() {
		super();
	}

	// En doGet comprobamos si el user está completo y si es así lo añadimos a
	// la BDD. En caso negativo, devolvemos el mismo formulario.
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//Tratamos la sesion actual
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("user");
		String userMail = (String) session.getAttribute("mail");
		

		try
		{
			DAO database = new DAO();
			
			String newQuery = "select pass from `User` where username = \"" + userName +"\";";
			ResultSet result_query = database.executeSQL(newQuery);
			result_query.next();
			
			BeanUser user = new BeanUser();
			BeanUtilities.populateBean(user, request);

		
			result_query = database.executeSQL("select * from `User` where username = \"" + userName + "\";");
			
			result_query.next();
			
			//Si no hemos cambiado la contraseña, no ha de cambiar
			if(user.getPassword().equals("")){
				user.setPassword(result_query.getString(8));
			}

			String update = "UPDATE `User` "
					+ "SET firstname='" + user.getName() + "',"
					+ "firstsurname='" + user.getSurname() + "',"
					+ "gender='" + user.getGender() + "', "
					+ "mail='" + userMail + "', "
					+ "birthdate='" + user.getDay() + "/" + user.getMonth() + "/" + user.getYear() + "',"
					+ "pass='" + user.getPassword() + "',"
					+ "description='" + user.getDescription() + "' "
					+ "WHERE username=\"" + userName + "\";";
					
			database.executeUpdate(update);
			
			session.setAttribute("name", user.getName());
			session.setAttribute("surname", user.getSurname());
			session.setAttribute("gender", user.getGender());
			session.setAttribute("day", user.getDay());
			session.setAttribute("month", user.getMonth());
			session.setAttribute("year", user.getYear());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("description", user.getDescription());
			

			
			String userid = (String)session.getAttribute("id");
			
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
			 
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("index.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
			
		}
		catch (SQLException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}	

	}

	// El método doPost sencillamente llama al doGet
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
