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
        bounds = new double[]{210,420,630,840,1260,1680,2100,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی داغ","داغ","طبیعی","تا حدی خنک","خنک","سرد","خیلی سرد","فوق‌العاده سرد، طوفانی"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما", "میانگین سرعت باد"};
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
        return (36.5 - inputs[0]) * (0.13 + (0.47*Math.sqrt(inputs[1]))) * 41.868 ;
    }

}
