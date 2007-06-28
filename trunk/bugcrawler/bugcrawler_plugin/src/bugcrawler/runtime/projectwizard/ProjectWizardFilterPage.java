package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import bugcrawler.runtime.filterdialog.BugTreeViewerFilterComponents;

/**
 * Second page of the ProjectWizzard to set a filter directly to the bugTreeViewer
 * 
 * @author TSO
 */
public class ProjectWizardFilterPage extends WizardPage{

	private BugTreeViewerFilterComponents components;
	
	//private StringFieldEditor filter;

	//private CheckBoxGroupFieldEditor filterOptions;
	
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
		Composite container = new Composite(parent, SWT.NONE);
		
		GridLayout dialogLayout = new GridLayout(1, false);
		container.setLayout(dialogLayout);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		container.setLayoutData(gridData);
		
		Composite dialogContentContainer = new Composite(container, SWT.NULL);
		GridLayout dialogContentLayout = new GridLayout(1, false);
		dialogContentLayout.marginLeft = 5;
		dialogContentLayout.marginRight = 5;
		dialogContentContainer.setLayout(dialogContentLayout);
		dialogContentContainer.setLayoutData(gridData);
		
		components = BugTreeViewerFilterComponents.getComponents();
		
		/*filter = */components.createFilterTextEditor(dialogContentContainer);
		/*filterOptions = */components.createFilterOptionRadioBoxes(dialogContentContainer);
		components.createRestoreButton(dialogContentContainer);
		
		setControl(container);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage(){
		return false;
	}
}
