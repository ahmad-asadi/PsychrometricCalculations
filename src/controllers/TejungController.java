package controllers;

import facilities.ExcelAdapter;
import uiElements.ThermalChartFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        numberOfRes = 12 ;
        System.out.println(getResList().length);
        indexOfBoundStrings = new ArrayList<>();
        indexOfBoundStrings.add(1);
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(4);
        indexOfBoundStrings.add(5);
        indexOfBoundStrings.add(12);
        indexOfBoundStrings.add(13);
        resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for(int i = 0 ; i < varTable.getColumnCount() ; i++)
            varTable.getColumnModel().getColumn(i).setPreferredWidth(100);

        constTable.setValueAt("عرض جغرافیایی",0,0);


        String[] cols1 = new String[]{"ردیف","ضریب تاثیر باد در روز", "سمبل" , "احساس غالب"} ;
        String[] cols2 = new String[]{"ردیف","مقدار دفع انرژی در شب","سمبل شب","احساس غالب"} ;
        String[] cols3 = new String[]{"ردیف","طول روز","ضریب تاثیر باد در ساعت","ضریب تاثیر باد در روز","انرژی خورشیدی روزانه","قدرت خنک‌کنندگی خالص باد در روز","مقدار دفع انرژی در روز","سمبل روز","احساس غالب"} ;
        String[] cols4 = new String[]{"ردیف","سمبل شب","احساس غالب در شب","سمبل روز","احساس غالب در روز"} ;

        table1 = new JTable(new String[400][cols1.length],cols1) ;
        table2 = new JTable(new String[400][cols2.length],cols2) ;
        table3 = new JTable(new String[400][cols3.length],cols3) ;
        table4 = new JTable(new String[400][cols4.length],cols4) ;


        JScrollPane jsp1 = createNewSubTable(table1);
        JScrollPane jsp2 = createNewSubTable(table2);
        JScrollPane jsp3 = createNewSubTable(table3);
        JScrollPane jsp4 = createNewSubTable(table4);

        for(int i = 0 ; i < table1.getColumnCount() ; i++)
            table1.getColumnModel().getColumn(i).setPreferredWidth(300);
        for(int i = 0 ; i < table2.getColumnCount() ; i++)
            table2.getColumnModel().getColumn(i).setPreferredWidth(200);
        for(int i = 0 ; i < table3.getColumnCount() ; i++)
            table3.getColumnModel().getColumn(i).setPreferredWidth(200);
        for(int i = 0 ; i < table4.getColumnCount() ; i++)
            table4.getColumnModel().getColumn(i).setPreferredWidth(200);

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

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
        table1.setShowHorizontalLines(true);
        table1.setShowVerticalLines(true);

        JScrollPane jsp1 = new JScrollPane(table1) ;
        jsp1.setSize(table1.getSize());
        jsp1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        ExcelAdapter adapter = new ExcelAdapter(table1) ;
        table1.addMouseListener( new MouseAdapter()
        {
            public void mousePressed(MouseEvent e){
                if (e.isPopupTrigger())
                    doPop(e);
            }

            public void mouseReleased(MouseEvent e){
                if (e.isPopupTrigger())
                    doPop(e);
            }

            private void doPop(MouseEvent e){
                PopUp menu = new PopUp(adapter);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });


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
            for (int i = 0; i < 4; i++) {
                table1.setValueAt(resTable.getValueAt(j, i), j, i);
            }
            table2.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 4; i < 7; i++) {
                table2.setValueAt(resTable.getValueAt(j, i), j, i-3);
            }
            table3.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 7; i < 15; i++) {
                table3.setValueAt(resTable.getValueAt(j, i), j, i-6);
            }
            table4.setValueAt(resTable.getValueAt(j,0),j,0);
            table4.setValueAt(resTable.getValueAt(j, 5), j, 1);
            table4.setValueAt(resTable.getValueAt(j, 6), j, 2);
            table4.setValueAt(resTable.getValueAt(j, 13), j, 3);
            table4.setValueAt(resTable.getValueAt(j, 14), j, 4);
        }

    }


    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین ساعت آفتابی روزانه","شماره ژولیوسی","میانگین سرعت باد به متر بر ثانیه","میانگین حداقل دما","میانگین حداکثر دما"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"ضریب تاثیر باد در روز","سمبل","احساس غالب","مقدار دفع انرژی در شب","سمبل شب","احساس غالب در شب","طول روز","ضریب تاثیر باد در ساعت","ضریب تاثیر باد در روز","انرژی خورشیدی روزانه","قدرت خنک‌کنندگی خالص باد در روز","مقدار دفع انرژی در روز","سمبل روز","احساس غالب در روز"};
    }

    @Override
    protected String[] getConstList() {
        return new String[]{"ردیف","مقدار پارامتر"};
    }

    @Override
    protected boolean hasConstList() {
        return true;
    }

    @Override
    protected double computeRes(double[] input , int index){
        double D3 = input[4] ;
        double D5 = input[2] ;

        double D7 = -(10.45+(10*(Math.sqrt(D5)))-D5)*(33-D3) ;

        D3 = input[3] ;
        double E7 = -(10.45+(10*(Math.sqrt(D5)))-D5)*(33-D3) ;

        double B16 = input[1] ;
        double C8 = getLat();
        double C23 = -23.4*Math.cos(Math.toRadians(((B16+10)*360)/365)) ;
        double B5 = (((double)2)/15)*(Math.toDegrees(Math.acos(((-Math.tan(Math.toRadians(C23)))*Math.tan(Math.toRadians(C8)))))) ;
        double B6 = D7 ;
        double B7 = B6 * B5 ;
        double B8 = Math.abs(B7) ;
        double B4 = input[0] ;
        double B9 = B4 * 200 ;
        double B10 = B8 - B9 ;
        double B11 = -B10 ;
        double B12 = B11/B5 ;

        switch (index){
//            case 0:
//                return B6 ;
            case 0:
                return D7 ;
            case 3:
                return E7 ;
            case 6:
                return B5;
            case 7:
                return B6 ;
            case 8:
                return B7 ;
            case 9:
                return B9 ;
            case 10:
                return B10 ;
            case 11:
                return B12 ;
            default:
                return 0 ;
        }
    }

    private double getLat() {
        return getCellData(constTable, 0,1);
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        double[] bounds1 = {-1400,-1200,-1000,-800,-600,-300,-200,-50,80,120,160,Double.MAX_VALUE} ;
        String[] boundStrings1 = {"-H","-G","-F","-E","-D","-C","-B","-A","N","A","B","C"} ;
        String[] boundStrings2 = {"گوشت در معرض این دما و باد منجمد می‌شود.","فوق‌العاده سرد","بسیار سرد","سرد","بسیار خنک","خنک","مطبوع","گرمای مطبوع","گرم","احساس گرما روی پوست بدن","احساس گرمای نامطبوع اضافی","احساس گرمای بسیار نامطبوع اضافی"} ;

        switch (i){
            case 1:
                return getBoundString(boundStrings1, bounds1,resInput[1]) ;
            case 2:
                return getBoundString(boundStrings2, bounds1,resInput[1]) ;
            case 4:
                return getBoundString(boundStrings1, bounds1,resInput[4]) ;
            case 5:
                return getBoundString(boundStrings2,bounds1,resInput[4]) ;
            case 12:
                return getBoundString(boundStrings1,bounds1,resInput[12]) ;
            case 13:
                return getBoundString(boundStrings2,bounds1,resInput[12]) ;
            default:
                return "ناشناخته" ;
        }
    }


}
