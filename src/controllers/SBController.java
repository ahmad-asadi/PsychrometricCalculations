package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SBController extends IndexController {

    public SBController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{1,2,3,4,5,7,Double.MAX_VALUE} ;
        boundStrings = new String[]{"معتدل","شرایط تقریبا سخت","شرایط تا حدی سخت","شرایط سخت","شرایط خیلی سخت","شرایط بشدت سخت","شرایط خیلی سخت"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        return (1-(0.04*inputs[0]))*((1+(0.272*inputs[1]))) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "SB" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
