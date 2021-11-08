package crud.tcases;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.tcases.TestCase;
import mysql.tcases.TestCaseDB;
import custom.DefaultValue;

@WebServlet("/tcase_create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {			
			request.setAttribute("frameworks", DefaultValue.frameworks);
			getServletContext().getRequestDispatcher("/tcase_create.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String name = request.getParameter("name");
            String framework = request.getParameter("framework");
            
            TestCase testCase = new TestCase(name, null, false, framework);
            TestCaseDB.insert(testCase);
            
            response.sendRedirect(request.getContextPath() + "/tcase_index");
        }
        catch(Exception ex) {           
        	throw new ServletException(ex);       	    
        }
	}
}
