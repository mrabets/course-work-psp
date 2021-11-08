package mysql.tcases;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class TestCase implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private int id;
    private String name;
    private String lastLaunch;    
    private boolean complete;
    private String framework;
     
	public TestCase(){ }
    public TestCase(String name, String lastLaunch, boolean complete, String framework) {   
        this.name = name;
        this.lastLaunch = lastLaunch;
        this.complete = complete;
        this.framework = framework;
    }
    public TestCase(int id, String name, String lastLaunch, boolean complete, String framework){
         
        this.id = id;
        this.name = name;
        this.lastLaunch = lastLaunch;
        this.complete = complete;
        this.framework = framework;
    }
     
    public int getId() {
        return id;
    }
     
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
	
    public String getlastLaunch() {
		return lastLaunch;
	}
    
	public void setlastLaunch(String lastLaunch) {
		this.lastLaunch = lastLaunch;
	}
	
	public boolean getComplete() {
        return complete;
    }
 
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    
    public String getFramework() {
		return framework;
	}
	public void setFramework(String framework) {
		this.framework = framework;
	}
}
