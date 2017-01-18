package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class OlgyayController extends IndexController {

    public OlgyayController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین حداکثر دما" ;
        model.addRow(row);
        row[0] = "میانگین حداقل دما" ;
        model.addRow(row);
        row[0] = "میانگین حداقل رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "میانگین حداکثر رطوبت نسبی" ;
        model.addRow(row);
    }
}
