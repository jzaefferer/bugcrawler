package bugcrawler.viewdatahandling;

import java.util.Observable;
import java.util.Observer;

public interface ViewDataListener extends Observer {
	
	public void update(Observable view, Object viewData);
	
}
