package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SSIController extends IndexController {

    public SSIController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای خشک" ;
        model.addRow(row);
        row[0] = "میانگین دمای خشک به فارنهایت" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت به درصد" ;
        model.addRow(row);
        row[0] = "شاخص داغی تابستان" ;
        model.addRow(row);
        row[0] = "اثرات" ;
        model.addRow(row);
        row[0] = "طبقه" ;
        model.addRow(row);
    }

}
