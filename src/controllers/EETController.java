package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EETController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public EETController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 2 ;
        bounds = new double[]{8,16,23,Double.MAX_VALUE} ;
        boundStrings = new String[]{"سرد","خنک","ملایم و مطبوع","خیلی داغ"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین رطوبت نسبی","میانگین سرعت باد به متر در ثانیه"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص EET" , "شرایط محیطی"};
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
        return (inputs[0]*(1-(0.003*(100-inputs[1])))-(0.365*(Math.pow(inputs[2],0.59)))*( (36.6-inputs[0])+(0.622*((inputs[2]-1))))+((0.0015*inputs[2])+0.008)*(((36.6-inputs[0])- 0.0167))*(100-inputs[1])) ;
    }
}
