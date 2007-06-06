package bugcrawler.testing.tree;

import java.util.Arrays;
import java.util.Date;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.data.Priority;
import bugcrawler.runtime.data.Project;
import bugcrawler.runtime.layoutmanagers.WeightedTableLayout;

public class BugTreeViewer extends TreeViewer {

    private final Tree tree;

    public BugTreeViewer(final Composite parent) {
	super(parent);
	tree = this.getTree();
	tree.setLayout(new WeightedTableLayout(new int[] { 25, 10, 25, 20, 20 }));
	tree.setLinesVisible(true);
	tree.setHeaderVisible(true);
	buildColumnsHeaders();
	this.setLabelProvider(new BugTreeLabelProvider(parent));
	this.setContentProvider(new BugTreeContentProviderNew());
	Project proj = new Project("testproj", "tobi", new Date());
	Bug bug1 = new Bug("Ah damn little Bug", "Tobi", new Date(), "J�rn", new Date());
	bug1.setPriority(Priority.High);
	proj.addBugToProject(bug1);
	Bug bug2 = new Bug("Ah damn little Bug2", "J�rn", new Date(), "Tobi", new Date());
	bug2.setPriority(Priority.Low);
	proj.addBugToProject(bug2);

	this.setInput(Arrays.asList(new Object[]{proj}));

	// new TreeViewerEditor(tree);
	// this.addFilter(new BugViewerFilter());
	// this.setComparator(new BugTreeComperator());
    }

    public void buildColumnsHeaders() {
	for (ColumnTitles titles : ColumnTitles.values()) {
	    TreeColumn column = new TreeColumn(tree, SWT.LEFT);
	    column.setText(titles.toString());
	}
    }

}