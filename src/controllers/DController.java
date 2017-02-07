package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class DController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public DController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{1,2.49,2.99,3.49,Double.MAX_VALUE} ;
        boundStrings = new String[]{"شرجی خیلی ضعیف","شرجی ضعیف","شرجی متوسط","شرجی شدید","شرجی بسیار شدید"};
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما به فارنهایت","میانگین رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص شدت شرجی D" , "شرجی بودن"};
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
        return (B4/21.55)-(100/B2)+1.3 ;
    }

}
