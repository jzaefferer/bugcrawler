package bugcrawler.runtime.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class UIBug implements IEditorInput{

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "POPOPO";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "-.- wieviele Errors noch";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}
