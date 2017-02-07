package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class NETController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public NETController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 2 ;
        bounds = new double[]{1,9,17,21,23,27,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی سرد","سرد","خنک","خوب","راحت","گرم","خیلی گرم"};
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین سرعت باد","میانگین رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"TE","اثرات عمومی روی بدن انسان"};
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
        double D3 = inputs[0] ;
        double D5 = inputs[1] ;
        double D7 = inputs[2] ;
        return (37-((37-D3)/(0.68-(0.0014*D7)+(1/(1.76+(1.4*(Math.pow(D5,0.75)))))))-((0.29*D3)*(1-(0.01*D7)))) ;
    }
}
