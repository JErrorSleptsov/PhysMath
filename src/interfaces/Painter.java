package interfaces;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

public interface Painter {
    JFreeChart paintGraphic(XYDataset dataset);
}
