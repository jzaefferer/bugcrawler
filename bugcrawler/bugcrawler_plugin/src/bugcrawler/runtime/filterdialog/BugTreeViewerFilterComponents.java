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

public class BugTreeViewerFilterComponents {

	private StringFieldEditor filter;
	
	private CheckBoxGroupFieldEditor filterOptions;
	
	private static BugTreeViewerFilterComponents instance;
	
	private String[] filterOptionsStoringLocations;
	
	private GridData gridData;
	
	private BugTreeViewerFilterComponents() {}
	
	public static BugTreeViewerFilterComponents getComponents(){
		if(instance==null){
			instance=new BugTreeViewerFilterComponents();
		}
		return instance;
	}
	
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

	public CheckBoxGroupFieldEditor createFilterOptionRadioBoxes(Composite dialogContentContainer) {
		String[] valuesAndNames = new String[TreeColumnTitles.values().length];
		for (int i = 0; i < TreeColumnTitles.values().length; i++) {
			String radioName = TreeColumnTitles.values()[i].toString();
			valuesAndNames[i] = radioName;
		}
		filterOptions = new CheckBoxGroupFieldEditor(Constants.FILTEROPTIONS,
				"FilterOptions", 1, valuesAndNames, dialogContentContainer, true);
		filterOptions.setPreferenceStore(getPreferenceStore());
		filterOptionsStoringLocations = filterOptions.getPreferenceStoringLocations();
		filterOptions.load();
		
		return filterOptions;

	}
	public void createRestoreButton(Composite dialogContentContainer){
		
		Button defaults = new Button(dialogContentContainer,SWT.PUSH);
		defaults.setText("Restore Defaults");
		defaults.addListener(SWT.Selection,new Listener(){
			public void handleEvent(Event event) {
				filterOptions.loadDefault();
				filter.loadDefault();
			}
		});
		
		gridData = new GridData();
		gridData.horizontalAlignment=GridData.END;
		gridData.verticalAlignment=GridData.END;
		defaults.setLayoutData(gridData);
		
	}
	
	public String[] getFilterOptionsStoringLocation(){
		return filterOptionsStoringLocations;
	}
	private IPreferenceStore getPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}
}
