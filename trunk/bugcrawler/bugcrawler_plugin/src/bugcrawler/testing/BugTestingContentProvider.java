package bugcrawler.testing;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class BugTestingContentProvider implements IStructuredContentProvider{

    public Object[] getElements(Object inputElement) {
	Bug[] bugs = new Bug[]{
		new Bug("Testbug","Tobias","25.1.2007","Jörn","26.2.2007"),
		new Bug("Testbug","Jörn","27.1.2007","Tobias","28.2.2007")
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
