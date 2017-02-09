package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class RSIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public RSIController(){
        super();
        numberOfVars = 5 ;
        numberOfRes = 4 ;
        bounds = new double[]{0.1,0.3,0.5,Double.MAX_VALUE} ;
        boundStrings = new String[]{"آسایش","عدم آسایش","اضطراب","ناتوانی"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(3);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین رطوبت", "باد","دمای نقطه شبنم", "فشار بخار به میلی بار  e"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص فشار نسبی","شخص متوسط","شخص سازگار","شخص سال‌خورده"};
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
        double[] secondBounds = new double[]{0.3,0.5,1,Double.MAX_VALUE} ;
        double[] thirdBounds = new double[]{0.1,0.3,0.4,Double.MAX_VALUE} ;

        switch (i){
            case 2:
                return getBoundString(boundStrings, bounds, resInput[i]) ;
            case 3:
                return getBoundString(boundStrings, secondBounds , resInput[i]) ;
            case 4:
                return getBoundString(boundStrings, thirdBounds, resInput[i]) ;
        }

        return "ناشناخته" ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        double B7 = 0.254*B4*(Math.pow((0.00739*B2)+0.807,8)) ;
        if(index == 0)
            return B7 ;
        else
            return (10.7+(0.74*(B2-35)))/(44-B7) ;

    }

}
