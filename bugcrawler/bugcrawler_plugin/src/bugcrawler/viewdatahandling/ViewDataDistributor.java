package bugcrawler.viewdatahandling;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.ui.part.ViewPart;

/**
 * Class to store and share data between Views
 * 
 * @author TSO
 */
public class ViewDataDistributor extends Observable {

	private List<String> viewIds = new ArrayList<String>();
	
	private Object viewData;

	/**
	 * Generates a new ViewDataDistributor. It is intended to use it in the
	 * Activator as static, so that the Distributor can be accessed from
	 * everywhere.
	 * 
	 * <br>
	 * Example:
	 * 
	 * <pre>
	 * private static ViewDataDistributor viewDataDistributor1 = new ViewDataDistributor();
	 * 
	 * public static ViewDataDistributor getViewDataDistributor() {
	 * 	return viewDataDistributor;
	 * }
	 * </pre>
	 * 
	 * Example-Call:
	 * 
	 * <pre>
	 * Activator.getViewDataDistributor().saveViewData(&quot;StringToSave&quot;);
	 * Activator.getViewDataDistributor().getViewData();
	 * </pre>
	 * 
	 * You also need to register a ViewDataListener to each view, which has to receive data
	 * 
	 * @see bugcrawler.viewdatahandling.ViewDataListener
	 */
	public ViewDataDistributor() {
		super();
	}

	/**
	 * saves ViewData in the created distributor
	 * 
	 * @param Object
	 *            viewData wanted to be shared for all views
	 */
	public void saveViewData(Object viewData) {
		this.viewData = viewData;
		super.setChanged();
		super.notifyObservers(viewData);
	}

	/**
	 * get the ViewData stored in this distributor
	 * 
	 * @return Object viewData that had been stored in this distributor
	 */
	public Object getViewData() {
		return viewData;
	}

	/**
	 * Adds a new View to the created distributor. It will also be checked, if a
	 * view was already added
	 * 
	 * @param Observer
	 *            ViewPart which has to be added.
	 * 
	 * @param viewId
	 *            the unique Id of the viewPart <b>Note</b>: it is <u>important</u> that
	 *            this id is unique because otherwise the view could be
	 *            registered serveral times
	 */
	public void addView(Observer viewPart, String viewId) {
		// check if the given Observer is a ViewPart
		if (!(viewPart instanceof ViewPart)) {
			throw new RuntimeException("Error the added observer isn't a view!");
		}
		// check if the given viewPart is already known
		for(String mentionedId : viewIds){
			if(mentionedId.equals(viewId)){
				return;
			}
		}
		// adding the viewId to the registered Ids
		viewIds.add(viewId);
		// add the view to the created observer
		this.addObserver(viewPart);
	}
}
