package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SETController extends IndexController {

    public SETController(){
        super();
        setCols();
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
    }

}
