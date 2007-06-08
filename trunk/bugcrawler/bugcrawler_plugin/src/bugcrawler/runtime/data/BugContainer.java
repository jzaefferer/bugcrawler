package bugcrawler.runtime.data;

import java.util.ArrayList;

public class BugContainer {

	private Priority priority;
	
	private ProjectNew project;
	
	private ArrayList<BugNew> bugs = new ArrayList<BugNew>();
	
	public BugContainer(Priority priority,ProjectNew project){
		this.priority = priority;
		this.project = project;
	}
	
	public Priority getPriority(){
		return priority;
	}
	
	public void addBug(BugNew bug){
		bugs.add(bug);
	}
	
	public Object[] getBugs(){
		return bugs.toArray();
	}
	
	public ProjectNew getProject(){
		return project;
	}
}
