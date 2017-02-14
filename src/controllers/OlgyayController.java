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
        double[][] tableData = getTableData(varTable) ;
        double[][] data = new double[2*(varTable.getColumnCount() - 1)][2] ;
        for (int i = 0 ; i < varTable.getColumnCount()-1; i++) {
            data[i][0] = tableData[0][i] ;
            data[i][1] = tableData[2][i] ;
        }
        for (int i = 0; i < varTable.getColumnCount()-1; i++) {
            data[varTable.getColumnCount() + i - 1][0] = tableData[1][i] ;
            data[varTable.getColumnCount() + i - 1][1] = tableData[3][i] ;
        }
        String[] colNames = new String[2*(varTable.getColumnCount()-1)];
        for (int i = 1; i < varTable.getColumnCount() ; i++) {
            colNames[i-1] = varTable.getColumnName(i) + " در روز" ;
        }
        for (int i = 1; i < varTable.getColumnCount() ; i++) {
            colNames[varTable.getColumnCount() + i-2] = varTable.getColumnName(i) + " در شب";
        }
        new ThermalChartFrame("olgay", data,colNames) ;
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
