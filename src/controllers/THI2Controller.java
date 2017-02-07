package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class THI2Controller extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public THI2Controller(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{-40,-20,-10,-1.8,13,15,20,26.5,30,Double.MAX_VALUE} ;
        boundStrings = new String[]{"فوق‌العاده یخ‌بندان","یخ‌بندان","فوق‌العاده سرد","خیلی سرد","سرد","خنک","راحت","گرم","بسیار داغ","سوزان"};
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین رطوبت"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"THI2","شرایط زیست-اقلیمی"};
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
        return (B2-((0.55-(0.0055*B4))*(B2-14.5))) ;
    }
}
