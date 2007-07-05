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
import org.eclipse.ui.PartInitException;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.TreeColumnTitles;
import bugcrawler.runtime.editor.BugEditor;
import bugcrawler.runtime.editor.UIBug;
import bugcrawler.utils.WeightedTableLayout;

public class BugTreeViewer extends TreeViewer {

	/**
	 * The Tree of this TreeViewer
	 */
	private final Tree tree;

	/**
	 * Instance of its own
	 */
	private final BugTreeViewer bugTreeViewer = this;

	/**
	 * bugTreeViewerFilter to store the current filter to the bugTreeViewer
	 */
	private BugTreeViewerFilter bugTreeViewerFilter;

	/**
	 * Editor to view bugs
	 */
	private BugEditor editor;

	/**
	 * Initializes the bugTreeViewer
	 * 
	 * @param parent
	 *            the parent where to initializes the bugTreeViewer on
	 */
	public BugTreeViewer(final Composite parent) {
		super(parent, SWT.FULL_SELECTION);
		tree = this.getTree();
		tree.setLayout(new WeightedTableLayout(new int[] { 25, 75, -1, -1, -1, -1, -1, -1, -1 }, new int[] {
				-1, -1, 70, 60, 60, 60, 60, 60, 70 }));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setLabelProvider(new BugTreeLabelProvider());
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

	/**
	 * Builds the columnHeaders for the bugTreeViewer
	 */
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

	/**
	 * adding a Listener to the bugTreeViewer and if a bug has been
	 * doubleclicked the a new editor will be opened. If an editor is opened a
	 * new page will be added shown the next clicked bug
	 */
	private void addDoubleClickListener() {
		this.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				Object selectedNode = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (selectedNode instanceof Bug) {
					try {
						Bug bug = (Bug) selectedNode;
						if (Activator.activePage().getActiveEditor() == null) {
							editor = (BugEditor) getBugEditor(bug);
							editor.setBugTreeViewer(bugTreeViewer);
						} else {
							if (editor.findPage(bug.toString()) == null) {
								editor.addPagesToEditor(new UIBug(bug));
							}
						}
						editor.setActivePage(bug.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});
	}

	/**
	 * Gets the Editor
	 * 
	 * @param bug
	 *            the doubleclicked bug
	 * @return IEditorPart the BugEditor
	 * @throws PartInitException
	 */
	private IEditorPart getBugEditor(Bug bug) throws PartInitException {
		return Activator.activePage().openEditor(new UIBug(bug), Constants.EDITOR_EXTENSION);
	}

	/**
	 * Adding a Listener which tests if the selected column is the column to
	 * sort. If it is so, the column will be sorted in a specific direction
	 * 
	 * @param column
	 *            for adding the listener
	 */
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

	/**
	 * Adds a new bugTreeViewerFilter to this viewer.
	 * 
	 * @param filterOptionsStoringLocations
	 *            of the checkboxes of the CheckBoxGroupFieldEditor displayed in
	 *            the Filterdialog or the wizard
	 */
	public void addBugTreeFilter(String[] filterOptionsStoringLocations) {
		if (bugTreeViewerFilter != null) {
			this.removeFilter(bugTreeViewerFilter);
		}
		bugTreeViewerFilter = new BugTreeViewerFilter(filterOptionsStoringLocations);
		this.addFilter(bugTreeViewerFilter);
	}

	/**
	 * Removes a existing bugTreeViewerFilter of this viewer.
	 */
	public void removeBugTreeFilter() {
		if (bugTreeViewerFilter != null) {
			this.removeFilter(bugTreeViewerFilter);
		}
	}
}