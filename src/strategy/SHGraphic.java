package strategy;

import entity.Data;
import entity.Point2D;
import interfaces.Graphic;

import java.util.ArrayList;
import java.util.List;

public class SHGraphic extends Graphic {
    public SHGraphic() {
        super.setTitle("Скорость")
                .setxAxisLabel("Скорость")
                .setyAxisLabel("Высота");
    }
    @Override
    public List<Point2D> fillSetPoints(Data data) {
        List<Point2D> points = new ArrayList<>();
        double k = data.getHeight()/1000;
        double h= data.getHeight();
        double X,Y;
        while (h>0){
            Y = h;
            X = calculator.findSpeedUseHeight(data.getHeight()-h, data.getStartSpeed());
            h -= k;
            points.add(new Point2D(X,Y));
        }
        return points;
    }
}
