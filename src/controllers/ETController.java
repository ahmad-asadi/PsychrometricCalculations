package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class ETController extends IndexController {
    public ETController(){
        super();
        setCols();
        numberOfRes = 2 ;
        numberOfVars = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{27,32,41,45,Double.MAX_VALUE} ;
        boundStrings = new String[]{"false","وضعیت گرم - احتیاط ضروری است","وضعیت داغ - به شدت احتیاط لازم است","وضعیت بسیار داغ - خطر جدی است","وضعیت به شدت خطرناک"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] input){
        double D2 = input[0] ;
        double D4 = input[1] ;
        return (0.4*(D2+D4))+4.8 ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "دمای تر" ;
        model.addRow(row);
        row[0] = "شاخص ET" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);

    }

}
