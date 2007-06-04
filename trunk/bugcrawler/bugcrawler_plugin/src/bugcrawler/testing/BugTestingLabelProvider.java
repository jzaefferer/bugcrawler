package bugcrawler.testing;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import bugcrawler.runtime.Activator;

public class BugTestingLabelProvider extends LabelProvider implements ITableLabelProvider {

    public Image getColumnImage(Object element, int columnIndex) {
	Bug bug = (Bug)element;
	switch(columnIndex){
		case 0: return bug.isSolved()? 
			       Activator.getImagestore().get("checkbox_checked.gif"):
			       Activator.getImagestore().get("checkbox_unchecked.gif");
		default: return null;
	}
    }

    public String getColumnText(Object element, int columnIndex) {
	Bug bug = (Bug)element;
	switch(columnIndex){
        	case 0: return null;
        	case 1: return bug.getBugname();
        	case 2: return bug.getCreator();
        	case 3: return bug.getCreationdate();
        	case 4: return bug.getLastmodifier();
        	case 5:	return bug.getLastmodificationdate();
        	default: return null;
	}
    }
}
