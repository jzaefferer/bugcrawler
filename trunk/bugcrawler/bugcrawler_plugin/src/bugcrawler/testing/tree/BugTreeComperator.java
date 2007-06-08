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
			for (int i=0;i<ColumnTitles.values().length;i++){
				if (columnToSort == ColumnTitles.Created) {
					returnValue = ((Bug) e1).getCreated().compareTo(((Bug) e2).getCreated());
				}
			}
			if (this.dir == SWT.DOWN) {
				returnValue = returnValue * -1;
			}
			return returnValue;
		}
		return 0;
	}
}
