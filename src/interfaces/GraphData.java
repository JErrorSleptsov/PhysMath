package interfaces;

import java.util.ArrayList;
/**
 * Интерфейс Formulas предоставляет методы для вычисления физических характеристик
 * тела при свободном падении вертикально вниз
 * @author Sleptsov D.A.
 * @version 1
 */
public interface GraphData {

     ArrayList createSpeedOnHeightGraphic(double height, double startSpeed);

     ArrayList createSpeedOnTimeGraphic(double height, double startSpeed);

     ArrayList createPotentialEnergyUseHeightGraphic(double weight, double height);

     ArrayList createKinematicEnergyUseHeightGraphic(double weight, double height, double startSpeed);

     ArrayList createKinematicEnergyUseTimeGraphic(double weight, double height, double startSpeed);

     ArrayList createPotentialEnergyUseTimeGraphic(double weight, double height, double startSpeed);
}
