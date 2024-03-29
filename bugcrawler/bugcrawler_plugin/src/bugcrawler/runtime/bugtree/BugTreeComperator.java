package bugcrawler.runtime.bugtree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.TreeColumnTitles;

/**
 * Sorts the bugTreeViewer
 * 
 * @author TSO
 */
public class BugTreeComperator extends ViewerSorter {

	/**
	 * The title of the column to sort
	 */
	private String columnToSort;

	/**
	 * the direction which to sort the column
	 */
	private int dir;

	/**
	 * Initializes the Comperator
	 * 
	 * @param columnToSort
	 *            the column that has to be sorted
	 * @param dir
	 *            the direction to sort the columnToSort
	 */
	public BugTreeComperator(String columnToSort, int dir) {
		super();
		this.columnToSort = columnToSort;
		this.dir = dir;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof Bug) {
			int returnValue = 0;

			if (columnToSort == TreeColumnTitles.Ticket.toString()) {
				returnValue = getTicketNumber(e1) - getTicketNumber(e2);
			} else if (columnToSort == TreeColumnTitles.Summary.toString()) {
				returnValue = ((Bug) e2).getSummary().compareTo(((Bug) e1).getSummary());
			} else if (columnToSort == TreeColumnTitles.Component.toString()) {
				returnValue = ((Bug) e2).getComponent().compareTo(((Bug) e1).getComponent());
			} else if (columnToSort == TreeColumnTitles.Version.toString()) {
				returnValue = ((Bug) e2).getVersion().compareTo(((Bug) e1).getVersion());
			} else if (columnToSort == TreeColumnTitles.Milestone.toString()) {
				returnValue = ((Bug) e2).getMilestone().compareTo(((Bug) e1).getMilestone());
			} else if (columnToSort == TreeColumnTitles.Type.toString()) {
				returnValue = ((Bug) e2).getType().compareTo(((Bug) e1).getType());
			} else if (columnToSort == TreeColumnTitles.Severity.toString()) {
				returnValue = ((Bug) e2).getSeverity().compareTo(((Bug) e1).getSeverity());
			} else if (columnToSort == TreeColumnTitles.Owner.toString()) {
				returnValue = ((Bug) e2).getOwner().compareTo(((Bug) e1).getOwner());
			} else if (columnToSort == TreeColumnTitles.Created.toString()) {
				returnValue = ((Bug) e2).getCreated().compareTo(((Bug) e1).getCreated());
			}
			if (this.dir == SWT.DOWN) {
				returnValue = returnValue * -1;
			}
			return returnValue;
		}
		return 0;
	}

	/**
	 * Get the int of the ticketnumber
	 * 
	 * @param object
	 *            the bug
	 * @return int of the ticketnumber
	 */
	private int getTicketNumber(Object o) {
		String ticketNumberAsString = ((Bug) o).getTicket().substring(1);
		return Integer.parseInt(ticketNumberAsString);
	}
}
