package interfaces;

import java.util.ArrayList;
/**
 * Интерфейс Formulas предоставляет методы для вычисления физических характеристик
 * тела при свободном падении вертикально вниз
 * @author Sleptsov D.A.
 */
public interface GraphData {

     /**
      * Метод createSpeedOnHeightGraphic формирует множество точек графика зависимости скорости тела
      * от высоты падения.
      * @param height - высота с которой тела падает (в метрах)
      * @param startSpeed - начальная скорость тела (в метрах в секунду)
      * @return множество точек графика.
      * */
     ArrayList createSpeedOnHeightGraphic(double height, double startSpeed);
     /**
      * Метод createSpeedOnTimeGraphic формирует множество точек графика зависимости скорости тела
      * от времени.
      * @param height - высота с которой тела падает (в метрах)
      * @param startSpeed - начальная скорость тела (в метрах в секунду)
      * @return множество точек графика.
      * */
     ArrayList createSpeedOnTimeGraphic(double height, double startSpeed);
     /**
      * Метод createPotentialEnergyOnHeightGraphic формирует множество точек графика зависимости потенциальной
      * энергии тела от высоты падения.
      * @param weight - масса тела (в киллограмах)
      * @param height - высота с которой тела падает (в метрах)
      * @return множество точек графика.
      * */
     ArrayList createPotentialEnergyOnHeightGraphic(double weight, double height);
     /**
      * Метод createKinematicEnergyOnHeightGraphic формирует множество точек графика зависимости кинетической
      * энергии тела от высоты падения.
      * @param weight - масса тела (в киллограмах)
      * @param height - высота с которой тела падает (в метрах)
      * @param startSpeed - начальная скорость тела (в метрах в секунду)
      * @return множество точек графика.
      * */
     ArrayList createKinematicEnergyOnHeightGraphic(double weight, double height, double startSpeed);
     /**
      * Метод createKinematicEnergyOnTimeGraphic формирует множество точек графика зависимости кинетической
      * энергии тела от времени.
      * @param weight - масса тела (в киллограмах)
      * @param height - высота с которой тела падает (в метрах)
      * @param startSpeed - начальная скорость тела (в метрах в секунду)
      * @return множество точек графика.
      * */
     ArrayList createKinematicEnergyOnTimeGraphic(double weight, double height, double startSpeed);
     /**
      * Метод createKinematicEnergyOnHeightGraphic формирует множество точек графика зависимости потенциальной
      * энергии тела от времени.
      * @param weight - масса тела (в киллограмах)
      * @param height - высота с которой тела падает (в метрах)
      * @param startSpeed - начальная скорость тела (в метрах в секунду)
      * @return множество точек графика.
      * */
     ArrayList createPotentialEnergyOnTimeGraphic(double weight, double height, double startSpeed);
}
