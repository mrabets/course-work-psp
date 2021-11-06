package mysql.users;

import java.io.Serializable;

public class User implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private int id;
    private String login;
    private String password;
    private Boolean isAdmin; 
    
    public User(){ }
    public User(String login, String password, Boolean isAdmin) {   
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public User(int id, String login, String password, Boolean isAdmin){ 
        this.id = id;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }
     
    public int getId() {
        return id;
    }
     
    public String getLogin() {
        return login;
    }
 
    public void setLogin(String login) {
        this.login = login;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getisAdmin() {
        return isAdmin;
    }
 
    public void setisAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
