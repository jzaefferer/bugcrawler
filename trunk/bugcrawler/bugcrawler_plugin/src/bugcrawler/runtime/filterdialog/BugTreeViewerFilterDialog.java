package bugcrawler.runtime.filterdialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import bugcrawler.runtime.bugtree.BugTreeViewer;

/**
 * creates a FilterDialog
 * 
 * @author TSO
 */
public class BugTreeViewerFilterDialog extends Dialog {

	/**
	 * GridDate for positioning the components
	 */
	private GridData gridData;

	/**
	 * The bugTreeViewer where to set the filter to
	 */
	private BugTreeViewer bugTreeViewer;

	/**
	 * The bugTreeFilterComponents
	 */
	private BugTreeViewerFilterComponents components;

	/**
	 * the preferenceStore references of the checkboxes
	 * 
	 * @see bugcrawler.utils.CheckBoxGroupFieldEditor
	 */
	private String[] filterOptionsStoringLocations;

	/**
	 * Creates the Dialog
	 * 
	 * @param parentShell
	 *            the parent Shell of which the dialog is created
	 */
	public BugTreeViewerFilterDialog(Shell parentShell) {
		super(parentShell);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
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

		components.createFilterTextEditor(dialogContentContainer);
		components.createFilterOptionRadioBoxes(dialogContentContainer);
		components.createRestoreButton(dialogContentContainer,bugTreeViewer);

		return container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		components.store();
		bugTreeViewer.addBugTreeFilter(components.getFilterOptionsStoringLocation());
		super.okPressed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
	 */
	@Override
	protected void cancelPressed() {
		super.cancelPressed();
	}

	/**
	 * sets the BugTreeViewer to this dialog
	 * 
	 * @param bugTreeViewer
	 *            where the filter has to be applied on
	 */
	public void setBugTreeViewer(BugTreeViewer bugTreeViewer) {
		this.bugTreeViewer = bugTreeViewer;
	}

	/**
	 * Get the FilterOptionsStoringLocation for each checkbox used in the
	 * CheckBoxGroupFieldEditor
	 * 
	 * @return String[] with the FilterOptions
	 */
	public String[] getfilterOptionsStoringLocations() {
		return filterOptionsStoringLocations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
	 */
	protected Point getInitialSize() {
		return new Point(200, 365);
	}
}
