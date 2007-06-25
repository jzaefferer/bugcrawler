package bugcrawler.runtime.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import bugcrawler.runtime.data.Bug;

public class UIBug implements IEditorInput {

	private Bug bug;

	public UIBug(Bug bug) {
		this.bug = bug;
	}

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return bug.getSummary();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return bug.getSummary();
	}

	public Object getAdapter(Class adapter) {
		return null;
	}
	
	public Bug getBug(){
		return bug;
	}

}
