package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class ProjectWizardFilterPage extends WizardPage{

	protected ProjectWizardFilterPage() {
		super("Filter Page");
		setTitle("Filter Selection");
		setDescription("Please select your Filter-Options to see a Buglistings you want to.");		
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2,false);
		composite.setLayout(gridLayout);
		setControl(composite);
	}
	
	public boolean canFlipToNextPage(){
		return false;
	}
	
	public void handleEvent(Event event) {
	}	

}
