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
	
	private IPreferenceStore getPreferenceStore(){
		return Activator.getDefault().getPreferenceStore();
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		String filterOption = getPreferenceStore().getString(PreferenceConstants.FILTEROPTIONS);		
		String filter = getPreferenceStore().getString(PreferenceConstants.FILTER);
		
		if(element instanceof Project){ 
			return true;
		}else if(element instanceof BugContainer){
			return true;
		}else if(element instanceof Bug){
			if(filterOption.equals(ColumnTitles.Ticket.toString())){
				if(((Bug)element).getTicket().contains(filter)){
					return true;
				}
			}else if(filterOption.equals(ColumnTitles.Summary.toString())){
				if(((Bug)element).getSummary().contains(filter)){
					return true;
				}
			}else if(filterOption.equals(ColumnTitles.Component.toString())){
				if(((Bug)element).getComponent().contains(filter)){
					return true;
				}
			}else if(filterOption.equals(ColumnTitles.Version.toString())){
				if(((Bug)element).getVersion().contains(filter)){
					return true;
				}				
			}else if(filterOption.equals(ColumnTitles.Milestone.toString())){
				if(((Bug)element).getMilestone().contains(filter)){
					return true;
				}				
			}else if(filterOption.equals(ColumnTitles.Type.toString())){
				if(((Bug)element).getType().contains((filter))){
					return true;
				}						
			}else if(filterOption.equals(ColumnTitles.Severity.toString())){
				if(((Bug)element).getSeverity().contains(filter)){
					return true;
				}		
			}else if(filterOption.equals(ColumnTitles.Owner.toString())){
				if(((Bug)element).getOwner().contains(filter)){
					return true;
				}
				
			// is noch hirnrissig wegen Mon Dec 11 16:06:57 CET 2006
			}else if(filterOption.equals(ColumnTitles.Created.toString())){
				if(((Bug)element).getCreated().toString().contains(filter)){
					System.out.println(((Bug)element).getCreated());
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}
}
