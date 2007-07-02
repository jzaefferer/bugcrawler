package bugcrawler.runtime.views;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import bugcrawler.swinginswt.EmbeddedSwingComposite;

/**
 * The Diagram View which displays a swing JFreeChart Diagram
 * 
 * @author TSO
 */
public class BugDiagramView extends ViewPart {

	public BugDiagramView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		EmbeddedSwingComposite embeddedComposite = new EmbeddedSwingComposite(parent, SWT.NONE) {
			protected JComponent createSwingComponent() {
				JPanel panel = new JPanel();
				panel.add(new JLabel("test"));
				return panel;
			}
		};
		embeddedComposite.populate();

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
