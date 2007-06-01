package bugcrawler_plugin.intro;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;

import bugcrawler.utils.ImageStore;

public class Intro implements IIntroPart {

	private Composite parent = null;
	
	private ImageStore imageStore = null;
	
	public void addPropertyListener(IPropertyListener listener) {
		// TODO Auto-generated method stub

	}

	public void createPartControl(Composite parent) {
		this.parent = parent;
		Label lab = new Label(this.parent,SWT.NONE);
		lab.setText("blaaa");
		
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public IIntroSite getIntroSite() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		return "Bugcrawler Welcome";
	}

	public Image getTitleImage() {
		imageStore = new ImageStore("/images");
		return imageStore.getImage("alt_window_16.gif");
	}

	public void init(IIntroSite site, IMemento memento)
			throws PartInitException {
		// TODO Auto-generated method stub

	}

	public void removePropertyListener(IPropertyListener listener) {
		// TODO Auto-generated method stub

	}

	public void saveState(IMemento memento) {
		// TODO Auto-generated method stub

	}

	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void standbyStateChanged(boolean standby) {
		// TODO Auto-generated method stub

	}

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

}
