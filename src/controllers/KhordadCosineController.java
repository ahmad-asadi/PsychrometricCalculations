package controllers;

import uiElements.CosineFrame;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class KhordadCosineController extends MonthCosineController {
    @Override
    protected double getA() {
        return 345;
    }

    @Override
    protected double getB() {
        return 0.205;
    }

    @Override
    protected double getMedar() {
        return 23.2546178057485;
    }

    @Override
    protected int getMonthNo() {
        return 2;
    }

    public KhordadCosineController(CosineFrame parent) {
        super(parent);
    }
}
