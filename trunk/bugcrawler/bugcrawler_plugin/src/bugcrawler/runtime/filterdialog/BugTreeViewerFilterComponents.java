package bugcrawler.runtime.filterdialog;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.runtime.data.TreeColumnTitles;
import bugcrawler.utils.CheckBoxGroupFieldEditor;

/**
 * Contains and initializes the main gui-components of the bugTreeFilter
 * 
 * @author TSO
 */
public class BugTreeViewerFilterComponents {

	/**
	 * The Text for which the bugTreeFilter has to filter
	 */
	private StringFieldEditor filter;

	/**
	 * Checkboxes in which column the text should be found in
	 */
	private CheckBoxGroupFieldEditor filterOptions;

	/**
	 * Singleton instance
	 */
	private static BugTreeViewerFilterComponents instance;

	/**
	 * the preferenceStore references of the checkboxes
	 * 
	 * @see bugcrawler.utils.CheckBoxGroupFieldEditor
	 */
	private String[] filterOptionsStoringLocations;

	/**
	 * GridData for all components
	 */
	private GridData gridData;

	/*
	 * Singleton instance
	 */
	private BugTreeViewerFilterComponents() {
	}

	/**
	 * get the instance of the BugTreeViewerFilterComponents
	 * 
	 * @return this instance of this class
	 */
	public static BugTreeViewerFilterComponents getComponents() {
		if (instance == null) {
			instance = new BugTreeViewerFilterComponents();
		}
		return instance;
	}

	/**
	 * Creates the TextFieldEditor on the given Composite
	 * 
	 * @param dialogContentContainer
	 *            parent where to create the fieldeditor on
	 * 
	 * @return the FieldEditor
	 */
	public StringFieldEditor createFilterTextEditor(Composite dialogContentContainer) {
		Group filterGroup = new Group(dialogContentContainer, SWT.NONE);
		GridLayout filterGroupLayout = new GridLayout(1, false);
		filterGroup.setLayout(filterGroupLayout);
		filterGroup.setText("Bugfilter");
		Composite groupContent = new Composite(filterGroup, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		groupContent.setLayoutData(gridData);
		filter = new StringFieldEditor(Constants.FILTER, "Filter", groupContent);
		filterGroup.setLayoutData(gridData);
		filter.setPreferenceStore(getPreferenceStore());
		filter.load();

		return filter;
	}

	/**
	 * Creates the CheckBoxGroupFieldEditor on the given Composite
	 * 
	 * @param dialogContentContainer
	 *            parent where to create the fieldeditor on
	 * 
	 * @return the FieldEditor
	 */
	public CheckBoxGroupFieldEditor createFilterOptionRadioBoxes(Composite dialogContentContainer) {
		String[] valuesAndNames = new String[TreeColumnTitles.values().length];
		for (int i = 0; i < TreeColumnTitles.values().length; i++) {
			String radioName = TreeColumnTitles.values()[i].toString();
			valuesAndNames[i] = radioName;
		}
		filterOptions = new CheckBoxGroupFieldEditor(Constants.FILTEROPTIONS, "FilterOptions", 1,
				valuesAndNames, dialogContentContainer, true);
		filterOptions.setPreferenceStore(getPreferenceStore());
		filterOptionsStoringLocations = filterOptions.getPreferenceStoringLocations();
		filterOptions.load();

		return filterOptions;
	}

	/**
	 * Creates a Restore-Button to reset the checkboxgroup- and the
	 * stringfieldeditor
	 * 
	 * @param dialogContentContainer
	 *            the Composite where to create the button on
	 */
	public void createRestoreButton(Composite dialogContentContainer) {

		Button defaults = new Button(dialogContentContainer, SWT.PUSH);
		defaults.setText("Restore Defaults");
		defaults.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				filterOptions.loadDefault();
				filter.loadDefault();
			}
		});

		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		gridData.verticalAlignment = GridData.END;
		defaults.setLayoutData(gridData);

	}

	/**
	 * Get the FilterOptionsStoringLocation for each checkbox used in the
	 * CheckBoxGroupFieldEditor
	 * 
	 * @return String[] with the FilterOptions
	 */
	public String[] getFilterOptionsStoringLocation() {
		return filterOptionsStoringLocations;
	}

	/**
	 * Get the PluginsPerferenceStore
	 * 
	 * @return the PreferenceStore of this plugin
	 */
	private IPreferenceStore getPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}
}
