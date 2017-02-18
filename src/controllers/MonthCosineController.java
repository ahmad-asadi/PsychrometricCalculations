package controllers;

import uiElements.CosineFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public abstract class MonthCosineController extends JTable {

    protected double medar ;
    protected double A ;
    protected double B ;

    protected CosineFrame parent;

    protected abstract double getA();
    protected abstract double getB();
    protected abstract double getMedar();
    protected abstract int getMonthNo();

    public MonthCosineController(CosineFrame parent){
        super(new String[49][15],new String[]{"ستون اول","ستون دوم","ستون سوم","ستون چهارم","ستون پنجم","ستون ششم","ستون هفتم","ستون هشتم","ستون نهم","ستون دهم","ستون یازدهم","ستون دوازدهم","ستون سیزدهم","ستون چهاردهم","ستون پانزدهم"});
        this.parent = parent ;
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setSize(parent.getWidth(), parent.getHeight() - 50);
        setLocation(0,0);

        double[] hours = new double[13] ;


        setValueAt("ساعت",0,1);
        for(int i = 6 ; i < 19 ; i++)
            setValueAt(Integer.toString(i), 0,i-4);
        setValueAt("ساعت h",1,1);
        setValueAt("-90",1,2);
        setValueAt("-75",1,3);
        setValueAt("-60",1,4);
        setValueAt("-45",1,5);
        setValueAt("-30",1,6);
        setValueAt("-15",1,7);
        setValueAt("0",1,8);
        setValueAt("-15",1,9);
        setValueAt("-30",1,10);
        setValueAt("-45",1,11);
        setValueAt("-60",1,12);
        setValueAt("-75",1,13);
        setValueAt("-90",1,14);

        setValueAt("قدر مطلق ساعت h",2,1);
        setValueAt("ساعت h",2,1);
        setValueAt("90",2,2);
        setValueAt("75",2,3);
        setValueAt("60",2,4);
        setValueAt("45",2,5);
        setValueAt("30",2,6);
        setValueAt("15",2,7);
        setValueAt("0",2,8);
        setValueAt("15",2,9);
        setValueAt("30",2,10);
        setValueAt("45",2,11);
        setValueAt("60",2,12);
        setValueAt("75",2,13);
        setValueAt("90",2,14);

        setValueAt("ارتفاع خورشید میلادی = sin a",3,1);

        setValueAt("وارون ارتفاع خورشید میلادی",4,1);
        setValueAt("کسینوس ارتفاع خورشید میلادی",5,1);

        setValueAt("جهت تابش یا سمت نسبت به جنوب",6,1);

        setValueAt("ساعت",7,1);
        for(int i = 6 ; i < 19 ; i++)
            setValueAt(Integer.toString(i), 7,i-4);
        setValueAt("زاویه دیوار",8,0);

        setValueAt("0",8,1);
        setValueAt("15",9,1);
        setValueAt("30",10,1);
        setValueAt("45",11,1);
        setValueAt("60",12,1);
        setValueAt("75",13,1);
        setValueAt("90",14,1);
        setValueAt("105",15,1);
        setValueAt("120",16,1);
        setValueAt("135",17,1);
        setValueAt("150",18,1);
        setValueAt("165",19,1);
        setValueAt("180",20,1);

        setValueAt("ساعت / (زاویه دیوار)",21,1);
        for(int i = 6 ; i < 19 ; i++)
            setValueAt(Integer.toString(i), 21,i-4);
        setValueAt("زاویه دیوار",22,0);
        setValueAt("0",22,1);
        setValueAt("15",23,1);
        setValueAt("30",24,1);
        setValueAt("45",25,1);
        setValueAt("60",26,1);
        setValueAt("75",27,1);
        setValueAt("90",28,1);
        setValueAt("105",29,1);
        setValueAt("120",30,1);
        setValueAt("135",31,1);
        setValueAt("150",32,1);
        setValueAt("165",33,1);
        setValueAt("180",34,1);

        setValueAt("ساعت",35,1);
        for(int i = 6 ; i < 19 ; i++)
            setValueAt(Integer.toString(i), 35,i-4);

        setValueAt("زاویه دیوار",36,0);
        setValueAt("0",36,1);
        setValueAt("15",37,1);
        setValueAt("30",38,1);
        setValueAt("45",39,1);
        setValueAt("60",40,1);
        setValueAt("75",41,1);
        setValueAt("90",42,1);
        setValueAt("105",43,1);
        setValueAt("120",44,1);
        setValueAt("135",45,1);
        setValueAt("150",46,1);
        setValueAt("165",47,1);
        setValueAt("180",48,1);

        for(int i = 2 ; i < 15 ; i++)
        {
            double D14 = getCellData(1,i) ;
            double O2 = getMedar() ;
            double E5 = parent.getLat() ;
            double D16 = (((Math.cos(Math.toRadians(E5)))*(Math.cos(Math.toRadians(O2))*(Math.cos(Math.toRadians(D14)))))+((Math.sin(Math.toRadians(E5)))*(Math.sin(Math.toRadians(O2))))) ;
            double D15 = getCellData(2,i) ;
            double D17 = Math.toDegrees(Math.asin(D16)) ;
            double D19 = Math.cos(Math.toRadians(D17)) ;
            double D20 = 180-(Math.toDegrees(Math.asin(((Math.cos(Math.toRadians(O2))*Math.sin(Math.toRadians(D15)))/Math.cos(Math.toRadians(D17)))))) ;
            setValueAt(Double.toString(D16),3,i);
            setValueAt(Double.toString(D17),4,i);
            setValueAt(Double.toString(D19),5,i);
            setValueAt(Double.toString(D20),6,i);
            //////////////////////////////////////////////////
            for(int j = 0 ; j < 13 ; j++) {
                double C22 = getCellData(8 + j, 1);
                double D22 = (D19 * (Math.cos(Math.toRadians(D20 - C22))));
                setValueAt(Double.toString(D22),8+j,i);
                double D37 = D22 < 0 ? 0 : D22 ;
                setValueAt(Double.toString(D37),22+j,i);
            }
            //////////////////////////////////////////////////
            double D57 = D17 ;
            double D58 = (Math.sin(Math.toRadians(D57))) ;
            double D59 = getB()/D58 ;
            double D60 = Math.exp(D59) ;
            double D61 = getA()/D60 ;

            for(int j = 0 ; j < 13 ; j++) {
                double C22 = getCellData(8 + j, 1);
                double D22 = (D19 * (Math.cos(Math.toRadians(D20 - C22))));
                double D37 = D22 < 0 ? 0 : D22 ;
                double D64 = D61*D37 ;
                setValueAt(Double.toString(D64), 36 + j , i);
                hours[j] += D64 ;
            }

        }

        for(int i = 0 ; i < getColumnCount() ; i ++)
            getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer2());

        parent.setMonthSummary(getMonthNo(), hours) ;
        parent.setMedar(getMonthNo(), getMedar());

    }

    protected double getCellData(int row, int col){
        try{
            return Double.parseDouble((String) getValueAt(row, col)) ;
        }catch (Exception e){
            return 0 ;
        }
    }

}


/**
 * @author ahmad
 *
 */
class CustomCellRenderer2 extends DefaultTableCellRenderer {

    /* (non-Javadoc)
     * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        if(row == 0 || row == 7 || row == 21 || row == 35) {//Set background color
            rendererComp.setBackground(new Color(255, 100, 255));
            rendererComp.setForeground(Color.black);
        }
        else {
            rendererComp.setBackground(Color.WHITE);
            rendererComp.setForeground(Color.black);
        }

        return rendererComp ;
    }

}

