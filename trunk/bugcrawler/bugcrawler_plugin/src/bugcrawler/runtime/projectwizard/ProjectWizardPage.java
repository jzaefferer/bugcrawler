package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ProjectWizardPage extends WizardPage implements IWizardPage {

	protected ProjectWizardPage() {
		super("MyFirstWizardPage");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1,false));
		Label lab = new Label(composite, SWT.NONE);
		lab.setText("urgss");
		
		setControl(composite);
	}
}
