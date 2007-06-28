package bugcrawler.runtime.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.constants.Constants;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(Constants.USERNAME, "Unknown");
		store.setDefault(Constants.PASSWORD, "");
		store.setDefault(Constants.HOSTNAME, "localhost");
		store.setDefault(Constants.PORT, 4000);
		store.setDefault(Constants.FILTER, "");
		// store.setDefault(PreferenceConstants.FILTEROPTIONS + ";Ticket",
		// true);
	}

}
