package bugcrawler.testing.tree;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import bugcrawler.runtime.layoutmanagers.WeightedTableLayout;


public class BugTreeViewer extends TreeViewer{

    	private final Tree tree;
    	
	public BugTreeViewer(final Composite parent){
	    	super(parent);
	    	tree = this.getTree();
		tree.setLayout(new WeightedTableLayout(new int[] { 25, 10, 25, 20, 20}));	    	
	    	tree.setLinesVisible(true);
	    	tree.setHeaderVisible(true);
	    	buildColumnsHeaders();
                this.setContentProvider(new BugTreeContentProviderNew());
                this.setInput("");
                this.setLabelProvider(new BugTreeLabelProvider());
		new TreeViewerEditor(tree);
	}

	public void buildColumnsHeaders(){
        	for (ColumnTitles titles : ColumnTitles.values()){
        	    TreeColumn column = new TreeColumn(tree,SWT.LEFT);
        	    column.setText(titles.toString());
        	}
	}
	

}