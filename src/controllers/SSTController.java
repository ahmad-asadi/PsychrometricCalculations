package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SSTController extends AnalyticalIndexController {
    public SSTController(){
        numberOfVars += 4 ;
        numberOfRes = 5 ;
        indexOfStringField = numberOfVars + numberOfRes -1 ;
        setBoundStrings = true ;
        bounds = new double[]{-7.5,0.3,2,5.2,11.3,18.8,20.9,23.7,Double.MAX_VALUE} ;
        boundStrings = new String[]{"به شدت سرد","خیلی سرد","سرد","خنک","طبیعی","گرم","داغ","خیلی داغ","به شدت داغ"} ;
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

        row[0] = "SST" ;
        model.addRow(row);
        row[0] = "محدوده‌های ارزیابی احساس حرارتی شاخص SST" ;
        model.addRow(row);


    }

    @Override
    protected double computeRes(double[] input , int index)
    {
        double B5 = super.getT(input) ;
        double B7 = super.getV(input) ;
        double B26 = B7==0?0.25:(B7>23?15:B7*0.66) ;

        double P13 = super.getLa(input) ;
        double O13 = super.getLg(input) ;
        double N13 = super.getRPrime(input) ;

        double H22 = getM(input);


        double G8 = input[augmentedParametersCount+4];
        double H7 = getAc(input) ;

        double SST = B5+0.42*(1-0.009*G8)*(100-H7)*1/(0.61+1.9*Math.pow(B26,0.5))-0.15673*H22*(1-1/(0.61+1.9*Math.pow(B26,0.5))) ;

        switch (index){
            case 0:
                return P13 ;
            case 1:
                return O13 ;
            case 2:
                return N13 ;
            case 3:
            case 4:
                return SST ;
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
