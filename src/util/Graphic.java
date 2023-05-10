package util;

import interfaces.GraphData;

import java.util.ArrayList;

/**
 * Класс Graphic представляет математическое представление графика, а именно
 * он представляет множество точек, на основании которых будет рисоватьс график
 * Содержит методы заполнения массива точек для различных графиков
 * @author Sleptsov D.A.
 * */
public class Graphic implements GraphData {
    private Calculator calculator =  new Calculator();
    private double X;
    private double Y;
    private ArrayList<PointOnGraphic> points = new ArrayList<>();

    @Override
    public ArrayList createSpeedOnHeightGraphic(double height, double startSpeed){
        double k = height/1000;
        double h=height;
        points.clear();
        while (h>0){
            Y = h;
            X = calculator.findSpeedUseHeight(height-h, startSpeed);
            h -= k;
            points.add(new PointOnGraphic(X,Y));
        }
        return points;
    }
    @Override
    public ArrayList createSpeedOnTimeGraphic(double height, double startSpeed){
        double fallTime = calculator.findFallTime(height, startSpeed);
        double time=0;
        double k = fallTime/1000;
        points.clear();
        while(time<=fallTime){
            X= calculator.findSpeedUseTime(time,startSpeed);
            Y=time;
            points.add(new PointOnGraphic(X,Y));
            time +=k;
        }
        return points;
    }
    @Override
    public ArrayList createPotentialEnergyUseHeightGraphic(double weight, double height){
        double k = height/1000;
        double h=height;
        points.clear();
        while (h>0){
            h -= k;
            Y = h;
            X = calculator.findPotentialEnergy(weight,h);
            points.add(new PointOnGraphic(X,Y));
        }
        return points;
    }
    @Override
    public ArrayList createKinematicEnergyUseHeightGraphic(double weight, double height, double startSpeed){
        double k = height/1000;
        double h=height;
        double speed=0;
        points.clear();
        while (h>0){
            h -= k;
            Y = h;
            speed = calculator.findSpeedUseHeight(height-h,startSpeed);
            X = calculator.findKineticEnergy(weight,speed);
            points.add(new PointOnGraphic(X,Y));
        }
        return points;
    }
    @Override
    public ArrayList createKinematicEnergyUseTimeGraphic(double weight, double height, double startSpeed){
        double fallTime= calculator.findFallTime(height,startSpeed);
        double time=0;
        double speed = startSpeed;
        double k = fallTime/1000;
        points.clear();
        while(time<=fallTime){
            speed = calculator.findSpeedUseTime(time,startSpeed);
            X= calculator.findKineticEnergy(weight,speed);
            Y=time;
            points.add(new PointOnGraphic(X,Y));
            time +=k;
        }
        return points;
    }
    @Override
    public ArrayList createPotentialEnergyUseTimeGraphic(double weight, double height, double startSpeed){
        double fallTime= calculator.findFallTime(height, startSpeed);
        double time=0;
        double k = fallTime/1000;
        points.clear();
        while(time<=fallTime){
            X= calculator.findPotentialEnergy(weight,height-(startSpeed*time+9.81*time*time/2));
            Y=time;
            points.add(new PointOnGraphic(X,Y));
            time +=k;
        }
        return points;
    }
}
