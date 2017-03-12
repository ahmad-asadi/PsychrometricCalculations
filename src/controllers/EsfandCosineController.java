package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class EsfandCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 376;
    }

    @Override
    protected double getB() {
        return 0.156;
    }

    @Override
    protected int getMonthNo() {
        return 11;
    }

    public EsfandCosineController(CosineFrame parent) {
        super(parent);
    }
}
