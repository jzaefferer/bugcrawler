package bugcrawler.runtime.projectwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import bugcrawler.runtime.bugtree.BugTreeViewer;
import bugcrawler.runtime.filterdialog.BugTreeViewerFilterComponents;

/**
 * Second page of the ProjectWizzard to set a filter directly to the bugTreeViewer
 * 
 * @author TSO
 */
public class ProjectWizardFilterPage extends WizardPage{

	private BugTreeViewerFilterComponents components;
		
	private BugTreeViewer bugTreeViewer;
	/**
	 * Initializes the FilterPage with Title and Description
	 */
	protected ProjectWizardFilterPage(BugTreeViewer bugTreeViewer) {
		super("Filter Page");
		this.bugTreeViewer = bugTreeViewer;
		setTitle("Filter Selection");
		setDescription("Please select your Filter-Options to see a Buglistings you want to.");		
	}

	/**
	 * create the Appearance of the FilterPage
	 */
	public void createControl(Composite parent) {		
		GridData gridData = new GridData(GridData.FILL_BOTH);
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout dialogLayout = new GridLayout(1, false);
		container.setLayout(dialogLayout);
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
		components.createRestoreButton(dialogContentContainer,bugTreeViewer);
		
		setControl(container);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage(){
		return false;
	}
}
