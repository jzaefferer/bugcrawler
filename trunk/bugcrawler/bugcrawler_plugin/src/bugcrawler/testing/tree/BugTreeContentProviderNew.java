package bugcrawler.testing.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.Priority;
import bugcrawler.runtime.data.Project;

public class BugTreeContentProviderNew implements ITreeContentProvider {
    
    Project proj;

    public Object[] getChildren(Object parentElement) {
	if (parentElement instanceof Project) {
	    return Priority.values();
	}
	if (parentElement instanceof Priority) {
	    Object[] bugObjects = proj.getBugs();
	    ArrayList<Bug> list = new ArrayList<Bug>();
	    for (Object bugObject : bugObjects) {
		Bug bug = (Bug) bugObject;
		if ((bug).getPriority() == parentElement)
		    list.add(bug);
	    }
	    return list.toArray();
	}
	if (parentElement instanceof Bug)
	    return null;
	return null;
    }

    public Object getParent(Object element) {
	if (element instanceof Project)
	    return null;
	if (element instanceof Priority)
	    return proj;
	if (element instanceof Bug)
	    ((Bug) element).getPriority();
	return null;
    }

    public boolean hasChildren(Object element) {
	if (element instanceof Project)
	    return true;
	if (element instanceof Priority)
	    return true;
	if (element instanceof Bug)
	    return false;
	return false;
    }

    public Object[] getElements(Object inputElement) {
	proj = (Project)((List) inputElement).get(0);
	return ((List)inputElement).toArray();
    }

    public void dispose() {
	// do nothing
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}
