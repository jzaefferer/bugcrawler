package bugcrawler.viewdatahandling;

import java.util.Observable;
import java.util.Observer;

/**
 * Interface to be implemented in views listening to ViewDataDistributor
 * 
 * @author TSO
 *
 */
public interface ViewDataListener extends Observer {
	
	/**
	 * This method will be called when a view implements this interface, is registered to
	 * a ViewDataDistributor and the saveViewData-Method is called. If several views are 
	 * registered to the a ViewDataDistributor each update-method of each view will be called.
	 * 
	 * Example:
	 * <pre>
	 * public class BugDiagramView extends ViewPart implements ViewDataListener{
	 * 
	 * ...
	 * 
	 * public DefaultConstructorOfMyViewPart(){
	 * 	ViewDataDistributor distributor = Activator.getViewDataDistributor();
	 * 	distributor.addView(this,"bugcrawler_plugin.views.BugDiagramView");
	 * 	distributor.saveViewData("When this method is called, the update method will be called");
	 * }
	 * 
	 * public void update(Observable viewDataDistributor, Object viewData) {
	 * 	if(viewDataDistributor==Activator.getViewDataDistributor()){
	 *		System.out.println("saveViewData-Information:"+viewData);
	 * 	}
	 * }
	 * 
	 * ...
	 * </pre>
	 * @see bugcrawler.viewdatahandling.ViewDataDistributor
	 */
	public void update(Observable viewDataDistributor, Object viewData);
	
}
