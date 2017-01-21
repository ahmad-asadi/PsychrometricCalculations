package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PSTController extends AnalyticalIndexController {
    public PSTController(){
        numberOfVars += 4 ;
        numberOfRes = 5 ;
        indexOfStringField = numberOfVars + numberOfRes -1 ;
        setBoundStrings = true ;
        bounds = new double[]{-36,-16.1,4,14,24,34,44,54,Double.MAX_VALUE} ;
        boundStrings = new String[]{"یخ‌زدگی","خیلی سرد","سرد","خنک","آسایش","گرم","داغ","خیلی گرم","سوزان"} ;
        solve();
    }

    @Override
    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];

        for(int i = 1 ; i < row.length ; i ++)
            row[i] = "0" ;


        row[0] = "جنسیت" ;
        model.addRow(row);
        row[0] = "AP (hPa)" ;
        model.addRow(row);
        row[0] = "سرعت حرکت انسان" ;
        model.addRow(row);
        row[0] = "متابولیسم" ;
        model.addRow(row);

        row[0] = "LA" ;
        model.addRow(row);
        row[0] = "LG" ;
        model.addRow(row);
        row[0] = "R'" ;
        model.addRow(row);

        row[0] = "PST" ;
        model.addRow(row);
        row[0] = "محدوده‌های آسایش حرارتی PST" ;
        model.addRow(row);


    }

    @Override
    protected double computeRes(double[] input , int index)
    {
        double B5 = super.getT(input) ;
        double B6 = super.getRH(input) ;
        double B7 = super.getV(input) ;
        double B14 = getGender(input) ;
        double B23 = getAp(input) ;
        double B25 = getVPrime(input) ;
        double B24 = B5<-30?3:(B5>25?0.6:-0.0436*B5+1.691) ;
        double B26 = B7==0?0.25:(B7>23?15:B7*0.66) ;

        double H6 = super.getVp(input) ;
        double P13 = super.getLa(input) ;
        double O13 = super.getLg(input) ;
        double N13 = super.getRPrime(input) ;
        double R13 = Math.pow((N13+O13+P13)/(0.95*0.0000000567),0.25)-273 ;

        double H22 = getM(input);
        double H43 = (26.4+0.02138*R13+0.2095*B5-0.0185*B6-0.009*B26)+((B24-1)*0.6)+0.00128*H22 ;
        double H38 = -0.04 * B5 + 0.013 * B23 - 0.503 ;
        double H40 = H38 * Math.sqrt(B25+B26) ;
        double H39 = H38 * 0.53/B24 * (1-0.27*Math.pow((B26+B25),0.4)) ;

        double H41 = H39/(H39+H40) ;
        double H44 = Math.exp(0.058*H43+2.003) ;
        double H45 = H43>36.5?1:(H43<22?0.002:(1.031/(37.5-H43))-0.065) ;
        double H37 = B5*(-0.00002*B23+0.00006*B5+0.011)+0.02*B23-0.773 ;
        double H42 = B14==1 ? (H37*Math.sqrt(B25+B26)*(H6-H44)*H45*H41-(0.42*((H22)-58)-5.04)):(H37*Math.sqrt(B25+B26)*(H6-H44)*H45*H41-(0.42*((H22)-58)-5.04))*0.8 ;
        double H55 = 0.056*B5+4.48 ;
        double H54 = H39 / (H39 + H40 + H55) ;

        double H46 = H42<-50?(H42+50)*0.066:0 ;
        double H47 = H42>0?H43:H43+H46 ;
        double H48 = H47<22?22:H47 ;
        double H49 = H48>36.5?1:(1.031/(37.5-H48))-0.065 ;
        double H50 = Math.exp(0.058*H49+2.003) ;
        double H57 = Math.pow(0.95*0.0000000567*(273+H43),4) ;
        double H56 = N13*H54 ;
        double H53 = (Math.pow(((H56+(O13+P13)*H54+0.5*H57)/(0.95*0.0000000567)),0.25))-273 ;
        double H51 = 6.112*Math.pow(10,(7.5*H53/(237.7+R13)))*0.01*B6 ;
        double H35 = H37*Math.sqrt(B26+B25)*(H51-H50)*H49*H41-(0.42*(H22-58)-5.04) ;
        double H29 = H40*(H53-H48)*H54 ;
        double H27 = (O13+P13-Math.pow(0.95*0.0000000567*(273+H48),4))*H54 ;
        double H26 = H56+H27 ;
        double H24 = 0.0014*(H22)*(B5-35)+0.0173*H22*(0.1*H6-5.624) ;
        double H20 = H22+H29+H35+H26+H24 ;
        double H58 = (Math.pow((Math.pow((Math.abs(H20)),0.75)/(0.00000005386)+Math.pow(273,4)),0.25))-273 ;
        double PST = H20<0?H53-H58:H53+H58 ;


        switch (index){
            case 0:
                return P13 ;
            case 1:
                return O13 ;
            case 2:
                return N13 ;
            case 3:
            case 4:
                return PST ;
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
