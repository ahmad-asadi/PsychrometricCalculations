package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SETController extends IndexController {

    public SETController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{-20,-10,1.67,15.5,17.8,22.2,25.6,27.5,30,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بسیار سرد","خیلی سرد","سرد","خیلی خنک","خنک","آسایش","گرم","خیلی گرم","شرجی","خنک"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        return (inputs[0]-(0.4*((inputs[0]-10)*(1-(inputs[1]/100))))) ;
    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "شاخص دمای موثر SET" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
