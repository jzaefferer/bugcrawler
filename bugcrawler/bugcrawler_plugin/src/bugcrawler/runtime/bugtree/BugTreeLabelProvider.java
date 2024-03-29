package bugcrawler.runtime.bugtree;

import static bugcrawler.runtime.Activator.getResourceStore;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.Priority;
import bugcrawler.runtime.data.Project;

/**
 * Provides the appearance of the bugTreeViewer
 * 
 * @author TSO
 */
public class BugTreeLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
	 *      int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		if ((element instanceof Bug) && (columnIndex == 1)) {
			if (((Bug) element).isSolved()) {
				return getResourceStore().getImage("solved.png");
			} else {
				return getResourceStore().getImage("notsolved.png");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
	 *      int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Project && columnIndex == 0) {
			return ((Project) element).getName();
		} else if (element instanceof BugContainer && columnIndex == 0) {
			return ((BugContainer) element).getPriority().toString();
		} else if (element instanceof Bug) {
			Bug bug = (Bug) element;
			switch (columnIndex) {
			case 0:
				return bug.getTicket();
			case 1:
				return bug.getSummary();
			case 2:
				return bug.getComponent();
			case 3:
				return bug.getVersion();
			case 4:
				return bug.getMilestone();
			case 5:
				return bug.getType();
			case 6:
				return bug.getSeverity();
			case 7:
				return bug.getOwner();
			case 8:
				return convertDate(bug.getCreated());
			default:
				return null;
			}
		}
		return null;
	}

	private String convertDate(Date date) {
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.lang.Object,
	 *      int)
	 */
	public Color getBackground(Object element, int columnIndex) {
		if (element instanceof Project) {
			return getResourceStore().getColor(225, 225, 225);
		}
		Priority priority = null;
		if (element instanceof Bug) {
			priority = ((Bug) element).getPriority();
		} else if (element instanceof BugContainer)
			priority = ((BugContainer) element).getPriority();
		if (priority != null) {
			return chooseColor(priority);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getForeground(java.lang.Object,
	 *      int)
	 */
	public Color getForeground(Object element, int columnIndex) {
		return null;
	}

	/**
	 * Creates a color for each priority
	 * 
	 * @param priority
	 *            to get the color for
	 * @return the color to the priority
	 */
	public Color chooseColor(Priority priority) {
		Color color = null;
		switch (priority) {
		case Highest:
			color = getResourceStore().getColor(255, 220, 204);
			break;
		case High:
			color = getResourceStore().getColor(255, 238, 222);
			break;
		case Medium:
			color = getResourceStore().getColor(255, 250, 205);
			break;
		case Low:
			color = getResourceStore().getColor(246, 246, 246);
			break;
		case Lowest:
			color = getResourceStore().getColor(251, 251, 251);
			break;
		}
		return color;
	}
}
