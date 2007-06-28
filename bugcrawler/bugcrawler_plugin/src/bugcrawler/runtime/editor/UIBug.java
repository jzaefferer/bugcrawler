package bugcrawler.runtime.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import bugcrawler.runtime.data.Bug;

/**
 * Wrapping Bug-Objects into a class for using them as input for an editor
 * 
 * @author TSO
 */
public class UIBug implements IEditorInput {

	/**
	 * the choosen bug in the bugTreeViewer
	 */
	private Bug bug;

	/**
	 * Initialize the Wrapperclass by giving the choosen bug
	 * 
	 * @param bug
	 *            the bug which was choosen in the bugTreeViewer
	 */
	public UIBug(Bug bug) {
		this.bug = bug;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName() {
		return bug.getSummary();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return bug.getSummary();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}

	/**
	 * Get the bug of this wrapper-class
	 * 
	 * @return Bug the bug of this wrapper-class
	 */
	public Bug getBug() {
		return bug;
	}

}
