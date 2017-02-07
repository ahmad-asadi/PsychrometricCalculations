package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class DIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public DIController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{21,25,28,30,32,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بدون ناراحتی","کمتر از پنجاه درصد از جمعیت احساس ناراحتی می‌کنند","بیش از پنجاه درصد از جمعیت احساس ناراحتی می‌کنند","بیشتر جمعیت از ناراحتی رنج می‌برند","همه احساس استرس می‌کنند","وضعیت اضطراری پزشکی"};
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);

    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص ضریب ناراحتی انسان","شرایط زیست-اقلیمی"};
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
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        return (B2-((0.55 - (0.0055*B4))*(B2-14.5))) ;
    }
}
