package bugcrawler;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction preferenceAction;

    private IWorkbenchAction aboutAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
	super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
	preferenceAction = ActionFactory.PREFERENCES.create(window);
	preferenceAction.setText("Configure...");
	preferenceAction.setAccelerator(SWT.CTRL | 'c');
	register(preferenceAction);

	aboutAction = ActionFactory.ABOUT.create(window);
	aboutAction.setText("About");
	aboutAction.setAccelerator(SWT.CTRL | 'a');
    }

    protected void fillMenuBar(IMenuManager menuBar) {
	MenuManager settingsMenu = new MenuManager("Preferences", null);
	settingsMenu.add(preferenceAction);
	menuBar.add(settingsMenu);

	MenuManager helpMenu = new MenuManager("Help", IWorkbenchActionConstants.M_HELP);
	helpMenu.add(aboutAction);
	menuBar.add(helpMenu);
    }

}
