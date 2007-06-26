package bugcrawler.runtime.bugtree;

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
import org.eclipse.ui.IEditorPart;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.TreeColumnTitles;
import bugcrawler.runtime.editor.BugEditor;
import bugcrawler.runtime.editor.UIBug;
import bugcrawler.utils.WeightedTableLayout;

public class BugTreeViewer extends TreeViewer {

	private final Tree tree;

	private final BugTreeViewer bugTreeViewer = this;

	private BugTreeViewerFilter bugTreeViewerFilter;

	private IEditorPart editor;

	public BugTreeViewer(final Composite parent) {
		super(parent, SWT.FULL_SELECTION);
		tree = this.getTree();
		tree.setLayout(new WeightedTableLayout(new int[] { 25, 75, -1, -1, -1, -1, -1, -1, -1 }, new int[] {
				-1, -1, 70, 60, 60, 60, 60, 60, 70 }));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setLabelProvider(new BugTreeLabelProvider(parent));
		this.setContentProvider(new BugTreeContentProvider());
		// this.setCellModifier(new BugTreeCellModifier());
		// addPropertiesAndEditors();
		addDoubleClickListener();
		// this.setInput(Arrays.asList(BugTestData.getTestData()));
		// this.expandToLevel(-1);
	}

	// private void addPropertiesAndEditors(){
	// String[] properties = new String[ColumnTitles.values().length];
	// TextCellEditor[] tce = new TextCellEditor[ColumnTitles.values().length];
	// for(int i=0;i<properties.length;i++){
	// properties[i]= (ColumnTitles.values())[i].toString();
	// if(i!=0){
	// tce[i] = new TextCellEditor(tree);
	// }
	// }
	// this.setColumnProperties(properties);
	// this.setCellEditors(tce);
	// }

	private void buildColumnsHeaders() {
		for (int i = 0; i < TreeColumnTitles.values().length; i++) {
			TreeColumnTitles title = TreeColumnTitles.values()[i];
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText(title.toString());
			addListenerForSortingColumns(column);
			if (i > 0) {
				column.setMoveable(true);
			}
		}
	}

	private void addDoubleClickListener() {
		this.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				Object selectedNode = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (selectedNode instanceof Bug) {
					try {
						if (Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()== null) {
							editor = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage()
									.openEditor(new UIBug((Bug) selectedNode), Constants.EDITOR_EXTENSION);
							((BugEditor)editor).setBugTreeViewer(bugTreeViewer);
						} else {
							((BugEditor)editor).addPagesToEditor(new UIBug((Bug) selectedNode));
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
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
				bugTreeViewer.setSorter(new BugTreeComperator(sortIdentifier, dir));
			}
		});
	}

	public void addBugTreeFilter(BugTreeViewerFilterDialog dialog) {
		if (bugTreeViewerFilter != null) {
			this.removeFilter(bugTreeViewerFilter);
		}
		bugTreeViewerFilter = new BugTreeViewerFilter(dialog);
		this.addFilter(bugTreeViewerFilter);
	}

	public void removeBugTreeFilter() {
		if (bugTreeViewerFilter != null) {
			this.removeFilter(bugTreeViewerFilter);
		}
	}

	public IEditorPart getEditor() {
		return editor;
	}

	public void setEditor(IEditorPart editor) {
		this.editor = editor;
	}

}