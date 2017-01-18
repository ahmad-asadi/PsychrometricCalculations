package controllers;

/**
 * This class is created by Ahmad Asadi on 1/18/17.
 */
public class HygrometricController extends IndexController {

    public HygrometricController(){
        super();
        setCols();
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
