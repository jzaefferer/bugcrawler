package bugcrawler.runtime.projectwizard;

import java.util.Arrays;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import bugcrawler.runtime.tree.BugTreeViewer;

public class ProjectWizard extends Wizard implements INewWizard{
	
	private BugTreeViewer bugTreeViewer;
	
	private ProjectWizardPage page;
	
	public ProjectWizard(BugTreeViewer bugTreeViewer) {
		super();
		this.bugTreeViewer = bugTreeViewer;
		setNeedsProgressMonitor(true);
		setWindowTitle("Bugcrawler Project-Wizzard");
	}

	@Override
	public boolean performFinish() {
		bugTreeViewer.setInput(Arrays.asList(page.getSelected()));
		bugTreeViewer.expandToLevel(-1);
		return true;
	}

	public void addPages() {
		page = new ProjectWizardPage();
		addPage(page);
	}

	public void handleEvent(Event event) {
		
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
}