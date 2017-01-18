package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class MahoneyController extends IndexController {

    public MahoneyController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای حداکثر ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین دمای حداقل ماهانه" ;
        model.addRow(row);
        row[0] = "نوسان دمای ماهانه" ;
        model.addRow(row);
        row[0] = "AMT" ;
        model.addRow(row);
        row[0] = "AMR" ;
        model.addRow(row);
    }
}
