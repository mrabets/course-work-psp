package mysql.utests;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class UnitTest implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private int id;
    private String name;
    private int errorsNumber;
    private Time leadTime;
    private Date createdAt;
     
    public UnitTest(){ }
    public UnitTest(String name, int errors_number, Time lead_time, Date created_at) {   
        this.name = name;
        this.errorsNumber = errors_number;
        this.leadTime = lead_time;
        this.createdAt = created_at;
    }
    public UnitTest(int id, String name, int errors_number, Time lead_time, Date created_at){
         
        this.id = id;
        this.name = name;
        this.errorsNumber = errors_number;
        this.leadTime = lead_time;
        this.createdAt = created_at;
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
 
    public int geterrorsNumber() {
        return errorsNumber;
    }
 
    public void seterrorsNumber(int errors_number) {
        this.errorsNumber = errors_number;
    }
    
    public Time getleadTime() {
        return leadTime;
    }
 
    public void setleadTime(Time lead_time) {
        this.leadTime = lead_time;
    }
	
    public Date getcreatedAt() {
		return createdAt;
	}
    
	public void setcreatedAt(Date created_at) {
		this.createdAt = created_at;
	}
}
