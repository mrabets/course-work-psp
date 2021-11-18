package crud.utests;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.utests.UnitTest;
import mysql.utests.UnitTestDB;

@WebServlet("/utest_index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		ArrayList<UnitTest> unit_tests = new UnitTestDB().select();
        request.setAttribute("unit_tests", unit_tests);
          
        getServletContext().getRequestDispatcher("/utest_index.jsp").forward(request, response);
	}
}
