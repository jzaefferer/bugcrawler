package bugcrawler.testing.tree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.ColumnTitles;

public class BugTreeComperator extends ViewerSorter {

	private ColumnTitles columnToSort;

	private int dir;

	public BugTreeComperator(ColumnTitles columnToSort, int dir) {
		super();
		this.columnToSort = columnToSort;
		this.dir = dir;
	}

	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof Bug) {
			int returnValue = 0;
			
			if (columnToSort == ColumnTitles.Created) {
				returnValue = ((Bug) e2).getCreated().compareTo(((Bug) e1).getCreated());
			}else if(columnToSort == ColumnTitles.Ticket){
				String ticketNumberAsString1 = ((Bug) e1).getTicket().substring(1);
				int ticketNumber1 = Integer.parseInt(ticketNumberAsString1);
				String ticketNumberAsString2 = ((Bug) e2).getTicket().substring(1);
				int ticketNumber2 = Integer.parseInt(ticketNumberAsString2);				
				returnValue = ticketNumber1-ticketNumber2;
				System.out.println(returnValue);
			}
			if (this.dir == SWT.DOWN) {
				returnValue = returnValue * -1;
			}
			return returnValue;
		}
		return 0;
	}
}
