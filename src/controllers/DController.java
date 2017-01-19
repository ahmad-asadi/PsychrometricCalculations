package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class DController extends IndexController {

    public DController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{1,2.49,2.99,3.49,Double.MAX_VALUE} ;
        boundStrings = new String[]{"شرجی خیلی ضعیف","شرجی ضعیف","شرجی متوسط","شرجی شدید","شرجی بسیار شدید"};
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        return (B4/21.55)-(100/B2)+1.3 ;
    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما به فارنهایت" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "شاخص شدت شرجی D" ;
        model.addRow(row);
        row[0] = "شرجی بودن" ;
        model.addRow(row);
    }

}
