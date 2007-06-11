package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.wizard.Wizard;

public class ProjectWizard extends Wizard {
	
	
	public ProjectWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("Project-Wizard");
	}

	@Override
	public boolean performFinish() {
		return false;
	}

	public void addPages() {
		super.addPages();
		addPage(new ProjectWizardPage());
	}
}