package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SSTController extends AnalyticalIndexController {
    private final double[] bounds;
    private final String[] boundStrings;

    public SSTController(){
        numberOfRes = 2 ;
        bounds = new double[]{-7.5,0.3,2,5.2,11.3,18.8,20.9,23.7,Double.MAX_VALUE} ;
        boundStrings = new String[]{"به شدت سرد","خیلی سرد","سرد","خنک","طبیعی","گرم","داغ","خیلی داغ","به شدت داغ"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص SST","احساس حرارتی"};
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return getBoundString(boundStrings, bounds , resInput[i]);
    }

    @Override
    protected double computeRes(int rowIndex , int index)
    {
        double B5 = super.getT(rowIndex) ;
        double B7 = super.getV(rowIndex) ;
        double B26 = B7==0?0.25:(B7>23?15:B7*0.66) ;

        double H22 = getM();
        double G8 = getCloud(rowIndex);
        double H7 = getAc() ;

        double SST = B5+0.42*(1-0.009*G8)*(100-H7)*1/(0.61+1.9*Math.pow(B26,0.5))-0.15673*H22*(1-1/(0.61+1.9*Math.pow(B26,0.5))) ;

        switch (index){
            default:
                return SST ;
        }
    }
}
