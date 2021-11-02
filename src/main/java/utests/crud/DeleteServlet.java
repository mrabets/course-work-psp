package utests.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.utests.UnitTestDB;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				UnitTestDB.delete(id);
				
				response.sendRedirect(request.getContextPath() + "/show");
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
