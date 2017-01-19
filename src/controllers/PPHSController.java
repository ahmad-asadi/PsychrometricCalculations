package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PPHSController extends IndexController {

    public PPHSController(){
        super();
        setCols();
        numberOfVars = 1 ;
        numberOfRes = 2 ;
        indexOfStringField = 2 ;
        bounds = new double[]{0,0.25,0.75,Double.MAX_VALUE} ;
        boundStrings = new String[]{"تعریف نشده","فشار حرارتی بالا","فشار حرارتی متوسط","فشار حرارتی متوسط"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        return Math.pow((2.12513 - 0.058018 *inputs[0]),2) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "TEK" ;
        model.addRow(row);
        row[0] = "شرایط زیست‌-اقلیمی" ;
        model.addRow(row);
    }

}
