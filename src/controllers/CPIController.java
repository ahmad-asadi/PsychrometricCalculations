package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class CPIController extends IndexController {

    public CPIController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین  سرعت باد" ;
        model.addRow(row);
        row[0] = "CPI" ;
        model.addRow(row);
        row[0] = "شرایط زیست‌-اقلیمی" ;
        model.addRow(row);
    }

}
