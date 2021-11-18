package mysql.utests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mysql.DatabaseTemplate;

public class UnitTestDB extends DatabaseTemplate<UnitTest> {       
    @Override
    public String getSqlSelect() { return "SELECT * FROM unit_tests"; }
    
    @Override
    public String getSqlSelectOne() { return "SELECT * FROM unit_tests WHERE id = ?"; }
    
    @Override
    public String getSqlInsert() { return "INSERT INTO unit_tests (name, errors_number, lead_time, created_at) Values (?, ?, ?, ?)"; }
    
    @Override
    public String getSqlUpdate() { return "UPDATE unit_tests SET name = ?, errors_number = ?, lead_time = ?, created_at = ? WHERE id = ?"; }
    
    @Override
    public String getSqlDelete() { return "DELETE FROM unit_tests WHERE id = ?"; }
    
    public UnitTest selectResult(ResultSet resultSet) throws Exception {
    	return new UnitTest(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getTime(4), resultSet.getDate(5));
	}
    
    public UnitTest selectOneResult(ResultSet resultSet) throws Exception {
        return new UnitTest(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getTime(4), resultSet.getDate(5));
	}

    public int insertResult(PreparedStatement preparedStatement, UnitTest unitTest) throws Exception {
    	preparedStatement.setString(1, unitTest.getName());
        preparedStatement.setInt(2, unitTest.geterrorsNumber());
        preparedStatement.setTime(3, unitTest.getleadTime());
        preparedStatement.setDate(4, unitTest.getcreatedAt());
        return preparedStatement.executeUpdate();
	}

    public int updateResult(PreparedStatement preparedStatement, UnitTest unitTest) throws Exception {
    	preparedStatement.setString(1, unitTest.getName());
        preparedStatement.setInt(2, unitTest.geterrorsNumber());
        preparedStatement.setTime(3, unitTest.getleadTime());
        preparedStatement.setDate(4, unitTest.getcreatedAt());
        preparedStatement.setInt(5, unitTest.getId()); 
        return preparedStatement.executeUpdate();
	}
}