package bugcrawler.runtime.views;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import bugcrawler.runtime.data.Priority;

public class BugDiagramComponent {

	private BugDiagramComponent(){}
	
	private static int highest = 0;
	private static int high = 0;
	private static int medium = 0;
	private static int low = 0;
	private static int lowest = 0;
	
	
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
		JFreeChart chart = ChartFactory.createPieChart3D("Priority-Overview", dataset, false, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSectionOutlinesVisible(false);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;
	}
	
	public static void addCount(Priority priority){
		if(priority.toString().equals("Highest")){
			highest++;
		}else if(priority.toString().equals("High")){
			high++;
		}else if(priority.toString().equals("Medium")){
			medium++;
		}else if(priority.toString().equals("Low")){
			low++;
		}else if(priority.toString().equals("Lowest")){
			lowest++;
		}
	}
	
	public static Double[] getValues(){
		return new Double[]{
			new Double(highest),
			new Double(high),
			new Double(medium),
			new Double(low),
			new Double(lowest)
		};
	}
	public static String[] getLabels(){
		String[] labels = new String[Priority.values().length];
		for(int i=0;i<Priority.values().length;i++){
			labels[i]=Priority.values()[i].toString();
		}
		return labels;
	}
}
