package bugcrawler.runtime.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ProgramView extends ViewPart {


    public ProgramView() {
    }

    /**
     * This is a callback that will allow us to create the viewer and
     * initialize it.
     */
    public void createPartControl(Composite parent) {
	new BugViewer(parent);
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
    }
}