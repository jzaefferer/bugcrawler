package bugcrawler.runtime.tree;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.layoutmanagers.WeightedTableLayout;

public class BugTreeViewer extends TreeViewer {

	private final Tree tree;

	private final TreeViewer viewer = this;

	private BugTreeViewerFilter bugTreeViewerFilter;
	
	public BugTreeViewer(final Composite parent) {
		super(parent,SWT.FULL_SELECTION);
		tree = this.getTree();
		tree.setLayout(new WeightedTableLayout(new int[] { 25, 75, -1, -1, -1, -1, -1, -1, -1 }, new int[] {
				-1, -1, 80, 80, 80, 80, 80, 80, 70 }));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setLabelProvider(new BugTreeLabelProvider(parent));
		this.setContentProvider(new BugTreeContentProvider());
		//this.setCellModifier(new BugTreeCellModifier());
		//addPropertiesAndEditors();		
		addDoubleClickListener();
		//this.setInput(Arrays.asList(BugTestData.getTestData()));
		//this.expandToLevel(-1);
	}

	private void buildColumnsHeaders() {
		for (int i=0;i<ColumnTitles.values().length;i++){
			ColumnTitles titles = ColumnTitles.values()[i];
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText(titles.toString());
			addListenerForSortingColumns(column);
			if(i>0){
				column.setMoveable(true);
			}
		}
	}
	
//	private void addPropertiesAndEditors(){
//		String[] properties = new String[ColumnTitles.values().length];
//		TextCellEditor[] tce = new TextCellEditor[ColumnTitles.values().length];
//		for(int i=0;i<properties.length;i++){
//			properties[i]= (ColumnTitles.values())[i].toString();
//			if(i!=0){
//				tce[i] = new TextCellEditor(tree);
//			}
//		}
//		this.setColumnProperties(properties);
//		this.setCellEditors(tce);
//	}

	private void addDoubleClickListener(){
		this.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event) {
				Object selectedNode = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if(selectedNode instanceof Bug){
					System.out.println("jep das isn bug");	
				}
			}
		});
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
				String sortIdentifier = column.getText();
				tree.setSortDirection(dir);
				viewer.setSorter(new BugTreeComperator(sortIdentifier, dir));
			}
		});
	}
	
	public void addBugTreeFilter(BugTreeViewerFilterDialog dialog){
		if(bugTreeViewerFilter!= null){
			this.removeFilter(bugTreeViewerFilter);
		}
		bugTreeViewerFilter = new BugTreeViewerFilter(dialog);
		this.addFilter(bugTreeViewerFilter);
	}
	
	public void removeBugTreeFilter(){
		if(bugTreeViewerFilter!=null){
			this.removeFilter(bugTreeViewerFilter);
		}
	}

}