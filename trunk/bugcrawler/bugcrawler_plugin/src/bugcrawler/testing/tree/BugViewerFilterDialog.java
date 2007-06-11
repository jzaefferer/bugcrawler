package bugcrawler.testing.tree;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPreferencePage;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.preferences.PreferenceConstants;

public class BugViewerFilterDialog extends Dialog{

	private GridData gridData;
	
	private StringFieldEditor filter;
	
	private RadioGroupFieldEditor filterOptions;

	public BugViewerFilterDialog(Shell parentShell) {
		super(parentShell);
	}

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

		return container;
	}

	private void createFilterTextEditor(Composite dialogContentContainer) {
		Group filterGroup = new Group(dialogContentContainer, SWT.NONE);
		GridLayout filterGroupLayout = new GridLayout(1, false);
		filterGroup.setLayout(filterGroupLayout);
		filterGroup.setText("Bugfilter");
		Composite groupContent = new Composite(filterGroup, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		groupContent.setLayoutData(gridData);
		filter = new StringFieldEditor(PreferenceConstants.FILTER, "Filter", groupContent);
		filterGroup.setLayoutData(gridData);
		filter.setPreferenceStore(Activator.getDefault().getPreferenceStore());
		filter.load();
	}

	private void createFilterOptionRadioBoxes(Composite dialogContentContainer) {
		String[][] valuesAndNames = new String[ColumnTitles.values().length][2];
		for (int i = 0; i < ColumnTitles.values().length; i++) {
			String radioName = ColumnTitles.values()[i].toString();
			valuesAndNames[i][0] = radioName;
			valuesAndNames[i][1] = radioName;
		}
		filterOptions = new RadioGroupFieldEditor(PreferenceConstants.FILTEROPTIONS,
				"FilterOptions", 1, valuesAndNames, dialogContentContainer, true);
		filterOptions.setPreferenceStore(Activator.getDefault().getPreferenceStore());
		filterOptions.load();
	}

	public void performOK(){
		filter.store();
		filterOptions.store();
	}
	
	public void performCancel(){
		filter.loadDefault();
		filterOptions.loadDefault();
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected Point getInitialSize() {
		return new Point(200, 350);
	}
}
