package crud.tcases;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import custom.DefaultValue;
import mysql.tcases.TestCase;
import mysql.tcases.TestCaseDB;

@WebServlet("/tcase_update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException { 
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			try {
	            int id = Integer.parseInt(request.getParameter("id"));
	            TestCase testCase = new TestCaseDB().selectOne(id);
	            if(testCase!=null) {
	            	request.setAttribute("frameworks", DefaultValue.frameworks);
	                request.setAttribute("testCase", testCase);
	                getServletContext().getRequestDispatcher("/tcase_update.jsp").forward(request, response);
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
            String name = request.getParameter("name");
            name = URLEncoder.encode( name, "ISO-8859-1" );
            name = URLDecoder.decode( name, "UTF-8" );
            
            String framework = request.getParameter("framework");
            framework = URLEncoder.encode( framework, "ISO-8859-1" );
            framework = URLDecoder.decode( framework, "UTF-8" );
            
            boolean complete = false; 
            if (request.getParameter("complete") != null) {
            	complete = true; 
            }
            
            TestCase testCase = new TestCase(id, name, null, complete, framework);
            new TestCaseDB().update(testCase);
            
            response.sendRedirect(request.getContextPath() + "/tcase_index");
        }
        catch(Exception ex) {
             
        	throw new ServletException(ex);
        }
	}
}
