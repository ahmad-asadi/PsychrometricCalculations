package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HumidexController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public HumidexController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 4 ;
        bounds = new double[]{30,40,55,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بی‌خطر","اخطار","خطرناک","بسیار خطرناک"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(3);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","دمای نقطه شبنم"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"VP","Humidex 2","سطح خطر","علائم خطر"};
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
        String[] secondBoundStrings = new String[]{"کمی آزار دهنده،درصورت طولانی شدن فعالیت امکان خستگی و کوفتگی", "اغلب آزار دهنده،گرمای آزار دهنده و خسته کننده و در صورت ادامه ی فعالیت گرفتگی عضلات", "محیط بسیار آزار دهنده .باید از فعالیت دوری کرد.احتمال گرفتگی عضلات و کوفتگی و درصورت ادامه فعالیت گرمازدگی حادث می شود", "گرمازدگی قریب الوقوع"};
        switch (i){
            case 2:
                return getBoundString(boundStrings, bounds, resInput[i]) ;
            case 3:
                return getBoundString(secondBoundStrings, bounds, resInput[i]) ;
        }
        return "ناشناخته" ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double D3 = inputs[0] ;
        double D5 = inputs[1] ;
        double D7 = (6.11*(Math.pow(2.71,((54.70753*(1/273.16)*((1/273.16)+D5)))))) ;
        if(index == 0)
            return D7;
        else
            return (D3+(0.555*(D7-10))) ;
    }
}
