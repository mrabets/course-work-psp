package utests.crud;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.utests.UnitTest;
import mysql.utests.UnitTestDB;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String name = request.getParameter("name");
            int errorsNumber = Integer.parseInt(request.getParameter("errorsNumber"));
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            java.sql.Time leadTime = new java.sql.Time(formatter.parse(request.getParameter("leadTime")).getTime());
            
            java.util.Date temp = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createdAt"));
            java.sql.Date createdAt = new java.sql.Date(temp.getTime());
            
            UnitTest unitTest = new UnitTest(name, errorsNumber, leadTime, createdAt);
            UnitTestDB.insert(unitTest);
            
            response.sendRedirect(request.getContextPath() + "/show");
        }
        catch(Exception ex) {           
        	throw new ServletException(ex);       	    
        }
	}
}
