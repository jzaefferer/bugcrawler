package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class ProjectWizard extends Wizard implements INewWizard{

    
    public ProjectWizard(){
	super();
	setNeedsProgressMonitor(true);
    }
    
    @Override
    public boolean performFinish() {
	return false;
    }

    public void addPages() {
        ProjectWizardPage page = new ProjectWizardPage();
        addPage(page);
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }    

}