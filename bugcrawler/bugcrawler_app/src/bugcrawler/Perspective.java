package bugcrawler;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		// layout.setEditorAreaVisible(false);
		layout.addShowViewShortcut("bugcrawler_plugin.views.BugTreeTableView");
		layout.addShowViewShortcut("bugcrawler_plugin.views.BugDiagramView");
		//layout.addView("org.eclipse.pde.runtime.LogView", IPageLayout.BOTTOM,
				//0.60f, Constants.LOG_EXTENSION);
	}

}
