package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class TEKController extends IndexController {

    public TEKController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 3 ;
        indexOfStringField = 4 ;
        bounds = new double[]{18,24,32,44,56,Double.MAX_VALUE} ;
        boundStrings = new String[]{"سرد","خنک","کمی خنک","آسایش","شرجی-داغ","خیلی گرم و مرطوب"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double D2 = inputs[0] ;
        double D4 = inputs[1] ;
        double D6 = 6.112*(Math.pow(10,((7.5*D2)/(273.7+D2))))*(D4/100) ;
        if(index == 0)
            return D6;
        else
            return D2+(1.5*D6) ;
    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "فشار بخار e" ;
        model.addRow(row);
        row[0] = "TEK" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
