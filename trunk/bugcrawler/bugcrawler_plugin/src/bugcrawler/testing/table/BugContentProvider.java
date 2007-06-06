package bugcrawler.testing.table;

import java.util.Date;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import bugcrawler.runtime.data.Bug;


public class BugContentProvider implements IStructuredContentProvider{

    public Object[] getElements(Object inputElement) {
	Bug[] bugs = new Bug[]{
		new Bug("Testbug","Tobias",new Date(),"Jörn",new Date()),
		new Bug("Testbug","Jörn",new Date(),"Tobias",new Date())
	};
	
	return bugs;
    }

    public void dispose() {
	//do nothing
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	// doNothing
    }

}
