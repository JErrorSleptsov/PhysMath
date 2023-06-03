package strategy;

import entity.Data;
import entity.Point2D;
import interfaces.Graphic;

import java.util.ArrayList;
import java.util.List;

public class KHGraphic extends Graphic {

    public KHGraphic() {
        super.setTitle("Кинетическая энергия")
                .setxAxisLabel("Энергия")
                .setyAxisLabel("Высота");
    }

    @Override
    public List<Point2D> fillSetPoints(Data data) {
        List<Point2D> points = new ArrayList<>();
        double k = data.getHeight()/1000;
        double h= data.getHeight();
        double speed = 0;
        double X,Y;
        while (h>0){
            h -= k;
            Y = h;
            speed = calculator.findSpeedUseHeight(data.getHeight()-h, data.getStartSpeed());
            X = calculator.findKineticEnergy(data.getWeight(), speed);
            points.add(new Point2D(X,Y));
        }
        return points;
    }
}
