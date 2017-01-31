package controllers;

import uiElements.ThermalChartFrame;

import javax.swing.table.DefaultTableModel;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PenwardenController extends IndexController {

    public PenwardenController(){
        super();
        setCols();
        numberOfVars = 3 ;
        numberOfRes = 4 ;
        setBoundStrings = false ;
    }

    @Override
    public void solve(){
        super.solve();

        double[][] data = new double[cols.length-1][2] ;
        String[] colNames = new String[cols.length - 1] ;
        model = (DefaultTableModel) getModel();
        for(int i = 1 ; i < model.getColumnCount() ; i++)
        {
            data[i-1][0] = getTableValue(6,i) ;
            data[i-1][1] = getTableValue(0,i) ;
            colNames[i-1] = getColumnName(i) ;
        }

        new ThermalChartFrame("penwarden", data,colNames) ;
    }

    private double getTableValue(int i , int j){
        double res = 0 ;
        try{
            res = Double.parseDouble((String)model.getValueAt(i,j)) ;
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

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین حداکثر دما" ;
        model.addRow(row);
        row[0] = "میانگین حداقل دما" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);

        row[0] = "پارامتر اول روش علیخانی" ;
        model.addRow(row);
        row[0] = "پارامتر دوم روش علیخانی" ;
        model.addRow(row);
        row[0] = "VG" ;
        model.addRow(row);

    }
}
