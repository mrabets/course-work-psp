package crud.tcases;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.tcases.TestCase;
import mysql.tcases.TestCaseDB;
import com.google.gson.*;

@WebServlet("/tcase_chart")
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		ArrayList<TestCase> test_cases = TestCaseDB.select();
		String test_cases_json = new Gson().toJson(test_cases);
		request.setAttribute("test_cases_json", test_cases_json);
          
        getServletContext().getRequestDispatcher("/tcase_chart.jsp").forward(request, response);
	}
}
