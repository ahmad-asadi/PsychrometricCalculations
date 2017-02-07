package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class KeController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public KeController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{0,51,101,201,401,601,801,1001,1201,1401,2000,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بسیار داغ","داغ","گرم","مطبوع","خنک","خیلی خنک","سرد","خیلی سرد","سرمای آزار دهنده","قسمت برهنه بدن یخ می‌زند","قسمت برهنه بدن ظرف ۶ ثانیه یخ می‌زند","غیر قابل تحمل"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای ماهانه","میانگین سرعت باد"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص برودت KE","شرایط زیست-اقلیمی"};
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
        double B4 = inputs[1] ;
        double B2 = inputs[0] ;
        return ((33-B2)*((10*(Math.sqrt(B4)))+10.5-B4)) ;
    }
}
