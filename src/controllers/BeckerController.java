package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class BeckerController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public BeckerController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 4 ;
        bounds = new double[]{5,10,20,30,40,50,60,Double.MAX_VALUE} ;
        boundStrings = new String[]{"داغ، گرم، شرجی و نامطبوع","گرم قابل تحمل","ملایم و مطبوع","خنک","سرد و کمی فشار دهنده","خیلی سرد","فوق العاده سرد، نامطبوع","تعریف نشده"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(3);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین سرعت باد به متر بر ثانیه"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"CP","شرایط محیطی","شرایط بیوکلیماتولوژی","علائم"};
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
        String[] secondBoundStrings = new String[]{"فشار زیست-اقلیمی", "آسایش زیست-اقلیمی", "آسایش زیست-اقلیمی", "تحریک ملایم", "تحریک متوسط تا شدید", "نسبتا آزاردهنده", "به شدت آزار دهنده" ,"تعریف نشده"};
        String[] thirdBoundStrings = new String[]{"A", "B", "C", "D", "E", "F" ,"G", "H"};
        switch (i){
            case 0:
                getBoundString(boundStrings, bounds, resInput[i]) ;
                break;
            case 1:
                getBoundString(secondBoundStrings, bounds, resInput[i]) ;
                break;
            case 2:
                getBoundString(thirdBoundStrings, bounds, resInput[i]) ;
                break;
        }

        return  "ناشناخته" ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double C4 = inputs[1] ;
        double C3 = inputs[0] ;
        return ((0.26+(0.34*(Math.pow(C4,0.672))))*(36.5-C3)) ;
    }

}
