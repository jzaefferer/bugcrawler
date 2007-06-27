package bugcrawler.runtime.projectwizard;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import bugcrawler.runtime.bugtree.BugTreeViewer;
import bugcrawler.runtime.data.BugTestData;
import bugcrawler.runtime.projectwizard.projecttable.ProjectTableViewer;

/**
 * The ProjectPage shown in the wizard for choosing a project
 * 
 * @author TSO
 */
public class ProjectWizardProjectPage extends WizardPage implements ISelectionChangedListener {

	/**
	 * the projectTableViewer showing the projects in this wizard
	 */
	private ProjectTableViewer projectTableViewer;

	/**
	 * boolean which indicates if the page is Completed for finishing the
	 * wizzard
	 */
	private boolean pageComplete = false;

	/**
	 * the bugTreeViewer to get the input of the projectTableViewer
	 */
	private BugTreeViewer bugTreeViewer;

	/**
	 * Initializes the ProjectPage, to let users choose a project for beeing
	 * displayed in the bugTreeViewer
	 * 
	 * @param bugTreeViewer
	 *            the bugTreeViewer for getting the input of the
	 *            projectTableViewer
	 */
	protected ProjectWizardProjectPage(BugTreeViewer bugTreeViewer) {
		super("Selection Page");
		this.bugTreeViewer = bugTreeViewer;
		setTitle("Project Selection");
		setDescription("Please select your Project to see the Buglistings");
	}

	/**
	 * Creates the appearance of this page
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Project:");
		GridData gridData = new GridData();
		label.setLayoutData(gridData);

		projectTableViewer = new ProjectTableViewer(composite);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		projectTableViewer.setLayoutData(gridData);
		projectTableViewer.addSelectionChangedListener(this);
		fillAndCheckProjectTableViewer();

		setControl(composite);
	}

	/**
	 * fill the projectTableViewer and if the project already have been shown in
	 * the bugTreeViewer they will be checked
	 */
	private void fillAndCheckProjectTableViewer() {
		Object input = bugTreeViewer.getInput();
		projectTableViewer.setInput(Arrays.asList(BugTestData.getTestData()));
		if (input != null) {
			projectTableViewer.setCheckedElements(((List) input).toArray());
		}
	}

	/**
	 * Get the selected projects
	 * 
	 * @return Object[] the Projects which are choosen in the projectTableViewer
	 */
	public Object[] getSelected() {
		return projectTableViewer.getCheckedElements();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return pageComplete;
	}

	/**
	 * Check if a project has been choosen and refreshing the buttons
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if (projectTableViewer.getCheckedElements().length > 0) {
			pageComplete = true;
		} else {
			pageComplete = false;
		}
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}
}
