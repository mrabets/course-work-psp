package mysql.users;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import custom.Encryption;
import mysql.DatabaseTemplate;
import javax.servlet.ServletException;

public class UserDB extends DatabaseTemplate<User> {    
    public static boolean isUserExist(String login, String userPassword) throws ServletException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class.forName(driver).getDeclaredConstructor().newInstance();
        try (Connection connector = DriverManager.getConnection(url, username, password)){
        	try (PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?")){
                preparedStatement.setString(1, login);
                userPassword = Encryption.encryptPassword(userPassword);
                preparedStatement.setString(2, userPassword);  
                ResultSet resultSet = preparedStatement.executeQuery();     
                if (resultSet.next())
                    if (login.equals(resultSet.getString(2)) && userPassword.equals(resultSet.getString(3)))
                    	return true;
            }
        }
        catch(Exception ex){ ex.printStackTrace(); }
	    return false;
	}
    
    public static boolean isUserExist(String login) throws ServletException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class.forName(driver).getDeclaredConstructor().newInstance();
        try (Connection connector = DriverManager.getConnection(url, username, password)){
        	try (PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM user WHERE login = ?")){
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    if (login.equals(resultSet.getString(2)))
                    	return true;
            }
        }
        catch(Exception ex){ ex.printStackTrace(); }
	    return false;
	}
    
    public static boolean isAdmin(String login) throws ServletException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class.forName(driver).getDeclaredConstructor().newInstance();
        try (Connection connector = DriverManager.getConnection(url, username, password)){
        	try (PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM user WHERE login = ?")){
                preparedStatement.setString(1, login); 
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    if (resultSet.getBoolean(4))
                    	return true;
            }
        }
        catch(Exception ex){ ex.printStackTrace(); }
	    return false;
	}

	@Override
	public String getSqlSelect() { return "SELECT * FROM user"; }

	@Override
	public String getSqlSelectOne() { return "SELECT * FROM user WHERE id = ?"; }

	@Override
	public String getSqlInsert() { return "INSERT INTO user (login, password, is_admin) VALUES (?, SHA1(?), ?)";}

	@Override
	public String getSqlUpdate() { return "UPDATE user SET login = ?, password = SHA1(?), is_admin = ? WHERE id = ?";}

	@Override
	public String getSqlDelete() { return "DELETE FROM user WHERE id = ?";}

	@Override
	public User selectResult(ResultSet resultSet) throws Exception {    
        return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4));
	}
	
	@Override
	public User selectOneResult(ResultSet resultSet) throws Exception {
        return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4));
	}
	
	@Override
	public int insertResult(PreparedStatement preparedStatement, User user) throws Exception  {
		 preparedStatement.setString(1, user.getLogin());
         preparedStatement.setString(2, user.getPassword());
         preparedStatement.setBoolean(3, user.isAdmin());   
         return preparedStatement.executeUpdate();
	}
	
	@Override
	public int updateResult(PreparedStatement preparedStatement, User user) throws Exception {
		preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setBoolean(3, user.isAdmin());
        preparedStatement.setInt(4, user.getId());        
        return preparedStatement.executeUpdate();
	}
}
