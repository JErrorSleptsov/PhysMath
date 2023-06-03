package util;

import entity.Data;
import entity.Point2D;
import interfaces.Graphic;
import interfaces.Painter;
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
import strategy.KHGraphic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс GraphicsPainter содержит методы для создания и обновления графиков,
 * используя инструменты библиотеки FreeChart.
 * @author Sleptsov D.A.
 * */
public class GraphicPainter implements Painter {
    Graphic graphic;
    private XYSeries series;
    private Data data;

    public void setGraphic(Graphic graphic) {
        this.graphic = graphic;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public XYDataset createDataset() {
         series = new XYSeries(graphic.getTitle()); // Переменная для записи точек графика
         return updateDataset();
    }

    public GraphicPainter() {
    }

    public GraphicPainter(Data data) {
        this.data = data;
    }

    public GraphicPainter(Graphic graphic, Data data) {
        this.graphic = graphic;
        this.data = data;
    }

    public XYDataset updateDataset(){
        series.clear();
        List<Point2D> points = graphic.fillSetPoints(data); // Массив объектов точка
        for (Point2D point: points) { // запись точек в график
            series.add(point.getX(), point.getY());
         }
        return new XYSeriesCollection(series);
    }

    @Override
    public JFreeChart paintGraphic(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                graphic.getTitle(),
                graphic.getxAxisLabel(),
                graphic.getyAxisLabel(),
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
