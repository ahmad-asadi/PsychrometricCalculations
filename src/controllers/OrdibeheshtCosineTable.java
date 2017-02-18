package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class OrdibeheshtCosineTable extends MonthCosineController {
    @Override
    protected double getA() {
        return 350;
    }

    @Override
    protected double getB() {
        return 0.196;
    }

    @Override
    protected double getMedar() {
        return 19.2636251749416;
    }

    @Override
    protected int getMonthNo() {
        return 1;
    }

    public OrdibeheshtCosineTable(CosineFrame parent) {
        super(parent);
    }
}
