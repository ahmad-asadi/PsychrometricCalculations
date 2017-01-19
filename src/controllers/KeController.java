package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class KeController extends IndexController {

    public KeController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{0,51,101,201,401,601,801,1001,1201,1401,2000,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بسیار داغ","داغ","گرم","مطبوع","خنک","خیلی خنک","سرد","خیلی سرد","سرمای آزار دهنده","قسمت برهنه بدن یخ می‌زند","قسمت برهنه بدن ظرف ۶ ثانیه یخ می‌زند","غیر قابل تحمل"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double B4 = inputs[1] ;
        double B2 = inputs[0] ;
        return ((33-B2)*((10*(Math.sqrt(B4)))+10.5-B4)) ;
    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "شاخص برودت KE" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
