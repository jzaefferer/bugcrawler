package bugcrawler.testing;

import java.util.Date;

public class Project {

    private String name;
    private String creator;
    private Date creationDate;
    
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
