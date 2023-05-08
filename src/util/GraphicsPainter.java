package util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ShapeUtilities;

import java.awt.*;
import java.util.ArrayList;

public class GraphicsPainter {
    private Graphic graphic = new Graphic();

    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    private double weight=1;
    private double height=1000;
    private double startSpeed=0;
    private XYSeries series;
    private ArrayList<PointOnGraphic> points = new ArrayList<>(); // Массив объектов точка

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getStartSpeed() {
        return startSpeed;
    }

    public void setStartSpeed(double startSpeed) {
        this.startSpeed = startSpeed;
    }

    public GraphicsPainter(String title, String xAxisLabel, String yAxisLabel) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getXAxisLabel() {
        return xAxisLabel;
    }
    public void setXAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }

    public String getYAxisLabel() {
        return yAxisLabel;
    }

    public void setYAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
    }

    public XYDataset createDataset() {
        series = new XYSeries(this.title); // Переменная для записи точек графикаv

        if (this.title=="Скорость" && yAxisLabel=="Высота"){ // Условие для графика скорость/высота
            points = graphic.createSpeedOnHeightGraphic(height, startSpeed);
        }
        if (this.title=="Скорость" && yAxisLabel=="Время"){ // Условие для графика скорость/время
            points = graphic.createSpeedOnTimeGraphic(height,startSpeed);
        }
        if (this.title=="Потенциальная энергия" && yAxisLabel=="Высота"){ // Условие для графика энергия/высота
            points = graphic.createPotentialEnergyUseHeightGraphic(weight,height);
        }
        if (this.title=="Кинетическая энергия" && yAxisLabel=="Высота"){ // Условие для графика энергия/высота
            points = graphic.createKinematicEnergyUseHeightGraphic(weight,height,startSpeed);
        }
        if (this.title=="Кинетическая энергия" && yAxisLabel=="Время"){ // Условие для графика энергия/высота
            points = graphic.createKinematicEnergyUseTimeGraphic(weight,height, startSpeed);
        }
        if (this.title=="Потенциальная энергия" && yAxisLabel=="Время"){ // Условие для графика энергия/высота
            points = graphic.createPotentialEnergyUseTimeGraphic(weight,height, startSpeed);
        }
        for (PointOnGraphic point: points) { // запись точек в график
            series.add(point.getX(), point.getY());
        }
        return new XYSeriesCollection(series);
    }
    public XYDataset updateDataset(){
        series.clear();
        if (this.title=="Скорость" && yAxisLabel=="Высота"){ // Условие для графика скорость/высота
            points = graphic.createSpeedOnHeightGraphic(height, startSpeed);
        }
        if (this.title=="Скорость" && yAxisLabel=="Время"){ // Условие для графика скорость/время
            points = graphic.createSpeedOnTimeGraphic(height,startSpeed);
        }
        if (this.title=="Потенциальная энергия" && yAxisLabel=="Высота"){ // Условие для графика энергия/высота
            points = graphic.createPotentialEnergyUseHeightGraphic(weight,height);
        }
        if (this.title=="Кинетическая энергия" && yAxisLabel=="Высота"){ // Условие для графика энергия/высота
            points = graphic.createKinematicEnergyUseHeightGraphic(weight,height,startSpeed);
        }
        if (this.title=="Кинетическая энергия" && yAxisLabel=="Время"){ // Условие для графика энергия/высота
            points = graphic.createKinematicEnergyUseTimeGraphic(weight,height, startSpeed);
        }
        if (this.title=="Потенциальная энергия" && yAxisLabel=="Время"){ // Условие для графика энергия/высота
            points = graphic.createPotentialEnergyUseTimeGraphic(weight,height, startSpeed);
        }
        for (PointOnGraphic point: points) { // запись точек в график
            series.add(point.getX(), point.getY());
         }
        return new XYSeriesCollection(series);
    }

    public JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                this.title,
                this.xAxisLabel,
                this.yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );
        XYPlot plot = chart.getXYPlot();
        plot.setDomainZeroBaselineVisible(false);
        plot.setRangeZeroBaselineVisible(false);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setDomainGridlinePaint(Color.gray);
        plot.setAxisOffset(new RectangleInsets(0, 10, 0, 0));
        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        renderer1.setSeriesShapesVisible(0, false);
        renderer1.setSeriesShape(0, ShapeUtilities.createDiagonalCross(1, 1));
        renderer1.setSeriesPaint(0, Color. red );
        plot.setRenderer(renderer1);
        return chart;
    }
}
