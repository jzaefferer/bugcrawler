package bugcrawler.runtime.projectwizard;

import java.util.Arrays;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Shell;

import bugcrawler.runtime.bugtree.BugTreeViewer;

public class ProjectWizardDialog extends WizardDialog {

	private WizardPage currentPage;
	
	private ProjectWizard projectWizard;
	
	public ProjectWizardDialog(Shell parentShell, IWizard projectWizard) {
		super(parentShell, projectWizard);
		this.projectWizard=(ProjectWizard)projectWizard;
	}
	
    /**
     * The Next button has been pressed.
     */
    protected void nextPressed() {
    	if(currentPage==null){
    		currentPage=((ProjectWizard)projectWizard).getFirstPage();
    	}
        IWizardPage page = currentPage.getNextPage();
        if (page == null) {
            // something must have happend getting the next page
            return;
        }
        if(currentPage instanceof ProjectWizardProjectPage){
        	BugTreeViewer bugTreeViewer = projectWizard.getBugTreeViewer();
    		bugTreeViewer.setInput(Arrays.asList(((ProjectWizardProjectPage)currentPage).getSelected()));
        	bugTreeViewer.expandToLevel(-1);
        }
        // show the next page
        showPage(page);
    }
}
