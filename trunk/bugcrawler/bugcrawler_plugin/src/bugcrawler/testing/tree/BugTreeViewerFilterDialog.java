package bugcrawler.testing.tree;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
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

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.data.ColumnTitles;
import bugcrawler.runtime.preferences.PreferenceConstants;

public class BugTreeViewerFilterDialog extends Dialog {

	private GridData gridData;

	private StringFieldEditor filter;

	private RadioGroupFieldEditor filterOptions;

	private BugTreeViewer bugTreeViewer;
	
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
		filter.setPreferenceStore(getPreferenceStore());
		filter.load();
	}

	@Override
	protected void okPressed() {
		filter.store();
		filterOptions.store();
		bugTreeViewer.addBugTreeFilter();
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		filter.loadDefault();
		filterOptions.loadDefault();
		bugTreeViewer.removeBugTreeFilter();
		super.cancelPressed();
	}

	private void createFilterOptionRadioBoxes(Composite dialogContentContainer) {
		String[][] valuesAndNames = new String[ColumnTitles.values().length][2];
		for (int i = 0; i < ColumnTitles.values().length; i++) {
			String radioName = ColumnTitles.values()[i].toString();
			valuesAndNames[i][0] = radioName;
			valuesAndNames[i][1] = radioName;
		}
		filterOptions = new RadioGroupFieldEditor(PreferenceConstants.FILTEROPTIONS, "FilterOptions", 1,
				valuesAndNames, dialogContentContainer, true);
		filterOptions.setPreferenceStore(getPreferenceStore());
		filterOptions.load();
	}
	
	private IPreferenceStore getPreferenceStore(){
		return Activator.getDefault().getPreferenceStore();
	}

	public void setBugTreeViewer(BugTreeViewer bugTreeViewer) {
		this.bugTreeViewer = bugTreeViewer;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(200, 350);
	}
}
