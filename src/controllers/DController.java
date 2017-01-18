package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class DController extends IndexController {

    public DController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما به فارنهایت" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "شاخص شدت شرجی D" ;
        model.addRow(row);
        row[0] = "شرجی بودن" ;
        model.addRow(row);
    }

}
