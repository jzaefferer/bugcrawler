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

import bugcrawler.runtime.bugtree.BugTreeViewer;
import bugcrawler.runtime.bugtree.BugTreeViewerFilterDialog;
import bugcrawler.runtime.projectwizard.ProjectWizard;
import bugcrawler.runtime.projectwizard.ProjectWizardDialog;
import bugcrawler.utils.ImageStore;

public class ProgramView extends ViewPart {

	private BugTreeViewer bugTreeViewer;

	private Action preferences;

	private Action projects;

	private Action filter;
	
	private Composite parent;
	
	public ProgramView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		this.parent=parent;
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

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		bars.getMenuManager().add(filter);
		bars.getMenuManager().add(preferences);
		bars.getToolBarManager().add(projects);
	}

	private void createActions() {
		preferences = new Action() {
			public void run() {
				String[] filter = new String[] { "bugcrawler.runtime.preferences" };
				PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, null, filter, null);
				dialog.open();
			}
		};
		preferences.setText("Bugcrawler Konfigurieren...");
		preferences.setToolTipText("Einstellungen zum Konfigurieren des Bugcrawlers...");
		preferences.setImageDescriptor(ImageStore.getImageDescriptor("images/preferences.png"));
		
		
		projects = new Action() {
			public void run() {
				try{
				ProjectWizard projectWizard = new ProjectWizard(bugTreeViewer);
				ProjectWizardDialog dialog = new ProjectWizardDialog(parent.getShell(), projectWizard);
				//WizardDialog dialog = new WizardDialog(parent.getShell(), projectWizard);
				dialog.open();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		projects.setText("Projekte...");
		projects.setToolTipText("Projekten zum Einsehen von Bugs wählen.");
		projects.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
				ISharedImages.IMG_TOOL_NEW_WIZARD));
		
		filter = new Action(){
			public void run(){
				BugTreeViewerFilterDialog bugFilterDialog = new BugTreeViewerFilterDialog(parent.getShell());
				bugFilterDialog.setBugTreeViewer(bugTreeViewer);
				bugFilterDialog.open();
			}
		};
		filter.setText("Bugfilter...");
		filter.setToolTipText("Filtern relevanter Bugs");
		filter.setImageDescriptor(ImageStore.getImageDescriptor("images/filter.png"));

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
}