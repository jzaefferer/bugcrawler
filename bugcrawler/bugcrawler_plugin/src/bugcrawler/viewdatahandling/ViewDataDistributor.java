package bugcrawler.viewdatahandling;

import java.util.Observable;
import java.util.Observer;

public class ViewDataDistributor extends Observable {

	private Object viewData;
	
	public ViewDataDistributor() {
		super();
	}

	public void saveViewData(Object viewData) {
		this.viewData = viewData;
		super.setChanged();
		super.notifyObservers(viewData);
	}

	public Object getViewData(){
		return viewData;
	}
	
	public void addView(Observer viewPart){
		this.addObserver(viewPart);
	}
}
