package util;

import interfaces.PhysicMethods;
/**
 * Класс, который реализует интерфейс абстрактные методы класса PhysicMethods
 * Предназначен для расчёта характеристик тела
 * @author Sleptsov D.A.
 * */
public class Calculator implements PhysicMethods {
    private final static double g = 9.81;

    @Override
    public double findSpeedUseHeight(double height, double startSpeed) {
        return Math.sqrt(2*height*g+startSpeed*startSpeed);
    }

    @Override
    public double findFallTime(double height, double startSpeed) {
        return ((-startSpeed) + Math.sqrt(startSpeed*startSpeed +2*g*height))/g;
    }

    @Override
    public double findSpeedUseTime(double time, double startSpeed) {
        return startSpeed+g*time;
    }

    @Override
    public double findKineticEnergy(double weight, double speed) {
        return weight*speed*speed/2;
    }

    @Override
    public double findPotentialEnergy(double weight, double height) {
        return weight*g*height;
    }

    @Override
    public double findMaxEnergy(double PotentialEnergy, double KineticEnergy) {
        return PotentialEnergy+KineticEnergy;
    }
    public double findMaxEnergy(double weight,double height, double startSpeed) {
        return findPotentialEnergy(weight,height)+findKineticEnergy(weight,startSpeed);
    }
}

