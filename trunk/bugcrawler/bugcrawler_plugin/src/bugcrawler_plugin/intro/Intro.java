package bugcrawler_plugin.intro;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;

public class Intro implements IIntroPart {

	public void addPropertyListener(IPropertyListener listener) {
		// TODO Auto-generated method stub

	}

	public void createPartControl(Composite parent) {

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
		// TODO Auto-generated method stub
		return null;
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
