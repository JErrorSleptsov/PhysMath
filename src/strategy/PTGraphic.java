package strategy;

import entity.Data;
import entity.Point2D;
import interfaces.Graphic;

import java.util.ArrayList;
import java.util.List;

public class PTGraphic extends Graphic {
    public PTGraphic() {
        super.setTitle("Потенциальная энергия")
                .setxAxisLabel("Энергия")
                .setyAxisLabel("Время");
    }

    @Override
    public List<Point2D> fillSetPoints(Data data) {
        List<Point2D> points = new ArrayList<>();
        double fallTime = calculator.findFallTime(data.getHeight(), data.getStartSpeed());
        double time=0;
        double k = fallTime/1000;
        double X,Y;
        while(time<=fallTime){
            X = calculator.findPotentialEnergy(data.getWeight(), data.getHeight())-
                    (data.getStartSpeed()*time+9.81*time*time/2);
            Y=time;
            points.add(new Point2D(X,Y));
            time +=k;
        }
        return points;
    }
}
