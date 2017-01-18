package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EETController extends IndexController {

    public EETController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "رطوبت" ;
        model.addRow(row);
        row[0] = "EET" ;
        model.addRow(row);
        row[0] = "شرایط محیطی" ;
        model.addRow(row);
    }

}
