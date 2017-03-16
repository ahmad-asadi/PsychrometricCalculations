package controllers;

import uiElements.ThermalChartFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 3/10/17.
 */
public class TerjungChartController extends IndexController {

    public TerjungChartController(){
        super();
        numberOfVars = 9 ;
        numberOfRes = 12 ;
        System.out.println(getResList().length);
        indexOfBoundStrings = new ArrayList<>();
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(3);
        indexOfBoundStrings.add(5);
        indexOfBoundStrings.add(6);
        resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for(int i = 0 ; i < varTable.getColumnCount() ; i++)
            varTable.getColumnModel().getColumn(i).setPreferredWidth(100);

        resTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین حداقل دما به فارنهایت","میانگین حداکثر دما به فارنهایت", "میانگین  حداقل رطوبت نسبی", "میانگین حداکثر رطوبت نسبی"};
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
    protected double computeRes(double[] input , int index){
        return 0 ;
    }

    @Override
    protected boolean hasChart(){
        return true;
    }

    @Override
    protected void createChart(){
        ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>() ;
        ArrayList<String> cols = new ArrayList<>() ;

        for(int row = 0 ; row < varTable.getRowCount() ; row ++)
        {
            if((row > 11 && getCellData(varTable,row,0)!=0) || row < 12){
                ArrayList rowData = new ArrayList() ;
                rowData.add(getCellData(varTable,row,2));
                rowData.add(getCellData(varTable,row,3));
                data.add(rowData);
                cols.add((String) varTable.getValueAt(row,0) + " در روز");
            }
        }

        for(int row = 0 ; row < varTable.getRowCount() ; row ++)
        {
            if((row > 11 && getCellData(varTable,row,0)!=0) || row < 12){
                ArrayList rowData = new ArrayList() ;
                rowData.add(getCellData(varTable,row,1));
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


        new ThermalChartFrame("terjung", dataArr, colNames) ;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return null;
    }



}
