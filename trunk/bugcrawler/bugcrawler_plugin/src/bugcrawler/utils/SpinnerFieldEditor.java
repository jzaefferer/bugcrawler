package bugcrawler.utils;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

/**
 * A field editor for a spinner type preference.
 * <p>
 * This class may be used as is, or subclassed as required.
 * </p>
 * 
 * @author TSO, JZA
 */
public class SpinnerFieldEditor extends FieldEditor {

	private Composite editorcomp;

	private Spinner spinner;

	/**
	 * Creates a SpinnerFieldEditor Example usage:
	 * 
	 * <pre>
	 * SpinnerFieldEditor spinner = new SpinnerFieldEditor(&quot;Spinner.Example&quot;, &quot;Port:&quot;, page);
	 * </pre>
	 * 
	 * @param name
	 *            the name of the preference this field editor works on.
	 * @param label
	 *            the label text of the field editor
	 * @param parent
	 *            the parent of the field editor's control
	 */
	public SpinnerFieldEditor(String name, String label, Composite parent) {
		super(name, label, parent);
	}

	/**
	 * adjust the field edtior for n-columns
	 */
	protected void adjustForNumColumns(int numColumns) {
		((GridData) editorcomp.getLayoutData()).horizontalSpan = numColumns;
	}

	/**
	 * Fill the components into the grid
	 */
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		editorcomp = parent;

		GridData griddata = new GridData(GridData.FILL_HORIZONTAL);
		griddata.horizontalSpan = numColumns;
		editorcomp.setLayoutData(griddata);

		Label label = getLabelControl(editorcomp);
		griddata = new GridData();
		label.setLayoutData(griddata);

		spinner = new Spinner(editorcomp, SWT.BORDER);
		griddata = new GridData();
		spinner.setLayoutData(griddata);
		spinner.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
	}

	/**
	 * Returns this field editor's spinner control.
	 * 
	 * @return Spinner the spinner of the field editor
	 */
	public Spinner getSpinner() {
		return spinner;
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoad() {
		int loadedint = getPreferenceStore().getInt(getPreferenceName());
		spinner.setSelection(loadedint);
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoadDefault() {
		int loadedint = getPreferenceStore().getDefaultInt(getPreferenceName());
		spinner.setSelection(loadedint);
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doStore() {
		getPreferenceStore().setValue(getPreferenceName(), spinner.getSelection());
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	public int getNumberOfControls() {
		return 2;
	}

}
