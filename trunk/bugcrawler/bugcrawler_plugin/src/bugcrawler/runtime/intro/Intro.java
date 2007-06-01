package bugcrawler.runtime.intro;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.IntroPart;

import bugcrawler.utils.ImageStore;

public class Intro extends IntroPart {

    private Composite parent = null;

    public void createPartControl(Composite parent) {
	this.parent = parent;
	Label lab = new Label(this.parent, SWT.NONE);
	lab.setText("blaaa");

    }
    
    public String getTitle() {
	return "Bugcrawler Welcome";
    }

    public Image getTitleImage() {
	return new ImageStore("images").get("alt_window_16.gif");
    }

    @Override
    public void setFocus(){}

    public void standbyStateChanged(boolean standby) {}
}
