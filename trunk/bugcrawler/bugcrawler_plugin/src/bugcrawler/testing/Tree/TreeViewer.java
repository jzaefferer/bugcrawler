/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package bugcrawler.testing.Tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import bugcrawler.runtime.layoutmanagers.WeightedTableLayout;


public class TreeViewer {

	public TreeViewer(final Composite parent){
	    
	    	TreeViewerContentProvider contentProvider = new TreeViewerContentProvider();
	    
		final Tree tree = new Tree(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tree.setLayout(new WeightedTableLayout(new int[] { 25, 10, 25, 20, 20}));
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		for (ColumnTitles titles : ColumnTitles.values()){
		    TreeColumn column = new TreeColumn(tree,SWT.LEFT);
		    column.setText(titles.toString());
		}	
		
		for (Priority priority : Priority.values()) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText(new String[] {priority.toString()});
			Display display = parent.getDisplay();
			chooseColor(priority, item, display);

			for (int j = 0; j < contentProvider.getContent().length; j++) {
				TreeItem subItem = new TreeItem(item, SWT.NONE);
				chooseColor(priority, subItem, display);
				subItem.setText(contentProvider.getContent()[j].getValues());
			}
		}
		
		new TreeViewerEditor(tree);
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