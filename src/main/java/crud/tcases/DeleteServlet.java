package crud.tcases;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.tcases.TestCaseDB;

@WebServlet("/tcase_multiple_delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			try {
				String[] testCasesIds = request.getParameterValues("testCases");
				
				for(int i = 0; i < testCasesIds.length; i++) {
				   TestCaseDB.delete(Integer.parseInt(testCasesIds[i]));
				}
				
				response.sendRedirect(request.getContextPath() + "/tcase_index");
			}
			catch(NullPointerException e) {
				response.sendRedirect(request.getContextPath() + "/tcase_index");
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
