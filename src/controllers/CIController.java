package controllers;

import facilities.ExcelAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class CIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    private final JTable table1;
    private final JTable table2;
    private final JTabbedPane tabbedPane;

    public CIController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 4 ;
        bounds = new double[]{-5,-1,1,5,10,15,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خنک با شرایط عدم آسایش","خنک با شرایط عدم آسایش","آسایش","گرم با شرایط آسایش","گرم با شرایط عدم آسایش","شرایط عدم آسایش زیاد","کاملا شرایط عدم آسایش"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(3);


        String[] cols1 = new String[]{"ردیف" , "شاخص H1" ,"شاخص H2" , "شرایط زیست‌-اقلیمی شاخص H1" , "شرایط زیست‌-اقلیمی شاخص H2"} ;
        String[] cols2 = new String[]{"ردیف" , "شاخص CI" , "شرایط زیست‌-اقلیمی"} ;
        table1 = new JTable(new String[400][cols1.length],cols1) ;
        table2 = new JTable(new String[400][cols1.length],cols2) ;



        JScrollPane jsp1 = createNewSubTable(table1);
        JScrollPane jsp2 = createNewSubTable(table2);

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN) ;
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN) ;

        tabbedPane = new JTabbedPane() ;

        tabbedPane.setSize(resJsp.getSize());
        tabbedPane.add("دمای بیش‌تر از ۲۰ درجه",jsp1) ;
        tabbedPane.add("دمای کم‌تر از ۲۰ درجه",jsp2) ;
//        tabbedPane.add("تست",jsp2) ;
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
        table1.setShowHorizontalLines(true);
        table1.setShowVerticalLines(true);


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


        return jsp1;
    }

    @Override
    public void solve(){
        super.solve();
        for(int j = 0 ; j < resTable.getRowCount() ; j++) {
            if(resTable.getValueAt(j,0) == null || resTable.getValueAt(j,0).equals("") || resTable.getValueAt(j,0).equals(" "))
                continue;
            table1.setValueAt(resTable.getValueAt(j,0),j,0);
            table2.setValueAt(resTable.getValueAt(j,0),j,0);
            if(getCellData(varTable,j,3) < 20){
                table1.setValueAt(resTable.getValueAt(j,1),j,1);
                table1.setValueAt(resTable.getValueAt(j,2),j,2);
                table1.setValueAt(resTable.getValueAt(j,3),j,3);
                table1.setValueAt(resTable.getValueAt(j,4),j,4);
                table2.setValueAt("false",j,1);
                table2.setValueAt("false",j,2);
            }
            else
            {
                table2.setValueAt(resTable.getValueAt(j,1),j,1);
                table2.setValueAt(resTable.getValueAt(j,3),j,2);
                table1.setValueAt("false",j,1);
                table1.setValueAt("false",j,2);
                table1.setValueAt("false",j,3);
                table1.setValueAt("false",j,4);
            }
        }

    }


    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین رطوبت","میانگین سرعت باد به متر بر ثانیه","میانگین دما"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"CI","H2" , "ضریب آسایش", "ضریب آسایش 2"};
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
    protected String getBoundString(double[] resInput, int i, int r) {
        double temper = getCellData(varTable, r,3) ;
        double[] bounds2 = new double[]{540,790,1000,1200,1440,Double.MAX_VALUE} ;
        String[] boundStrings2 = new String[]{"خنک","خیلی خنک","سرد","خیلی سرد","سرمای گزنده","سطح پوست به سرعت یخ می‌زند"} ;
        if(temper > 20)
            return getBoundString(boundStrings, bounds, resInput[i]);
        else
            return getBoundString(boundStrings2, bounds2, resInput[i-1]) ;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return null;
    }

    @Override
    protected double computeRes(double[] inputs, int index){

        double D5 = inputs[0] ;
        double D7 = inputs[1] * 1.151 ;
        double D9 = inputs[2] ;
        double D27 = (0.5+((Math.pow(D5,2))*(Math.pow(10,(-4)))))*(D9-80+(0.11*D5)) ;
        double D28 = ((((-0.35)*(Math.pow(D7,0.5)))*(((20+(0.5*D5)))-(0.2*D9)))-(35*(Math.pow(D7,0.5)))) ;

        if(inputs[2] > 20)
            return D27 - D28 ;
        else {
            if(index == 1)
                return 0.57* Math.pow(D7,0.42) *(36.5-D9) * 36 ;
            else
                return (10.9*Math.pow(D7,0.5)+9-D7)*(33-D9) ;
        }

    }
}
