package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SBController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public SBController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{1,2,3,4,5,7,Double.MAX_VALUE} ;
        boundStrings = new String[]{"معتدل","شرایط تقریبا سخت","شرایط تا حدی سخت","شرایط سخت","شرایط خیلی سخت","شرایط بشدت سخت","شرایط خیلی سخت"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین سرعت باد"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"SB","شرایط زیست-اقلیمی"};
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
        return (1-(0.04*inputs[0]))*((1+(0.272*inputs[1]))) ;
    }
}
