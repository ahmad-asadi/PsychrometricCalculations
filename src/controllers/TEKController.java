package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class TEKController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public TEKController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 3 ;
        bounds = new double[]{18,24,32,44,56,Double.MAX_VALUE} ;
        boundStrings = new String[]{"سرد","خنک","کمی خنک","آسایش","شرجی-داغ","خیلی گرم و مرطوب"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(2);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"فشار بخار e","TEK","شرایط زیست-اقلیمی"};
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
        return getBoundString(boundStrings, bounds, resInput[2]);
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double D2 = inputs[0] ;
        double D4 = inputs[1] ;
        double D6 = 6.112*(Math.pow(10,((7.5*D2)/(273.7+D2))))*(D4/100) ;
        if(index == 0)
            return D6;
        else
            return D2+(1.5*D6) ;
    }
}
