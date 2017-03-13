package controllers;

import facilities.ExcelAdapter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EvanzController extends IndexController{

    private JTable table5;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTable table4;
    private JTabbedPane tabbedPane ;

    public EvanzController(){
        super();
        numberOfVars = 4 ;
        numberOfRes = 18 ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(12);
        indexOfBoundStrings.add(13);
        indexOfBoundStrings.add(14);
        indexOfBoundStrings.add(15);
        indexOfBoundStrings.add(16);
        indexOfBoundStrings.add(17);
        indexOfBoundStrings.add(18);

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
    protected String[] getVarList() {
        return new String[]{"میانگین دمای حداکثر","میانگین دمای حداقل","میانگین حداکثر رطوبت نسبی","میانگین حداقل رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"منطقه راحت روز بیشینه مقیاس الف","منطقه راحت روز کمینه مقیاس الف","منطقه راحت روز بیشینه مقیاس ب","منطقه راحت روز کمینه مقیاس ب","منطقه راحت روز، بیشینه مقیاس ج","منطقه راحت روز، بیشینه مقیاس ج","منطقه راحت شب، بیشینه مقیاس الف","منطقه راحت شب کمینه مقیاس الف" , "منطقه راحت شب بیشینه مقیاس ب","منطقه راحت شب کمینه مقیاس ب","منطقه راحت شب بیشینه مقیاس ج","منطقه راحت شب کمینه مقیاس ج","منطقه راحت روز با مقیاس الف","منطقه راحت روز با مقیاس ب","منطقه راحت روز با مقیاس ج","منطقه راحت شب با مقیاس الف","منطقه راحت شب با مقیاس ب","منطقه راحت شب با مقیاس ج","شرایط گرمایی"};
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
    protected String getBoundString(double[] resInput, int i){
        return "ناشناخته" ;
    }

//    @Override
//    protected void setResTable(TableModel resModel){
//        double[][] inputData = getTableData(varTable);
//        TableModel varModel = varTable.getModel() ;
//
//        for (int i = 0 ; i < resModel.getRowCount() ; i ++)
//        {
//            if(varModel.getValueAt(i,0) != null && !varModel.getValueAt(i,0).equals("")) {
//
//                resModel.setValueAt(varModel.getValueAt(i,0),i,0);
//
//                double[] inputs = new double[varModel.getColumnCount() - 1];
//                for (int ri = 0; ri < varModel.getColumnCount() - 1; ri++)
//                    inputs[ri] = inputData[i][ri];
//
//                for (int j = 0; j < resModel.getColumnCount() - 1; j++) {
//                    if (indexOfBoundStrings.contains(new Integer(j))) {
//                        double[] resInputs = new double[resModel.getColumnCount() - 1];
//                        for (int ri = 1; ri < resModel.getColumnCount() - 7; ri++)
//                            resInputs[ri-1] = Double.parseDouble((String) resModel.getValueAt(i,ri));
//                        resModel.setValueAt(getBoundString(resInputs, j-11, i), i, j + 1);
//                    }
//                    else
//                        resModel.setValueAt(Double.toString(computeRes(inputs, j)), i, j + 1);
//                }
//            }
//        }
//
//    }

    protected String getBoundString(double[] resInput, int i, int row) {
        i -= 12 ;
        double maxValue = resInput[i * 2 + 1];
        double minValue = resInput[i*2+2];
        double temper = getCellData(varTable, row, 1) ;
        if(i >= 3)
            temper = getCellData(varTable, row, 2) ;

        if(i < 6) {
            if (temper > maxValue)
                return "گرم";
            else if (temper < minValue)
                return "سرد";
            else
                return "راحت";
        }
        else{
            double dayTemper = getCellData(varTable, row, 1);
            double nightTemper = getCellData(varTable, row, 2);
            double minRH = getCellData(varTable, row,4);
            double maxRH = getCellData(varTable, row,3);
            if((minRH >= 0 && maxRH <= 30 && dayTemper >= 29.5 && dayTemper <= 32.5 && nightTemper >= 27.5 && nightTemper <= 29.5)
                    || (minRH >= 30 && maxRH <= 50 && dayTemper >= 28.5 && dayTemper <= 30.5 && nightTemper >= 26.5 && nightTemper <= 29)
                    || (minRH >= 50 && maxRH <= 70 && dayTemper >= 27.5 && dayTemper <= 29.5 && nightTemper >= 26 && nightTemper <= 28.5)
                    || (minRH >= 70 && maxRH <= 100 && dayTemper >= 26 && dayTemper <= 29 && nightTemper >= 25.5 && nightTemper <= 28)
                    )
                return "محدوده منطقه راحت به ازای جریان هوایی معادل ۱ متر بر ثانیه" ;

            else if((minRH >= 0 && maxRH <= 30 && dayTemper >= 22.5 && dayTemper <= 30 && nightTemper >= 20 && nightTemper <= 27.5)
                    || (minRH >= 30 && maxRH <= 50 && dayTemper >= 22.5 && dayTemper <= 28 && nightTemper >= 20 && nightTemper <= 26.5)
                    || (minRH >= 50 && maxRH <= 70 && dayTemper >= 22.5 && dayTemper <= 27.5 && nightTemper >= 20 && nightTemper <= 26)
                    || (minRH >= 70 && maxRH <= 100 && dayTemper >= 22.5 && dayTemper <= 27 && nightTemper >= 20 && nightTemper <= 25.5)
                    )
                return "محدوده منطقه راحت به ازای لباس سبک تابستانی و روانداز سبک در شب، جریان هوا نامحسوس" ;

            else if((minRH >= 0 && maxRH <= 30 && dayTemper >= 18 && dayTemper <= 22.5 && nightTemper >= 16 && nightTemper <= 20)
                    || (minRH >= 30 && maxRH <= 50 && dayTemper >= 18 && dayTemper <= 22.5 && nightTemper >= 16 && nightTemper <= 20)
                    || (minRH >= 50 && maxRH <= 70 && dayTemper >= 18 && dayTemper <= 22.5 && nightTemper >= 16 && nightTemper <= 20)
                    || (minRH >= 70 && maxRH <= 100 && dayTemper >= 18 && dayTemper <= 22.5 && nightTemper >= 16 && nightTemper <= 20)
                    )
                return "محدوده منطقه راحت به ازای لباس معمولی و گرم و روانداز ضخیم در شب" ;

            else
                return "-" ;
        }


    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double [][] maxVals = new double[][]{
                {32.5,30.5,29.5,29,Double.MAX_VALUE},
                {30,28,27.5,27,Double.MAX_VALUE},
                {22.5,22.5,22.5,22.5,Double.MAX_VALUE},
                {29.5,29,28.5,28,Double.MAX_VALUE},
                {27.5,26.5,26,25.5,Double.MAX_VALUE},
                {20,20,20,20,Double.MAX_VALUE},
        };
        double [][] minVals = new double[][]{
                {29.5,28.5,27.5,26,Double.MAX_VALUE},
                {22.5,22.5,22.5,22.5,Double.MAX_VALUE},
                {18,18,18,18,Double.MAX_VALUE},
                {27.5,26.5,26,25.5,Double.MAX_VALUE},
                {20,20,20,20,Double.MAX_VALUE},
                {16,16,16,16,Double.MAX_VALUE},
        };

        double[][] vals ;
        if(index % 2 == 0)
            vals = maxVals ;
        else
            vals = minVals ;

        return matchColumn(inputs,index,vals) ;
    }

    private Double matchColumn(double[] inputs, int index, double[][] vals) {
        double [] bounds = new double[]{30,50,70,100,Double.MAX_VALUE} ;
        double input;
        if (index < 6 || (index > 11 && index < 18))
            input = inputs[3];
        else
            input = inputs[2] ;
        index %= 12 ;
        for(int i = 0 ; i < bounds.length ; i++)
            if(input < bounds[i])
                return vals[index/2][i] ;
        return Double.MAX_VALUE;
    }

}


/**
 * @author suhas
 *
 */
class CustomCellRenderer extends DefaultTableCellRenderer {

    private Color color ;
    public CustomCellRenderer(Color color)
    {
        this.color = color ;
    }
    /* (non-Javadoc)
     * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        //Set background color
        rendererComp .setBackground(color);

        return rendererComp ;
    }

}

