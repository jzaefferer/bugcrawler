package bugcrawler.runtime.filterdialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import bugcrawler.runtime.bugtree.BugTreeViewer;
import bugcrawler.utils.CheckBoxGroupFieldEditor;

public class BugTreeViewerFilterDialog extends Dialog {

	private GridData gridData;

	private StringFieldEditor filter;

	private CheckBoxGroupFieldEditor filterOptions;

	private BugTreeViewer bugTreeViewer;

	private BugTreeViewerFilterComponents components;

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

		components = BugTreeViewerFilterComponents.getComponents();

		filter = components.createFilterTextEditor(dialogContentContainer);
		filterOptions = components.createFilterOptionRadioBoxes(dialogContentContainer);
		components.createRestoreButton(dialogContentContainer);

		return container;
	}

	@Override
	protected void okPressed() {
		filter.store();
		filterOptions.store();
		bugTreeViewer.addBugTreeFilter(components.getFilterOptionsStoringLocation());
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		bugTreeViewer.removeBugTreeFilter();
		super.cancelPressed();
	}

	public void setBugTreeViewer(BugTreeViewer bugTreeViewer) {
		this.bugTreeViewer = bugTreeViewer;
	}

	public String[] getfilterOptionsStoringLocations() {
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
