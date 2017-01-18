package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class CIController extends IndexController {

    public CIController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین رطوبت" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "I" ;
        model.addRow(row);
        row[0] = "DI" ;
        model.addRow(row);
        row[0] = "CI" ;
        model.addRow(row);
        row[0] = "ضریب آسایش" ;
        model.addRow(row);
    }
}
