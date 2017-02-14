package controllers;

import uiElements.ThermalChartFrame;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PenwardenController extends IndexController {

    public PenwardenController(){
        super();
        numberOfVars = 5 ;
        numberOfRes = 3 ;
        indexOfBoundStrings = new ArrayList<>() ;
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین حداکثر دما","میانگین حداقل دما","میانگین سرعت باد","رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"پارامتر اول روش علیخانی","پارامتر دوم روش علیخانی","VG"};
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
    protected boolean hasChart() {
        return true;
    }

    @Override
    protected void createChart() {
        double[][] data = new double[varTable.getColumnCount()-1][2] ;
        String[] colNames = new String[varTable.getColumnCount() - 1] ;
        for(int i = 1 ; i < varTable.getColumnCount() ; i++)
        {
            data[i-1][0] = getTableValue(6,i) ;
            data[i-1][1] = getTableValue(0,i) ;
            colNames[i-1] = varTable.getColumnName(i) ;
        }

        new ThermalChartFrame("penwarden", data,colNames) ;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return null;
    }

    private double getTableValue(int i , int j){
        double res = 0 ;
        try{
            res = Double.parseDouble((String)varTable.getModel().getValueAt(i,j)) ;
        }catch (Exception e){}
        finally {
            return res ;
        }
    }

    @Override
    protected double computeRes(double[] input, int index){
        double B1 = input[0] ;
        double C7 = input[3] ;
        double R7 = C7*0.514444 ;
        double B4 = R7 ;
        double B6 = ((B4)/(Math.pow((10/270 ),(0.14 ))/Math.pow((10/460 ),(0.33)))) ;
        double B8 = B6*Math.pow((2/10),(0.33)) ;
        double VG = 1.35 * B4 ;

        switch (index){
            case 0:
                return B6 ;
            case 1:
                return B8 ;
            case 2:
                return VG ;
            default:
                return 0 ;
        }
    }
}
