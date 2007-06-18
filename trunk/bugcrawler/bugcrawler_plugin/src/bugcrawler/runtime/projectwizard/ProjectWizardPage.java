package bugcrawler.runtime.projectwizard;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

import bugcrawler.runtime.data.BugTestData;
import bugcrawler.runtime.data.Project;

public class ProjectWizardPage extends WizardPage implements Listener {

	private List list;
	
	protected ProjectWizardPage() {
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
		
		Button button = new Button(composite,SWT.PUSH);
		button.setText("Refresh List");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		button.setLayoutData(gridData);
		button.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				refreshList();
			}
		});
		
		list = new List (composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;		
		list.setLayoutData(gridData);
		
		setControl(composite);
	}

	private void refreshList(){
		list.removeAll();
		Object[] testdata = BugTestData.getTestData();
		for(Object proj:testdata){
			list.add(((Project)proj).getName());
		}
	}
	
	public Object[] getSelected(){
		ArrayList<Object> fufufu = new ArrayList<Object>();
		Object[] testdata = BugTestData.getTestData();
		String[] items = list.getSelection();
		for(String item:items){
			for(Object data:testdata){
				if(item.equals(((Project)data).getName())){
					fufufu.add(data);
				}
			}
		}
		return fufufu.toArray();
	}
	
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
	}
}
