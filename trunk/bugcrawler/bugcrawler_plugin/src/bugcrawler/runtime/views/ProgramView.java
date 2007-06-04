package bugcrawler.runtime.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.ViewPart;

public class ProgramView extends ViewPart {
    
    private BugViewer bugViewer;
    
    private Action preferences;

    public ProgramView() {
    }

    /**
     * This is a callback that will allow us to create the viewer and
     * initialize it.
     */
    public void createPartControl(Composite parent) {
	bugViewer = new BugViewer(parent);
	createPulldownMenu();
	createActions();
	contributeToActionBars();
    }
    
    private void createPulldownMenu() {
	MenuManager menuMgr = new MenuManager("#PopupMenu");
	menuMgr.setRemoveAllWhenShown(true);
	Menu menu = menuMgr.createContextMenu(bugViewer.getControl());
	bugViewer.getControl().setMenu(menu);
	getSite().registerContextMenu(menuMgr, bugViewer);

    }
    private void showMessage(String message) {
    	MessageDialog.openInformation(
    		bugViewer.getControl().getShell(),
    		"Sample View",
    		message);
    }
    
    private void contributeToActionBars(){
	IActionBars bars = getViewSite().getActionBars();
	bars.getMenuManager().add(preferences);
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
	preferences.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
		getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
    }
    
    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
    }
}