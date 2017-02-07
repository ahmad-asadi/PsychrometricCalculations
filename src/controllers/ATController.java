package controllers;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class ATController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public ATController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 3 ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(2);
        bounds = new double[]{27,32,41,54,Double.MAX_VALUE} ;

        boundStrings = new String[]{"امکان خستگی با فعالیت ممتد فیزیکی","آفتاب‌زدگی و انقباض حرارتی","امکان گرمازدگی با فعالیت فیزیکی","خطر گرمازدگی،‌ آفتاب‌زدگی و شوک جدی","خطر گرمازدگی،‌ آفتاب‌زدگی و شوک جدی"} ;
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین باد","دمای نقطه شبنم"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"VP","AT","اثرات عمومی روی بدن انسان"};
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
        double D3 = inputs[0] ;
        double D5 = inputs[1] ;
        double D7 = inputs[2] ;
        double D9 = 6.11*Math.pow(2.71,((54.70753*(1/273.16)*((1/273.16)+D7)))) ;
        double D11 = D3+(0.32*D9)-(0.7*D5)-4 ;

        switch (index){
            case 0:
                return D9 ;
            default:
                return D11 ;
        }

    }


}
