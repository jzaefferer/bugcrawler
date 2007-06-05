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
	
	public void setTreeItemColor(TreeItem item,Display display,int r,int g,int b){
	    item.setBackground(new Color(display,r,g,b));
	}
	
	public void chooseColor(Priority priority, TreeItem item, Display display){
		switch(priority){
        		case Highest: setTreeItemColor(item,display,255, 220, 204); break;
        		case High: setTreeItemColor(item, display, 255, 238, 222); break;
        		case Medium: setTreeItemColor(item, display, 255, 250, 205); break;
        		case Low: setTreeItemColor(item, display, 246, 246, 246); break;
        		case Lowest: setTreeItemColor(item,display,251, 251, 251 ); break;
		}	    
	}
}