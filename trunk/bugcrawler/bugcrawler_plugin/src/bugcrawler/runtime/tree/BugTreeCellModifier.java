package bugcrawler.runtime.tree;

import org.eclipse.jface.viewers.ICellModifier;

import bugcrawler.runtime.data.Bug;
import bugcrawler.runtime.data.ColumnTitles;

public class BugTreeCellModifier implements ICellModifier{
	
	public boolean canModify(Object element, String property) {
		if(!(element instanceof Bug)){
			return false;
		}else{
			return true;
		}
	}
	
	//TODO Filling editors with the right stuff when editing.
	public Object getValue(Object element, String property) {
		if (element instanceof Bug){
			if(property.equals(ColumnTitles.Summary.toString())){
				return ((Bug)element).getSummary();
			}else if(property.equals(ColumnTitles.Summary.toString())){
				return ((Bug)element).getSummary();
			}else{
				return "";
			}
		}
		return "";
	}
	
	public void modify(Object element, String property, Object value) {
		//do nothing
	}
}
