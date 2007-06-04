package bugcrawler.runtime.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class ProgramView extends ViewPart {

    private class BugViewer extends TableViewer {

	private Table table;
	
	final String[] coltit = new String[] { 
		"Status", 
		"Bugname", 
		"Erstelldatum", 
		"Ersteller", 
		"Letzter Beitrag am",
		"Letzter Beitrag von"
	};
	    
	public BugViewer(Composite parent) {
	    super(parent);
	    this.table = this.getTable();
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    buildColumnHeaders();
	    
	}

	private void buildColumnHeaders() {
	    for(int i=0;i<coltit.length;i++){
		TableColumn tableColumn = new TableColumn(table, SWT.LEFT);
		tableColumn.setText(coltit[i]);
		if(i%2!=0){
		    tableColumn.setWidth(200);
		}else{
		    tableColumn.setWidth(100);
		}
	    }
	}
    };

    public ProgramView() {
    }

    /**
     * This is a callback that will allow us to create the viewer and
     * initialize it.
     */
    public void createPartControl(Composite parent) {
	new BugViewer(parent);
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
    }
}