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

/**
 * Servlet implementation class logincontroller
 */
public class logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public logincontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HOLA");
		BeanLogin login = new BeanLogin();
		BeanUtilities.populateBean(login, request);
		if (login.isComplete()) {
			// ****INICIAMOS LA SESIÓN****
			HttpSession session = request.getSession();
			session.setAttribute("user", login.getUser());

			// ****HACEMOS LA BUSQUEDA AQUI, NO EN EL .JSP****
			try {
				DAO database = new DAO();
				String user = login.getUser();
				ResultSet result_query = database.executeSQL("SELECT * FROM `User` where username = \""+ user + "\";");
				
				result_query.next();
				String userid = result_query.getString(1);
				String name = result_query.getString(3);
				String surname = result_query.getString(4);
				session.setAttribute("id", userid);
				session.setAttribute("name", name);
				session.setAttribute("surname", surname);
				session.setAttribute("gender", result_query.getString(5));
				session.setAttribute("mail", result_query.getString(6));
				String date = result_query.getString(7);
				String dateSplitted[] = new String[3];
				dateSplitted = date.split("/");
				session.setAttribute("day", dateSplitted[0]);
				session.setAttribute("month", dateSplitted[1]);
				session.setAttribute("year", dateSplitted[2]);
				session.setAttribute("password", result_query.getString(8));
				session.setAttribute("description", result_query.getString(9));
				session.setAttribute("admin", result_query.getString(10));
				
				//DECIMOS A CUANTOS SIGUE I CUANTOS LE SIGUEN
				ResultSet follow_query = database.executeSQL("select count(*) from Follower where followid = " + userid + ";");
				follow_query.next();
				session.setAttribute("followers", follow_query.getString(1));
				
				follow_query = database.executeSQL("select count(*) from Follower where usernameid = " + userid + ";");
				follow_query.next();
				session.setAttribute("following", follow_query.getString(1));
				
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
				
				// ****HORA Y FECHA DE LA CREACION DE LA SESION*****
				Timestamp stamp = new Timestamp(System.currentTimeMillis());
				Date date2 = new Date(stamp.getTime());
				SimpleDateFormat sdf = new SimpleDateFormat(
						"MM/dd/yyyy h:mm:ss a");
				sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
				String formattedDate = sdf.format(date2);
				session.setAttribute("date", formattedDate);
				
				
				String followingSQL = "select username from `User` where usernameid in (select followid from follower  where usernameid = " + userid + ");";
				
				ResultSet following = database.executeSQL(followingSQL);
				
				ArrayList<String> ListF = new ArrayList<String>();
				
				while(following.next()){
					ListF.add(following.getString(1));
				}
				
				
				session.setAttribute("followingList", ListF);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginOk.jsp");
				if (dispatcher != null)
					dispatcher.forward(request, response);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		} else {

			request.setAttribute("login", login);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);

		}
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
