package bugcrawler.runtime.projectwizard;

import java.util.Arrays;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.bugtree.BugTreeViewer;
import bugcrawler.runtime.views.BugDiagramView;

/**
 * Wizard for choosing a project for getting bug information
 * 
 * @author TSO
 */
public class ProjectWizard extends Wizard implements INewWizard{
	
	/**
	 * the bugTreeViewer to get the displayed projects
	 */
	private BugTreeViewer bugTreeViewer;
	
	/**
	 * the projectpage to choose a project
	 */
	private ProjectWizardProjectPage projectpage;
	

	/**
	 * the filterpage to set a filter for the choosen projects
	 */
	private ProjectWizardFilterPage filterpage;
	
	/**
	 * Initializes the Wizard
	 * 
	 * @param bugTreeViewer
	 * 			bugTreeViewer of the mainview
	 */	
	public ProjectWizard(BugTreeViewer bugTreeViewer) {
		super();
		this.bugTreeViewer = bugTreeViewer;
		setNeedsProgressMonitor(true);
		setWindowTitle("Bugcrawler Project-Wizzard");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		bugTreeViewer.setInput(Arrays.asList(projectpage.getSelected()));
		bugTreeViewer.expandToLevel(-1);
		BugDiagramView bugDiagramView = (BugDiagramView)Activator.activePage().findView("bugcrawler_plugin.views.BugDiagramView");
		if(bugDiagramView != null){
			Activator.getViewDataDistributor().saveViewData("");
		}
		return true;
	}
	
	/**
	 * Get the BugTreeViewer of the wizard 
	 * @return BugTreeViewer
	 * 			the BugTreeViewer of this wizard
	 */
	public BugTreeViewer getBugTreeViewer(){
		return bugTreeViewer;
	}

	/**
	 * Adding the wizardpages to the wizard
	 */
	public void addPages() {
		projectpage = new ProjectWizardProjectPage(bugTreeViewer);
		addPage(projectpage);
		
		filterpage = new ProjectWizardFilterPage(bugTreeViewer);
		addPage(filterpage);
	}
	
	/**
	 * Get the first page of this wizard
	 * 
	 * @return WizardPage
	 * 			the projectpage, because it is the first page in the wizard
	 */
	public WizardPage getFirstPage(){
		return projectpage;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {}
}