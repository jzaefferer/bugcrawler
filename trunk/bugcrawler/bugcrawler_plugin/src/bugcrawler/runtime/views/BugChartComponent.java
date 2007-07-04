package bugcrawler.runtime.views;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class BugChartComponent {

	private BugChartComponent(){}
	
	public static JFreeChart getBugChart(String[] labels,Double[] values){
		if(labels.length != values.length){
			throw new RuntimeException("not equal count of labels and values");
		}
		return createChart(createDataset(labels,values));
	}
	
	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private static PieDataset createDataset(String[] labels,Double[] values) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(int i=0;i<labels.length;i++){
			dataset.setValue(labels[i], values[i]);
		}
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
		JFreeChart chart = ChartFactory.createPieChart3D("Priority-Overview", dataset, false, false, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSectionOutlinesVisible(false);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;
	}
}
