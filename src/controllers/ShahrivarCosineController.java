package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class ShahrivarCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 365;
    }

    @Override
    protected double getB() {
        return 0.177;
    }

    @Override
    protected double getMedar() {
        return 1.61335714612628;
    }

    @Override
    protected int getMonthNo() {
        return 5;
    }

    public ShahrivarCosineController(CosineFrame parent) {
        super(parent);
    }
}
