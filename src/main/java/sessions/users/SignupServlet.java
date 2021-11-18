package sessions.users;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.users.User;
import mysql.users.UserDB;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
		login = URLEncoder.encode( login, "ISO-8859-1" );
        login = URLDecoder.decode( login, "UTF-8" );
        
		String password = request.getParameter("password");
		password = URLEncoder.encode( password, "ISO-8859-1" );
		password = URLDecoder.decode( password, "UTF-8" );
        
		String password_confirm = request.getParameter("password_confirm");
		password_confirm = URLEncoder.encode( password_confirm, "ISO-8859-1" );
		password_confirm = URLDecoder.decode( password_confirm, "UTF-8" );
		
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
					new UserDB().insert(new User(login, password, false));
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
