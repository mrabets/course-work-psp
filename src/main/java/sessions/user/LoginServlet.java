package sessions.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static String url = "jdbc:mysql://localhost:3306/db_utest?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static String username = "mrabets";
    private static String password = "12102001Km_";
    
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("login")!=null) {
			response.sendRedirect(request.getContextPath());
		}
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		try {
			if (UserDB.isUserExist(login, password)) {
				session.setAttribute("login", login);
				request.setAttribute("alert", "<div class=\"alert alert-success\" role=\"alert\">\n"
						+ "Вы вошли в систему"
						+ "</div>");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errorMsg", "<div class=\"alert alert-danger\" role=\"alert\">\n"
						+ "Неправильный логин или пароль"
						+ "</div>");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
