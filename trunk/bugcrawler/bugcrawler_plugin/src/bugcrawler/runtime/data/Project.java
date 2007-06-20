package bugcrawler.runtime.data;

import java.util.ArrayList;
import java.util.Date;

public class Project {

	private String name;

	private String owner;

	private Date created;

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

	public Project(String name, String owner, Date created) {
		this.name = name;
		this.owner = owner;
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
