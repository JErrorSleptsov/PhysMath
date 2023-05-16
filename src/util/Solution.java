package util;
/**
 * Класс Solution является представлением решения произведенного над физическим телом при свободном падении.
 * ОН содержит необходимые для расчётов и анализа данные, такие как
 * <ul>
 * <li> weight - масса тела (кг)
 * <li> height - высота сбрасывания (м)
 * <li> startSpeed - начальная скорость (м/c)
 * <li> maxSpeed - максимальная развиваемая скорость (м/c)
 * <li> maxEnergy - максимальная энергия тела
 * <li> fallTime -  время падения тела
 * </ul>
 * @author Sleptsov D.A.
 * */
public class Solution{
    private final double weight;
    private final double height;
    private final double startSpeed;
    private final double maxSpeed;
    private final double maxEnergy;
    private final double fallTime;


    public Solution(double weight, double height, double startSpeed,
                    double maxSpeed, double maxEnergy, double fallTime){
        this.weight =weight;
        this.height = height;
        this.startSpeed =startSpeed;

        this.maxSpeed = maxSpeed;
        this.maxEnergy =maxEnergy;
        this.fallTime = fallTime;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getStartSpeed() {
        return startSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxEnergy() {
        return maxEnergy;
    }

    public double getFallTime() {
        return fallTime;
    }

    @Override
    public String toString() {
        return "Решение:" +
                "\n1. Масса =" + weight +
                "\n2. Высота=" + height +
                "\n3. Начальная скорость=" + startSpeed +
                "\n4. Максимальная скорость=" + maxSpeed +
                "\n5. Максимальная энергия=" + maxEnergy +
                "\n6. Время падения=" + fallTime;
    }
}
