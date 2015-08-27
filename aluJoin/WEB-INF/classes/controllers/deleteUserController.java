package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.BeanUser;
import utils.BeanUtilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.DAO;

//formcontroller es un servlet que se encarga de realizar las peticiones doGet y doPost
public class deleteUserController extends HttpServlet {
	// Atributo
	private static final long serialVersionUID = 1L;

	// Constructor
	public deleteUserController() {
		super();
	}

	// En doGet comprobamos si el user está completo y si es así lo añadimos a
	// la BDD. En caso negativo, devolvemos el mismo formulario.
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String toDelete = request.getParameter("deletealias");
		
		System.out.println(toDelete);
		
		HttpSession session = request.getSession();
		
		
		String deleteQuery1 = "delete from `User` where username = '" + toDelete + "';";
		String deleteQuery2 = "delete from follower where usernameid in " +  
		"(select u.usernameid from `User` u where u.username = '" + toDelete +"');";
		String deleteQuery3 = "delete from votes where usernameid in " + 
		"(select u.usernameid from `USer` u where u.username = '" + toDelete + "');";
		
		try {
			DAO database = new DAO();
			
			ResultSet consulta_nom_usuari = database.executeSQL("SELECT * FROM `User` WHERE username IN ('"+toDelete+"');");
			
			if(consulta_nom_usuari.next()){
			
			database.executeUpdate(deleteQuery3);
			database.executeUpdate(deleteQuery2);
			database.executeUpdate(deleteQuery1);
			
			String userid = (String) session.getAttribute("id");
			
			String followingSQL = "select username from `User` where usernameid in (select followid from follower  where usernameid = " + userid + ");";
			
			ResultSet following = database.executeSQL(followingSQL);
			
			ArrayList<String> ListF = new ArrayList<String>();
			
			while(following.next()){
				ListF.add(following.getString(1));
			}
			
			
			session.setAttribute("followingList", ListF);
			request.setAttribute("error", "Deleted");
			}
			else{
				request.setAttribute("error", "IncorrectUser");
			}
			
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("loginOk.jsp");
		if (dispatcher != null)
			dispatcher.forward(request, response);
		
	}

	// El método doPost sencillamente llama al doGet
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
