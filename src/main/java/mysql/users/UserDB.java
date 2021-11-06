package mysql.users;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import custom.Encryption;

import javax.servlet.ServletException;

import config.Config;

public class UserDB {
	private final static String url = Config.url.label;
    private final static String username = Config.username.label;
    private final static String dbPassword = Config.password.label;
    private final static String driver = Config.driver.label;
    
    public static ArrayList<User> select() throws ServletException {
         
        ArrayList<User> users = new ArrayList<User>();
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, dbPassword)){
                  
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
                while (resultSet.next()){   
                	int id = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    Boolean isAdmin = resultSet.getBoolean(4);
                    
                    User user = new User(id, login, password, isAdmin);
                    users.add(user);
                }
            }
        }
        catch(Exception ex){
        	throw new ServletException(ex);
        }
        return users;
    }
    
    public static int insert(User user) {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection connector = DriverManager.getConnection(url, username, dbPassword)){
                  
                String sql = "INSERT INTO user (login, password) VALUES (?, SHA1(?))";
                try (PreparedStatement preparedStatement = connector.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    
                    return preparedStatement.executeUpdate();
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
            try (Connection conn = DriverManager.getConnection(url, username, dbPassword)){
                  
                String sql = "DELETE FROM user WHERE id = ?";
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
    
    public static boolean isUserExist(String login, String password) throws ServletException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class.forName(driver).getDeclaredConstructor().newInstance();
        try (Connection connector = DriverManager.getConnection(url, username, dbPassword)){
            
        	String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
        	try (PreparedStatement preparedStatement = connector.prepareStatement(sql)){
                preparedStatement.setString(1, login);
                password = Encryption.encryptPassword(password);
                preparedStatement.setString(2, password);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {   
                    
                    if (login.equals(resultSet.getString(2)) && password.equals(resultSet.getString(3))) {
                    	return true;
                    }
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
	    return false;
	}
    
    public static boolean isUserExist(String login) throws ServletException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class.forName(driver).getDeclaredConstructor().newInstance();
        try (Connection connector = DriverManager.getConnection(url, username, dbPassword)){
            
        	String sql = "SELECT * FROM user WHERE login = ?";
        	try (PreparedStatement preparedStatement = connector.prepareStatement(sql)){
                preparedStatement.setString(1, login);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {   
                    
                    if (login.equals(resultSet.getString(2))) {
                    	return true;
                    }
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
	    return false;
	}
    
    public static boolean isAdmin(String login) throws ServletException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class.forName(driver).getDeclaredConstructor().newInstance();
        try (Connection connector = DriverManager.getConnection(url, username, dbPassword)){
            
        	String sql = "SELECT * FROM user WHERE login = ?";
        	try (PreparedStatement preparedStatement = connector.prepareStatement(sql)){
                preparedStatement.setString(1, login);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {   
                    
                    if (resultSet.getBoolean(4)) {
                    	return true;
                    }
                }
            }
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
	    return false;
	}
}
