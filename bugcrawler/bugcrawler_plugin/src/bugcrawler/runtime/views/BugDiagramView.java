package bugcrawler.runtime.views;

import java.awt.Font;

import javax.swing.JComponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

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

	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("One", new Double(43.2));
		dataset.setValue("Two", new Double(10.0));
		dataset.setValue("Three", new Double(27.5));
		dataset.setValue("Four", new Double(17.5));
		dataset.setValue("Five", new Double(11.0));
		dataset.setValue("Six", new Double(19.4));
		return dataset;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return A chart.
	 */
	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Pie Chart Demo 1",
				dataset, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSectionOutlinesVisible(false);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;

	}

	@Override
	public void createPartControl(Composite parent) {
		EmbeddedSwingComposite embeddedComposite = new EmbeddedSwingComposite(
				parent, SWT.NONE) {
			protected JComponent createSwingComponent() {
				JFreeChart chart = createChart(createDataset());
				return new ChartPanel(chart);
			}
		};
		embeddedComposite.populate();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}
