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
/**
 * Класс GraphicsPainter содержит методы для создания и обновления графиков,
 * используя инструменты библиотеки FreeChart.
 * @author Sleptsov D.A.
 * */
public class GraphicsPainter {

    /**
     * Объект Graphic, на основе которого и будет происходить создание графика
     * */
    private Graphic graphic = new Graphic();

    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    private double weight=1;
    private double height=1000;
    private double startSpeed=0;
    private XYSeries series;
    private ArrayList<Point2D> points = new ArrayList<>(); // Массив объектов точка

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

    /**
     * Создание Dataset для графика
     * @return новый Dataset
     * */
    public XYDataset createDataset() {
        series = new XYSeries(this.title); // Переменная для записи точек графика
         return updateDataset();
    }
    /**
     * Метод updateDataset предназначен для обновления точек графика, если данные состояния физического тела изменятся.
     * @return обновленный Dataset
     * */
    public XYDataset updateDataset(){
        series.clear();
        if (this.title=="Скорость" && yAxisLabel=="Высота"){ // Условие для графика скорость/высота
            points = graphic.createSpeedOnHeightGraphic(height, startSpeed);
        }
        if (this.title=="Скорость" && yAxisLabel=="Время"){ // Условие для графика скорость/время
            points = graphic.createSpeedOnTimeGraphic(height,startSpeed);
        }
        if (this.title=="Потенциальная энергия" && yAxisLabel=="Высота"){ // Условие для графика энергия/высота
            points = graphic.createPotentialEnergyOnHeightGraphic(weight,height);
        }
        if (this.title=="Кинетическая энергия" && yAxisLabel=="Высота"){ // Условие для графика энергия/высота
            points = graphic.createKinematicEnergyOnHeightGraphic(weight,height,startSpeed);
        }
        if (this.title=="Кинетическая энергия" && yAxisLabel=="Время"){ // Условие для графика энергия/высота
            points = graphic.createKinematicEnergyOnTimeGraphic(weight,height, startSpeed);
        }
        if (this.title=="Потенциальная энергия" && yAxisLabel=="Время"){ // Условие для графика энергия/высота
            points = graphic.createPotentialEnergyOnTimeGraphic(weight,height, startSpeed);
        }
        for (Point2D point: points) { // запись точек в график
            series.add(point.getX(), point.getY());
         }
        return new XYSeriesCollection(series);
    }

    /**
     * Метод для конструирования внешнего вида графика, а также отрисовки гривой на основе DataSet
     * @return объект типа JChart - который представляет общее представление графика(Оси, деления, кривая,
     * заголовки осей и тп.)
     * */
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
