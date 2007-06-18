package bugcrawler.utils;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.util.Assert;
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

public class CheckBoxGroupFieldEditor extends FieldEditor{

	
	private String[][] labelsAndValues;
	
	private int numColumns;
	
	private boolean useGroup;
	
	private String value;
	
    private Composite checkBox;
	
	private Button[] checkBoxButtons;
	
	private int indent = HORIZONTAL_GAP;
	
    protected CheckBoxGroupFieldEditor() {
    }
    public CheckBoxGroupFieldEditor(String name, String labelText, int numColumns,
            String[][] labelAndValues, Composite parent) {
        this(name, labelText, numColumns, labelAndValues, parent, false);
    }

    public CheckBoxGroupFieldEditor(String name, String labelText, int numColumns,
            String[][] labelAndValues, Composite parent, boolean useGroup) {
        init(name, labelText);
        Assert.isTrue(checkArray(labelAndValues));
        this.labelsAndValues = labelAndValues;
        this.numColumns = numColumns;
        this.useGroup = useGroup;
        createControl(parent);
    }
    
    private boolean checkArray(String[][] table) {
        if (table == null) {
			return false;
		}
        for (int i = 0; i < table.length; i++) {
            String[] array = table[i];
            if (array == null || array.length != 2) {
				return false;
			}
        }
        return true;
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

            checkBoxButtons = new Button[labelsAndValues.length];
            for (int i = 0; i < labelsAndValues.length; i++) {
                Button check = new Button(checkBox, SWT.CHECK | SWT.LEFT);
                checkBoxButtons[i] = check;
                String[] labelAndValue = labelsAndValues[i];
                check.setText(labelAndValue[0]);
                check.setData(labelAndValue[1]);
                check.setFont(font);
                check.addSelectionListener(new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent event) {
                        String oldValue = value;
                        value = (String) event.widget.getData();
                        setPresentsDefaultValue(false);
                        fireValueChanged(VALUE, oldValue, value);
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
    	updateValue(getPreferenceStore().getString(getPreferenceName()));
    }


    protected void doLoadDefault() {
        updateValue(getPreferenceStore().getDefaultString(getPreferenceName()));
    }


    protected void doStore() {
        if (value == null) {
            getPreferenceStore().setToDefault(getPreferenceName());
            return;
        }

        getPreferenceStore().setValue(getPreferenceName(), value);
    }

	@Override
	public int getNumberOfControls() {
		return 1;
	}
    private void updateValue(String selectedValue) {
        this.value = selectedValue;
        if (checkBoxButtons == null) {
			return;
		}

        if (this.value != null) {
            boolean found = false;
            for (int i = 0; i < checkBoxButtons.length; i++) {
                Button check = checkBoxButtons[i];
                boolean selection = false;
                if (((String) check.getData()).equals(this.value)) {
                    selection = true;
                    found = true;
                }
                check.setSelection(selection);
            }
            if (found) {
				return;
			}
        }
        
        if (checkBoxButtons.length > 0) {
        	checkBoxButtons[0].setSelection(true);
            this.value = (String) checkBoxButtons[0].getData();
        }
        return;
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
