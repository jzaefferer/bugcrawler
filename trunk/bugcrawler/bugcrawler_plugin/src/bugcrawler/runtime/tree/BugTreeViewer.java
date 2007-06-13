package bugcrawler.runtime.tree;

import java.util.Arrays;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import bugcrawler.runtime.data.BugTestData;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.layoutmanagers.WeightedTableLayout;

public class BugTreeViewer extends TreeViewer {

	private final Tree tree;

	private final TreeViewer viewer = this;

	private BugTreeViewerFilter bugTreeViewerFilter;
	
	public BugTreeViewer(final Composite parent) {
		super(parent);
		tree = this.getTree();
		tree.setLayout(new WeightedTableLayout(new int[] { 25, 75, -1, -1, -1, -1, -1, -1, -1 }, new int[] {
				-1, -1, 80, 80, 80, 80, 80, 80, 70 }));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setLabelProvider(new BugTreeLabelProvider(parent));
		this.setContentProvider(new BugTreeContentProvider());
		this.setInput(Arrays.asList(BugTestData.getTestData()));
		
		TextCellEditor[] tce = {
				null,
				new TextCellEditor(tree),
				new TextCellEditor(tree),
				new TextCellEditor(tree),
				new TextCellEditor(tree),
				new TextCellEditor(tree),
				new TextCellEditor(tree),
				new TextCellEditor(tree),
				new TextCellEditor(tree)
			};
		this.setCellEditors(tce);
		this.setColumnProperties(new String[]{
			ColumnTitles.Ticket.toString(),
			ColumnTitles.Summary.toString(),
			ColumnTitles.Component.toString(),
			ColumnTitles.Version.toString(),
			ColumnTitles.Milestone.toString(),
			ColumnTitles.Type.toString(),
			ColumnTitles.Severity.toString(),
			ColumnTitles.Owner.toString(),
			ColumnTitles.Created.toString()
		});
		this.setCellModifier(new ICellModifier(){
			public boolean canModify(Object element, String property) {
				return true;
			}
			public Object getValue(Object element, String property) {
				return "";
			}
			public void modify(Object element, String property, Object value) {
				
			}
		});
		this.expandToLevel(-1);

	}

	public void buildColumnsHeaders() {
		for (ColumnTitles titles : ColumnTitles.values()) {
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText(titles.toString());
			addListenerForSortingColumns(column);
		}
	}

	public void addListenerForSortingColumns(final TreeColumn column) {
		column.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TreeColumn sortColumn = tree.getSortColumn();
				TreeColumn currentColumn = (TreeColumn) e.widget;
				int dir = tree.getSortDirection();
				if (sortColumn == currentColumn) {
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				} else {
					tree.setSortColumn(currentColumn);
					dir = SWT.UP;
				}
				ColumnTitles sortIdentifier = null;

				if (column.getText().equals(ColumnTitles.Ticket.toString())) {
					sortIdentifier = ColumnTitles.Ticket;
				} else if (column.getText().equals(ColumnTitles.Summary.toString())) {
					sortIdentifier = ColumnTitles.Summary;
				} else if (column.getText().equals(ColumnTitles.Component.toString())) {
					sortIdentifier = ColumnTitles.Component;
				} else if (column.getText().equals(ColumnTitles.Version.toString())) {
					sortIdentifier = ColumnTitles.Version;
				} else if (column.getText().equals(ColumnTitles.Milestone.toString())) {
					sortIdentifier = ColumnTitles.Milestone;
				} else if (column.getText().equals(ColumnTitles.Type.toString())) {
					sortIdentifier = ColumnTitles.Type;
				} else if (column.getText().equals(ColumnTitles.Severity.toString())) {
					sortIdentifier = ColumnTitles.Severity;
				} else if (column.getText().equals(ColumnTitles.Owner.toString())) {
					sortIdentifier = ColumnTitles.Owner;
				} else if (column.getText().equals(ColumnTitles.Created.toString())) {
					sortIdentifier = ColumnTitles.Created;
				}

				tree.setSortDirection(dir);
				viewer.setSorter(new BugTreeComperator(sortIdentifier, dir));
			}
		});
	}
	
	public void addBugTreeFilter(){
		if(bugTreeViewerFilter!= null){
			this.removeFilter(bugTreeViewerFilter);
		}
		bugTreeViewerFilter = new BugTreeViewerFilter();
		this.addFilter(bugTreeViewerFilter);
	}
	
	public void removeBugTreeFilter(){
		if(bugTreeViewerFilter!=null){
			this.removeFilter(bugTreeViewerFilter);
		}
	}

}