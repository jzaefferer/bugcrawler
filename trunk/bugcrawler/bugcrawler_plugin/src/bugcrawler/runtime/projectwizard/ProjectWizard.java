package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class ProjectWizard extends Wizard implements INewWizard{
	
	
	public ProjectWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("Bugcrawler Project-Wizzard");
	}

	@Override
	public boolean performFinish() {
		return false;
	}

	public void addPages() {
		addPage(new ProjectWizardPage());
	}

	public void handleEvent(Event event) {
		
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
}