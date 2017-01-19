package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class THI1Controller extends IndexController {

    public THI1Controller(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{50,60,70,75,80,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احساس سرما","نسبتا سرد","راحت","نسبتا راحت","ناراحت","بسیار ناراحت"};
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        return (B2-((0.55-(0.55*B4))*(B2-58))) ;
    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای خشک" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت به درصد" ;
        model.addRow(row);
        row[0] = "شاخص دما-رطوبت THI" ;
        model.addRow(row);
        row[0] = "احساس افراد" ;
        model.addRow(row);
    }

}
