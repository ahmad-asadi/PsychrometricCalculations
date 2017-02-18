package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class TSController extends IndexController {
    private final double[] bounds;
    private final String[] boundStrings;

    public TSController(){
        super();
        numberOfRes = 2 ;
        numberOfVars = 3 ;
        bounds = new double[]{2,3,4,5,6,7,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی سرد","سرد","خنک","آسایش","گرم","داغ","بسیار داغ"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای ماهانه","میانگین سرعت باد","میانگین تابش مستقیم بر افق"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"CV","شرایط زیست-اقلیمی"};
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
    protected double computeRes(double[] input, int index){
        double B2 = input[0] ;
        double B4 = input[1] ;
        double B6 = input[2] ;
        return ((0.0033*B6)+(0.22*B2)-(0.05*B4)-2.3) ;
    }
}
