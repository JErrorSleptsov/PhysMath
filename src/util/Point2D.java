package util;

/**
 * Класс Point2D представляет точку на двухмерной плоскости. Имеет всего 2 поля
 * координата X и координата Y
 * */
public class Point2D {
    private double X;
    private double Y;
    public Point2D(){}
    /**
     * Конструктор который принимает обе координаты точки
     * */
    public Point2D(double x, double y) {
        X = x;
        Y = y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}
