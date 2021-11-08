package crud.utests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.utests.UnitTestDB;

@WebServlet("/unit_test_report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		UnitTestDB.exportToFile();
		
		getServletContext().getRequestDispatcher("/utest_report.jsp").forward(request, response);
	}
}
