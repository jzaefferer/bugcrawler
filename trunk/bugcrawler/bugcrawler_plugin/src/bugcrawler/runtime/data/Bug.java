package bugcrawler.runtime.data;

import java.util.Date;

public class Bug {

	private boolean solved = false;

	private Priority priority;

	private String name;

	private String creator;

	private Date creationDate;

	private String lastModifier;

	private Date lastModificationDate;

	public Bug(String name, String creator, Date creationDate, String lastModifier, Date lastModificationDate) {
		this.name = name;
		this.creator = creator;
		this.creationDate = creationDate;
		this.lastModifier = lastModifier;
		this.lastModificationDate = lastModificationDate;

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

	public String[] getValues() {
		return new String[] { name, creator, creationDate.toString(), lastModifier,
				lastModificationDate.toString() };
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}