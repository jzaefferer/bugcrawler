package bugcrawler.runtime.data;

import java.util.ArrayList;
import java.util.Date;


public class Project {

    private String name;
    private String creator;
    private Date creationDate;
    private ArrayList<Bug> bugs = new ArrayList<Bug>();
    
    public void addBugToProject(Bug bug){
	bugs.add(bug);
    }
    
    public Object[] getBugs(){
	return bugs.toArray();
    }
    
    public Project(String name, 
	       String creator, 
	       Date creationDate){
	this.name=name;
	this.creator=creator;
	this.creationDate=creationDate;	
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
