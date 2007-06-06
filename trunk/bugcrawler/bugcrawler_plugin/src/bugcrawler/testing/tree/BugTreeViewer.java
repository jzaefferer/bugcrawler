package bugcrawler.testing.tree;

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
	tree.setLayout(new WeightedTableLayout(new int[] { 50, -1, 25, -1, 25, -1 },
					       new int[] {-1, 18, -1, 85, -1, 105 }));
	tree.setLinesVisible(true);
	tree.setHeaderVisible(true);
	buildColumnsHeaders();
	this.expandToLevel(-1);
	this.setLabelProvider(new BugTreeLabelProvider(parent));
	this.setContentProvider(new BugTreeContentProvider());
	// this.addFilter(new BugViewerFilter());
	// this.setComparator(new BugTreeComperator());
	
	Project proj = new Project("testproj", "tobi", new Date());
	Bug bug1 = new Bug("Ah damn little Bug", "Tobi", new Date(), "Jörn", new Date());
	bug1.setPriority(Priority.High);
	proj.addBugToProject(bug1);
	Bug bug2 = new Bug("Ah damn little Bug2", "Jörn", new Date(), "Tobi", new Date());
	bug2.setPriority(Priority.Low);
	proj.addBugToProject(bug2);
	
	this.setInput(proj.getInput());
    }

    public void buildColumnsHeaders() {
	for (ColumnTitles titles : ColumnTitles.values()) {
	    TreeColumn column = new TreeColumn(tree, SWT.LEFT);
	    column.setText(titles.toString());
	}
    }

}