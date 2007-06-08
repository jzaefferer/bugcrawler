package bugcrawler.testing.tree;

import java.util.Arrays;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import bugcrawler.runtime.data.BugTestData;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.layoutmanagers.WeightedTableLayout;

public class BugTreeViewer extends TreeViewer {

	private final Tree tree;

	public BugTreeViewer(final Composite parent) {
		super(parent);
		tree = this.getTree();
		tree.setLayout(new WeightedTableLayout(new int[] {  25, 75, -1, -1, -1, -1 , -1, -1, -1 },
											   new int[] {  -1, -1, 80, 80, 80 , 80, 80, 80, 70}));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setLabelProvider(new BugTreeLabelProvider(parent));
		this.setContentProvider(new BugTreeContentProvider());
		// this.addFilter(new BugViewerFilter());
		this.setComparator(new BugTreeComperator());
		this.setInput(Arrays.asList(BugTestData.getTestData()));
		this.expandToLevel(-1);
	}

	public void buildColumnsHeaders() {
		for (ColumnTitles titles : ColumnTitles.values()) {
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText(titles.toString());
		}
	}

}