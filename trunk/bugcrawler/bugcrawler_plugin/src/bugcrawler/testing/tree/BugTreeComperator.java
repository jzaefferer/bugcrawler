package bugcrawler.testing.tree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import bugcrawler.runtime.data.BugContainer;

public class BugTreeComperator extends ViewerComparator{
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if(e1 instanceof BugContainer){
			System.out.println("NOW");
		}
		return 0;
	}
}
