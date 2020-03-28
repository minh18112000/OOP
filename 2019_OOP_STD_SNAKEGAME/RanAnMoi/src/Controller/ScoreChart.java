package Controller;


import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ScoreChart extends ApplicationFrame {

    HighScore hs = new HighScore();
    List<String> sort = hs.readFile();
    List<String> sorted = new ArrayList<String>();
    public ScoreChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "USER NAME",
                "SCORE",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        
        
    }

    private CategoryDataset createDataset() {
        for (String s : sort) {
            if (!sorted.contains(s)) {
                sorted.add(s);
            }
        }
        String[] str0 = sorted.get(0).split(" ");
        String[] str1 = sorted.get(1).split(" ");
        String[] str2 = sorted.get(2).split(" ");
        String[] str3 = sorted.get(3).split(" ");
        String[] str4 = sorted.get(4).split(" ");
        final String fiat = "SCORE";

        final String score1 = str0[2];
        final String score2 = str1[2];
        final String score3 = str2[2];
        final String score4 = str3[2];
        final String score5 = str4[2];

        final String user1 = str0[0];
        final String user2 = str1[0];
        final String user3 = str2[0];
        final String user4 = str3[0];
        final String user5 = str4[0];
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        dataset.addValue(Integer.parseInt(score1), fiat, user1);
        dataset.addValue(Integer.parseInt(score2), fiat, user2);
        dataset.addValue(Integer.parseInt(score3), fiat, user3);
        dataset.addValue(Integer.parseInt(score3), fiat, user4);
        dataset.addValue(Integer.parseInt(score5), fiat, user5);
        return dataset;
    }

    public static void main(String[] args) {
 
        ScoreChart chart = new ScoreChart("CHART SCORE",
                "TOP 5 HIGHEST SCORE");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);

        chart.setVisible(true);

    }
}
