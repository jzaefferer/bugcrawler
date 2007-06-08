package bugcrawler.testing.tree;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.BugNew;
import bugcrawler.runtime.data.ProjectNew;

public class BugTreeContentProviderNew implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ProjectNew) {
			return ((ProjectNew) parentElement).getBugContainers();
		} else if (parentElement instanceof BugContainer) {
			return ((BugContainer) parentElement).getBugs();
		} else if (parentElement instanceof BugNew){
			return null;
		}
		throw new RuntimeException("No children-handling for the given parentElement.");
	}

	public Object getParent(Object element) {
		if (element instanceof ProjectNew) {
			return null;
		} else if (element instanceof BugContainer) {
			return ((BugContainer) element).getProject();
		} else if (element instanceof BugNew) {
			((BugNew) element).getBugContainer();
		}
		throw new RuntimeException("No parent-handling for the given element.");
	}

	public boolean hasChildren(Object element) {
		if (element instanceof ProjectNew){
			return true;
		} else if (element instanceof BugContainer){
			return true;
		} else if (element instanceof BugNew){
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
	}
}
