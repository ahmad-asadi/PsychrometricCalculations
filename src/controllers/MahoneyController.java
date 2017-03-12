package controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class MahoneyController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public MahoneyController(){
        super();
        numberOfVars = 8 ;
        numberOfRes = 9 ;
        bounds = new double[]{90,105,130,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احتیاط","احتیاط بسیار","خطرناک","بسیار خطرناک"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(6);
        indexOfBoundStrings.add(7);
        indexOfBoundStrings.add(8);

        String[] cols1 = new String[]{"ردیف","بیشینه مقیاس الف","کمینه مقیاس الف","بیشینه مقیاس ب","کمینه مقیاس ب","بیشینه مقیاس ج","کمینه مقیاس ج"} ;
        String[] cols3 = new String[]{"ردیف","مقیاس الف","مقیاس ب","مقیاس ج"} ;
        String[] cols4 = new String[]{"ردیف","شرایط گرمایی"} ;

        table1 = new JTable(new String[400][cols1.length],cols1) ;
        table2 = new JTable(new String[400][cols1.length],cols1) ;
        table3 = new JTable(new String[400][cols3.length],cols3) ;
        table4 = new JTable(new String[400][cols3.length],cols3) ;
        table5 = new JTable(new String[400][cols4.length],cols4) ;


        JScrollPane jsp1 = createNewSubTable(table1);
        JScrollPane jsp2 = createNewSubTable(table2);
        JScrollPane jsp3 = createNewSubTable(table3);
        JScrollPane jsp4 = createNewSubTable(table4);
        JScrollPane jsp5 = createNewSubTable(table5);

        table5.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        tabbedPane = new JTabbedPane() ;

        tabbedPane.setSize(resJsp.getSize());
        tabbedPane.add("محدوده مقیاس‌های روز",jsp1) ;
        tabbedPane.add("محدوده مقیاس‌های شب",jsp2) ;
        tabbedPane.add("منطقه راحت روز",jsp3) ;
        tabbedPane.add("منطقه راحت شب",jsp4) ;
//        tabbedPane.add("شرایط گرمایی",jsp5) ;
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
            for (int i = 0; i < 7; i++) {
                table1.setValueAt(resTable.getValueAt(j, i), j, i);
            }
            table2.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 7; i < 13; i++) {
                table2.setValueAt(resTable.getValueAt(j, i), j, i-6);
            }
            table3.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 13; i < 16; i++) {
                table3.setValueAt(resTable.getValueAt(j, i), j, i-12);
            }
            table4.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 16; i < 19; i++) {
                table4.setValueAt(resTable.getValueAt(j, i), j, i-15);
            }
            table5.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 19; i < 20; i++) {
                table5.setValueAt(resTable.getValueAt(j, i), j, i-18);
            }
        }

    }


    @Override
    protected double computeRes(double[] inputs, int index){
        double B10 = inputs[0] ;
        double B11 = inputs[1] ;
        double B12 = B10 - B11 ;
        double[][] tableData = getTableData(varTable) ;
        double maxMean = 0 ;
        double minMean = 0 ;
        for(int i = 0 ; i < tableData[0].length ; i++) {
            maxMean += tableData[0][i] ;
            minMean += tableData[1][i] ;
        }
        maxMean /= tableData[0].length ;
        minMean /= tableData[0].length ;

        double AMT = (maxMean + minMean)/2 ;
        double AMR = (maxMean - minMean) ;

        double C8 = inputs[2] ;

        double C9 ;
        if(C8 < 30)
            C9 = 1 ;
        else if(C8 < 50)
            C9 = 2 ;
        else if(C8 < 70)
            C9 = 3 ;
        else
            C9 = 4 ;

        double D13 = 24 - C9;
        double D14 ;
        if(C8 < 30)
            D14 = 32 ;
        else if(C8 < 50)
            D14 = 30 ;
        else if(C8 < 70)
            D14 = 28 ;
        else
            D14 = 25 ;

        double D16 = 14 ;
        double D17 = D13 ;
        double D19 = (B10 > D13) ? 1 : ((B10 < D14)? -1 : 0) ;
        double D20 = (B11 > D16) ? 1 : ((B11 < D17)? -1 : 0) ;

        switch (index){
            case 0 :
                return B12;
            case 1 :
                return C9;
            case 2 :
                return D13;
            case 3 :
                return D14;
            case 4 :
                return D16;
            case 5 :
                return D17;
            case 6 :
                return D19;
            case 7 :
                return D20;
            default:
                return 0 ;

        }


    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای حداکثر ماهانه","میانگین دمای حداقل ماهانه","میانگین رطوبت نسبی","میانگین حداکثر رطوبت نسبی","میانگین حداقل رطوبت نسبی","بالاترین فراوانی باد نخستین باد غالب","بالاترین فراوانی دومین اوج جهت باد فرعي","بارندگی ماهیانه"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"نوسان دمای ماهانه","گروه رطوبت","حداکثر آسایش روز","حداقل آسایش روز","حداکثر آسایش شب","حداقل آسایش شب","تنش حرارتی روز","تنش حرارتی شب","مشخصات اقلیمی شاخص"};
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
    protected String getBoundString(double[] resInput, int i) {
        double field = resInput[6] ;
        switch ((int)field){
            case 0:
                return "H" ;
            case 1:
                return "-" ;
            default:
                return "C" ;
        }
    }
}
