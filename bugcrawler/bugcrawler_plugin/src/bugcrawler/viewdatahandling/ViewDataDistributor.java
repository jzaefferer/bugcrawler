package bugcrawler.viewdatahandling;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.ui.part.ViewPart;

/**
 * Class to store and share data between Views
 * 
 * @author TSO
 */
public class ViewDataDistributor extends Observable {

	private Object viewData;

	private ArrayList<Observer> views = new ArrayList<Observer>();

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
	 * }</pre>
	 * 
	 * Example-Call:
	 * 
	 * <pre>
	 * Activator.getViewDataDistributor().saveViewData(&quot;StringToSave&quot;);
	 * Activator.getViewDataDistributor().getViewData();
	 * </pre>
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
	 */
	public void addView(Observer viewPart) {
		// check if the given Observer is a ViewPart
		if (viewPart instanceof ViewPart) {
			throw new RuntimeException("Error the added observer isn't a view!");
		}
		// check if the view has been added before
		for (Observer view : views) {
			if (view.equals(viewPart)) {
				return;
			}
		}
		// add the view to the arraylist to be known
		views.add(viewPart);

		// add the view to the created observer
		this.addObserver(viewPart);
	}

	/**
	 * Adds a new View to the created distributor. It will also be checked, if a
	 * view was already added
	 * 
	 * @see addView(Observer viewPart);
	 * 
	 * @param Observer
	 *            ViewPart which has to be added.
	 * 
	 */
	public synchronized void addObserver(Observer o) {
		addView(o);
	}
}
