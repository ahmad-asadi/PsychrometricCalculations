package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class MordadCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 351;
    }

    @Override
    protected double getB() {
        return 0.201;
    }

    @Override
    protected double getMedar() {
        return 13.1223682035186;
    }

    @Override
    protected int getMonthNo() {
        return 4;
    }

    public MordadCosineController(CosineFrame parent) {
        super(parent);
    }
}
