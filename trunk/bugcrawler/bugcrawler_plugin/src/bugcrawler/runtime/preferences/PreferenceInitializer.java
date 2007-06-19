package bugcrawler.runtime.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import bugcrawler.runtime.Activator;

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
		store.setDefault(PreferenceConstants.USERNAME, "Unknown");
		store.setDefault(PreferenceConstants.PASSWORD, "");
		store.setDefault(PreferenceConstants.HOSTNAME, "localhost");
		store.setDefault(PreferenceConstants.PORT, 4000);
		store.setDefault(PreferenceConstants.FILTER, "");
		//store.setDefault(PreferenceConstants.FILTEROPTIONS + ";Ticket", true);
	}

}
