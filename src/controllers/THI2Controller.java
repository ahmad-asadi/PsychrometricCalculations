package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class THI2Controller extends IndexController {

    public THI2Controller(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{-40,-20,-10,-1.8,13,15,20,26.5,30,Double.MAX_VALUE} ;
        boundStrings = new String[]{"فوق‌العاده یخ‌بندان","یخ‌بندان","فوق‌العاده سرد","خیلی سرد","سرد","خنک","راحت","گرم","بسیار داغ","سوزان"};
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        return (B2-((0.55-(0.0055*B4))*(B2-14.5))) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت" ;
        model.addRow(row);
        row[0] = "THI2" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
