package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class TSController extends IndexController {
    public TSController(){
        super();
        setCols();
        numberOfRes = 2 ;
        numberOfVars = 3 ;
        indexOfStringField = 4 ;
        bounds = new double[]{1,2,3,4,5,7,Double.MAX_VALUE} ;
        boundStrings = new String[]{"معتدل","شرایط تقریبا سخت","شرایط تا حدی سخت","شرایط سخت","شرایط خیلی سخت","شرایط بشدت سخت","شرایط خیلی سخت"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] input){
        double B2 = input[0] ;
        double B4 = input[1] ;
        double B6 = input[2] ;
        return ((0.0033*B6)+(0.22*B2)-(0.05*B4)-2.3) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای ماهیانه" ;
        model.addRow(row);
        row[0] = "میانگین  سرعت باد" ;
        model.addRow(row);
        row[0] = "میانگین تابش مستقیم بر افق" ;
        model.addRow(row);
        row[0] = "CV" ;
        model.addRow(row);
        row[0] = "شرایط زیست‌-اقلیمی" ;
        model.addRow(row);

    }

}
