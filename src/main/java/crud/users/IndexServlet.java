package crud.users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.users.User;
import mysql.users.UserDB;
import mysql.utests.UnitTest;
import mysql.utests.UnitTestDB;

@WebServlet("/user_index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		if (session.getAttribute("is_admin") != null) {
			ArrayList<User> users = UserDB.select();
	        request.setAttribute("users", users);
	          
	        getServletContext().getRequestDispatcher("/user_index.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
