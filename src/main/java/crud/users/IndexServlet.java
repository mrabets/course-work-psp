package crud.users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.users.User;
import mysql.users.UserDB;
import mysql.utests.UnitTest;
import mysql.utests.UnitTestDB;

@WebServlet("/admin_panel")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		ArrayList<User> users = UserDB.select();
        request.setAttribute("users", users);
          
        getServletContext().getRequestDispatcher("/user_show.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
