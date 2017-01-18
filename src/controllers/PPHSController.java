package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PPHSController extends IndexController {

    public PPHSController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "TEK" ;
        model.addRow(row);
        row[0] = "شرایط زیست‌-اقلیمی" ;
        model.addRow(row);
        row[0] = "Humidex 2" ;
        model.addRow(row);
        row[0] = "سطح خطر" ;
        model.addRow(row);
        row[0] = "علائم" ;
        model.addRow(row);
    }

}
