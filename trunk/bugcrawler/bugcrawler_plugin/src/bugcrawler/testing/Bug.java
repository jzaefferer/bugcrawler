package bugcrawler.testing;

public class Bug {

    private boolean solved = false;
    private String bugname="";
    private String creator="";
    private String creationdate="";
    private String lastmodifier="";
    private String lastmodificationdate="";
    
    public Bug(){}
    
    public Bug(String bugname, 
	       String creator, 
	       String creationdate,
	       String lastmodifier,
	       String lastmodificationdate){
	this.bugname=bugname;
	this.creator=creator;
	this.creationdate=creationdate;
	this.lastmodifier=lastmodifier;
	this.lastmodificationdate=lastmodificationdate;
	
    }
    public String getCreationdate() {
        return creationdate;
    }
    
    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public String getLastmodificationdate() {
        return lastmodificationdate;
    }
    
    public void setLastmodificationdate(String lastmodificationdate) {
        this.lastmodificationdate = lastmodificationdate;
    }
    
    public String getLastmodifier() {
        return lastmodifier;
    }
    
    public void setLastmodifier(String lastmodifier) {
        this.lastmodifier = lastmodifier;
    }

    public String getBugname() {
        return bugname;
    }

    public void setBugname(String bugname) {
        this.bugname = bugname;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
