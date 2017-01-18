package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HIController extends IndexController {

    public HIController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما به فارنهایت" ;
        model.addRow(row);
        row[0] = "رطوبت" ;
        model.addRow(row);
        row[0] = "نقطه شبنم" ;
        model.addRow(row);
        row[0] = "HI" ;
        model.addRow(row);
        row[0] = "طبقه خطر" ;
        model.addRow(row);
        row[0] = "حساسیت حرارتی" ;
        model.addRow(row);
    }

}
