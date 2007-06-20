package bugcrawler.runtime.projectwizard.projecttable;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import bugcrawler.runtime.data.Project;

public class ProjectTableLabelProvider extends LabelProvider implements ITableLabelProvider{

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		Project project = (Project)element;
		switch(columnIndex){
			case 0:return null;
			case 1:return project.getName();
			case 2:return project.getOwner();
			case 3:return convertDate(project.getCreated());
			default: return null;
		}
		
	}
	
	public String convertDate(Date date) {
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
