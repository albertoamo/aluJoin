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
public class followController extends HttpServlet {
	// Atributo
	private static final long serialVersionUID = 1L;

	// Constructor
	public followController() {
		super();
	}

	// En doGet comprobamos si el user está completo y si es así lo añadimos a
	// la BDD. En caso negativo, devolvemos el mismo formulario.
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HOLA");
		String toFollow = request.getParameter("followalias");
		System.out.println(toFollow);
		HttpSession session = request.getSession();
		String followerId = (String) session.getAttribute("id");

		try {
			DAO database = new DAO();

			System.out
					.println("select u.usernameid from `User` u where u.username = '"
							+ toFollow + "';");

			ResultSet followId = database
					.executeSQL("select u.usernameid from `User` u where u.username = '"
							+ toFollow + "';");

			if (followId.next()) {

				String toFollowId = (String) followId.getString(1);

				if (!toFollowId.equals(followerId)) {

					String followInsert = "insert into follower values ("
							+ followerId + ", " + toFollowId + ");";

					database.executeUpdate(followInsert);

					String followingSQL = "select username from `User` where usernameid in (select followid from follower  where usernameid = "
							+ followerId + ");";

					ResultSet following = database.executeSQL(followingSQL);

					ArrayList<String> ListF = new ArrayList<String>();

					while (following.next()) {
						ListF.add(following.getString(1));
					}

					session.setAttribute("followingList", ListF);
				}
			} else {
				request.setAttribute("error", "IncorrectUser");
			}

		} catch (SQLException e) {
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
