package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class MehrCosineTable extends MonthCosineController {
    @Override
    protected double getA() {
        return 278;
    }

    @Override
    protected double getB() {
        return 0.16;
    }

    @Override
    protected double getMedar() {
        return -10.3301654930191;
    }

    @Override
    protected int getMonthNo() {
        return 6;
    }

    public MehrCosineTable(CosineFrame parent) {
        super(parent);
    }
}
