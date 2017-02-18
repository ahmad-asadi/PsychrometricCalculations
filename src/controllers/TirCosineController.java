package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class TirCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 344;
    }

    @Override
    protected double getB() {
        return 0.207;
    }

    @Override
    protected double getMedar() {
        return 21.1836935645139;
    }

    @Override
    protected int getMonthNo() {
        return 3;
    }

    public TirCosineController(CosineFrame parent) {
        super(parent);
    }
}
