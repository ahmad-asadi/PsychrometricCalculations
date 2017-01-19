package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class CIController extends IndexController {

    public CIController(){
        super();
        setCols();
        numberOfVars = 3 ;
        numberOfRes = 4 ;
        indexOfStringField = 6 ;
        bounds = new double[]{-5,-1,1,5,10,15,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خنک با شرایط عدم آسایش","خنک با شرایط عدم آسایش","آسایش","گرم با شرایط آسایش","گرم با شرایط عدم آسایش","شرایط عدم آسایش زیاد","کاملا شرایط عدم آسایش"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){

        double D5 = inputs[0] ;
        double D7 = inputs[1] ;
        double D9 = inputs[2] ;
        double D27 = (0.5+((Math.pow(D5,2))*(Math.pow(10,(-4)))))*(D9-80+(0.11*D5)) ;
        double D28 = ((((-0.35)*(Math.pow(D7,0.5)))*(((20+(0.5*D5)))-(0.2*D9)))-(35*(Math.pow(D7,0.5)))) ;
        if(index == 0)
            return D27 ;
        else if(index == 1)
            return D28 ;
        else
            return D27 - D28 ;

    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین رطوبت" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "I" ;
        model.addRow(row);
        row[0] = "DI" ;
        model.addRow(row);
        row[0] = "CI" ;
        model.addRow(row);
        row[0] = "ضریب آسایش" ;
        model.addRow(row);
    }
}
