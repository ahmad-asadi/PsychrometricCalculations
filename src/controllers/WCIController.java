package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WCIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public WCIController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{58.3,116.3,232.6,581.5,930.3,1628.2,2326,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی داغ","داغ","گرم","راحت","خنک","سرد","یخ‌زده","تعریف نشده"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین سرعت باد"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"WCI","اثرات عمومی روی بدن انسان"};
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
        double D5 = inputs[1] ;
        double D3 = inputs[0] ;
        return (((10*(Math.sqrt(D5)))+10.45-D5)*(33-D3)*1.163) ;
    }
}
