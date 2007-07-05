package bugcrawler.runtime;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import bugcrawler.utils.ResourceStore;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The shared instance
	private static Activator plugin;

	private static ResourceStore resourceStore;

	private FormColors formColors;

	public Activator() {
		plugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		resourceStore = new ResourceStore(getBundle());
		resourceStore.setImagesPath("images");
		/*
		 * if(Activator.getDefault().getPreferenceStore().getBoolean(Constants.LOADEDONCE)==false){
		 * Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ProgramView.ID);
		 * Activator.getDefault().getPreferenceStore().setValue(Constants.LOADEDONCE,
		 * true); }
		 */
	}


	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static ResourceStore getResourceStore() {
		return resourceStore;
	}

	public FormColors getFormColors(Display display) {
		if (formColors == null) {
			formColors = new FormColors(display);
			formColors.markShared();
		}
		return formColors;
	}
	
	public static IWorkbenchPage activePage() {
		return Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}
}
