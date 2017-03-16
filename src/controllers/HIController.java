package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public HIController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 3 ;
        bounds = new double[]{80,90,105,130,Double.MAX_VALUE} ;
        boundStrings = new String[]{"تعریف نشده","احتیاط","احتیاط بسیار","خطرناک","بسیار خطرناک"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
        indexOfBoundStrings.add(2);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما به فارنهایت","میانگین رطوبت نسبی به درصد" , "دمای نقطه شبنم به سانتی‌گراد"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"HI","طبقه خطر","حساسیت حرارتی"};
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
        String[] secondBoundStrings = new String[]{"تعریف نشده","بسیار گرم", "داغ", "بسیار داغ", "خطرناک"};;
        if(resInput[i] == -1)
            return "false" ;
        switch (i){
            case 0:
                return getBoundString(boundStrings, bounds, resInput[i]) ;
            case 1:
                return getBoundString(secondBoundStrings, bounds, resInput[i]) ;
        }
        return null;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double cond = inputs[2] ;
        if(cond >= 12 && inputs[0] >= 80 && inputs[1] >= 40)
            return -24.379+(2.04901523*inputs[0])+(10.14333127*inputs[1])-(0.22475541*inputs[0]*inputs[1])-(0.00683783*inputs[0]*inputs[0])-(0.05481717*inputs[1]*inputs[1])+(0.001228*inputs[0]*inputs[0]*inputs[1])+(0.00085282*inputs[0]*inputs[1]*inputs[1])-(0.00000199*inputs[0]*inputs[0]*inputs[1]*inputs[1]) ;
        else
            return -1 ;
    }

}
