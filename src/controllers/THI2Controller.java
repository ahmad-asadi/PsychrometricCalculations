package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class THI2Controller extends IndexController {

    public THI2Controller(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت" ;
        model.addRow(row);
        row[0] = "THI2" ;
        model.addRow(row);
        row[0] = "شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
