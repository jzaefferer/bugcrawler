package bugcrawler.runtime.projectwizard;

import java.util.Arrays;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import bugcrawler.runtime.tree.BugTreeViewer;

public class ProjectWizard extends Wizard implements INewWizard{
	
	private BugTreeViewer bugTreeViewer;
	
	private ProjectWizardProjectPage projectpage;
	
	private ProjectWizardFilterPage filterpage;
	
	public ProjectWizard(BugTreeViewer bugTreeViewer) {
		super();
		this.bugTreeViewer = bugTreeViewer;
		setNeedsProgressMonitor(true);
		setWindowTitle("Bugcrawler Project-Wizzard");
	}

	@Override
	public boolean performFinish() {
		bugTreeViewer.setInput(Arrays.asList(projectpage.getSelected()));
		bugTreeViewer.expandToLevel(-1);
		return true;
	}
	
	public BugTreeViewer getBugTreeViewer(){
		return bugTreeViewer;
	}

	public void addPages() {
		projectpage = new ProjectWizardProjectPage();
		addPage(projectpage);
		
		filterpage = new ProjectWizardFilterPage();
		addPage(filterpage);
	}
	
	public WizardPage getFirstPage(){
		return projectpage;
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
}