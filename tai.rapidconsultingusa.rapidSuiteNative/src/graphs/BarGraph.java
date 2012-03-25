package graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import report.Report;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
public class BarGraph
{

	private Report report;
	public static final String BACKGROUND = "#aaaaaa";

	public BarGraph (Report report)
	{
		this.report = report;
	}

	public Intent getIntent (Context context)
	{
		float[] revenues = new float[12];
		
		String[] x_axis = new String[]{"January", "Feburary", "March", "April", "May",
				"June", "July", "August", "September", "October", "November", "December"};
		

		revenues[0] = report.getJanRev();
		revenues[1] = report.getFebRev();
		revenues[2] = report.getMarRev();
		revenues[3] = report.getAprRev();
		revenues[4] = report.getMayRev();
		revenues[5] = report.getJunRev();
		revenues[6] = report.getJulRev();
		revenues[7] = report.getAugRev();
		revenues[8] = report.getSepRev();
		revenues[9] = report.getOctRev();
		revenues[10] = report.getNovRev();
		revenues[11] = report.getDecRev();


		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		
		CategorySeries series = new CategorySeries (report.getYear() + " Report");
		for (int i = 0; i < revenues.length; i++)
		{
			series.add(x_axis[i], revenues[i]);
			mRenderer.addXTextLabel(i+1, Integer.toString(i+1));
			mRenderer.addYTextLabel(i+1, "$" + revenues[i]);
			
		}
		
		// think of it as an object that hold a list of series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

	
		dataset.addSeries(series.toXYSeries());

		
		// Customize the graph as a whole
	
		mRenderer.setChartTitle(report.getYear() + " Report");
		mRenderer.setYTitle("Revenue");
		mRenderer.setXTitle("Month");
		mRenderer.setXAxisMax(12);
		mRenderer.setXAxisMin(1);
	
		mRenderer.setLabelsTextSize(14);
		mRenderer.setXLabelsAlign(Align.CENTER);
		mRenderer.setYLabelsAlign(Align.CENTER);
		mRenderer.setZoomEnabled(true);
		mRenderer.setZoomButtonsVisible(true);
		
		
		// One series has one renderer
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		
		// Customizations
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float) 0.5);
		mRenderer.addSeriesRenderer(renderer);
		renderer.setFillBelowLine(false);
		renderer.setChartValuesTextSize(15);
		mRenderer.setLabelsColor(Color.WHITE);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setAntialiasing(true);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.parseColor(BACKGROUND));
		mRenderer.setBarSpacing(0.5);
		mRenderer.setAxesColor(Color.BLACK);
	mRenderer.setFitLegend(true);
	mRenderer.setGridColor(Color.RED);

//	double[] ir = new double[]{1,2,3,4,5,6,7,8,9,10,11,12};
//	mRenderer.setRange(ir);
		

		Intent intent = ChartFactory.getBarChartIntent(context, dataset,
				mRenderer, Type.DEFAULT);
		
		
		
		
		return intent;
	}
}
