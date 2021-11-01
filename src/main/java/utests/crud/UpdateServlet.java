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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	 
		try {
            int id = Integer.parseInt(request.getParameter("id"));
            UnitTest unitTest = UnitTestDB.selectOne(id);
            if(unitTest!=null) {
                request.setAttribute("unitTest", unitTest);
                getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
            }
            else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        }
        catch(Exception ex) {
        	throw new ServletException(ex);
        }
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	 
		try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int errorsNumber = Integer.parseInt(request.getParameter("errorsNumber"));
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            java.sql.Time leadTime = new java.sql.Time(formatter.parse(request.getParameter("leadTime")).getTime());
            
            java.util.Date temp = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createdAt"));
            java.sql.Date createdAt = new java.sql.Date(temp.getTime());
            
            UnitTest unitTest = new UnitTest(id, name, errorsNumber, leadTime, createdAt);
            UnitTestDB.update(unitTest);
            response.sendRedirect(request.getContextPath() + "/show");
        }
        catch(Exception ex) {
             
        	throw new ServletException(ex);
        }
	    }
}
