package bugcrawler.testing.tree;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import bugcrawler.runtime.views.Bug;
import bugcrawler.runtime.views.Project;

public class BugTreeLabelProvider extends LabelProvider implements ITableLabelProvider {

    public Image getColumnImage(Object element, int columnIndex) {
	return null;
    }

    public String getColumnText(Object element, int columnIndex) {
	if (element instanceof Project && columnIndex == 0)
	    return ((Project) element).getName();
	if (element instanceof Priority && columnIndex == 0)
	    return ((Priority) element).toString();
	if (element instanceof Bug) {
	    Bug bug = (Bug) element;
	    switch (columnIndex) {
	    case 0:
		return bug.getName();
	    case 1:
		return bug.getCreator();
	    case 2:
		return convertDate(bug.getCreationDate());
	    case 3:
		return bug.getLastModifier();
	    case 4:
		return convertDate(bug.getLastModificationDate());
	    default:
		return null;
	    }
	}
	return null;
    }

    public String convertDate(Date date) {
	return new SimpleDateFormat().format(date);
    }
}
