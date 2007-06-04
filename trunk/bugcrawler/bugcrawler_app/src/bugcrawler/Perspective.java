package bugcrawler;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

    private static IPageLayout perspectiveLayout;
    
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(false);
	perspectiveLayout = layout;
    }
    
    public static IPageLayout getPerspectiveLayout(){
	return perspectiveLayout;
    }
    
    
}
