package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PHSController extends AnalyticalIndexController {
    private final double[] bounds;
    private final String[] boundStrings;

    public PHSController(){
        numberOfRes = 2 ;
        bounds = new double[]{0,0.24,0.74,1.5,4,8,Double.MAX_VALUE} ;
        boundStrings = new String[]{"فشار گرمای شدید","فشار گرمای زیاد","فشار گرمای متوسط","طبیعی","فشار سرمای متوسط","فشار سرمای زیاد","فشار سرمای شدید"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return null;
    }

    @Override
    protected String[] getResList(){
        return new String[]{"PHS","محدودیت‌های شاخص تنش فیزیکی PHS"} ;
    }

    @Override
    protected double computeRes(int rowIndex , int index)
    {
        double B5 = getT(rowIndex) ;
        double B6 = getRH(rowIndex) ;
        double B7 = getV(rowIndex) ;
        double B14 = getGender() ;
        double B23 = getAp(rowIndex) ;
        double B25 = getHV() ;
        double B24 = B5<-30?3:(B5>25?0.6:-0.0436*B5+1.691) ;
        double B26 = B7==0?0.25:(B7>23?15:B7*0.66) ;

        double H6 = getVp(rowIndex) ;
        double P13 = getLa(rowIndex) ;
        double O13 = getLg(rowIndex) ;
        double N13 = getRPrime(rowIndex) ;
        double R13 = Math.pow((N13+O13+P13)/(0.95*0.0000000567),0.25)-273 ;

        double H22 = getM();
        double H43 = (26.4+0.02138*R13+0.2095*B5-0.0185*B6-0.009*B26)+((B24-1)*0.6)+0.00128*H22 ;
        double H38 = -0.04 * B5 + 0.013 * B23 - 0.503 ;
        double H40 = H38 * Math.sqrt(B25+B26) ;
        double temp = Math.pow((B26+B25),0.4) ;
        double H39 = H38 * 0.53/(B24 * (1-0.27*temp)) ;

        double H41 = H39/(H39+H40) ;
        double H44 = Math.exp(0.058*H43+2.003) ;
        double H45 = H43>36.5?1:(H43<22?0.002:(1.031/(37.5-H43))-0.065) ;
        double H37 = B5*(-0.00002*B23+0.00006*B5+0.011)+0.02*B23-0.773 ;
        double H42 = B14==1 ? (H37*Math.sqrt(B25+B26)*(H6-H44)*H45*H41-(0.42*((H22)-58)-5.04)):(H37*Math.sqrt(B25+B26)*(H6-H44)*H45*H41-(0.42*((H22)-58)-5.04))*0.8 ;
        double H55 = 0.056*B5+4.48 ;
        double H54 = H39 / (H39 + H40 + H55) ;

        double O62 = H40*(B5-H43)*H54 ;

        return O62 / H42 ;
    }
}
