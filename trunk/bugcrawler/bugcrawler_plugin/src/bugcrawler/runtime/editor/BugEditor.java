package bugcrawler.runtime.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.bugtree.BugTreeViewer;

public class BugEditor extends FormEditor {

	
	private BugTreeViewer bugTreeViewer;

	protected FormToolkit createToolkit(Display display) {
		// Create a toolkit that shares colors between editors.
		return new FormToolkit(Activator.getDefault().getFormColors(display));
	}

	@Override
	protected void addPages() {
		addPagesToEditor(this.getEditorInput());
	}

	public void addPagesToEditor(IEditorInput bug) {
		try {
			this.setInput(bug);
			addPage(new BugFormPage(this, "1", ((UIBug)bug).getBug().getTicket()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public String getTitleToolTip() {
		return "FUUFUFUF";
	}
	
	public BugTreeViewer getBugTreeViewer() {
		return bugTreeViewer;
	}

	public void setBugTreeViewer(BugTreeViewer bugTreeViewer) {
		this.bugTreeViewer = bugTreeViewer;
	}

}