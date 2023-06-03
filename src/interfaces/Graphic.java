package interfaces;

import entity.Data;
import entity.Point2D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import util.Calculator;

import java.util.List;

public abstract class Graphic {
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;
    private XYDataset dataset;
    private PlotOrientation orientation = PlotOrientation.HORIZONTAL;
    private boolean legend = true;
    private boolean tooltips = true;
    private boolean urls = true;
    public static Calculator calculator;

    public Graphic() {
         calculator = new Calculator();
    }
    public Graphic(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.dataset = dataset;
    }

    public Graphic setTitle(String title) {
        this.title = title;
        return this;
    }

    public Graphic setxAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
        return this;
    }

    public Graphic setyAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
        return this;
    }

    public Graphic setDataset(XYDataset dataset) {
        this.dataset = dataset;
        return this;
    }

    public Graphic setOrientation(PlotOrientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public Graphic setLegend(boolean legend) {
        this.legend = legend;
        return this;
    }

    public Graphic setTooltips(boolean tooltips) {
        this.tooltips = tooltips;
        return this;
    }

    public Graphic setUrls(boolean urls) {
        this.urls = urls;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getxAxisLabel() {
        return xAxisLabel;
    }

    public String getyAxisLabel() {
        return yAxisLabel;
    }

    public XYDataset getDataset() {
        return dataset;
    }

    public PlotOrientation getOrientation() {
        return orientation;
    }

    public boolean isLegend() {
        return legend;
    }

    public boolean isTooltips() {
        return tooltips;
    }

    public boolean isUrls() {
        return urls;
    }

    public abstract List<Point2D> fillSetPoints(Data data);
}
