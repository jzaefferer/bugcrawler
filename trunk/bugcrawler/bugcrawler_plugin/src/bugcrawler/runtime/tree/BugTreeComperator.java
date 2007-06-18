package bugcrawler.runtime.tree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.ColumnTitles;

public class BugTreeComperator extends ViewerSorter {

	private String columnToSort;

	private int dir;

	public BugTreeComperator(String columnToSort, int dir) {
		super();
		this.columnToSort = columnToSort;
		this.dir = dir;
	}

	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof Bug) {
			int returnValue = 0;
			
			if(columnToSort == ColumnTitles.Ticket.toString()){
				returnValue = getTicketNumber(e1)-getTicketNumber(e2);
			}else if(columnToSort == ColumnTitles.Summary.toString()){
				returnValue = ((Bug) e2).getSummary().compareTo(((Bug)e1).getSummary());
			}else if(columnToSort == ColumnTitles.Component.toString()){
				returnValue = ((Bug) e2).getComponent().compareTo(((Bug)e1).getComponent());
			}else if(columnToSort == ColumnTitles.Version.toString()){
				returnValue = ((Bug) e2).getVersion().compareTo(((Bug)e1).getVersion());
			}else if(columnToSort == ColumnTitles.Milestone.toString()){
				returnValue = ((Bug) e2).getMilestone().compareTo(((Bug)e1).getMilestone());
			}else if(columnToSort == ColumnTitles.Type.toString()){
				returnValue = ((Bug) e2).getType().compareTo(((Bug)e1).getType());
			}else if(columnToSort == ColumnTitles.Severity.toString()){
				returnValue = ((Bug) e2).getSeverity().compareTo(((Bug)e1).getSeverity());
			}else if(columnToSort == ColumnTitles.Owner.toString()){
				returnValue = ((Bug) e2).getOwner().compareTo(((Bug)e1).getOwner());
			}else if (columnToSort == ColumnTitles.Created.toString()) {
				returnValue = ((Bug) e2).getCreated().compareTo(((Bug) e1).getCreated());
			}
			if (this.dir == SWT.DOWN) {
				returnValue = returnValue * -1;
			}
			return returnValue;
		}
		return 0;
	}
	
	private int getTicketNumber(Object o){
		String ticketNumberAsString = ((Bug) o).getTicket().substring(1);
		return Integer.parseInt(ticketNumberAsString);
	}
}
