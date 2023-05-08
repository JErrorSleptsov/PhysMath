package test;
import org.junit.Test;
import util.Calculator;

import static org.junit.Assert.*;


public class CalculatorTest {
    Calculator calculator = new Calculator();
    double delta = 0.001;

    @Test
    public void testFindSpeedUseHeight() {
        double expected = 140.071; // calculated from 100m and 0 start speed
        double actual = calculator.findSpeedUseHeight(1000, 0);
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testFindFallTime() {
        double expected = 13.118; // calculated from 100m and 0 start speed
        double actual = calculator.findFallTime(1500, 50);
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testFindSpeedUseTime() {
        double expected = 92.05; // calculated from 0 time and 20 start speed
        double actual = calculator.findSpeedUseTime(5, 43);
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testFindKineticEnergy() {
        double expected = 98000; // calculated from 10 weight and 20 speed
        double actual = calculator.findKineticEnergy(10, 140);
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testFindPotentialEnergy() {
        double expected = 11536.56; // calculated from 10 weight and 1 height
        double actual = calculator.findPotentialEnergy(8, 147);
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testFindMaxEnergy() {
        double expected = 19810.0; // calculated from 9810 potential energy and 10000 kinetic energy
        double actual = calculator.findMaxEnergy(9810, 10000);
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testFindMaxEnergyWithWeightHeightAndStartSpeed() {
        double expected = 91573.36; // calculated from 10 weight, 1 height and 20 start speed
        double actual = calculator.findMaxEnergy(8, 1007, 56);
        assertEquals(expected, actual, delta);
    }
}
