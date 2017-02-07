package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class CIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public CIController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 4 ;
        bounds = new double[]{-5,-1,1,5,10,15,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خنک با شرایط عدم آسایش","خنک با شرایط عدم آسایش","آسایش","گرم با شرایط آسایش","گرم با شرایط عدم آسایش","شرایط عدم آسایش زیاد","کاملا شرایط عدم آسایش"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین رطوبت","میانگین سرعت باد","میانگین دما"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"CI" , "ضریب آسایش"};
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

        double D5 = inputs[0] ;
        double D7 = inputs[1] * 1.151 ;
        double D9 = inputs[2] ;
        double D27 = (0.5+((Math.pow(D5,2))*(Math.pow(10,(-4)))))*(D9-80+(0.11*D5)) ;
        double D28 = ((((-0.35)*(Math.pow(D7,0.5)))*(((20+(0.5*D5)))-(0.2*D9)))-(35*(Math.pow(D7,0.5)))) ;

        return D27 - D28 ;

    }
}
