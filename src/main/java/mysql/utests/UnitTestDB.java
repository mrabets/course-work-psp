package mysql.utests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UnitTestDB {
	private static String url = "jdbc:mysql://localhost:3306/db_utest?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static String username = "mrabets";
    private static String password = "12102001Km_";
    
    public static ArrayList<UnitTest> select() {
         
        ArrayList<UnitTest> unitTests = new ArrayList<UnitTest>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM unit_tests");
                while(resultSet.next()){
                      
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int errors_number = resultSet.getInt(3);
                    java.sql.Time lead_time = resultSet.getTime(4);
                    java.sql.Date created_at = resultSet.getDate(5);
                    
                    UnitTest unitTest = new UnitTest(id, name, errors_number, lead_time, created_at);
                    unitTests.add(unitTest);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return unitTests;
    }
    
    public static UnitTest selectOne(int id) {
    	
    	UnitTest unitTest = null;
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "SELECT * FROM unit_tests WHERE id=?";
                
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next()){
 
                    	int prodId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        int errors_number = resultSet.getInt(3);
                        java.sql.Time lead_time = resultSet.getTime(4);
                        java.sql.Date created_at = resultSet.getDate(5);
                        
                        unitTest = new UnitTest(prodId, name, errors_number, lead_time, created_at);
                    }
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }      
        return unitTest;
    }
    
    public static int insert(UnitTest unitTest) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "INSERT INTO unit_tests (name, errors_number, lead_time, created_at) Values (?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, unitTest.getName());
                    preparedStatement.setInt(2, unitTest.geterrorsNumber());
                    preparedStatement.setTime(3, unitTest.getleadTime());
                    preparedStatement.setDate(4, unitTest.getcreatedAt());
                    
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        return 0;
    }
    
    public static int update(UnitTest unitTest) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "UPDATE unit_tests SET name = ?, errors_number = ?, lead_time = ?, created_at = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                	preparedStatement.setString(1, unitTest.getName());
                    preparedStatement.setInt(2, unitTest.geterrorsNumber());
                    preparedStatement.setTime(3, unitTest.getleadTime());
                    preparedStatement.setDate(4, unitTest.getcreatedAt());
                    preparedStatement.setInt(5, unitTest.getId());
                      
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        return 0;
    }
    
    public static int delete(int id) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "DELETE FROM unit_tests WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                      
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        return 0;
    }
}
