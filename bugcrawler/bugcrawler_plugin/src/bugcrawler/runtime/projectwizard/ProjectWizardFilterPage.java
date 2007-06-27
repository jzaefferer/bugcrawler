package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Second page of the ProjectWizzard to set a filter directly to the bugTreeViewer
 * 
 * @author TSO
 */
public class ProjectWizardFilterPage extends WizardPage{

	/**
	 * Initializes the FilterPage with Title and Description
	 */
	protected ProjectWizardFilterPage() {
		super("Filter Page");
		setTitle("Filter Selection");
		setDescription("Please select your Filter-Options to see a Buglistings you want to.");		
	}

	/**
	 * create the Appearance of the FilterPage
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2,false);
		composite.setLayout(gridLayout);
		setControl(composite);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage(){
		return false;
	}
}
