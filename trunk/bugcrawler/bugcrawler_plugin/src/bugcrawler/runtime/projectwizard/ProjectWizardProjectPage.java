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

public class ProjectWizardProjectPage extends WizardPage implements ISelectionChangedListener{
	
	private ProjectTableViewer projectTableViewer;
	
	private boolean pageComplete = false;
	
	private BugTreeViewer bugTreeViewer;
	
	protected ProjectWizardProjectPage(BugTreeViewer bugTreeViewer){
		super("Selection Page");
		this.bugTreeViewer=bugTreeViewer;
		setTitle("Project Selection");
		setDescription("Please select your Project to see the Buglistings");
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2,false);
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
		fillProjectTableViewer();

		setControl(composite);
	}
	
	private void fillProjectTableViewer(){
		Object input = bugTreeViewer.getInput();
		projectTableViewer.setInput(Arrays.asList(BugTestData.getTestData()));
		if(input!=null){
			projectTableViewer.setCheckedElements(((List) input).toArray());
		}
	}
	
	public Object[] getSelected(){
		return projectTableViewer.getCheckedElements();
	}
	
	public boolean canFlipToNextPage(){
		return isPageComplete();
	}
	
	public boolean isPageComplete(){
		return pageComplete;
	}

	public void selectionChanged(SelectionChangedEvent event) {
		if(projectTableViewer.getCheckedElements().length > 0){
			pageComplete=true;
		}else{
			pageComplete=false;
		}
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}
}
