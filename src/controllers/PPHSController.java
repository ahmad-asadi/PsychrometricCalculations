package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PPHSController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public PPHSController(){
        super();
        numberOfVars = 1 ;
        numberOfRes = 2 ;
        bounds = new double[]{0,0.25,0.75,Double.MAX_VALUE} ;
        boundStrings = new String[]{"تعریف نشده","فشار حرارتی بالا","فشار حرارتی متوسط","فشار حرارتی متوسط"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"PPHS","شرایط زیست-اقلیمی"};
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
        return Math.pow((2.12513 - 0.058018 *inputs[0]),2) ;
    }
}
