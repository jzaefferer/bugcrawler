package bugcrawler.testing.tree;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;

import bugcrawler.runtime.views.Bug;
import bugcrawler.runtime.views.Project;

public class BugTreeLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

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

    public Color getBackground(Object element, int columnIndex) {
	return null;
    }

    public Color getForeground(Object element, int columnIndex) {
	// TODO Auto-generated method stub
	return null;
    }

    public void setTreeItemColor(TreeItem item, Display display, int r, int g, int b) {
	item.setBackground(new Color(display, r, g, b));
    }

    public void chooseColor(Priority priority, TreeItem item, Display display) {
	switch (priority) {
	case Highest:
	    setTreeItemColor(item, display, 255, 220, 204);
	    break;
	case High:
	    setTreeItemColor(item, display, 255, 238, 222);
	    break;
	case Medium:
	    setTreeItemColor(item, display, 255, 250, 205);
	    break;
	case Low:
	    setTreeItemColor(item, display, 246, 246, 246);
	    break;
	case Lowest:
	    setTreeItemColor(item, display, 251, 251, 251);
	    break;
	}
    }    
}
