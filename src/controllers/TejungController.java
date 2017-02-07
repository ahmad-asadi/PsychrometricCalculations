//package controllers;
//
//import uiElements.ThermalChartFrame;
//
//import javax.swing.table.DefaultTableModel;
//
///**
// * This class is created by Ahmad Asadi on 1/17/17.
// */
//public class TejungController extends IndexController {
//
//    private double lat = 0 ;
//
//    public TejungController(){
//        super();
//        setCols();
//        numberOfVars = 9 ;
//        numberOfRes = 16 ;
//        indexOfStringField = 21 ;
//        setBoundStrings = true ;
//    }
//
//    @Override
//    protected double computeRes(double[] input , int index){
//        double B4 = input[0] ;
//        double B5 = input[1] ;
//        double B6 = B4/B5 ;
//        double C4 = input[3] ;
//        double R4 = C4*0.514444 ;
//        double C8 = input[4] ;
//        if(lat > 0 )
//            C8 = lat ;
//        else
//            lat = C8 ;
//        double B16 = input[2] ;
//
//
//        double C23 = -23.4*Math.cos(Math.toRadians(((B16+10)*360)/365)) ;
//        double C25 = (((double)24)/360)*(Math.toDegrees(Math.acos((-Math.tan(Math.toRadians(C8)))*Math.tan(Math.toRadians(C23))))) ;
//        double C27 = ((-(double)24)/360)*(Math.toDegrees(Math.acos((-Math.tan(Math.toRadians(C8)))*Math.tan(Math.toRadians(C23))))) ;
//        double C29 = (((double)2)/15)*(Math.toDegrees(Math.acos(((-Math.tan(Math.toRadians(C23)))*Math.tan(Math.toRadians(C8)))))) ;
//        double D3 = input[6] ;
//        double D5 = R4 ;
//        double D7 = -(10.45+(10*(Math.sqrt(D5)))-D5)*(33-D3) ;
//        D3 = input[5] ;
//        double D77 = -(10.45+(10*(Math.sqrt(D5)))-D5)*(33-D3) ;
//        double B9 = B6 * 200 ;
//        double B8 = Math.abs(D7 * C29) ;
//        double B10 = B8 - B9 ;
//        double B11 = - B10 ;
//        double B12 = B11/ C29 ;
//
//
//        switch (index){
//            case 0:
//                return B6 ;
//            case 1:
//                return R4 ;
//            case 2:
//                return C23 ;
//            case 3:
//                return C25 ;
//            case 4:
//                return C27 ;
//            case 5 :
//                return C29;
//            case 6:
//                return D7 ;
//            case 7:
//                return D77 ;
//            case 8:
//                return B9 ;
//            case 9:
//                return B10 ;
//            case 10:
//                return B11 ;
//            case 11:
//                return B12 ;
//            default:
//                return -1 ;
//
//        }
//    }
//
//
//
//    protected void setCols(){
//        super.setCols();
//        String[] row = new String[cols.length];
//        row[0] = "MEAN" ;
//        model.addRow(row);
//        row[0] = "تعداد روزهای ماه" ;
//        model.addRow(row);
//        row[0] = "شماره ژولیوسی روز اول ماه" ;
//        model.addRow(row);
//        row[0] = "میانگین سرعت باد به نات" ;
//        model.addRow(row);
//        row[0] = "عرض جغرافیایی" ;
//        model.addRow(row);
//        row[0] = "میانگین حداقل دما" ;
//        model.addRow(row);
//        row[0] = "میانگین حداکثر دما" ;
//        model.addRow(row);
//        row[0] = "میانگین حداقل رطوبت نسبی" ;
//        model.addRow(row);
//        row[0] = "میانگین حداکثر رطوبت نسبی" ;
//        model.addRow(row);
//
//
//        row[0] = "میانگین ساعت واقعی آفتاب روزانه" ;
//        model.addRow(row);
//        row[0] = "میانگین سرعت باد به متر بر ثانیه" ;
//        model.addRow(row);
//        row[0] = "مدار میل خورشید" ;
//        model.addRow(row);
//        row[0] = "WS(+24)" ;
//        model.addRow(row);
//        row[0] = "WS(-24)" ;
//        model.addRow(row);
//        row[0] = "طول روز" ;
//        model.addRow(row);
//        row[0] = "ضریب تاثیر باد در روز" ;
//        model.addRow(row);
//        row[0] = "میزان دفع انرژی شبانه" ;
//        model.addRow(row);
//        row[0] = "انرژی خورشیدی روزانه" ;
//        model.addRow(row);
//        row[0] = "قدرت خنک‌کنندگی خالص باد در روز" ;
//        model.addRow(row);
//        row[0] = "منفی‌دار کردن پاسخ" ;
//        model.addRow(row);
//        row[0] = "مقدار دفع انرژی روزانه" ;
//        model.addRow(row);
//
//        row[0] = "سمبل روز" ;
//        model.addRow(row);
//        row[0] = "سمبل شب" ;
//        model.addRow(row);
//        row[0] = "احساس غالب در روز" ;
//        model.addRow(row);
//        row[0] = "احساس غالب در شب" ;
//        model.addRow(row);
//    }
//
//    @Override
//    public void solve(){
//        super.solve();
//        double[][] tableData = getTableData() ;
//        double[][] data = new double[2*(cols.length - 1)][2] ;
//        for (int i = 0 ; i < cols.length-1; i++) {
//            data[i][0] = tableData[5][i] ;
//            data[i][1] = tableData[7][i] ;
//        }
//        for (int i = 0; i < cols.length-1; i++) {
//            data[cols.length + i - 1][0] = tableData[6][i] ;
//            data[cols.length + i - 1][1] = tableData[8][i] ;
//        }
//        String[] colNames = new String[2*(cols.length-1)];
//        for (int i = 1; i < cols.length ; i++) {
//            colNames[i-1] = model.getColumnName(i) + " در روز" ;
//        }
//        for (int i = 1; i < cols.length ; i++) {
//            colNames[cols.length + i-2] = model.getColumnName(i) + " در شب";
//        }
//        new ThermalChartFrame("terjung", data, colNames) ;
//    }
//
//    @Override
//    protected void setBoundStrings(double[] res){
//        double[][] tableData = getFullTableData() ;
//        double[] bounds = new double[]{-1400,-1200,-1000,-800,-600,-300,-200,-50,80,120,160,Double.MAX_VALUE} ;
//        String[] boundStrings = new String[]{"H-","G-","F-","E-","D-","C-","B-","A-","N","A","B","C"} ;
//        String[] climateString = new String[]{"گوشت در معرض این دما و باد منجمد می‌شود","فوق‌العاده سرد","بسیار سرد","سرد","بسیار خنک","خنک","مطبوع","گرمای مطبوع","گرم","احساس گرمای پوست بدن","احساس گرمای نامطبوع اضافی","احساس گرمای بسیار نامطبوع اضافی"} ;
//        for(int j = 0 ; j < cols.length - 1 ; j++){
//            for(int i = 0 ; i < bounds.length ; i ++){
//                if(tableData[numberOfRes + numberOfVars - 5][j] < bounds[i]){
//                    model.setValueAt(boundStrings[i] , indexOfStringField , j + 1);
//                    model.setValueAt(climateString[i] , indexOfStringField + 2, j + 1);
//                    break;
//                }
//            }
//            for(int i = 0 ; i < bounds.length ; i ++) {
//                if (tableData[numberOfRes + numberOfVars - 9][j] < bounds[i]) {
//                    model.setValueAt(boundStrings[i], indexOfStringField + 1, j + 1);
//                    model.setValueAt(climateString[i], indexOfStringField + 3, j + 1);
//                    break;
//                }
//            }
//
//        }
//    }
//
//    protected double[][] getFullTableData() {
//        model = (DefaultTableModel) getModel();
//        int nRow = model.getRowCount(), nCol = model.getColumnCount();
//        double[][] tableData = new double[nRow-1][nCol-1];
//        for (int i = 0 ; i < nRow - 1; i++)
//            for (int j = 1 ; j < nCol ; j++) {
//                try {
//                    tableData[i][j - 1] = Double.parseDouble((String) model.getValueAt(i, j));
//                }catch (Exception e)
//                {
//                    tableData[i][j - 1] = 0 ;
//                }
//            }
//        return tableData;
//    }
//
//}
