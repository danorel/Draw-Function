package drawer.chart;

import drawer.GeneralOptions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChartFrame extends ApplicationFrame {
    private JFreeChart chart;
    private final XYSeries positive_series
            = new XYSeries("+ Циссоіда Діоклеса");
    private final XYSeries negative_series
            = new XYSeries("- Циссоіда Діоклеса");

    public ChartFrame(String title, String function){
        super(title);
        final XYSeriesCollection data = new XYSeriesCollection(positive_series);
        data.addSeries(negative_series);
        chart = ChartFactory.createXYLineChart(
                function,
                ChartOptions.X_AXIS_LABEL,
                ChartOptions.Y_AXIS_LABEl,
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        final ChartPanel canvas
                = new ChartPanel(chart);
        canvas.setPreferredSize(
                new Dimension
                        (
                                ChartOptions.CHART_WIDTH,
                                ChartOptions.CHART_HEIGHT
                        )
        );
        setContentPane(canvas);
    }
    /*
        Drawing the two parts from the function:
        - positive values over the OX axis
        - negative values under the OX axis
    */
    public void draw(String function){
        for(
                double x = FunctionConstants.LEFT_BORDER;
                x < FunctionConstants.RIGHT_BORDER;
                x += FunctionConstants.STEP
        ) {
            positive_series.add(x, calculateFunction(x));
        }
        for(
                double x = FunctionConstants.LEFT_BORDER;
                x < FunctionConstants.RIGHT_BORDER;
                x += FunctionConstants.STEP
        ) {
            negative_series.add(x, -calculateFunction(x));
        }
    }
    /*
        Calculating the function value, which corresponds to the task function
     */
    private double calculateFunction(double x){
        return Math.sqrt(
                (Math.pow(x, 3)/(FunctionConstants.PARAMETER - x))
        );
    }
    /*
        Displaying the graphic on the chart screen
     */
    public void display(){
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }
    /*
        Save the current graphic in the PNG format
     */
    public void saveGraphicAsPNG(){
        try {
            ChartUtilities
                    .saveChartAsPNG(
                            new File(GeneralOptions.SRC),
                            chart,
                            ChartOptions.CHART_WIDTH,
                            ChartOptions.CHART_HEIGHT);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
