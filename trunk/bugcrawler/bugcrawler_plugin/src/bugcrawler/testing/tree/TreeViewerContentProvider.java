package bugcrawler.testing.tree;

import java.util.Date;

import bugcrawler.runtime.views.Bug;

public class TreeViewerContentProvider {

    private Bug[] bugs;
    
    public TreeViewerContentProvider(){
	bugs = new Bug[]{
		new Bug("Testbug","Tobias",new Date(),"Jörn",new Date()),
		new Bug("Testbug","Jörn",new Date(),"Tobias",new Date())
	};
    }
    
    public Bug[] getContent(){
	return bugs;
    }
}
