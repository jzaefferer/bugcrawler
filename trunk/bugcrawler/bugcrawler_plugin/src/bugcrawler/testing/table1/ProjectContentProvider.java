package bugcrawler.testing.table1;

import java.util.Date;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import bugcrawler.runtime.views.Project;


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