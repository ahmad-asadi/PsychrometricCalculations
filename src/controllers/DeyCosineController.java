package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class DeyCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 390;
    }

    @Override
    protected double getB() {
        return 0.142;
    }

    @Override
    protected double getMedar() {
        return -21.0963438933451;
    }

    @Override
    protected int getMonthNo() {
        return 9;
    }

    public DeyCosineController(CosineFrame parent) {
        super(parent);
    }
}
