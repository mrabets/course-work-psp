package crud.tcases;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.tcases.TestCaseDB;


@WebServlet("/tcase_batch")
public class BatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("login") != null) {
				String commit = request.getParameter("commit");
		
				String[] testCasesIds = request.getParameterValues("testCases");
		
				switch(commit) {
				case "Delete":				
					for(int i = 0; i < testCasesIds.length; i++) {
						TestCaseDB.delete(Integer.parseInt(testCasesIds[i]));
					}
					break;
				case "Run":
					for(int i = 0; i < testCasesIds.length; i++) {
						TestCaseDB.markStatus(Integer.parseInt(testCasesIds[i]));
					}
					break;
				}
				
				response.sendRedirect(request.getContextPath() + "/tcase_index");	
			}
			else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}
		catch(NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/tcase_index");
		}
        catch(Exception ex) {   
        	throw new ServletException(ex);
        }
	}
}
