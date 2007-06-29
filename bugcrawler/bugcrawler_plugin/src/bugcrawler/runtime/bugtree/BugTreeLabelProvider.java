package bugcrawler.runtime.bugtree;

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
import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.Priority;
import bugcrawler.runtime.data.Project;
import bugcrawler.utils.ResourceStore;

public class BugTreeLabelProvider extends LabelProvider implements ITableLabelProvider,
		ITableColorProvider {

	private Composite parent;
	
	private ResourceStore resourceStore = Activator.getResourceStore();

	public BugTreeLabelProvider(Composite parent) {
		this.parent = parent;
	}

	public Image getColumnImage(Object element, int columnIndex) {
		if ((element instanceof Bug) && (columnIndex == 1)) {
			if (((Bug) element).isSolved()) {
				return resourceStore.getImage("solved.png");
			} else {
				return resourceStore.getImage("notsolved.png");
			}
		}
		return null;
	}

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

	public Color getBackground(Object element, int columnIndex) {
		if (element instanceof Project) {
			return resourceStore.getColor(225, 225, 225);
		}
		Priority priority = null;
		if (element instanceof Bug) {
			priority = ((Bug) element).getPriority();
		} else if (element instanceof BugContainer)
			priority = ((BugContainer) element).getPriority();
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
			color = resourceStore.getColor(255, 220, 204);
			break;
		case High:
			color = resourceStore.getColor(255, 238, 222);
			break;
		case Medium:
			color = resourceStore.getColor(255, 250, 205);
			break;
		case Low:
			color = resourceStore.getColor(246, 246, 246);
			break;
		case Lowest:
			color = resourceStore.getColor(251, 251, 251);
			break;
		}
		return color;
	}
}
