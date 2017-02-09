package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HSIController extends AnalyticalIndexController {
    private final double[] bounds;
    private final String[] boundStrings;

    public HSIController(){
        numberOfRes = 2 ;
        bounds = new double[]{-20,10,30,70,90,100,Double.MAX_VALUE} ;
        boundStrings = new String[]{"استرس گرمایی کم","شرایط نرمال (نه گرم و نه سرد)","استرس گرمایی خفیف و متوسط","استرس گرمایی شدید و خطرناک برای وضعیت سلامت افرادی که سازگاری پایینی دارند","استرس گرمایی بسیار شدید، ذخیره آب و مواد معدنی بسیار ضروری است","ماکزیمم استرس گرمایی قابل کنترل توسط افراد جوان و کسانی‌ که سازگاری بالایی دارند","خطر بیش از حد گرم شدن یک ارگانیسم زمان رویارویی باید کنترل شود"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getResList(){
        return new String[]{"HSI" , "واکنش فیزیکی یک ارگانیسم در شاخص استرس گرمایی HSI"} ;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return getBoundString(boundStrings, bounds, resInput[i]);
    }

    @Override
    protected double computeRes(int input , int index)
    {
        double B5 = getT(input) ;
        double B6 = getRH(input) ;
        double B7 = getV(input) ;
        double B14 = getGender() ;
        double B23 = getAp(input) ;
        double B25 = getHV() ;
        double B24 = B5<-30?3:(B5>25?0.6:-0.0436*B5+1.691) ;
        double B26 = B7==0?0.25:(B7>23?15:B7*0.66) ;

        double H6 = super.getVp(input) ;
        double P13 = super.getLa(input) ;
        double O13 = super.getLg(input) ;
        double N13 = super.getRPrime(input) ;
        double R13 = Math.pow((N13+O13+P13)/(0.95*0.0000000567),0.25)-273 ;

        double H22 = getM();
        double H43 = (26.4+0.02138*R13+0.2095*B5-0.0185*B6-0.009*B26)+((B24-1)*0.6)+0.00128*H22 ;
        double H38 = -0.04 * B5 + 0.013 * B23 - 0.503 ;
        double H40 = H38 * Math.sqrt(B25+B26) ;
        double H39 = H38 * 0.53/B24 * (1-0.27*Math.pow((B26+B25),0.4)) ;

        double H41 = H39/(H39+H40) ;
        double H44 = Math.exp(0.058*H43+2.003) ;
        double H45 = H43>36.5?1:(H43<22?0.002:(1.031/(37.5-H43))-0.065) ;
        double H37 = B5*(-0.00002*B23+0.00006*B5+0.011)+0.02*B23-0.773 ;
        double H42 = B14==1 ? (H37*Math.sqrt(B25+B26)*(H6-H44)*H45*H41-(0.42*((H22)-58)-5.04)):(H37*Math.sqrt(B25+B26)*(H6-H44)*H45*H41-(0.42*((H22)-58)-5.04))*0.8 ;
        double H46 = H42<-50?(H42+50)*0.066:0 ;
        double H47 = H42 > 0 ? H43 : H43 + H46 ;
        double H48 = H47 < 22 ? 22 : H47 ;
        double H55 = 0.056*B5+4.48 ;
        double H54 = H39 / (H39 + H40 + H55) ;
        double H27 = (O13+P13-(0.95*0.0000000567*Math.pow(273+H48,4)))*H54 ;
        double H56 = N13 + H54 ;
        double H26 = H56 + H27 ;
        double H57 = (0.95*0.0000000567*Math.pow((273+H43),4)) ;
        double H53 = Math.pow(((H56+(O13+P13)*H54+0.5*H57)/(0.95*0.0000000567)),0.25)-273 ;
        double H24 = 0.0014*(H22)*(B5-35)+0.0173*H22*(0.1*H6-5.624) ;
        double H29 = H40*(H53-H48)*H54 ;
        double H73 = H22 + H26 + H29 + H24 ;
        double H74 = 7*(Math.pow(B7,0.6))*(56-H6) ;
        double HSI = 100*(H73/H74) ;

        switch (index){
            case 0:
                return P13 ;
            case 1:
                return O13 ;
            case 2:
                return N13 ;
            case 3:
                return H73 ;
            case 4:
                return H74 ;
            case 5:
                return HSI ;
            default:
                return -1 ;
        }
    }

    private double getM(double[] input) {
        return input[18];
    }

    private double getVPrime(double[] input) {
        return input[15] ;
    }

    private double getAp(double[] input) {
        return input[16] ;
    }

    private double getGender(double[] input) {
        return input[17];
    }
}
