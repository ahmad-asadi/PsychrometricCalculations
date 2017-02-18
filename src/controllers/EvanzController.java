package controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EvanzController extends IndexController{

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
        Color color1 = new Color(200,100,200) ;
        Color color2 = new Color(100,200,200) ;
        Color color3 = new Color(200,200,100) ;
        resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resTable.getColumnModel().getColumn(1).setCellRenderer(new CustomCellRenderer(color1));
        resTable.getColumnModel().getColumn(2).setCellRenderer(new CustomCellRenderer(color1));
        resTable.getColumnModel().getColumn(3).setCellRenderer(new CustomCellRenderer(color1));
        resTable.getColumnModel().getColumn(4).setCellRenderer(new CustomCellRenderer(color1));
        resTable.getColumnModel().getColumn(5).setCellRenderer(new CustomCellRenderer(color1));
        resTable.getColumnModel().getColumn(6).setCellRenderer(new CustomCellRenderer(color1));
        resTable.getColumnModel().getColumn(7).setCellRenderer(new CustomCellRenderer(color2));
        resTable.getColumnModel().getColumn(8).setCellRenderer(new CustomCellRenderer(color2));
        resTable.getColumnModel().getColumn(9).setCellRenderer(new CustomCellRenderer(color2));
        resTable.getColumnModel().getColumn(10).setCellRenderer(new CustomCellRenderer(color2));
        resTable.getColumnModel().getColumn(11).setCellRenderer(new CustomCellRenderer(color2));
        resTable.getColumnModel().getColumn(12).setCellRenderer(new CustomCellRenderer(color2));
        resTable.getColumnModel().getColumn(13).setCellRenderer(new CustomCellRenderer(color3));
        resTable.getColumnModel().getColumn(14).setCellRenderer(new CustomCellRenderer(color3));
        resTable.getColumnModel().getColumn(15).setCellRenderer(new CustomCellRenderer(color3));
        resTable.getColumnModel().getColumn(16).setCellRenderer(new CustomCellRenderer(color3));
        resTable.getColumnModel().getColumn(17).setCellRenderer(new CustomCellRenderer(color3));
        resTable.getColumnModel().getColumn(18).setCellRenderer(new CustomCellRenderer(color3));

    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای حداقل","میانگین دمای حداکثر","میانگین حداقل رطوبت نسبی","میانگین حداکثر رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"منطقه راحت روز بیشینه مقیاس الف","منطقه راحت روز کمینه مقیاس الف","منطقه راحت روز بیشینه مقیاس ب","منطقه راحت روز کمینه مقیاس ب","منطقه راحت روز، بیشینه مقیاس ج","منطقه راحت روز، بیشینه مقیاس ج","منطقه راحت شب، بیشینه مقیاس الف","منطقه راحت شب کمینه مقیاس الف" , "منطقه راحت شب بیشینه مقیاس ب","منطقه راحت شب کمینه مقیاس ب","منطقه راحت شب بیشینه مقیاس ج","منطقه راحت شب کمینه مقیاس ج","منطقه راحت روز با مقیاس الف","منطقه راحت روز با مقیاس ب","منطقه راحت روز با مقیاس ج","منطقه راحت شب با مقیاس الف","منطقه راحت شب با مقیاس ب","منطقه راحت شب با مقیاس ج"};
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

    @Override
    protected void setResTable(TableModel resModel){
        double[][] inputData = getTableData(varTable);
        TableModel varModel = varTable.getModel() ;

        for (int i = 0 ; i < resModel.getRowCount() ; i ++)
        {
            if(varModel.getValueAt(i,0) != null && !varModel.getValueAt(i,0).equals("")) {

                resModel.setValueAt(varModel.getValueAt(i,0),i,0);

                double[] inputs = new double[varModel.getColumnCount() - 1];
                for (int ri = 0; ri < varModel.getColumnCount() - 1; ri++)
                    inputs[ri] = inputData[i][ri];

                for (int j = 0; j < resModel.getColumnCount() - 1; j++) {
                    if (indexOfBoundStrings.contains(new Integer(j))) {
                        double[] resInputs = new double[resModel.getColumnCount() - 1];
                        for (int ri = 1; ri < resModel.getColumnCount() - 7; ri++)
                            resInputs[ri-1] = Double.parseDouble((String) resModel.getValueAt(i,ri));
                        resModel.setValueAt(getBoundString(resInputs, j-11, i), i, j + 1);
                    }
                    else
                        resModel.setValueAt(Double.toString(computeRes(inputs, j)), i, j + 1);
                }
            }
        }

    }


    protected String getBoundString(double[] resInput, int i, int row) {
        double maxValue = resInput[i * 2];
        double minValue = resInput[i*2+1];
        double temper = getCellData(varTable, row, 1-(i%2)) ;
        if(temper > maxValue)
            return "گرم" ;
        else if(temper < minValue)
            return "سرد" ;
        else
            return "راحت" ;
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

