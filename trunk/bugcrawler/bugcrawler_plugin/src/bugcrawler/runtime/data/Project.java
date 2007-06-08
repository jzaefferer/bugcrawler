package bugcrawler.runtime.data;

import java.util.ArrayList;
import java.util.Date;

public class Project {

	private String name;

	private String creator;

	private Date creationDate;

	private ArrayList<BugContainer> bugsContainers = new ArrayList<BugContainer>();

	public void addBugToProject(Bug bug) {
		BugContainer bugContainer = getBugContainer(bug.getPriority());
		bugContainer.addBug(bug);
	}

	public BugContainer getBugContainer(Priority priority){
		
		//Search for an existing bugContainer
		for(BugContainer bugContainer:bugsContainers){
			if(bugContainer.getPriority()==priority){
				return bugContainer;
			}
		}
		//If no bugContainer with this Priority exists, create one.
		BugContainer newBugContainer = new BugContainer(priority,this);
		bugsContainers.add(newBugContainer);
		return newBugContainer;
	}
	
	public Object[] getBugContainers(){
		return bugsContainers.toArray();
	}

	public Project(String name, String creator, Date creationDate) {
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
