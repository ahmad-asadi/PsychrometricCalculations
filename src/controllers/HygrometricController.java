package controllers;

/**
 * This class is created by Ahmad Asadi on 1/18/17.
 */
public class HygrometricController extends IndexController {

    public HygrometricController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{60,75,80,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احساس سرما","احساس آسایش و راحتی حرارتی","پنجاه درصد افراد به دلیل گرما از ناراحتی رنج می‌برند","صد در صد افراد به دلیل گرما از ناراحتی رنج می‌برند"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double B4 = inputs[1] ;
        double B2 = inputs[0] ;
        return (((0.99*B4)+(0.36*B2))+41.5) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای نقطه شبنم" ;
        model.addRow(row);
        row[0] = "میانگین دمای خشک" ;
        model.addRow(row);
        row[0] = "ترموهیگرومتریک DI" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }
}
