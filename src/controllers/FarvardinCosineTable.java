package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class FarvardinCosineTable extends MonthCosineController {

    @Override
    protected double getA() {
        return 360;
    }

    @Override
    protected double getB() {
        return 0.196;
    }

    @Override
    protected int getMonthNo() {
        return 0;
    }

    public FarvardinCosineTable(CosineFrame parent) {
        super(parent);
    }
}

