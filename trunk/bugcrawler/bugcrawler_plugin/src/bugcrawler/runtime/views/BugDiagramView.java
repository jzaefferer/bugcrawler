package bugcrawler.runtime.views;

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
		EmbeddedSwingComposite embeddedComposite = new EmbeddedSwingComposite(sectionContent, SWT.NONE) {
			protected JComponent createSwingComponent() {
				JFreeChart chart = BugChartComponent.getBugChart(new String[]{"Highest","High"},new Double[]{10.0,90.0});
				return new ChartPanel(chart);
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
}
