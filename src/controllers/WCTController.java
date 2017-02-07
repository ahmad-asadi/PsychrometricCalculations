package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WCTController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public WCTController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{-60,-45,-25,-10,Double.MAX_VALUE} ;
        boundStrings = new String[]{"گرمای متوسط","خیلی سرد","سرد","ملایم و متوسط","کم"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین سرعت باد"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"WCT","اثرات عمومی روی بدن انسان"};
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
        double D5 = inputs[1] ;
        double D3 = inputs[0] ;
        return 13.12+(0.6215*D3)-(11.37*(Math.pow((1.5*D5),0.16)))+(0.3965*D3*((Math.pow((1.5*D5),0.16)))) ;
    }
}
