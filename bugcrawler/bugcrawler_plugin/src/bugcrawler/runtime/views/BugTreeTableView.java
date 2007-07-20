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
import bugcrawler.utils.ResourceStoreOld;
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
		preferences.setImageDescriptor(ResourceStoreOld.getImageDescriptor("images/preferences.png"));

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
		projects.setToolTipText("Projekten zum Einsehen von Bugs wählen.");
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
		filter.setImageDescriptor(ResourceStoreOld.getImageDescriptor("images/filter.png"));

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