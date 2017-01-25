package controllers;

import javax.swing.table.DefaultTableModel;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EvanzController extends IndexController{

    public EvanzController(){
        super();
        setCols();
        numberOfVars = 4 ;
        numberOfRes = 18 ;
        indexOfStringField = 16 ;
        setBoundStrings = true ;
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

    @Override
    protected void setBoundStrings(double[] res)
    {
        model = (DefaultTableModel) getModel();
        for(int stringIndex = 0 ; stringIndex < 6 ; stringIndex++){

            for(int j = 1 ; j < cols.length ; j++) {
                double maxValue ;
                try {
                    maxValue = Double.parseDouble((String) model.getValueAt(numberOfVars + stringIndex * 2, j));
                }catch (Exception e)
                {
                    maxValue = 0 ;
                }
                double minValue ;
                try {
                    minValue = Double.parseDouble((String) model.getValueAt(numberOfVars + stringIndex * 2 + 1, j));
                }catch (Exception e)
                {
                    minValue = 0 ;
                }
                int inputIndex = 0 ;
                if(stringIndex < 3)
                    inputIndex = 1 ;
                double temper ;
                try {
                    temper = Double.parseDouble((String) model.getValueAt(inputIndex, j));
                }catch (Exception e)
                {
                    temper = 0 ;
                }
                if(temper > maxValue)
                    model.setValueAt("گرم" , stringIndex + indexOfStringField , j);
                else if(temper < minValue)
                    model.setValueAt("سرد" , stringIndex + indexOfStringField , j);
                else
                    model.setValueAt("راحت" , stringIndex + indexOfStringField , j);
            }
        }
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

        row[0] = "بیشینه مقیاس الف منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "کمینه مقیاس الف منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "بیشینه مقیاس ب منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "کمینه مقیاس ب منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "بیشینه مقیاس ج منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "کمینه مقیاس ج منطقه راحت روز" ;
        model.addRow(row);

        row[0] = "بیشینه مقیاس الف منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "کمینه مقیاس الف منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "بیشینه مقیاس ب منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "کمینه مقیاس ب منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "بیشینه مقیاس ج منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "کمینه مقیاس ج منطقه راحت شب" ;
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
