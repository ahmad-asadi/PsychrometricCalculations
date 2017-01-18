package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PenwardenController extends IndexController {

    public PenwardenController(){
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
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "VG" ;
        model.addRow(row);
    }
}
