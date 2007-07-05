package bugcrawler.utils;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

/**
 * A field editor for checkbox type preferences.
 * <p>
 * This class may be used as is, or subclassed as required.
 * </p>
 * 
 * @author TSO
 */
public class CheckBoxGroupFieldEditor extends FieldEditor {

	/**
	 * List of check button entries of the form [label].
	 */
	private String[] labels;

	/**
	 * Number of columns into which to arrange the radio buttons.
	 */
	private int numColumns;

	/**
	 * Indent used for the first column of the radion button matrix.
	 */
	private int indent = HORIZONTAL_GAP;

	/**
	 * Whether to use a Group control.
	 */
	private boolean useGroup;

	/**
	 * The box of check buttons, or <code>null</code> if none (before creation
	 * and after disposal).
	 */
	private Composite checkBox;

	/**
	 * The check buttons, or <code>null</code> if none (before creation and
	 * after disposal).
	 */
	private Button[] checkBoxButtons;

	/**
	 * Creates a new radio group field editor
	 */
	protected CheckBoxGroupFieldEditor() {
	}

	/**
	 * Creates a checkbox group field editor. This constructor does not use a
	 * <code>Group</code> to contain the check buttons. It is equivalent to
	 * using the following constructor with <code>false</code> for the
	 * <code>useGroup</code> argument.
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * CheckBoxGroupFieldEditor editor = new CheckBoxGroupFieldEditor(&quot;GeneralPage.DoubleClick&quot;, resName, 1,
	 * 		new String[] { &quot;Open Browser&quot;, &quot;Expand Tree&quot; }, parent);
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param name
	 *            the name of the preference this field editor works on. The
	 *            given labelText array will be used to store if the check
	 *            buttons are checked in the following way: name;labelText ->
	 *            true or false
	 * @param labelText
	 *            the label text of the field editor
	 * @param numColumns
	 *            the number of columns for the check button presentation
	 * @param label
	 *            list of check button entries; true or fals is returned if the
	 *            check button is selected
	 * @param parent
	 *            the parent of the field editor's control
	 */
	public CheckBoxGroupFieldEditor(String name, String labelText, int numColumns, String[] labels,
			Composite parent) {
		this(name, labelText, numColumns, labels, parent, false);
	}

	/**
	 * Creates a checkbox group field editor.
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * CheckBoxGroupFieldEditor editor = new CheckBoxGroupFieldEditor(&quot;GeneralPage.DoubleClick&quot;, resName, 1,
	 * 		new String[] { &quot;Open Browser&quot;, &quot;Expand Tree&quot; }, parent, true);
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param name
	 *            the name of the preference this field editor works on. The
	 *            given labelText array will be used to store if the check
	 *            buttons are checked in the following way: name;labelText ->
	 *            true or false
	 * @param labelText
	 *            the label text of the field editor
	 * @param numColumns
	 *            the number of columns for the check button presentation
	 * @param label
	 *            list of check button entries; true or fals is returned if the
	 *            check button is selected
	 * @param parent
	 *            the parent of the field editor's control
	 * @param useGroup
	 *            whether to use a Group control to contain the radio buttons
	 */
	public CheckBoxGroupFieldEditor(String name, String labelText, int numColumns, String[] labels,
			Composite parent, boolean useGroup) {
		init(name, labelText);
		this.labels = labels;
		this.numColumns = numColumns;
		this.useGroup = useGroup;
		createControl(parent);
	}

	/**
	 * adjust the field edtior for n-columns
	 */
	protected void adjustForNumColumns(int numColumns) {
		Control control = getLabelControl();
		if (control != null) {
			((GridData) control.getLayoutData()).horizontalSpan = numColumns;
		}
		((GridData) checkBox.getLayoutData()).horizontalSpan = numColumns;
	}

