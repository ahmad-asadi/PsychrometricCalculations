package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class THI1Controller extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public THI1Controller(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{50,60,70,75,80,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احساس سرما","نسبتا سرد","راحت","نسبتا راحت","ناراحت","بسیار ناراحت"};
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای خشک","میانگین رطوبت به درصد"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص دما-رطوبت THI", "احساس افراد"};
    }

    @Override
    protected String[] getConstList() {
        return new String[0];
    }

    @Override
    protected boolean hasConstList() {
        return false;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return getBoundString(boundStrings, bounds, resInput[i]);
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        return (B2-((0.55-(0.55*B4))*(B2-58))) ;
    }
}
