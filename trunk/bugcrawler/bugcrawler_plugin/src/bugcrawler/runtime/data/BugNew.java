package bugcrawler.runtime.data;

import java.util.Date;


public class BugNew {

    private boolean solved = false; 
    private BugContainer bugContainer;
    private String name;
    private String creator;
    private Date creationDate;
    private String lastModifier;
    private Date lastModificationDate;
    
    public BugNew(String name, 
	       String creator, 
	       Date creationDate,
	       String lastModifier,
	       Date lastModificationDate,
	       Priority priority,
	       ProjectNew project){
	this.name=name;
	this.creator=creator;
	this.creationDate=creationDate;
	this.lastModifier=lastModifier;
	this.lastModificationDate=lastModificationDate;
	this.bugContainer=project.getBugContainer(priority);
	project.addBugToProject(this);
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

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
    
    public String[] getValues(){
	return new String[]{    
            name,
            creator,
            creationDate.toString(),
            lastModifier,
            lastModificationDate.toString()
	};
    }

    public BugContainer getBugContainer(){
    	return bugContainer;
    }
    
    public Priority getPriority(){
    	return bugContainer.getPriority();
    }
}
