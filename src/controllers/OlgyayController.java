package controllers;

import uiElements.ThermalChartFrame;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class OlgyayController extends IndexController {

    public OlgyayController(){
        super();
        numberOfVars = 4 ;
        numberOfRes = 0 ;
        indexOfBoundStrings = new ArrayList<>() ;
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین حداکثر دما","میانگین حداقل دما","میانگین حداکثر رطوبت","میانگین حداقل رطوبت"};
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
        return true ;
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
                rowData.add(getCellData(varTable,row,3));
                data.add(rowData);
                cols.add((String) varTable.getValueAt(row,0) + " در روز");
            }
        }

        for(int row = 0 ; row < varTable.getRowCount() ; row ++)
        {
            if((row > 11 && getCellData(varTable,row,0)!=0) || row < 12){
                ArrayList rowData = new ArrayList() ;
                rowData.add(getCellData(varTable,row,2));
                rowData.add(getCellData(varTable,row,4));
                data.add(rowData);
                cols.add((String) varTable.getValueAt(row,0) + " در شب");
            }
        }

        String[] colNames = new String[cols.size()] ;
        double[][] dataArr = new double[data.size()][2] ;
        for (int i = 0; i < data.size(); i++) {
            dataArr[i][0] = data.get(i).get(0) ;
            dataArr[i][1] = data.get(i).get(1) ;
            colNames[i] = cols.get(i) ;
        }

        new ThermalChartFrame("olgay", dataArr,colNames) ;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return null;
    }

    @Override
    protected double computeRes(double[] inputs, int resIndex) {
        return 0;
    }
}
