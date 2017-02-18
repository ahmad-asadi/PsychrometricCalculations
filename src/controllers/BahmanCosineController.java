package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class BahmanCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 385;
    }

    @Override
    protected double getB() {
        return 0.144;
    }

    @Override
    protected double getMedar() {
        return 13.2891561850267;
    }

    @Override
    protected int getMonthNo() {
        return 10;
    }

    public BahmanCosineController(CosineFrame parent) {
        super(parent);
    }
}
