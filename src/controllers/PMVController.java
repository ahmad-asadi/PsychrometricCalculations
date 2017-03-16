package controllers;

import facilities.PMVTable;
import uiElements.ThermalChartFrame;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PMVController extends IndexController {

    private final String[] boundStrings1;
    private final String[] boundStrings2;
    private final double[] bounds;

    public PMVController(){
        super();
        numberOfRes = 2 ;
        numberOfVars = 6 ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(2);
//        for(int i = 0 ; i < varTable.getRowCount() ; i++)
//            if(varTable.getValueAt(i,0) != null && !varTable.getValueAt(i,0).equals("") && !varTable.getValueAt(i,0).equals(" ")) {
//                varTable.setValueAt("1", i, 1);
//                varTable.setValueAt("1.2", i, 2);
//            }

        bounds = new double[]{-3.5, -2.5,-1.5,-0.5,0.5,1.5,2.5,3.5,Double.MAX_VALUE} ;
        boundStrings1 = new String[] {"خیلی سرد","سرد","خنک","کمی خنک","راحت","کمی گرم","گرم","خیلی گرم","داغ","خیلی داغ"} ;
        boundStrings2 = new String[] {"تنش سرمای بسیار شدید","تنش سرمای شدید","تنش سرمای متوسط","تنش سرمای اندک","بدون تنش سرما","تنش گرمای اندک","تنش گرمای متوسط","تنش گرمای شدید","تنش گرمای بسیار شدید","تنش گرمای بشدت شدید"} ;

    }

    @Override
    protected String[] getVarList() {
        return new String[]{"دمای هوا","رطوبت نسبی","دمای کروی","سرعت باد به متر بر ثانیه"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص PMV","شاخص PPD","حساسیت حرارتی"};
    }

    @Override
    protected String[] getConstList() {
        return new String[]{"نام پارامتر","مقدار پارامتر"};
    }

    @Override
    protected boolean hasConstList() {
        return true;
    }

    @Override
    public void createChart(){
        double[][] data = new double[varTable.getColumnCount()-1][2] ;
        String[] colNames = new String[varTable.getColumnCount()] ;
        for(int j = 0 ; j < varTable.getColumnCount() - 1 ; j++){
            data[j][1] = Double.parseDouble((String) varTable.getValueAt(varTable.getRowCount() - 1, j + 1)) ;
            data[j][0] = Double.parseDouble((String) varTable.getValueAt(varTable.getRowCount() - 2, j + 1)) ;
            colNames[j] = varTable.getColumnName(j+1) ;
        }

        new ThermalChartFrame("pmv", data ,colNames) ;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        switch (i){
            case 2:
                return getBoundString(boundStrings1, bounds, resInput[1]) ;
            case 3:
                return getBoundString(boundStrings2, bounds, resInput[1]) ;
        }
        return "ناشناخته";
    }

    @Override
    protected double computeRes(double[] input, int index){
        double CLO = ((PMVTable)constTable).m ;
        double MET = ((PMVTable)constTable).ac() ;
        double TEQ = input[0] ;
        double REL = input[1] ;
        double Tg = input[2] ;
        double V = input[3] ;

        double TA = Math.pow(Math.pow((Tg + 273.15),4) + ((Math.pow((1.335 + V),0.71) + (Tg - TEQ))/(0.95*Math.pow(0.15,0.4))) *100000000,0.25) - 273.15 ;

        double FNSP = Math.exp(16.6536 - 4030.183 / (TEQ + 235)) ;
        double PA = REL * 10 * FNSP ;
        double ICL = 0.155 * CLO ;
        double TAA = TEQ + 273 ;
        double TRA = TA + 273 ;
        double M = 58.15 * MET ;
        double FCL ;

        if (ICL < 0.078)
                FCL = 1 + 1.29 * ICL ;
        else
                FCL = 1.05 + 0.645 * ICL ;

        double TCLA = TAA + (35.5 - TEQ) / (3.5 * (6.45 * ICL + 0.1)) ;
        double P1 = ICL * FCL ;
        double P2 = P1 * 3.96 ;
        double P3 = P1 * 100 ;
        double P4 = P1 * TAA ;
        double P5 = 308.7 - 0.028 * M + P2 * Math.pow((TRA / 100), 4) ;
        double XN = TCLA / 100 ;
        double XF = XN ;
        double EPS = 0.0015 ;

        double HC = 0;
        for (int N = 1 ; N <= 150 ; N++) {
            XF = (XF + XN) / 2;
            HC = 2.38 * Math.pow(Math.abs(100 * XF - TAA), 0.25);
            XN = (P5 + P4 * HC - P2 * Math.pow(XF, 4)) / (100 + P3 * HC);
        }
        double TCL = 100 * XN - 273 ;

        double HL1 = 3.05 * 0.001 * (5733 - 6.99 * M - PA) ;

        double HL2 ;
        if (M > 58.15)
                HL2 = 0.42 * (M - 58.15) ;
        else
                HL2 = 0 ;

        double HL3 = 1.7 * 0.00001 * M * (5867 - PA) ;

        double HL4 = 0.0014 * M * (34 - TEQ) ;

        double HL5 = 3.96 * FCL * (Math.pow(XN, 4) - Math.pow((TRA / 100), 4) );

        double HL6 = FCL * HC * (TCL - TEQ) ;
        double TS = 0.303 * Math.exp(-0.036 * M) + 0.028 ;
        double PMVval = TS * (M - HL1 - HL2 - HL3 - HL4 - HL5 - HL6) ;
        double PPDval = 100 - 95 * Math.exp(-0.3353 * Math.pow(PMVval, 4) - 0.2179 * Math.pow(PMVval, 2)) ;

        switch (index){
            case 1:
                return PPDval ;
            default:
                return PMVval ;
        }

    }

    @Override
    protected String[][] getConstRowList(String[] list) {
        String[][] rows = new String[400][list.length] ;
        rows[0][0] = "متابولیسم" ;
        rows[1][0] = "کلو لباس" ;
        return rows ;
    }

}
