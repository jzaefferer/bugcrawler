package bugcrawler.runtime.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.utils.SpinnerFieldEditor;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class PreferencePageExt
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

    private StringFieldEditor usereditor;
    
    private StringFieldEditor passwordeditor;

    private StringFieldEditor hosteditor;

    private SpinnerFieldEditor porteditor;
    
	public PreferencePageExt() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Bugcrawler Preferences");
	}

    @Override
    protected Control createContents(Composite parent) {
        Composite page = new Composite(parent, SWT.LEFT);
        page.setLayout(new GridLayout(2, false));

        GridData griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = 2;
        Label userinfo = new Label(page, SWT.NULL);
        userinfo
                .setText("Hier den Benutzernamen eingeben mit dem man bei Bugcrawler Projekte oder Bug-Reports anlegt.");
        userinfo.setLayoutData(griddata);

        griddata = new GridData(GridData.FILL_HORIZONTAL);
        usereditor = new StringFieldEditor(Constants.USERNAME, "Username:", page);
        usereditor.setPage(this);
        usereditor.setPreferenceStore(getPreferenceStore());
        usereditor.load();

        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = 2;
        Label passwordinfo = new Label(page, SWT.NULL);
        passwordinfo
                .setText("Hier das Passwort angeben mit dem man sich bei Bugcrawler anmeldet.");
        passwordinfo.setLayoutData(griddata);

        griddata = new GridData(GridData.FILL_HORIZONTAL);
        passwordeditor = new StringFieldEditor(Constants.PASSWORD, "Passwort:", page);
        passwordeditor.setPage(this);
        passwordeditor.setPreferenceStore(getPreferenceStore());
        passwordeditor.load();        

        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = 2;
        Label hostinfo = new Label(page, SWT.NULL);
        hostinfo
                .setText("Hier den Bugcrawler-Host angeben zu dem sich das Plugin verbinden soll.");
        hostinfo.setLayoutData(griddata);

        griddata = new GridData(GridData.FILL_HORIZONTAL);
        hosteditor = new StringFieldEditor(Constants.HOSTNAME, "Host:", page);
        hosteditor.setPage(this);
        hosteditor.setPreferenceStore(getPreferenceStore());
        hosteditor.load();

        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = 2;
        Label portinfo = new Label(page, SWT.NULL);
        portinfo
                .setText("Hier den Port angeben mit dem sich man sich bei Bugcrawler anmelden möchte.");
        portinfo.setLayoutData(griddata);

        griddata = new GridData(GridData.FILL_HORIZONTAL);
        porteditor = new SpinnerFieldEditor(Constants.PORT, "Port:", page);
        porteditor.getSpinner().setMaximum(10000);
        porteditor.getSpinner().setMinimum(1500);
        porteditor.setPage(this);
        porteditor.setPreferenceStore(getPreferenceStore());
        porteditor.load();

        return page;
    }	
    
    @Override
    protected void performDefaults() {
        usereditor.loadDefault();
        hosteditor.loadDefault();
        porteditor.loadDefault();
        super.performDefaults();
    }

    @Override
    public boolean performOk() {
        usereditor.store();
        hosteditor.store();
        porteditor.store();
        return true;
    }
	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		// do nothing		
	}

	@Override
	protected void createFieldEditors() {
		// do nothing		
	}
	
}