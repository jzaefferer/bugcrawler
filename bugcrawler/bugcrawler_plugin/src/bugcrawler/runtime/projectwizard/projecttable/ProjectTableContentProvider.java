package bugcrawler.runtime.projectwizard.projecttable;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ProjectTableContentProvider implements IStructuredContentProvider{

	public Object[] getElements(Object inputElement) {
		return ((List) inputElement).toArray();
	}

	public void dispose() {
		//do nothing		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//do nothing
	}

}