	/**
	 * Fill the components into the grid
	 */
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		if (useGroup) {
			Control control = getCheckBoxControl(parent);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			control.setLayoutData(gd);
		} else {
			Control control = getLabelControl(parent);
			GridData gd = new GridData();
			gd.horizontalSpan = numColumns;
			control.setLayoutData(gd);
			control = getCheckBoxControl(parent);
			gd = new GridData();
			gd.horizontalSpan = numColumns;
			gd.horizontalIndent = indent;
			control.setLayoutData(gd);
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoad() {
		for (int i = 0; i < checkBoxButtons.length; i++) {
			updateValue(getPreferenceStore().getBoolean((getPreferenceName() + ";" + labels[i])), i);
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoadDefault() {
		for (int i = 0; i < checkBoxButtons.length; i++) {
			updateValue(getPreferenceStore().getDefaultBoolean(getPreferenceName() + ";" + labels[i]), i);
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doStore() {
		for (int i = 0; i < checkBoxButtons.length; i++) {
			boolean checkState = ((Boolean) checkBoxButtons[i].getData()).booleanValue();
			getPreferenceStore().setValue(getPreferenceName() + ";" + labels[i], checkState);
		}
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditor#getNumberOfControls()
	 */
	public int getNumberOfControls() {
		return 1;
	}

	/**
	 * Returns this field editor's check group control.
	 * 
	 * @param parent
	 *            The parent to create the checkBox in
	 * @return the check group control
	 */
	public Composite getCheckBoxControl(Composite parent) {
		if (checkBox == null) {
			Font font = parent.getFont();

			if (useGroup) {
				Group group = new Group(parent, SWT.NONE);
				group.setFont(font);
				String text = getLabelText();
				if (text != null) {
					group.setText(text);
				}
				checkBox = group;
				GridLayout layout = new GridLayout();
				layout.horizontalSpacing = HORIZONTAL_GAP;
				layout.numColumns = numColumns;
				checkBox.setLayout(layout);
			} else {
				checkBox = new Composite(parent, SWT.NONE);
				GridLayout layout = new GridLayout();
				layout.marginWidth = 0;
				layout.marginHeight = 0;
				layout.horizontalSpacing = HORIZONTAL_GAP;
				layout.numColumns = numColumns;
				checkBox.setLayout(layout);
				checkBox.setFont(font);
			}

			checkBoxButtons = new Button[labels.length];
			for (int i = 0; i < labels.length; i++) {
				Button check = new Button(checkBox, SWT.CHECK | SWT.LEFT);
				checkBoxButtons[i] = check;
				check.setText(labels[i]);
				check.setData(new Boolean(false));
				check.setSelection(false);
				check.setFont(font);
				check.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						Boolean checkState = (Boolean) event.widget.getData();
						if (checkState.booleanValue()) {
							fireValueChanged(VALUE, checkState, Boolean.FALSE);
							event.widget.setData(new Boolean(false));
						} else {
							fireValueChanged(VALUE, checkState, Boolean.TRUE);
							event.widget.setData(new Boolean(true));
						}
					}
				});
			}
			checkBox.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					checkBox = null;
					checkBoxButtons = null;
				}
			});
		} else {
			checkParent(checkBox, parent);
		}
		return checkBox;
	}

	/**
	 * Sets the indent used for the first column of the check button matrix.
	 * 
	 * @param indent
	 *            the indent (in pixels)
	 */
	public void setIndent(int indent) {
		if (indent < 0) {
			this.indent = 0;
		} else {
			this.indent = indent;
		}
	}

	/**
	 * Set the check buttons to checked or notchecked.
	 * 
	 * @param selectedValue
	 *            the selected value
	 */
	private void updateValue(boolean selectedValue, int i) {
		setPresentsDefaultValue(false);
		if (checkBoxButtons == null) {
			return;
		}
		Button check = checkBoxButtons[i];
		check.setData(new Boolean(selectedValue));
		check.setSelection(selectedValue);
	}

	/*
	 * @see FieldEditor.setEnabled(boolean,Composite).
	 */
	public void setEnabled(boolean enabled, Composite parent) {
		if (!useGroup) {
			super.setEnabled(enabled, parent);
		}
		for (int i = 0; i < checkBoxButtons.length; i++) {
			checkBoxButtons[i].setEnabled(enabled);
		}
	}

	/**
	 * Get the StoringLocations of the boolean that belongs to each check
	 * buttons
	 * 
	 * @return storingLocations the Locations in the PreferenceStore where the
	 *         values has been stored.
	 */
	public String[] getPreferenceStoringLocations() {
		String[] storingLocations = new String[checkBoxButtons.length];
		for (int i = 0; i < checkBoxButtons.length; i++) {
			storingLocations[i] = getPreferenceName() + ";" + labels[i];
		}
		return storingLocations;
	}

}
