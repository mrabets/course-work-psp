package crud.tcases;

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
		TestCaseDB.exportToFile();
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		getServletContext().getRequestDispatcher("/tcase_report.jsp").forward(request, response);
	}
}
