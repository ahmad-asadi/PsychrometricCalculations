package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SETController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public SETController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{-20,-10,1.67,15.5,17.8,22.2,25.6,27.5,30,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بسیار سرد","خیلی سرد","سرد","خیلی خنک","خنک","آسایش","گرم","خیلی گرم","شرجی","خنک"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص دمای موثر SET","شرایط زیست-اقلیمی"};
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
        return null;
    }

    @Override
    protected double computeRes(double[] inputs,int index){
        return (inputs[0]-(0.4*((inputs[0]-10)*(1-(inputs[1]/100))))) ;
    }
}
