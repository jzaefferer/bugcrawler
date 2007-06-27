package bugcrawler.runtime.projectwizard.projecttable;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import bugcrawler.runtime.data.TableColumnTitles;
import bugcrawler.utils.WeightedTableLayout;

/**
 * Viewer to choose a project in the ProjectWizzard
 * 
 * @author TSO
 */
public class ProjectTableViewer extends CheckboxTableViewer {

	/**
	 * the table widget of this viewer
	 */
	private Table table;

	/**
	 * Initializes the ProjectTableViewer with Content- and LabelProvider The
	 * Viewer uses the WeightTableLayout and set the header for beeing
	 * displayed. Here the deprecated Constructor of the CheckboxTableViewer is
	 * beeing used because of creating own layout and class.
	 * 
	 * @param parent
	 *            the parent's control to display the table on
	 */
	@SuppressWarnings("deprecation")
	public ProjectTableViewer(Composite parent) {
		super(parent, SWT.MULTI | SWT.FULL_SELECTION); // SWT.HIDE_SELECTION
		table = this.getTable();
		table.setLayout(new WeightedTableLayout(new int[] { -1, 2, 1, 1 }, new int[] { 20, -1, -1, -1 }));
		table.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setContentProvider(new ProjectTableContentProvider());
		this.setLabelProvider(new ProjectTableLabelProvider());
	}

	/**
	 * buildes the columnheaders with the titles.
	 */
	private void buildColumnsHeaders() {
		for (int i = 0; i < TableColumnTitles.values().length; i++) {
			TableColumnTitles title = TableColumnTitles.values()[i];
			TableColumn column = new TableColumn(table, SWT.LEFT);
			column.setText(title.toString());
		}
	}

	/**
	 * Sets layout-data to the table of this viewer
	 * 
	 * @param layoutData
	 *            the given layoutdata
	 */
	public void setLayoutData(Object layoutData) {
		this.table.setLayoutData(layoutData);
	}
}
