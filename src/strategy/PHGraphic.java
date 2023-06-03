package strategy;

import entity.Data;
import entity.Point2D;
import interfaces.Graphic;

import java.util.ArrayList;
import java.util.List;

public class PHGraphic extends Graphic {
    public PHGraphic() {
        super.setTitle("Потенциальная энергия")
                .setxAxisLabel("Энергия")
                .setyAxisLabel("Высота");
    }
    @Override
    public List<Point2D> fillSetPoints(Data data) {
        List<Point2D> points = new ArrayList<>();
        double k = data.getHeight()/1000;
        double h= data.getHeight();
        double X,Y;
        while (h>0){
            h -= k;
            Y = h;
            X = calculator.findPotentialEnergy(data.getWeight(), h);
            points.add(new Point2D(X,Y));
        }
        return points;
    }
}
