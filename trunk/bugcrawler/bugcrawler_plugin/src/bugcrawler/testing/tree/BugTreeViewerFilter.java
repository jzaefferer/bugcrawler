package bugcrawler.testing.tree;

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
			if(filterOption.equals(ColumnTitles.Summary.toString())){
				
			}else if(filterOption.equals(ColumnTitles.Component.toString())){
				
			}else if(filterOption.equals(ColumnTitles.Version.toString())){
				
			}else if(filterOption.equals(ColumnTitles.Milestone.toString())){
				
			}else if(filterOption.equals(ColumnTitles.Type.toString())){
				
			}else if(filterOption.equals(ColumnTitles.Severity.toString())){
				
			}else if(filterOption.equals(ColumnTitles.Owner.toString())){
				
			}
			if(((Bug)element).getOwner().equals("Tobias")){
				return true;				
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
