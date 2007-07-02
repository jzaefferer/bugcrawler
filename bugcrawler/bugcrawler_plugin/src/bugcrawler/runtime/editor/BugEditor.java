package bugcrawler.runtime.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.bugtree.BugTreeViewer;
import bugcrawler.runtime.data.Bug;

/**
 * The Editor-Extension in this case a FormEditor is used to show bugs
 * 
 * @author TSO
 */
public class BugEditor extends FormEditor {

	/**
	 * The bugTreeViewer where the bugs are received from
	 */
	private BugTreeViewer bugTreeViewer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormEditor#createToolkit(org.eclipse.swt.widgets.Display)
	 */
	protected FormToolkit createToolkit(Display display) {
		return new FormToolkit(Activator.getDefault().getFormColors(display));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	protected void addPages() {
		addPagesToEditor(this.getEditorInput());
	}

	public void addPagesToEditor(IEditorInput UIBug) {
		try {
			this.setInput(UIBug);
			Bug bug = ((UIBug) UIBug).getBug();
			//addPage(new BugFormPage(this, bug.getTicket(), bug.getTicket()));
			addPage(new BugFormPage(this, bug.toString(), bug.getTicket()));
			setActivePage(bug.getTicket());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		// TODO Do Save modified Bugs
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	public void doSaveAs() {
		// TODO Do Save modified Bugs
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorPart#getTitleToolTip()
	 */
	public String getTitleToolTip() {
		return "FUUFUFUF";
	}

	/**
	 * get the bugTreeViewer of the Editor-instance
	 * 
	 * @return the current bugTreeViewer
	 */
	public BugTreeViewer getBugTreeViewer() {
		return bugTreeViewer;
	}

	/**
	 * set the bugTreeViewer to the Editor-instance
	 * 
	 * @param the
	 *            current bugTreeViewer
	 */
	public void setBugTreeViewer(BugTreeViewer bugTreeViewer) {
		this.bugTreeViewer = bugTreeViewer;
	}

}