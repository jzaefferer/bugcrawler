package bugcrawler.runtime.tree;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.Project;

public class BugTreeContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Project) {
			return ((Project) parentElement).getBugContainers();
		} else if (parentElement instanceof BugContainer) {
			return ((BugContainer) parentElement).getBugs();
		} else if (parentElement instanceof Bug){
			return null;
		}
		throw new RuntimeException("No children-handling for the given parentElement.");
	}

	public Object getParent(Object element) {
		if (element instanceof Project) {
			return null;
		} else if (element instanceof BugContainer) {
			return ((BugContainer) element).getProject();
		} else if (element instanceof Bug) {
			((Bug) element).getBugContainer();
		}
		throw new RuntimeException("No parent-handling for the given element.");
	}

	public boolean hasChildren(Object element) {
		if (element instanceof Project){
			return true;
		} else if (element instanceof BugContainer){
			return true;
		} else if (element instanceof Bug){
			return false;
		}
		throw new RuntimeException("No hasChildren-handling for the given element.");
	}

	public Object[] getElements(Object inputElement) {
		return ((List) inputElement).toArray();
	}

	public void dispose() {
		// do nothing
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//do nothing
	}
}
