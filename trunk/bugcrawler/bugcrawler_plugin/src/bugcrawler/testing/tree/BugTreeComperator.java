package bugcrawler.testing.tree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import bugcrawler.runtime.data.BugContainer;

public class BugTreeComperator extends ViewerComparator{
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if(e1 instanceof BugContainer){
			String priorityName1 = ((BugContainer)e1).getPriority().toString();
			String priorityName2 = ((BugContainer)e2).getPriority().toString();
			return getComparator().compare(priorityName1, priorityName2);
		}
		return 0;
	}
}
