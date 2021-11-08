package mysql.tcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import config.Config;

public class TestCaseDB {
	private final static String url = Config.url.label;
    private final static String username = Config.username.label;
    private final static String password = Config.password.label;
    private final static String driver = Config.driver.label;
    
    public static ArrayList<TestCase> select() {
         
        ArrayList<TestCase> unitTests = new ArrayList<TestCase>();
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM test_cases");
                while(resultSet.next()){
                      
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String last_launch = resultSet.getString(3);
                    boolean complete = resultSet.getBoolean(4);
                    String framework = resultSet.getString(5);
                    
                    TestCase unitTest = new TestCase(id, name, last_launch, complete, framework);
                    unitTests.add(unitTest);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return unitTests;
    }
    
    public static TestCase selectOne(int id) {
    	
    	TestCase unitTest = null;
    	
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "SELECT id, name, framework FROM test_cases WHERE id=?";
                
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next()){
 
                    	int prodId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String framework = resultSet.getString(3);
                        
                        unitTest = new TestCase(prodId, name, null, false, framework);
                    }
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }      
        return unitTest;
    }
    
    public static int insert(TestCase testCase) {
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "INSERT INTO test_cases (name, framework) Values (?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, testCase.getName());
                    preparedStatement.setString(2, testCase.getFramework());
                    
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        return 0;
    }
    
    public static int update(TestCase testCase) {
        
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "UPDATE test_cases SET name = ?, framework = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                	preparedStatement.setString(1, testCase.getName());
                    preparedStatement.setString(2, testCase.getFramework());
                    preparedStatement.setInt(3, testCase.getId());
                      
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
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                String sql = "DELETE FROM test_cases WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                      
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        return 0;
    }
}
