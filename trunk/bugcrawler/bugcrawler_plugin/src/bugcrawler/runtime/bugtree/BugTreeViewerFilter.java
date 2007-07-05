package bugcrawler.runtime.bugtree;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.Project;
import bugcrawler.runtime.data.TreeColumnTitles;

/**
 * Filters bugs
 * 
 * @author NoCTUrN's pLaCe
 */
public class BugTreeViewerFilter extends ViewerFilter {
	
	/**
	 * the preferenceStore references of the checkboxes
	 * 
	 * @see bugcrawler.utils.CheckBoxGroupFieldEditor
	 */
	private String[] filterOptionsStoringLocations;
	
	/*
	 * Make the Default-Constructor not accessible
	 */
	private BugTreeViewerFilter(){}
	
	/**
	 * Initializes the BugTreeViewerFilter
	 * 
	 * @param filterOptionsStoringLocations
	 * 					look which column should be sorted and which nit by reading the storingLocations of the
	 * 					CheckBogGroupFieldEditor
	 */
	public BugTreeViewerFilter(String[] filterOptionsStoringLocations){
		this.filterOptionsStoringLocations = filterOptionsStoringLocations;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		String filter = preferenceStore().getString(Constants.FILTER);
		
		if(element instanceof Project){ 
			return true;
		}else if(element instanceof BugContainer){
			return true;
		}else if(element instanceof Bug){
			
			for(String filterOptionValue:filterOptionsStoringLocations){
				
				boolean toCheck = preferenceStore().getBoolean(filterOptionValue);
				if(!toCheck){
					return false;
				}
				String filterOption = filterOptionValue.split(";")[1];
				
				if(filterOption.equals(TreeColumnTitles.Ticket.toString())){
					if(bug(element).getTicket().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(TreeColumnTitles.Summary.toString())){
					if(bug(element).getSummary().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(TreeColumnTitles.Component.toString())){
					if(bug(element).getComponent().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(TreeColumnTitles.Version.toString())){
					if(bug(element).getVersion().contains(filter)){
						return true;
					}				
				}else if(filterOption.equals(TreeColumnTitles.Milestone.toString())){
					if(bug(element).getMilestone().contains(filter)){
						return true;
					}				
				}else if(filterOption.equals(TreeColumnTitles.Type.toString())){
					if(bug(element).getType().contains((filter))){
						return true;
					}						
				}else if(filterOption.equals(TreeColumnTitles.Severity.toString())){
					if(bug(element).getSeverity().contains(filter)){
						return true;
					}		
				}else if(filterOption.equals(TreeColumnTitles.Owner.toString())){
					if(bug(element).getOwner().contains(filter)){
						return true;
					}
					
				// is noch hirnrissig wegen Mon Dec 11 16:06:57 CET 2006
				}else if(filterOption.equals(TreeColumnTitles.Created.toString())){
					if(bug(element).getCreated().toString().contains(filter)){
						return true;
					}
				}
			}
			return false;
		}else{
			return false;
		}
	}

	private Bug bug(Object element) {
		return (Bug)element;
	}
	

	private IPreferenceStore preferenceStore(){
		return Activator.getDefault().getPreferenceStore();
	}
}
