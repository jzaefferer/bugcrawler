package bugcrawler.runtime.projectwizard.projecttable;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import bugcrawler.runtime.data.TableColumnTitles;
import bugcrawler.utils.WeightedTableLayout;

public class ProjectTableViewer extends CheckboxTableViewer {

	private Table table;
	
	@SuppressWarnings("deprecation")
	public ProjectTableViewer(Composite parent) {
		super(parent, SWT.MULTI | SWT.FULL_SELECTION); // SWT.HIDE_SELECTION
		table = this.getTable();
		table.setLayout(new WeightedTableLayout(new int[] {-1,2,1,1}, new int[] {20,-1,-1,-1}));
		table.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setContentProvider(new ProjectTableContentProvider());
		this.setLabelProvider(new ProjectTableLabelProvider());
	}
    
	private void buildColumnsHeaders() {
		for (int i=0;i<TableColumnTitles.values().length;i++){
			TableColumnTitles title = TableColumnTitles.values()[i];
			TableColumn column = new TableColumn(table,SWT.LEFT);
			column.setText(title.toString());
		}
	}
	
	public void setLayoutData(Object layoutData){
		this.table.setLayoutData(layoutData);
	}
}