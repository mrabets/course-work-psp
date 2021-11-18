package crud.utests;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.utests.UnitTestDB;

@WebServlet("/utest_multiple_delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			try {
				String[] unitTestsIds = request.getParameterValues("unitTests");
				
				for(int i = 0; i < unitTestsIds.length; i++) {
					//UnitTestDB.delete(Integer.parseInt(unitTestsIds[i]));
					new UnitTestDB().delete(Integer.parseInt(unitTestsIds[i]));
				}
				
				response.sendRedirect(request.getContextPath() + "/utest_index");
			}
			catch(NullPointerException e) {
				response.sendRedirect(request.getContextPath() + "/utest_index");
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
