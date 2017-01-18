package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class RSIController extends IndexController {

    public RSIController(){
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
        row[0] = "باد" ;
        model.addRow(row);
        row[0] = "فشار بخار به میلی بار e" ;
        model.addRow(row);
        row[0] = "نقطه شبنم" ;
        model.addRow(row);
        row[0] = "شاخص فشار نسبی" ;
        model.addRow(row);
        row[0] = "شخص متوسط" ;
        model.addRow(row);
        row[0] = "شخص سازگار" ;
        model.addRow(row);
        row[0] = "شخص سال‌خورده" ;
        model.addRow(row);
    }
}
