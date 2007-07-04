package bugcrawler.runtime.bugtree;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.Project;

/**
 * Provides the content of the bugTreeViewer
 * 
 * @author TSO
 */
public class BugTreeContentProvider implements ITreeContentProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Project) {
			return ((Project) parentElement).getBugContainers();
		} else if (parentElement instanceof BugContainer) {
			return ((BugContainer) parentElement).getBugs();
		} else if (parentElement instanceof Bug) {
			return null;
		}
		throw new RuntimeException("No children-handling for the given parentElement.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof Project) {
			return true;
		} else if (element instanceof BugContainer) {
			return true;
		} else if (element instanceof Bug) {
			return false;
		}
		throw new RuntimeException("No hasChildren-handling for the given element.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return ((List) inputElement).toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// do nothing
	}
}
