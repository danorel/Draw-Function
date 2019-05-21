package drawer.chart;

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
    private final XYSeries positive_series = new XYSeries("+ Циссоіда Діоклеса");
    private final XYSeries negative_series = new XYSeries("- Циссоіда Діоклеса");
    public ChartFrame(String title, String function){
        super(title);
        final XYSeriesCollection data = new XYSeriesCollection(positive_series);
        data.addSeries(negative_series);
        chart = ChartFactory.createXYLineChart(
                function,
                Preferences.X_AXIS_LABEL,
                Preferences.Y_AXIS_LABEl,
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        final ChartPanel desk = new ChartPanel(chart);
        desk.setPreferredSize(new Dimension(Preferences.CHART_WIDTH, Preferences.CHART_HEIGHT));
        setContentPane(desk);
    }

    public void draw(String function){
        for(double x = 0; x < Constants.a; x += 0.01){
            positive_series.add(x, calculateFunction(x));
        }
        for(double x = 0; x < Constants.a; x += 0.01){
            negative_series.add(x, -calculateFunction(x));
        }
    }

    private double calculateFunction(double x){
        return Math.sqrt((Math.pow(x, 3)/(Constants.a - x)));
    }

    public void display(){
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    public void saveAsPNG(){
        try {
            ChartUtilities
                    .saveChartAsPNG(new File("math_function.PNG"), chart, Preferences.CHART_WIDTH, Preferences.CHART_HEIGHT);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
