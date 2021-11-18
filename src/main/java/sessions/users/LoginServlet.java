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
import mysql.users.UserDB;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
   
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
		login = URLEncoder.encode( login, "ISO-8859-1" );
        login = URLDecoder.decode( login, "UTF-8" );
        
		String password = request.getParameter("password");
		password = URLEncoder.encode( password, "ISO-8859-1" );
		password = URLDecoder.decode( password, "UTF-8" );
		
		try {
			if (UserDB.isUserExist(login, password)) {
				session.setAttribute("login", login);
				if (UserDB.isAdmin(login)) {
					session.setAttribute("is_admin", true);
				}
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
