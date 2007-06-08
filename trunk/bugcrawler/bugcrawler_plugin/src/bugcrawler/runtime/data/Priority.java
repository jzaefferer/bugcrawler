package bugcrawler.runtime.data;

/*public class Priority {
	
	private Priority(String name) {
		this.name = name;
	}

	private String name;
	
	@Override
	public String toString() {
		return name;
	}
	
	public static final Priority HIGH = new Priority("high");
	public static final Priority MEDIUM = new Priority("medium");
	public static final Priority LOW = new Priority("low");
	
	public static final Priority[] priorities = {
		HIGH,MEDIUM,LOW
	};
}*/

/**
 * Priorities in which the bugs can be categorized
 */
public enum Priority{
	Highest, High, Medium, Low, Lowest
}
