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
		tree.setLayout(new WeightedTableLayout(new int[] {  25, 75, -1, -1, -1, -1 , -1, -1, -1 },
											   new int[] {  -1, -1, 80, 80, 80 , 80, 80, 80, 70}));
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		buildColumnsHeaders();
		this.setLabelProvider(new BugTreeLabelProvider(parent));
		this.setContentProvider(new BugTreeContentProvider());
		// this.addFilter(new BugViewerFilter());
		// this.setComparator(new BugTreeComperator());

		Project proj = new Project("Bugcrawler", "Tobias", new Date());
		new Bug("#1", "A little bug while showing the wizzard", "Wizzard", "0.1 aplha", "0.1",
				"defect","normal","Tobias",new Date(),Priority.High,proj);
		
		new Bug("#2", "The tree has to show only one Project :SSS", "Tree", "0.1 aplha", "0.1",
				"task","normal","Jörn",new Date(),Priority.Lowest,proj);
		
		Project proj2 = new Project("Intension", "Tobias", new Date());
		new Bug("#3", "The Datamodel of the Browser is not working correctly", "Browser", "none", "none",
				"defect","major","Tobias",new Date(),Priority.High,proj2);
		
		new Bug("#6", "The chat making troubles in Server-Client-Communication", "Chat", "none", "none",
				"defect","critical","Tobias" ,new Date(),Priority.High,proj2);


		this.setInput(Arrays.asList(new Object[] { proj, proj2 }));
		this.expandToLevel(-1);
	}

	public void buildColumnsHeaders() {
		for (ColumnTitles titles : ColumnTitles.values()) {
			TreeColumn column = new TreeColumn(tree, SWT.LEFT);
			column.setText(titles.toString());
		}
	}

}