package controllers;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EvanzController extends IndexController{

    public EvanzController(){
        super();
        setCols();
        numberOfVars = 4 ;
        numberOfRes = 12 ;
        indexOfStringField = 10 ;
        bounds = new double[]{90,105,130,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احتیاط","احتیاط بسیار","خطرناک","بسیار خطرناک"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double [] bounds = new double[]{30,50,70,100,Double.MAX_VALUE} ;
        double [][] vals = new double[][]{
                {32.5,30.5,29.5,29,Double.MAX_VALUE},
                {30,28,27.5,27,Double.MAX_VALUE},
                {22.5,22.5,22.5,22.5,Double.MAX_VALUE},
                {29.5,29,28.5,28,Double.MAX_VALUE},
                {27.5,26.5,26,25.5,Double.MAX_VALUE},
                {20,20,20,20,Double.MAX_VALUE},
        };
        double input ;

        if(index < 3 || (index >= 6 && index < 9))
            input = inputs[3] ;
        else
            input = inputs[2] ;

        for(int i = 0 ; i < bounds.length ; i++)
            if(input < bounds[i])
                return vals[index%6][i] ;
        return  Double.MAX_VALUE ;
    }

    @Override
    protected void setBoundStrings(double[] res)
    {

    }

    @Override
    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای حداقل" ;
        model.addRow(row);
        row[0] = "میانگین دمای حداکثر" ;
        model.addRow(row);
        row[0] = "میانگین حداکثر رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "میانگین حداقل رطوبت نسبی" ;
        model.addRow(row);

        row[0] = "مقیاس الف منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس الف منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت شب" ;
        model.addRow(row);

        row[0] = "مقیاس الف منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس الف منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت شب" ;
        model.addRow(row);
    }

}
