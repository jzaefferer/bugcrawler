package bugcrawler.runtime.intro;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.IntroPart;

import bugcrawler.runtime.Activator;

/**
 * A little introduction for a mainapp (only a test)
 * 
 * @author TSO
 */
public class Intro extends IntroPart {


	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IntroPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {
		Label lab = new Label(parent, SWT.NONE);
		lab.setText("blaaa");

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IntroPart#getTitle()
	 */
	public String getTitle() {
		return "Bugcrawler Welcome";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IntroPart#getTitleImage()
	 */
	public Image getTitleImage() {
		return Activator.getImagestore().get("alt_window_16.gif");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IntroPart#setFocus()
	 */
	public void setFocus() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#standbyStateChanged(boolean)
	 */
	public void standbyStateChanged(boolean standby) {
	}
}
