package bugcrawler.runtime.views;

import java.util.Observable;

import javax.swing.JComponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import bugcrawler.runtime.Activator;
import bugcrawler.swinginswt.EmbeddedSwingComposite;
import bugcrawler.viewdatahandling.ViewDataListener;

/**
 * The Diagram View which displays a swing JFreeChart Diagram
 * 
 * @author TSO
 */
public class BugDiagramView extends ViewPart implements ViewDataListener{

	private EmbeddedSwingComposite embeddedComposite;
	
	private ChartPanel chartPanel;
	private JFreeChart chart;
	
	public BugDiagramView() {
		Activator.getViewDataDistributor().addView(this);
		Activator.getViewDataDistributor2().addView(this);
	}

	@Override
	public void createPartControl(final Composite parent) {
		GridLayout gl = new GridLayout(1,false);
		gl.marginHeight=0;
		gl.marginWidth=0;
		parent.setLayout(gl);
		GridData data = new GridData(GridData.FILL_BOTH);
		parent.setLayoutData(data);
		final Composite sectionContent = new Composite(parent, SWT.WRAP);
		sectionContent.setLayout(new GridLayout());
		data = new GridData(GridData.FILL_HORIZONTAL);
		sectionContent.setLayoutData(data);
		sectionContent.addControlListener(new ControlAdapter(){
			@Override
			public void controlResized(ControlEvent e) {
				Rectangle rect = sectionContent.getClientArea();
				if(parent.getClientArea().height>rect.width){
					sectionContent.setSize(rect.width, rect.width);
				}else{
					sectionContent.setSize(rect.width,parent.getClientArea().height);
				}
			}
		});		
		embeddedComposite = new EmbeddedSwingComposite(sectionContent, SWT.NONE) {
			protected JComponent createSwingComponent() {
				chart = BugDiagramComponent.getBugChart(BugDiagramComponent.getLabels(),BugDiagramComponent.getValues());
				chartPanel = new ChartPanel(chart);
				return chartPanel;
			}
		};
		embeddedComposite.setLayout(new GridLayout());
		embeddedComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		embeddedComposite.populate();
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public void update(Observable viewDataDistributor, Object viewData) {
		/*chart = BugDiagramComponent.getBugChart(BugDiagramComponent.getLabels(),BugDiagramComponent.getValues());
		chartPanel.revalidate();
		embeddedComposite.redraw();
		embeddedComposite.populate();*/
		if(viewDataDistributor==Activator.getViewDataDistributor()){
			System.out.println("ViewDataDistributor 1 has sent information."+viewData);
		}else if (viewDataDistributor==Activator.getViewDataDistributor2()){
			System.out.println("ViewDataDistributor 2 has sent information."+viewData);
		}
	}
}
