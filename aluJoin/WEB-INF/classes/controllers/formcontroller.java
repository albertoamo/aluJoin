package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.BeanUser;
import utils.BeanUtilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DAO;

//formcontroller es un servlet que se encarga de realizar las peticiones doGet y doPost
public class formcontroller extends HttpServlet {
	// Atributo
	private static final long serialVersionUID = 1L;

	// Constructor
	public formcontroller() {
		super();
	}

	// En doGet comprobamos si el user está completo y si es así lo añadimos a
	// la BDD. En caso negativo, devolvemos el mismo formulario.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("HEY: " + request.getParameter("description"));
		
		BeanUser user = new BeanUser();
		BeanUtilities.populateBean(user, request);
		String insertion = new String();

		// Si el usuario está listo para ser registrado
		if(user.isComplete()){

			try {
				DAO database = new DAO();

				insertion = "insert into `User` (username, firstname, firstsurname, gender, mail, birthdate, pass, description, admin) values('"
						+ user.getUser() + "', '" + user.getName() + "', '"
						+ user.getSurname() + "', '" + user.getGender()
						+ "', '" + user.getMail() + "', '" + user.getDay() + "/" + user.getMonth() 
						+ "/" + user.getYear() + "', '" + user.getPassword() + "', '"
						+ user.getDescription() + "', " + "False" + ");";

				database.executeUpdate(insertion); // insertamos en nuestra BBDD el usuario
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			    if (dispatcher != null) dispatcher.forward(request, response);
				
				database.disconnectBD();
			}
			// Catches de las excepciones de instanciar el DAO y de la ejecución
			// SQL
			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// En caso de que no esté completo, devolvemos el mismo formulario con
		// toda la información
		else {
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
			if(dispatcher != null) dispatcher.forward(request, response);
		}
	}

	// El método doPost sencillamente llama al doGet
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
