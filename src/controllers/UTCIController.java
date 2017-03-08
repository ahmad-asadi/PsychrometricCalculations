package controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class UTCIController extends AnalyticalIndexController {
    private final double[] bounds;
    private final String[] boundStrings;

    public UTCIController(){
        numberOfRes = 2 ;
        bounds = new double[]{-40,-27,-13,0,9,26,32,38,46,Double.MAX_VALUE} ;
        boundStrings = new String[]{"تنش سرمایی شدید","تنش سرمایی خیلی زیاد","تنش سرمایی زیاد","تنش سرمایی متوسط","تنش سرمایی اندک","بدون تنش حرارتی","تنش گرمایی متوسط","تنش گرمایی زیاد","تنش گرمایی بسیار زیاد","تنش گرمایی شدید"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(7);
        indexOfBoundStrings.add(9);

        resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    @Override
    protected double computeRes(int rowIndex, int resIndex) {

        double superRes = super.computeRes(rowIndex, resIndex) ;
        if(superRes != -1)
            return superRes ;

        double R13 = getTmrtSunny(rowIndex) ;
        double R14 = getTmrtShaded(rowIndex) ;

        double B5 = getT(rowIndex) ;
        double B7 = getV(rowIndex) ;
        double B6 = getRH(rowIndex) ;

        double S13 = 3.21+0.872*B5+0.2459*R13+(-2.5078*B7)-0.0176*B6 ;
        double S14 = 3.21+0.872*B5+0.2459*R14+(-2.5078*B7)-0.0176*B6 ;

        if(resIndex < 2)
            return S13 ;
        else
            return S14 ;
    }

    @Override
    protected String[] getLocalResList(){

        return new String[]{"شاخص UTCI در شرایط آفتابی","شرایط زیست-اقلیمی در شرایط آفتابی","شاخص UTCI در شرایط سایه","شرایط زیست-اقلیمی در شرایط سایه"};
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return getBoundString(boundStrings, bounds, resInput[i<2?0 : 2]);
    }


}
