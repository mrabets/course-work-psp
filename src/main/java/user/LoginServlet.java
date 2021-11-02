package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static String url = "jdbc:mysql://localhost:3306/db_utest?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static String username = "mrabets";
    private static String password = "12102001Km_";
    
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("login")!=null) 
		{
			response.sendRedirect("welcome.jsp");
		}

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			Connection con = DriverManager.getConnection(url, username, password);
			
			if(request.getParameter("btn_login")!=null) //check login button click event not null
			{
				String dblogin,dbpassword;
				
				String login, password;
				
				login = request.getParameter("login"); //txt_email
				password = request.getParameter("password"); //txt_password
				
				PreparedStatement pstmt=null; //create statement
				
				pstmt=con.prepareStatement("select * from user where login=? AND password=?"); //sql select query 
				pstmt.setString(1, login);
				pstmt.setString(2, password);
				
				ResultSet rs=pstmt.executeQuery(); //execute query and store in resultset object rs.
				
				if(rs.next())
				{
					dblogin=rs.getString("login");
					dbpassword=rs.getString("password");
					
					if(login.equals(dblogin) && password.equals(dbpassword))
					{
						session.setAttribute("login", dblogin); //session name is login and store fetchable database email address
						response.sendRedirect("welcome.jsp"); //after login success redirect to welcome.jsp page
					}
				}
				else
				{
					request.setAttribute("errorMsg","Неверный логин или пароль"); //invalid error message for email or password wrong
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				
				con.close(); 
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
