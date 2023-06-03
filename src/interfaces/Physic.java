package interfaces;
/**
 * Интерфейс Formulas предоставляет методы для вычисления физических характеристик
 * тела при свободном падении вертикально вниз
 * @author Sleptsov D.A.
 * @version 1
 */
public interface Physic {

    /**
     * Вычисляет время падения тела с учетом начальной скорости и высоты.
     *
     * @param height высота, с которой тело падает ( в метрах )
     * @param startSpeed начальная скорость, с которой тело падает ( в метрах в секунду )
     * @return время падения тела ( в секундах )
     */
    double findFallTime(double height, double startSpeed);

    /**
     * Вычисляет скорость тела в определенный момент времени с учетом начальной скорости.
     *
     * @param time момент времени, для которого требуется вычислить ( скорость в секундах )
     * @param startSpeed начальная скорость тела ( в метрах в секунду )
     * @return скорость тела в момент времени time ( в метрах в секунду )
     */
    double findSpeedUseTime(double time, double startSpeed);
    /**
     * Вычисляет скорость тела в определенный момент времени с учетом начальной скорости.
     *
     * @param height высота, для которой требуется вычислить ( скорость в секундах )
     * @param startSpeed начальная скорость тела ( в метрах в секунду )
     * @return скорость тела в момент времени time ( в метрах в секунду )
     */
    double findSpeedUseHeight(double height, double startSpeed);

    /**
     * Вычисляет кинетическую энергию тела с учетом его массы и скорости.
     *
     * @param weight масса тела ( в килограммах )
     * @param speed скорость тела ( в метрах в секунду )
     * @return кинетическая энергия тела ( в Джоулях )
     */
    double findKineticEnergy(double weight, double speed);

    /**
     * Вычисляет потенциальную энергию тела с учетом его массы и высоты.
     *
     * @param weight масса тела ( в килограммах )
     * @param height высота, на которую поднялось тело ( в метрах )
     * @return потенциальная энергия тела ( в Джоулях )
     */
    double findPotentialEnergy(double weight, double height);

    /**
     * Вычисляет максимальную механическую энергию тела
     *
     * @param PotentialEnergy потенциальная энергия тела ( в Джоулях )
     * @param KineticEnergy кинетическая энергия тела ( в Джоулях )
     * @return максимальная механическая энергия тела ( в Джоулях )
     */
    double findMaxEnergy(double PotentialEnergy,double KineticEnergy);
}

