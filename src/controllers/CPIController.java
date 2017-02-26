package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class CPIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public CPIController(){
        super();
        numberOfRes = 2 ;
        numberOfVars = 2 ;
        bounds = new double[]{0,51,101,201,401,601,801,1001,1201,1401,2000,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی داغ","داغ","گرم","مطبوع","خنک","خیلی خنک","سرد","خیلی سرد","سرمای آزاردهنده","قسمت برهنه بدن یخ می‌زند","قسمت برهنه بدن ظرف 6 ثانیه یخ می‌زند","غیر قابل تحمل"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما", "میانگین سرعت باد به متر بر ثانیه"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"CPI","شرایط زیست-اقلیمی"};
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
        return getBoundString(boundStrings,bounds,resInput[i]);
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double v = inputs[1]*0.54444 ;
        if(v < 1)
            return (36.5 - inputs[0]) * (0.2 + (0.4*Math.sqrt(v))) * 41.868 ;
        else
            return (36.5 - inputs[0]) * (0.13 + (0.47*Math.sqrt(v))) * 41.868 ;
    }

}
