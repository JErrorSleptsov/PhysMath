package entity;

public class Data {
    private double weight;
    private double height;
    private double startSpeed;

    public Data(double weight, double height, double startSpeed) {
        this.weight = weight;
        this.height = height;
        this.startSpeed = startSpeed;
    }

    public double getWeight() {
        return weight;
    }

    public Data setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public Data setHeight(double height) {
        this.height = height;
        return this;
    }

    public double getStartSpeed() {
        return startSpeed;
    }

    public Data setStartSpeed(double startSpeed) {
        this.startSpeed = startSpeed;
        return this;
    }
}
