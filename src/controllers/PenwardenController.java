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
        numberOfVars = 3 ;
        numberOfRes = 3 ;
        indexOfBoundStrings = new ArrayList<>() ;
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین سرعت باد","رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[0];
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
        ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>() ;
        ArrayList<String> cols = new ArrayList<>() ;

        for(int row = 0 ; row < varTable.getRowCount() ; row ++)
        {
            if((row > 11 && getCellData(varTable,row,0)!=0) || row < 12){
                ArrayList rowData = new ArrayList() ;
                rowData.add(getCellData(varTable,row,1));
                rowData.add(getCellData(varTable,row,2));
                data.add(rowData);
//                cols.add((String) varTable.getValueAt(row,0) + " در آفتاب");
                cols.add((String) varTable.getValueAt(row,0) );
            }
        }

//        for(int row = 0 ; row < varTable.getRowCount() ; row ++)
//        {
//            if((row > 11 && getCellData(varTable,row,0)!=0) || row < 12){
//                ArrayList rowData = new ArrayList() ;
//                rowData.add(getCellData(varTable,row,1));
//                rowData.add(getCellData(varTable,row,2));
//                data.add(rowData);
//                cols.add((String) varTable.getValueAt(row,0) + " در سایه");
//            }
//        }

        String[] colNames = new String[cols.size()] ;
        double[][] dataArr = new double[data.size()][2] ;
        for (int i = 0; i < data.size(); i++) {
            dataArr[i][0] = data.get(i).get(0) ;
            dataArr[i][1] = data.get(i).get(1) ;
            colNames[i] = cols.get(i) ;
        }

        new ThermalChartFrame("penwarden", dataArr,colNames) ;
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
