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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static String url = "jdbc:mysql://localhost:3306/db_utest?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("login") != null) {
			response.sendRedirect(request.getContextPath());
		}

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");
		
		if (!password.equals(password_confirm)) {
			request.setAttribute("errorMsg", "<div class=\"alert alert-danger\" role=\"alert\">\n"
					+ "Пароль и подтверждение пароля не совпадают"
					+ "</div>");
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		} else
			try {
				if (UserDB.isUserExist(login)) {
					request.setAttribute("errorMsg", "<div class=\"alert alert-danger\" role=\"alert\">\n"
							+ "Такой пользователь уже существует"
							+ "</div>");
					request.getRequestDispatcher("/signup.jsp").forward(request, response);
				}
				else {
					UserDB.insert(new User(login, password));
					session.setAttribute("login", login);
					request.setAttribute("alert", "<div class=\"alert alert-success\" role=\"alert\">\n"
							+ "Вы успешно зарегестрировались в системе"
							+ "</div>");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
