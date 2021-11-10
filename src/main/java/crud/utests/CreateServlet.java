package crud.utests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.utests.UnitTest;
import mysql.utests.UnitTestDB;
import java.net.URLEncoder;
import java.net.URLDecoder;

@WebServlet("/utest_create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != null) {
			getServletContext().getRequestDispatcher("/utest_create.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
            String name = request.getParameter("name");
            name = URLEncoder.encode( name, "ISO-8859-1" );
            name = URLDecoder.decode( name, "UTF-8" );
            
            int errorsNumber = Integer.parseInt(request.getParameter("errorsNumber"));
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            java.sql.Time leadTime = new java.sql.Time(formatter.parse(request.getParameter("leadTime")).getTime());
            
            java.util.Date temp = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createdAt"));
            java.sql.Date createdAt = new java.sql.Date(temp.getTime());
            
            UnitTest unitTest = new UnitTest(name, errorsNumber, leadTime, createdAt);
            UnitTestDB.insert(unitTest);
            
            response.sendRedirect(request.getContextPath() + "/utest_index");
        }
        catch(Exception ex) {           
        	throw new ServletException(ex);       	    
        }
	}
}
