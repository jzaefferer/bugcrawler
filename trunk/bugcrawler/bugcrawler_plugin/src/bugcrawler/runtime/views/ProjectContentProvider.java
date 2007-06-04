package bugcrawler.runtime.views;

import java.util.Date;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class ProjectContentProvider implements IStructuredContentProvider{

    public Object[] getElements(Object inputElement) {
	Project[] projects = new Project[]{
		new Project("TestProj","Tobias",new Date()),
		new Project("TestProj","Jörn",new Date())
	};
	
	return projects;
    }

    public void dispose() {
	//do nothing
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	// doNothing
    }

}