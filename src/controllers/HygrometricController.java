package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/18/17.
 */
public class HygrometricController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public HygrometricController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{60,75,80,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احساس سرما","احساس آسایش و راحتی حرارتی","پنجاه درصد افراد به دلیل گرما از ناراحتی رنج می‌برند","صد در صد افراد به دلیل گرما از ناراحتی رنج می‌برند"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای نقطه شبنم","میانگین دمای خشک"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"ترموهیگرومتریک DI","شرایط زیست-اقلیمی"};
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
        return (((0.99*B4)+(0.36*B2))+41.5) ;
    }
}
