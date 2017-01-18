package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class THI1Controller extends IndexController {

    public THI1Controller(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای خشک" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت به درصد" ;
        model.addRow(row);
        row[0] = "CP" ;
        model.addRow(row);
        row[0] = "شاخص دما-رطوبت THI" ;
        model.addRow(row);
        row[0] = "احساس افراد" ;
        model.addRow(row);
    }

}
