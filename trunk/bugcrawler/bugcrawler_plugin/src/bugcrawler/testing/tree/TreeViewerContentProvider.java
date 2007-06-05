package bugcrawler.testing.tree;

import java.util.Date;

import bugcrawler.runtime.views.Bug;

public class TreeViewerContentProvider {

    private Bug[] bugs;
    
    public TreeViewerContentProvider(){
	bugs = new Bug[]{
		new Bug("Testbug","Tobias",new Date(),"J�rn",new Date()),
		new Bug("Testbug","J�rn",new Date(),"Tobias",new Date())
	};
    }
    
    public Bug[] getContent(){
	return bugs;
    }
}
