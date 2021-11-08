package crud.users;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat; 

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

@WebServlet("/user_create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			getServletContext().getRequestDispatcher("/user_create.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();

			if (session.getAttribute("is_admin") == null) {
				response.sendRedirect(request.getContextPath());
				return;
			}
			
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            boolean admin = request.getParameter("isAdmin") != null ? true : false;
            if (UserDB.isUserExist(login)) {
				request.setAttribute("errorMsg", "<div class=\"alert alert-danger\" role=\"alert\">\n"
						+ "Такой пользователь уже существует"
						+ "</div>");
				request.getRequestDispatcher("/user_create.jsp").forward(request, response);
			}
			else {
				UserDB.insert(new User(login, password, admin));
				response.sendRedirect(request.getContextPath() + "/user_index");
			}
        }
        catch(Exception ex) {           
        	throw new ServletException(ex);       	    
        }
	}
}
