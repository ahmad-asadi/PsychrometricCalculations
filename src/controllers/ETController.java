package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class ETController extends IndexController {
    private final double[] bounds;
    private final String[] boundStrings;

    public ETController(){
        super();
        numberOfRes = 2 ;
        numberOfVars = 2 ;
        bounds = new double[]{27,32,41,45,Double.MAX_VALUE} ;
        boundStrings = new String[]{"false","وضعیت گرم - احتیاط ضروری است","وضعیت داغ - به شدت احتیاط لازم است","وضعیت بسیار داغ - خطر جدی است","وضعیت به شدت خطرناک"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","دمای تر"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص  ET","شرایط زیست-اقلیمی"};
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
        double D2 = input[0] ;
        double D4 = input[1] ;
        return (0.4*(D2+D4))+4.8 ;
    }
}
