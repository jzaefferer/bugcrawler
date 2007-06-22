package bugcrawler.runtime.bugtree;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.runtime.data.TreeColumnTitles;
import bugcrawler.utils.CheckBoxGroupFieldEditor;

public class BugTreeViewerFilterDialog extends Dialog {

	private GridData gridData;

	private StringFieldEditor filter;

	private CheckBoxGroupFieldEditor filterOptions;

	private BugTreeViewer bugTreeViewer;

	private String[] filterOptionsStoringLocations;
	
	public BugTreeViewerFilterDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout dialogLayout = new GridLayout(1, false);
		container.setLayout(dialogLayout);

		Composite dialogContentContainer = new Composite(container, SWT.NULL);
		GridLayout dialogContentLayout = new GridLayout(1, false);
		dialogContentLayout.marginLeft = 5;
		dialogContentLayout.marginRight = 5;
		dialogContentContainer.setLayout(dialogContentLayout);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		dialogContentContainer.setLayoutData(gridData);

		createFilterTextEditor(dialogContentContainer);
		createFilterOptionRadioBoxes(dialogContentContainer);
		
		
		Button defaults = new Button(dialogContentContainer,SWT.PUSH);
		defaults.setText("Restore Defaults");
		defaults.addListener(SWT.Selection,new Listener(){
			public void handleEvent(Event event) {
				filterOptions.loadDefault();
				filter.loadDefault();
			}
		});
		
		gridData = new GridData();
		gridData.horizontalAlignment=GridData.END;
		defaults.setLayoutData(gridData);
		
		return container;
	}
	
	@Override
	protected void okPressed() {
		filter.store();
		filterOptions.store();
		bugTreeViewer.addBugTreeFilter(this);
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		bugTreeViewer.removeBugTreeFilter();
		super.cancelPressed();
	}
	
	private void createFilterTextEditor(Composite dialogContentContainer) {
		Group filterGroup = new Group(dialogContentContainer, SWT.NONE);
		GridLayout filterGroupLayout = new GridLayout(1, false);
		filterGroup.setLayout(filterGroupLayout);
		filterGroup.setText("Bugfilter");
		Composite groupContent = new Composite(filterGroup, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		groupContent.setLayoutData(gridData);
		filter = new StringFieldEditor(Constants.FILTER, "Filter", groupContent);
		filterGroup.setLayoutData(gridData);
		filter.setPreferenceStore(getPreferenceStore());
		filter.load();
	}

	private void createFilterOptionRadioBoxes(Composite dialogContentContainer) {
		String[] valuesAndNames = new String[TreeColumnTitles.values().length];
		for (int i = 0; i < TreeColumnTitles.values().length; i++) {
			String radioName = TreeColumnTitles.values()[i].toString();
			valuesAndNames[i] = radioName;
		}
		filterOptions = new CheckBoxGroupFieldEditor(Constants.FILTEROPTIONS, "FilterOptions", 1,
				valuesAndNames, dialogContentContainer, true);
		filterOptions.setPreferenceStore(getPreferenceStore());
		filterOptionsStoringLocations = filterOptions.getPreferenceStoringLocations();
		filterOptions.load();
	}

	private IPreferenceStore getPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

	public void setBugTreeViewer(BugTreeViewer bugTreeViewer) {
		this.bugTreeViewer = bugTreeViewer;
	}
	
	public String[] getfilterOptionsStoringLocations(){
		return filterOptionsStoringLocations;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(200, 365);
	}
}
