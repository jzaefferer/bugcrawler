package bugcrawler.runtime.tree;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.BugContainer;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.data.Project;
import bugcrawler.runtime.preferences.PreferenceConstants;

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
		String filter = getPreferenceStore().getString(PreferenceConstants.FILTER);
		
		if(element instanceof Project){ 
			return true;
		}else if(element instanceof BugContainer){
			return true;
		}else if(element instanceof Bug){
			
			for(String filterOptionValue:filterOptionsStoringLocations){
				
				boolean toCheck = getPreferenceStore().getBoolean(filterOptionValue);
				String filterOption = filterOptionValue.split(";")[1];
				
				if(filterOption.equals(ColumnTitles.Ticket.toString()) && toCheck){
					if(((Bug)element).getTicket().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(ColumnTitles.Summary.toString()) && toCheck){
					if(((Bug)element).getSummary().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(ColumnTitles.Component.toString()) && toCheck){
					if(((Bug)element).getComponent().contains(filter)){
						return true;
					}
				}else if(filterOption.equals(ColumnTitles.Version.toString()) && toCheck){
					if(((Bug)element).getVersion().contains(filter)){
						return true;
					}				
				}else if(filterOption.equals(ColumnTitles.Milestone.toString()) && toCheck){
					if(((Bug)element).getMilestone().contains(filter)){
						return true;
					}				
				}else if(filterOption.equals(ColumnTitles.Type.toString()) && toCheck){
					if(((Bug)element).getType().contains((filter))){
						return true;
					}						
				}else if(filterOption.equals(ColumnTitles.Severity.toString()) && toCheck){
					if(((Bug)element).getSeverity().contains(filter)){
						return true;
					}		
				}else if(filterOption.equals(ColumnTitles.Owner.toString()) && toCheck){
					if(((Bug)element).getOwner().contains(filter)){
						return true;
					}
					
				// is noch hirnrissig wegen Mon Dec 11 16:06:57 CET 2006
				}else if(filterOption.equals(ColumnTitles.Created.toString()) && toCheck){
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
