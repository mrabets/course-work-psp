package sessions.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {    
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.invalidate();
		request.setAttribute("alert", "<div class=\"alert alert-success\" role=\"alert\">\n"
				+ "Вы вышли из системы"
				+ "</div>");
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
