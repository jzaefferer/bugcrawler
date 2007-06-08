package bugcrawler.testing.tree;

import java.util.Arrays;
import java.util.Date;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import bugcrawler.runtime.data.BugNew;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.data.Priority;
import bugcrawler.runtime.data.ProjectNew;
import bugcrawler.runtime.layoutmanagers.WeightedTableLayout;

public class BugTreeViewer extends TreeViewer {

	private final Tree tree;

	public BugTreeViewer(final Composite parent) {
		super(parent);
		tree = this.getTree();
		tree.setLayout(new WeightedTableLayout(new int[] { 50, -1, 25, -1, 25, -1 }, new int[] { -1, 18, -1,
				85, -1, 105 }));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setLabelProvider(new BugTreeLabelProviderNew(parent));
		this.setContentProvider(new BugTreeContentProviderNew());
		// this.addFilter(new BugViewerFilter());
		// this.setComparator(new BugTreeComperator());

		ProjectNew proj = new ProjectNew("testproj", "tobi", new Date());
		new BugNew("Ah damn little Bug", "Tobi", new Date(), "Jörn", new Date(),
				Priority.Highest, proj);
		new BugNew("Ah damn little Bug2", "Jörn", new Date(), "Tobi", new Date(),
				Priority.Highest, proj);
		// System.out.println(((BugNew)proj.getBugContainer(Priority.High).getBugs()[1]).getName());

		this.setInput(Arrays.asList(new Object[] { proj }));
		this.expandToLevel(-1);
	}

	public void buildColumnsHeaders() {
		for (ColumnTitles titles : ColumnTitles.values()) {
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText(titles.toString());
		}
	}

}