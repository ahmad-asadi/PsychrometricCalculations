package controllers;

import uiElements.ThermalChartFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class TejungController extends IndexController {

    private final JTable table1;
    private final JTable table2;
    private final JTable table3;
    private final JTable table4;
    private final JTabbedPane tabbedPane;

    private double lat = 0 ;

    public TejungController(){
        super();
        numberOfVars = 9 ;
        numberOfRes = 15 ;
        System.out.println(getResList().length);
        indexOfBoundStrings = new ArrayList<>();
        indexOfBoundStrings.add(11);
        indexOfBoundStrings.add(12);
        indexOfBoundStrings.add(13);
        indexOfBoundStrings.add(14);
        resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        String[] cols1 = new String[]{"ردیف","ضریب تاثیر باد در روز"} ;
        String[] cols3 = new String[]{"ردیف","محاسبه دفع انرژی در شب","سمبل شب","احساس غالب"} ;
        String[] cols4 = new String[]{"ردیف","محاسبه دفع انرژی در روز","سمبل روز","احساس غالب"} ;
        String[] cols5 = new String[]{"ردیف","سمبل شب","احساس غالب در شب","سمبل روز","احساس غالب در روز"} ;

        table1 = new JTable(new String[400][cols1.length],cols1) ;
        table2 = new JTable(new String[400][cols3.length],cols3) ;
        table3 = new JTable(new String[400][cols4.length],cols4) ;
        table4 = new JTable(new String[400][cols5.length],cols5) ;


        JScrollPane jsp1 = createNewSubTable(table1);
        JScrollPane jsp2 = createNewSubTable(table2);
        JScrollPane jsp3 = createNewSubTable(table3);
        JScrollPane jsp4 = createNewSubTable(table4);


        tabbedPane = new JTabbedPane() ;

        tabbedPane.setSize(resJsp.getSize());
        tabbedPane.add("ضریب تاثیر باد در روز",jsp1) ;
        tabbedPane.add("دفع انرژی در شب",jsp2) ;
        tabbedPane.add("دفع انرژی در روز",jsp3) ;
        tabbedPane.add("نتیجه نهایی",jsp4) ;
        tabbedPane.setLocation(resJsp.getLocation());
        tabbedPane.setBorder(BorderFactory.createBevelBorder(0));
        tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        remove(resJsp);
        add(tabbedPane) ;

    }

    private JScrollPane createNewSubTable(JTable table1) {
        JScrollPane jsp1 = new JScrollPane(table1) ;
        jsp1.setSize(table1.getSize());
        jsp1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        table1.getColumnModel().getColumn(0).setPreferredWidth(100);
        for(int i = 1 ; i < table1.getColumnCount() ; i++)
            table1.getColumnModel().getColumn(i).setPreferredWidth(180);

        table1.setFillsViewportHeight(true);
        return jsp1;
    }

    @Override
    public void solve(){
        super.solve();
        for(int j = 0 ; j < resTable.getRowCount() ; j++) {
            for (int i = 0; i < 2; i++) {
                table1.setValueAt(resTable.getValueAt(j, i), j, i);
            }
            table2.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 2; i < 5; i++) {
                table2.setValueAt(resTable.getValueAt(j, i), j, i-1);
            }
            table3.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 5; i < 8; i++) {
                table3.setValueAt(resTable.getValueAt(j, i), j, i-4);
            }
            table4.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 8; i < 12; i++) {
                table4.setValueAt(resTable.getValueAt(j, i), j, i-7);
            }
        }

    }



    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین ساعت آفتابی روزانه","شماره ژولیوسی","میانگین سرعت باد به نات","میانگین حداقل دما","میانگین حداکثر دما","میانگین حداقل رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"مدار میل خورشید","میانگین سرعت باد به متر بر ثانیه","WS(+24)","WS(-24)","طول روز","ضریب تاثیر باد در روز","میزان دفع انرژی شبانه","انرژی خورشیدی روزانه","قدرت خنک\u200Cکنندگی خالص باد در روز","منفی‌دار کردن پاسخ","میزان دفع انرژی روزانه","سمبل روز","سمبل شب","احساس غالب روز","احساس غالب شب"};
    }

    @Override
    protected String[] getConstList() {
        return new String[]{"عرض جغرافیایی"};
    }

    @Override
    protected boolean hasConstList() {
        return true;
    }

    @Override
    protected double computeRes(double[] input , int index){
        double B6 = input[0] ;
        double C4 = input[2] ;
        double R4 = C4*0.514444 ;
        double C8 = getCellData(constTable,0,0) ;
        if(lat > 0 )
            C8 = lat ;
        else
            lat = C8 ;
        double B16 = input[1] ;


        double C23 = -23.4*Math.cos(Math.toRadians(((B16+10)*360)/365)) ;
        double C25 = (((double)24)/360)*(Math.toDegrees(Math.acos((-Math.tan(Math.toRadians(C8)))*Math.tan(Math.toRadians(C23))))) ;
        double C27 = ((-(double)24)/360)*(Math.toDegrees(Math.acos((-Math.tan(Math.toRadians(C8)))*Math.tan(Math.toRadians(C23))))) ;
        double C29 = (((double)2)/15)*(Math.toDegrees(Math.acos(((-Math.tan(Math.toRadians(C23)))*Math.tan(Math.toRadians(C8)))))) ;
        double D3 = input[4] ;
        double D5 = R4 ;
        double D7 = -(10.45+(10*(Math.sqrt(D5)))-D5)*(33-D3) ;
        D3 = input[4] ;
        double D77 = -(10.45+(10*(Math.sqrt(D5)))-D5)*(33-D3) ;
        double B9 = B6 * 200 ;
        double B8 = Math.abs(D7 * C29) ;
        double B10 = B8 - B9 ;
        double B11 = - B10 ;
        double B12 = B11/ C29 ;


        switch (index){
//            case 0:
//                return B6 ;
            case 0:
                return R4 ;
            case 1:
                return C23 ;
            case 2:
                return C25 ;
            case 3:
                return C27 ;
            case 4 :
                return C29;
            case 5:
                return D7 ;
            case 6:
                return D77 ;
            case 7:
                return B9 ;
            case 8:
                return B10 ;
            case 9:
                return B11 ;
            case 10:
                return B12 ;
            default:
                return -1 ;

        }
    }

    @Override
    protected boolean hasChart(){
        return true;
    }

    @Override
    protected void createChart(){
        double[][] tableData = getTableData(varTable) ;
        double[][] data = new double[2*(varTable.getColumnCount() - 1)][2] ;
        for (int i = 0 ; i < varTable.getColumnCount()-1; i++) {
            data[i][0] = tableData[5][i] ;
            data[i][1] = tableData[7][i] ;
        }
        for (int i = 0; i < varTable.getColumnCount()-1; i++) {
            data[varTable.getColumnCount() + i - 1][0] = tableData[6][i] ;
            data[varTable.getColumnCount() + i - 1][1] = tableData[8][i] ;
        }
        String[] colNames = new String[2*(varTable.getColumnCount()-1)];
        for (int i = 1; i < varTable.getColumnCount() ; i++) {
            colNames[i-1] = varTable.getColumnName(i) + " در روز" ;
        }
        for (int i = 1; i < varTable.getColumnCount() ; i++) {
            colNames[varTable.getColumnCount() + i-2] = varTable.getColumnName(i) + " در شب";
        }
        new ThermalChartFrame("terjung", data, colNames) ;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        double[] bounds = new double[]{-1400,-1200,-1000,-800,-600,-300,-200,-50,80,120,160,Double.MAX_VALUE} ;
        String[] boundStrings = new String[]{"H-","G-","F-","E-","D-","C-","B-","A-","N","A","B","C"} ;
        String[] climateString = new String[]{"گوشت در معرض این دما و باد منجمد می‌شود","فوق‌العاده سرد","بسیار سرد","سرد","بسیار خنک","خنک","مطبوع","گرمای مطبوع","گرم","احساس گرمای پوست بدن","احساس گرمای نامطبوع اضافی","احساس گرمای بسیار نامطبوع اضافی"} ;

        switch (i%2){
            case 0:
                return getBoundString(boundStrings,bounds,resInput[i]) ;
            case 1:
                return getBoundString(climateString,bounds,resInput[i]) ;
        }

        return "ناشناخته";
    }


}
