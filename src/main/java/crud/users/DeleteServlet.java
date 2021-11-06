package crud.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.users.UserDB;
import mysql.utests.UnitTestDB;

@WebServlet("/user_delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("is_admin") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				UserDB.delete(id);
				
				response.sendRedirect(request.getContextPath() + "/admin_panel");
			}
	        catch(Exception ex) {   
	        	throw new ServletException(ex);
	        }
		}
		else {
			response.sendRedirect(request.getContextPath() + "/login");
		}	
	}
}
