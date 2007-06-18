package bugcrawler.runtime.data;

import java.util.Date;

public class Bug {

	private boolean solved = false;

	private BugContainer bugContainer;

	private String ticket;

	private String summary;

	private String component;

	private String version;

	private String milestone;

	private String type;

	private String severity;

	private String owner;

	private Date created;

	public Bug(String ticket, String summary, String component, String version, String milestone,
			String type, String severity, String owner, Date created, Priority priority, Project project) {
		this.ticket = ticket;
		this.summary = summary;
		this.component = component;
		this.version = version;
		this.milestone = milestone;
		this.type = type;
		this.severity = severity;
		this.owner = owner;
		this.created = created;
		this.bugContainer = project.getBugContainer(priority);
		project.addBugToProject(this);
	}
	
	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public BugContainer getBugContainer() {
		return bugContainer;
	}

	public void setBugContainer(BugContainer bugContainer) {
		this.bugContainer = bugContainer;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Priority getPriority() {
		return bugContainer.getPriority();
	}
}
