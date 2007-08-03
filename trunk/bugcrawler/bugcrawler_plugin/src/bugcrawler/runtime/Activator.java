package bugcrawler.runtime;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import bugcrawler.utils.ResourceStoreOld;
import bugcrawler.viewdatahandling.ViewDataDistributor;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The shared instance
	private static Activator plugin;

	private static ResourceStoreOld resourceStore;

	private static ViewDataDistributor viewDataDistributor = new ViewDataDistributor();
	
	private static ViewDataDistributor viewDataDistributor2 = new ViewDataDistributor();
	
	private FormColors formColors;

	public Activator() {
		plugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		resourceStore = new ResourceStoreOld(getBundle());
		resourceStore.setImagesPath("images");
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

	public static ResourceStoreOld getResourceStore() {
		return resourceStore;
	}
	
	public static ViewDataDistributor getViewDataDistributor() {
		return viewDataDistributor;
	}
	
	public static ViewDataDistributor getViewDataDistributor2() {
		return viewDataDistributor2;
	}	

	public FormColors getFormColors(Display display) {
		if (formColors == null) {
			formColors = new FormColors(display);
			formColors.markShared();
		}
		return formColors;
	}
	
	public static IWorkbenchPage activePage() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}
}
