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

public class CheckBoxGroupFieldEditor extends FieldEditor {

	private String[] labels;

	private int numColumns;

	private boolean useGroup;

	private Composite checkBox;

	private Button[] checkBoxButtons;

	private int indent = HORIZONTAL_GAP;

	protected CheckBoxGroupFieldEditor() {
	}

	public CheckBoxGroupFieldEditor(String name, String labelText, int numColumns, String[] labels,
			Composite parent) {
		this(name, labelText, numColumns, labels, parent, false);
	}

	public CheckBoxGroupFieldEditor(String name, String labelText, int numColumns, String[] labels,
			Composite parent, boolean useGroup) {
		init(name, labelText);
		this.labels = labels;
		this.numColumns = numColumns;
		this.useGroup = useGroup;
		createControl(parent);
	}

	@Override
	protected void adjustForNumColumns(int numColumns) {
		Control control = getLabelControl();
		if (control != null) {
			((GridData) control.getLayoutData()).horizontalSpan = numColumns;
		}
		((GridData) checkBox.getLayoutData()).horizontalSpan = numColumns;
	}

	@Override
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
				System.out.println("1");
				check.setData(new Boolean(false));
				check.setSelection(false);
				check.setFont(font);
				check.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						Boolean checkState = ((Boolean) event.widget.getData()).booleanValue();
						if (checkState.booleanValue() == true) {
							setPresentsDefaultValue(true);
							fireValueChanged(VALUE, checkState, new Boolean(false));
							event.widget.setData(new Boolean(false));	
						} else {
							setPresentsDefaultValue(false);
							fireValueChanged(VALUE, checkState, new Boolean(true));
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

	protected void doLoad() {
		for (int i = 0; i < checkBoxButtons.length; i++) {
			updateValue(getPreferenceStore().getBoolean((getPreferenceName() + ";" + labels[i])), i);
		}
	}

	protected void doLoadDefault() {
		for (int i = 0; i < checkBoxButtons.length; i++) {
			updateValue(getPreferenceStore().getDefaultBoolean(getPreferenceName() + ";" + labels[i]), i);
		}
	}

	protected void doStore() {
		for (int i = 0; i < checkBoxButtons.length; i++) {

			boolean checkState = ((Boolean) checkBoxButtons[i].getData()).booleanValue();
			if (checkState == false) {
				getPreferenceStore().setToDefault(getPreferenceName() + ";" + labels[i]);
				continue;
			}
			getPreferenceStore().setValue(getPreferenceName() + ";" + labels[i], checkState);
		}
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	private void updateValue(boolean selectedValue, int i) {
		if (checkBoxButtons == null) {
			return;
		}		
		Button check = checkBoxButtons[i];
		check.setSelection(selectedValue);
		check.setData(new Boolean(selectedValue));
	}

	public void setEnabled(boolean enabled, Composite parent) {
		if (!useGroup) {
			super.setEnabled(enabled, parent);
		}
		for (int i = 0; i < checkBoxButtons.length; i++) {
			checkBoxButtons[i].setEnabled(enabled);
		}
	}

	public void setIndent(int indent) {
		if (indent < 0) {
			this.indent = 0;
		} else {
			this.indent = indent;
		}
	}
}
