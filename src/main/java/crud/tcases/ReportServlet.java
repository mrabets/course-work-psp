package crud.tcases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.tcases.TestCaseDB;

@WebServlet("/test_case_report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		new TestCaseDB().exportToFile();
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
	    try(BufferedReader br = new BufferedReader(new FileReader("Reportt.txt"))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        String everything = sb.toString();
	        request.setAttribute("everything", everything);
	    }
	    
	    
	    
		getServletContext().getRequestDispatcher("/tcase_report.jsp").forward(request, response);
	}
}
