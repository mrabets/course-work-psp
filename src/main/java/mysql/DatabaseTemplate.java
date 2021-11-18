package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import config.Config;

public abstract class DatabaseTemplate<T> {
    public ArrayList<T> select() {       
        ArrayList<T> records = new ArrayList<T>();
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
            	Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(getSqlSelect());
                while(resultSet.next()){                	
                   records.add(selectResult(resultSet));
                }
            }
        }
        catch(Exception ex){ ex.printStackTrace(); }
        return records;
    }
    
    public T selectOne(int id) {   	
    	T record = null;   	
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                try (PreparedStatement preparedStatement = conn.prepareStatement(getSqlSelectOne())){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()){
                        record = selectOneResult(resultSet);
                    }
                }
            }
        }
        catch(Exception ex){ ex.printStackTrace(); }      
        return record;
    }
    
    public int insert(T record) {
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                try(PreparedStatement preparedStatement = conn.prepareStatement(getSqlInsert())){ 
                    return insertResult(preparedStatement, record);
                }
            }
        }
        catch(Exception ex){ ex.printStackTrace(); }
        return 0;
    }
    
    public int update(T record) {
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){                                 
                try(PreparedStatement preparedStatement = conn.prepareStatement(getSqlUpdate())){
                	return updateResult(preparedStatement, record);
                }
            }
        } catch(Exception ex){ ex.printStackTrace(); }
        return 0;
    }
    
	public int delete(int id) {
		try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try(Connection conn = DriverManager.getConnection(url, username, password)){
                try(PreparedStatement preparedStatement = conn.prepareStatement(getSqlDelete())){
                    preparedStatement.setInt(1, id); 
                    return preparedStatement.executeUpdate();
                }
            }
        } catch(Exception ex){ ex.printStackTrace(); }
	    return 0;
	}
	
	public abstract String getSqlSelect();
	public abstract String getSqlSelectOne();
	public abstract String getSqlInsert();
	public abstract String getSqlUpdate();
	public abstract String getSqlDelete();
	public abstract T selectResult(ResultSet resultSet) throws Exception;	
	public abstract T selectOneResult(ResultSet resultSet) throws Exception;		
	public abstract int insertResult(PreparedStatement preparedStatement, T record) throws Exception;
	public abstract int updateResult(PreparedStatement preparedStatement, T record) throws Exception;
	
	protected final static String url = Config.url.label;
	protected final static String username = Config.username.label;
	protected final static String password = Config.password.label;
	protected final static String driver = Config.driver.label;
}