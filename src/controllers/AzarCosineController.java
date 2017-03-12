package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class AzarCosineController extends MonthCosineController {

    @Override
    protected double getA() {
        return 391;
    }

    @Override
    protected double getB() {
        return 0.142;
    }

    @Override
    protected int getMonthNo() {
        return 8;
    }

    public AzarCosineController(CosineFrame parent) {
        super(parent);
    }
}
