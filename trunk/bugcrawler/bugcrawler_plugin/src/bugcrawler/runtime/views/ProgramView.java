package bugcrawler.runtime.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.ViewPart;

import bugcrawler.testing.tree.BugTreeViewer;
import bugcrawler.utils.ImageStore;

public class ProgramView extends ViewPart {
    
    private BugTreeViewer bugTreeViewer;
    
    private Action preferences;
    
    private Action projects;

    public ProgramView() {
    }

    /**
     * This is a callback that will allow us to create the viewer and
     * initialize it.
     */
    public void createPartControl(Composite parent) {
	bugTreeViewer = new BugTreeViewer(parent);
	createPulldownMenu();
	createActions();
	contributeToActionBars();
	
    }
    
    private void createPulldownMenu() {
	MenuManager menuManager = new MenuManager("#PopupMenu");
	menuManager.setRemoveAllWhenShown(true);
	Menu menu = menuManager.createContextMenu(bugTreeViewer.getControl());
	bugTreeViewer.getControl().setMenu(menu);
	getSite().registerContextMenu(menuManager, bugTreeViewer);

    }
    
    private void contributeToActionBars(){
	IActionBars bars = getViewSite().getActionBars();
	bars.getMenuManager().add(preferences);
	bars.getToolBarManager().add(projects);
    }
    
    private void createActions(){
	preferences = new Action() {
		public void run() {
		    String[] filter = new String[]{"bugcrawler.runtime.preferences"};
		    PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, null,filter , null);
		    dialog.open();
		}
	};
	preferences.setText("Bugcrawler Konfigurieren...");
	preferences.setToolTipText("Einstellungen zum Konfigurieren des Bugcrawlers...");
	//preferences.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
	//	getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
	preferences.setImageDescriptor(ImageStore.getImageDescriptor("images/preferences.png"));
	projects = new Action() {
		public void run() {
		    //ProjectWizard projectWizard = new ProjectWizard();
		}
	};
	projects.setText("Projekte...");
	projects.setToolTipText("Eine Auswahl zu Projekten, zu denen es Bugs gibt.");
	projects.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	
    }
    
    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
    }
}