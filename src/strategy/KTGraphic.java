package strategy;

import entity.Data;
import entity.Point2D;
import interfaces.Graphic;

import java.util.ArrayList;
import java.util.List;

public class KTGraphic extends Graphic {
    public KTGraphic() {
        super.setTitle("Кинетическая энергия")
                .setxAxisLabel("Энергия")
                .setyAxisLabel("Время");
    }
    @Override
    public List<Point2D> fillSetPoints(Data data) {
        List<Point2D> points = new ArrayList<>();
        double fallTime= calculator.findFallTime(data.getHeight(), data.getStartSpeed());
        double time=0;
        double speed;
        double k = fallTime/1000;
        double X,Y;
        while(time<=fallTime){
            speed = calculator.findSpeedUseTime(time, data.getStartSpeed());
            X= calculator.findKineticEnergy(data.getWeight(), speed);
            Y=time;
            points.add(new Point2D(X,Y));
            time +=k;
        }
        return points;
    }
}
