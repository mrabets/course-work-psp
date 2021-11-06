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

@WebServlet("/user_update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException { 
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			try {
	            int id = Integer.parseInt(request.getParameter("id"));
	            User user = UserDB.selectOne(id);
	            if(user!=null) {
	                request.setAttribute("user", user);
	                getServletContext().getRequestDispatcher("/user_update.jsp").forward(request, response);
	            }
	            else {
	                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
	            }
	        }
	        catch(Exception ex) {
	        	throw new ServletException(ex);
	        }
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	 
		try {
            int id = Integer.parseInt(request.getParameter("id"));
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            boolean admin = request.getParameter("isAdmin") != null ? true : false;      
            
            User user = new User(id, login, password, admin);
            UserDB.update(user);
            response.sendRedirect(request.getContextPath() + "/admin_panel");
        }
        catch(Exception ex) {
             
        	throw new ServletException(ex);
        }
	}
}
