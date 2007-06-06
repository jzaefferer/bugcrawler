package bugcrawler.testing.tree;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.Priority;
import bugcrawler.runtime.data.Project;

public class BugTreeLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

    private Composite parent;

    public BugTreeLabelProvider(Composite parent) {
	this.parent = parent;
    }

    public Image getColumnImage(Object element, int columnIndex) {
	if ((element instanceof Bug) && (columnIndex == 1)) {
	    if (((Bug) element).isSolved()) {
		return Activator.getImagestore().get("solved.png");
	    }else{
		return Activator.getImagestore().get("notsolved.png");
	    }
	}

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
		return null;
	    case 2:
		return bug.getCreator();
	    case 3:
		return convertDate(bug.getCreationDate());
	    case 4:
		return bug.getLastModifier();
	    case 5:
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

    public Color getBackground(Object element, int columnIndex) {
	if (element instanceof Project) {
	    return new Color(parent.getDisplay(), 225, 225, 225);
	}
	Priority priority = null;
	if (element instanceof Bug) {
	    priority = ((Bug) element).getPriority();
	} else if (element instanceof Priority)
	    priority = (Priority) element;
	if (priority != null) {
	    return chooseColor(priority, parent.getDisplay());
	}
	return null;
    }

    public Color getForeground(Object element, int columnIndex) {
	return null;
    }

    public Color chooseColor(Priority priority, Display display) {
	Color color = null;
	switch (priority) {
	case Highest:
	    color = new Color(display, 255, 220, 204);
	    break;
	case High:
	    color = new Color(display, 255, 238, 222);
	    break;
	case Medium:
	    color = new Color(display, 255, 250, 205);
	    break;
	case Low:
	    color = new Color(display, 246, 246, 246);
	    break;
	case Lowest:
	    color = new Color(display, 251, 251, 251);
	    break;
	}
	return color;
    }
}