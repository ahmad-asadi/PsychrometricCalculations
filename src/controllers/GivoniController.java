//package controllers;
//
//import uiElements.ThermalChartFrame;
//
//import javax.swing.table.DefaultTableModel;
//
///**
// * This class is created by Ahmad Asadi on 1/17/17.
// */
//public class GivoniController extends IndexController {
//
//    public GivoniController(){
//        super();
//        numberOfVars = 4 ;
//        numberOfRes = 0 ;
//        setCols();
//    }
//
//    @Override
//    public void solve(){
//        model = (DefaultTableModel) getModel();
//        double[][] tableData = getTableData() ;
//        double[][] data = new double[2*(cols.length - 1)][2] ;
//        for (int i = 0 ; i < cols.length-1; i++) {
//            data[i][0] = tableData[0][i] ;
//            data[i][1] = tableData[2][i] ;
//        }
//        for (int i = 0; i < cols.length-1; i++) {
//            data[cols.length + i - 1][0] = tableData[1][i] ;
//            data[cols.length + i - 1][1] = tableData[3][i] ;
//        }
//        String[] colNames = new String[2*(cols.length-1)];
//        for (int i = 1; i < cols.length ; i++) {
//            colNames[i-1] = model.getColumnName(i) + " در روز" ;
//        }
//        for (int i = 1; i < cols.length ; i++) {
//            colNames[cols.length + i-2] = model.getColumnName(i) + " در شب";
//        }
//        new ThermalChartFrame("givoni", data,colNames) ;
//    }
//
//    protected void setCols(){
//        super.setCols();
//        String[] row = new String[cols.length];
//        row[0] = "میانگین حداکثر دما" ;
//        model.addRow(row);
//        row[0] = "میانگین حداقل دما" ;
//        model.addRow(row);
//        row[0] = "شاخص حداکثر رطوبت نسبی" ;
//        model.addRow(row);
//        row[0] = "میانگین حداقل رطوبت نسبی" ;
//        model.addRow(row);
//    }
//}
