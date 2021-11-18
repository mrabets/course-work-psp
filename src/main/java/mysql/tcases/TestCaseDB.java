package mysql.tcases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import mysql.DatabaseTemplate;

public class TestCaseDB extends DatabaseTemplate<TestCase> {
    public static int markStatus(int id) {
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String sql = "UPDATE test_cases SET is_complete = 1, last_launch = NOW() WHERE id = ?;";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){ ex.printStackTrace(); }
        return 0;
    }
    
	@SuppressWarnings("resource")
	public void exportToFile() throws IOException {	
   	 ArrayList<TestCase> testCases = select(); 
   	 FileWriter fw = new FileWriter(new File("Reportt.txt"), StandardCharsets.UTF_8);
   	 Formatter formatter = new Formatter(fw);
   	 formatter.format(
   			 "%3s %80s %25s %20s %25s",
   			 "ID*", "Name*", "Framework*", "Status*", "Last launch*"
   			 );
   	 fw.append('\n');
   	 
   	 for (TestCase testCase : testCases) {
   		 formatter = new Formatter(fw);
         formatter.format(
        		 "%3s %80s %25s %20s %25s",
        		String.valueOf(testCase.getId()),
        		String.valueOf(testCase.getName()),
        		String.valueOf(testCase.getFramework()),
        		String.valueOf(testCase.getComplete()),
        		String.valueOf(testCase.getlastLaunch()));     
	        fw.append('\n');
   	 }
   	 fw.flush();
   	 fw.close();
   }
    
    @Override
    public String getSqlSelect() { return "SELECT * FROM test_cases"; }
    
    @Override
    public String getSqlSelectOne() { return "SELECT id, name, framework, is_complete FROM test_cases WHERE id = ?"; }
    
    @Override
    public String getSqlInsert() { return "INSERT INTO test_cases (name, framework) Values (?, ?)"; }
    
    @Override
    public String getSqlUpdate() { return "UPDATE test_cases SET name = ?, framework = ?, is_complete = ? WHERE id = ?"; }
    
    @Override
    public String getSqlDelete() { return "DELETE FROM test_cases WHERE id = ?"; }
    
    public TestCase selectResult(ResultSet resultSet) throws Exception {
    	return new TestCase(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getString(5));
	}
    
    public TestCase selectOneResult(ResultSet resultSet) throws Exception {
    	return new TestCase(resultSet.getInt(1), resultSet.getString(2), null, resultSet.getBoolean(4), resultSet.getString(3));
	}

    public int insertResult(PreparedStatement preparedStatement, TestCase testCase) throws Exception {
    	preparedStatement.setString(1, testCase.getName());
        preparedStatement.setString(2, testCase.getFramework());
        return preparedStatement.executeUpdate();
	}

    public int updateResult(PreparedStatement preparedStatement, TestCase testCase) throws Exception {
    	preparedStatement.setString(1, testCase.getName());
        preparedStatement.setString(2, testCase.getFramework());
        preparedStatement.setBoolean(3, testCase.getComplete());
        preparedStatement.setInt(4, testCase.getId());
        return preparedStatement.executeUpdate();
	}
}
