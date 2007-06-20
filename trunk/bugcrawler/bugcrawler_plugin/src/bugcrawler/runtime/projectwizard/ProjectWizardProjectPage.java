package bugcrawler.runtime.projectwizard;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

import bugcrawler.runtime.data.BugTestData;
import bugcrawler.runtime.data.Project;

public class ProjectWizardProjectPage extends WizardPage implements Listener{

	private List list;
	
	private boolean pageComplete = false;
	
	protected ProjectWizardProjectPage(){
		super("Selection Page");
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
		gridData.widthHint=40;
		gridData.heightHint=15;
		gridData.verticalAlignment=GridData.END;
		label.setLayoutData(gridData);
		
		list = new List (composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;		
		list.setLayoutData(gridData);
		list.addListener(SWT.Selection, this);
		refreshList();
		setControl(composite);
	}
	
	public Object[] getSelected(){
		ArrayList<Object> projectsList = new ArrayList<Object>();
		Object[] projects = BugTestData.getTestData();
		String[] projectsSelected = list.getSelection();
		for(String item:projectsSelected){
			for(Object project:projects){
				if(item.equals(((Project)project).getName())){
					projectsList.add(project);
				}
			}
		}
		return projectsList.toArray();
	}
	
	
	public boolean canFlipToNextPage(){
		return isPageComplete();
	}

	public void handleEvent(Event event) {
		if(event.widget==list && ((List)event.widget).getSelection().length>0){
			pageComplete=true;
		}
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}
	
	private void refreshList(){
		list.removeAll();
		Object[] testdata = BugTestData.getTestData();
		for(Object proj:testdata){
			list.add(((Project)proj).getName());
		}
	}
	
	public boolean isPageComplete(){
		return pageComplete;
	}
	
}
