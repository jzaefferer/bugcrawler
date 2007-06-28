package bugcrawler;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction preferenceAction;

	private IWorkbenchAction aboutAction;

	private IContributionItem viewsShortList;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		preferenceAction = ActionFactory.PREFERENCES.create(window);
		preferenceAction.setText("Configure...");
		preferenceAction.setAccelerator(SWT.CTRL | 'c');
		register(preferenceAction);

		viewsShortList = ContributionItemFactory.VIEWS_SHORTLIST.create(window);

		aboutAction = ActionFactory.ABOUT.create(window);
		aboutAction.setText("About");
		aboutAction.setAccelerator(SWT.CTRL | 'a');
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager settingsMenu = new MenuManager("Preferences", null);
		settingsMenu.add(preferenceAction);
		menuBar.add(settingsMenu);

		MenuManager viewMenu = new MenuManager("Windows", null);
		viewMenu.add(viewsShortList);
		menuBar.add(viewMenu);

		MenuManager helpMenu = new MenuManager("Help", IWorkbenchActionConstants.M_HELP);
		helpMenu.add(aboutAction);
		menuBar.add(helpMenu);
	}

}
