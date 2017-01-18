package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WCIController extends IndexController {

    public WCIController(){
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
        row[0] = "WCI" ;
        model.addRow(row);
        row[0] = "اثرات عمومی روی انسان" ;
        model.addRow(row);
    }

}
