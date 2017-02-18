package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class AbanCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 387;
    }

    @Override
    protected double getB() {
        return 0.149;
    }

    @Override
    protected double getMedar() {
        return -19.4902292262213;
    }

    @Override
    protected int getMonthNo() {
        return 7;
    }

    public AbanCosineController(CosineFrame parent) {
        super(parent);
    }
}
