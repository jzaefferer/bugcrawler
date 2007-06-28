package bugcrawler.runtime.bugtree;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.TreeColumnTitles;
import bugcrawler.runtime.data.Project;
import bugcrawler.runtime.filterdialog.BugTreeViewerFilterDialog;

public class BugTreeViewerFilter extends ViewerFilter {
	
	private String[] filterOptionsStoringLocations;
	
	protected BugTreeViewerFilter(){}
	
	public BugTreeViewerFilter(BugTreeViewerFilterDialog dialog){
		this.filterOptionsStoringLocations = dialog.getfilterOptionsStoringLocations();
	}
	
	private IPreferenceStore getPreferenceStore(){
		return Activator.getDefault().getPreferenceStore();
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		String filter = getPreferenceStore().getString(Constants.FILTER);
		
		if(element instanceof Project){ 
			return true;
		}else if(element instanceof BugContainer){
			return true;
		}else if(element instanceof Bug){
			
			for(String filterOptionValue:filterOptionsStoringLocations){
				
				boolean toCheck = getPreferenceStore().getBoolean(filterOptionValue);
				String filterOption = filterOptionValue.split(";")[1];
				
				if(filterOption.equals(TreeColumnTitles.Ticket.toString()) && toCheck){
					if(((Bug)element).getTicket().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(TreeColumnTitles.Summary.toString()) && toCheck){
					if(((Bug)element).getSummary().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(TreeColumnTitles.Component.toString()) && toCheck){
					if(((Bug)element).getComponent().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(TreeColumnTitles.Version.toString()) && toCheck){
					if(((Bug)element).getVersion().contains(filter)){
						return true;
					}				
				}else if(filterOption.equals(TreeColumnTitles.Milestone.toString()) && toCheck){
					if(((Bug)element).getMilestone().contains(filter)){
						return true;
					}				
				}else if(filterOption.equals(TreeColumnTitles.Type.toString()) && toCheck){
					if(((Bug)element).getType().contains((filter))){
						return true;
					}						
				}else if(filterOption.equals(TreeColumnTitles.Severity.toString()) && toCheck){
					if(((Bug)element).getSeverity().contains(filter)){
						return true;
					}		
				}else if(filterOption.equals(TreeColumnTitles.Owner.toString()) && toCheck){
					if(((Bug)element).getOwner().contains(filter)){
						return true;
					}
					
				// is noch hirnrissig wegen Mon Dec 11 16:06:57 CET 2006
				}else if(filterOption.equals(TreeColumnTitles.Created.toString()) && toCheck){
					if(((Bug)element).getCreated().toString().contains(filter)){
						System.out.println(((Bug)element).getCreated());
						return true;
					}
				}
			}
			return false;
		}else{
			return false;
		}
	}
}
