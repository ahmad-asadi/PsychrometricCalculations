package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class DIController extends IndexController {

    public DIController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{21,25,28,30,32,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بدون ناراحتی","کمتر از پنجاه درصد از جمعیت احساس ناراحتی می‌کنند","بیش از پنجاه درصد از جمعیت احساس ناراحتی می‌کنند","بیشتر جمعیت از ناراحتی رنج می‌برند","همه احساس استرس می‌کنند","وضعیت اضطراری پزشکی"};
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        return (B2-((0.55 - (0.0055*B4))*(B2-14.5))) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت" ;
        model.addRow(row);
        row[0] = "شاخص ضریب ناراحتی انسان" ;
        model.addRow(row);
        row[0] = " شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }
}
