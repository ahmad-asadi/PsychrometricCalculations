package controllers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class RSIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;
    private final JTable table1;
    private final JTable table2;
    private final JTabbedPane tabbedPane;

    public RSIController(){
        super();
        numberOfVars = 4 ;
        numberOfRes = 7 ;
        bounds = new double[]{0.1,0.3,0.5,Double.MAX_VALUE} ;
        boundStrings = new String[]{"آسایش","عدم آسایش","اضطراب","ناتوانی"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(3);
        indexOfBoundStrings.add(4);
        indexOfBoundStrings.add(6);


        String[] cols1 = new String[]{"ردیف","فشار بخار آب","شاخص فشار نسبی ۱","شخص سازگار","شخص متوسط","شخص سال‌خورده"} ;
        String[] cols3 = new String[]{"ردیف","شاخص فشار نسبی ۲","شرایط زیست-اقلیمی"} ;

        table1 = new JTable(new String[400][cols1.length],cols1) ;
        table2 = new JTable(new String[400][cols3.length],cols3) ;


        JScrollPane jsp1 = createNewSubTable(table1);
        JScrollPane jsp2 = createNewSubTable(table2);

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tabbedPane = new JTabbedPane() ;

        tabbedPane.setSize(resJsp.getSize());
        tabbedPane.add("شاخص فشار نسبی ۱",jsp1) ;
        tabbedPane.add("شاخص فشار نسبی ۲",jsp2) ;
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
            for (int i = 0; i < 6; i++) {
                table1.setValueAt(resTable.getValueAt(j, i), j, i);
            }
            table2.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 6; i < 8; i++) {
                table2.setValueAt(resTable.getValueAt(j, i), j, i-5);
            }
        }

    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","میانگین رطوبت", "میانگین سرعت باد به نات","دمای نقطه شبنم"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"فشار بخار به میلی بار  e","شاخص فشار نسبی 1","شخص متوسط","شخص سازگار","شخص سال‌خورده","شاخص فشار نسبی 2","شرایط شاخص ۲"};
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
        double[] secondBounds = new double[]{0.3,0.5,1,Double.MAX_VALUE} ;
        double[] thirdBounds = new double[]{0.1,0.3,0.4,Double.MAX_VALUE} ;
        double[] fourthBounds = new double[]{0.2,0.3,0.4,0.5,Double.MAX_VALUE} ;
        String[] fourthBoundString = new String[]{"100 درصد بدون استرس","75 درصد بدون استرس","بدون استرس","75 درصد مضطرب","100 درصد مضطرب" } ;

        switch (i){
            case 2:
                return getBoundString(boundStrings, bounds, resInput[2]) ;
            case 3:
                return getBoundString(boundStrings, secondBounds , resInput[2]) ;
            case 4:
                return getBoundString(boundStrings, thirdBounds, resInput[2]) ;
            case 6:
                return getBoundString(fourthBoundString, fourthBounds, resInput[6]) ;
        }

        return "ناشناخته" ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double B2 = inputs[0] ;
        double Td = inputs[3] ;
        double B7 = 4.58*Math.pow(10,(7.5*Td)/(237.3+Td)) ;
        if(index == 0)
            return B7 ;
        else if (index == 1)
            return (B2 - 21) / (58 - B7) ;
        else
            return (10.7+(0.74*(B2-35)))/(44-B7) ;
    }

}
