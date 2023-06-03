package interfaces;

import entity.Point2D;
import org.jfree.data.xy.XYDataset;

import java.util.List;

public interface Dataset {
    XYDataset createDataset(List<Point2D> points);
    XYDataset updateDataset(List<Point2D> points);
    XYDataset clearDataset();
}
