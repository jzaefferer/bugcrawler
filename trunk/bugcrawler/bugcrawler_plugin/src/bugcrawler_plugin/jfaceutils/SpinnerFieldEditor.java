package bugcrawler_plugin.jfaceutils;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class SpinnerFieldEditor extends FieldEditor {

    private Composite editorcomp;

    private Spinner spinner;

    public SpinnerFieldEditor(String name, String label, Composite parent) {
        super(name, label, parent);
    }

    @Override
    protected void adjustForNumColumns(int numColumns) {
        ((GridData) editorcomp.getLayoutData()).horizontalSpan = numColumns;
    }

    @Override
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
        spinner.setBackground(Display.getCurrent().getSystemColor(
                SWT.COLOR_LIST_BACKGROUND));
    }

    public Spinner getSpinner() {
        return spinner;
    }

    @Override
    protected void doLoad() {
        int loadedint = getPreferenceStore().getInt(getPreferenceName());
        spinner.setSelection(loadedint);
    }

    @Override
    protected void doLoadDefault() {
        int loadedint = getPreferenceStore().getDefaultInt(getPreferenceName());
        spinner.setSelection(loadedint);
    }

    @Override
    protected void doStore() {
        getPreferenceStore().setValue(getPreferenceName(),
                spinner.getSelection());
    }

    @Override
    public int getNumberOfControls() {
        return 2;
    }

}
