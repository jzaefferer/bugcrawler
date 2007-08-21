package bugcrawler.runtime.views;

import java.util.Observable;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.ViewPart;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.bugtree.BugTreeViewer;
import bugcrawler.runtime.constants.Constants;
import bugcrawler.runtime.filterdialog.BugTreeViewerFilterDialog;
import bugcrawler.runtime.projectwizard.ProjectWizard;
import bugcrawler.utils.ResourceStore;
import bugcrawler.viewdatahandling.ViewDataListener;

/**
 * The Main View which displays the bugTreeViewer
 * 
 * @author TSO
 */
public class BugTreeTableView extends ViewPart implements ViewDataListener{

	/**
	 * the TreeViewer listen bugs to projects in priorities
	 */
	private BugTreeViewer bugTreeViewer;

	/**
	 * Action which allow to configure the preferences of the bugcrawler
	 */
	private Action preferences;

	/**
	 * Action which opens a wizard to select a project shown in the tree
	 */
	private Action projects;

	/**
	 * Action which opens a filter to allow custom filtering
	 */
	private Action filter;

	/**
	 * the parent Control of this ViewPart
	 */
	private Composite parent;
	
	/**
	 * Default Constructor for initialize this ViewPart
	 */
	public BugTreeTableView() {
//		Activator.getViewDataDistributor().addView(this,Constants.BUG_TREE_TABLE_VIEW_ID);
//		Activator.getViewDataDistributor().saveViewData(" - Information as String");
//		Activator.getViewDataDistributor2().addView(this,Constants.BUG_TREE_TABLE_VIEW_ID);
//		Activator.getViewDataDistributor2().saveViewData(new Integer(1));
	}

	/**
	 * Creates the viewer and initialize it.
	 * 
	 * @param parent
	 *            the parent's control
	 */
	public void createPartControl(Composite parent) {
		this.parent = parent;
		bugTreeViewer = new BugTreeViewer(parent);
		createPulldownMenu();
		createActions();
		contributeToActionBars();

	}

	/**
	 * Creating a pulldownmenu and set the control to the treeviewer
	 */
	private void createPulldownMenu() {
		MenuManager menuManager = new MenuManager("#PopupMenu");
		menuManager.setRemoveAllWhenShown(true);
		Menu menu = menuManager.createContextMenu(bugTreeViewer.getControl());
		bugTreeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, bugTreeViewer);

	}

	/**
	 * Adding all actions to the bars
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		bars.getMenuManager().add(filter);
		bars.getMenuManager().add(preferences);
		bars.getToolBarManager().add(projects);
	}

	/* ACTION DIE DIE LOCATION SPEICHERT UND ZURECHTSTUTZT, 
	FALLS ANDERE PLUGINS NICHT IN DEN PREFERENCES ERSCHEINEN SOLLEN*/
	
	/*private class PreferencesAction extends Action {
		
		private PreferenceDialog dialog;
		private String[] filter = PreferenceConstants.PREFERENCES_IDS;
		private Shell dialogShell;
		private IPreferenceStore store = ReportalDesignerPlugin.getDefault().getPreferenceStore(); 
		
		public PreferencesAction(){
			super("Designer Preferences", Action.AS_PUSH_BUTTON);
			this.setId(PreferenceConstants.PREFERENCES_ACTION_ID);
			createDialog();
			buildDialogEnvironment();
		}
		public void run() {
			if(dialog == null){
				createDialog();
			}
			int i = dialog.open();
			if(i == Window.OK || i == Window.CANCEL){
				dialog = null;
				destroyDialogShell();
			}
		}
		private void buildDialogEnvironment(){
			List<IPreferenceNode> prefs = dialog.getPreferenceManager().getElements(PreferenceManager.PRE_ORDER);
			Iterator prefit = prefs.iterator();
			while(prefit.hasNext()){
				String prefID = ((IPreferenceNode)prefit.next()).getId();
				if(!prefID.startsWith("de.systemwerke")){
					dialog.getPreferenceManager().remove(prefID);
				}
			}
		}
		private void createDialogShell(){
			dialogShell = new Shell();
			dialogShell.setLocation(store.getInt("Pref_Loc_X"),store.getInt("Pref_Loc_Y"));
		}
		private PreferenceDialog createDialog(){
			createDialogShell();
			dialog = PreferencesUtil.createPreferenceDialogOn(dialogShell, filter[0], null, null);
			return dialog;
		}
		private void destroyDialogShell(){
			Point location = dialogShell.getLocation();
			store.setValue("Pref_Loc_X", location.x);
			store.setValue("Pref_Loc_Y", location.y);
			dialogShell.dispose();
			dialogShell = null;
		}
	};*/
	
	/**
	 * Creates the following actions shown in the view: Preferences which allow
	 * to configure the preferences of the bugcrawler Projects opens a wizard to
	 * select a project shown in the tree Filter opens a dialog to allow to
	 * apply a custom filter on the tree
	 */
	private void createActions() {
		preferences = new Action() {
			public void run() {
				String[] filter = new String[] { Constants.PREFERENCES_PAGE_EXTENSION };
				PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, filter[0], filter, null);
				dialog.open();
			}
		};
		preferences.setText("Bugcrawler Konfigurieren...");
		preferences.setToolTipText("Einstellungen zum Konfigurieren des Bugcrawlers...");
		preferences.setImageDescriptor(ResourceStore.getImageDescriptor(Constants.PLUGIN_ID,"images/preferences.png"));

		projects = new Action() {
			public void run() {
				try {
					ProjectWizard projectWizard = new ProjectWizard(bugTreeViewer);
					// ProjectWizardDialog dialog = new
					// ProjectWizardDialog(parent.getShell(), projectWizard);
					WizardDialog dialog = new WizardDialog(parent.getShell(), projectWizard);
					dialog.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		projects.setText("Projekte...");
		projects.setToolTipText("Projekten zum Einsehen von Bugs w�hlen.");
		projects.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
				ISharedImages.IMG_TOOL_NEW_WIZARD));

		filter = new Action() {
			public void run() {
				BugTreeViewerFilterDialog bugFilterDialog = new BugTreeViewerFilterDialog(parent.getShell());
				bugFilterDialog.setBugTreeViewer(bugTreeViewer);
				bugFilterDialog.open();
			}
		};
		filter.setText("Bugfilter...");
		filter.setToolTipText("Filtern relevanter Bugs");
		filter.setImageDescriptor(ResourceStore.getImageDescriptor(Constants.PLUGIN_ID,"images/filter.png"));

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

	public void update(Observable viewDataDistributor, Object viewData) {
		if(viewDataDistributor==Activator.getViewDataDistributor()){
			System.out.println("ViewDataDistributor 1 has sent information:"+viewData);
		}else if (viewDataDistributor==Activator.getViewDataDistributor2()){
			System.out.println("ViewDataDistributor 2 has sent information: - "+viewData);
		}
	}
}