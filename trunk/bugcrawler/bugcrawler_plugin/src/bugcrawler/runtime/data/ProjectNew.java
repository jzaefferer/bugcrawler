package bugcrawler.runtime.data;

import java.util.Date;

public class ProjectNew {

	private String name;

	private String creator;

	private Date creationDate;

	private BugContainer[] bugsContainers = { 
			new BugContainer(Priority.Highest,this), 
			new BugContainer(Priority.High,this),
			new BugContainer(Priority.Medium,this), 
			new BugContainer(Priority.Low,this),
			new BugContainer(Priority.Lowest,this) 
	};

	public void addBugToProject(BugNew bug) {
		BugContainer bugContainer = getBugContainer(bug.getPriority());
		bugContainer.addBug(bug);
	}

	public BugContainer getBugContainer(Priority priority){
		for(BugContainer bugContainer:bugsContainers){
			if(bugContainer.getPriority()==priority){
				return bugContainer;
			}
		}
		return null;
	}
	
	public BugContainer[] getBugContainers(){
		return bugsContainers;
	}

	public ProjectNew(String name, String creator, Date creationDate) {
		this.name = name;
		this.creator = creator;
		this.creationDate = creationDate;
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
