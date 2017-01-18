package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class TejungController extends IndexController {

    public TejungController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "متوسط حداکثر دما به فارنهایت" ;
        model.addRow(row);
        row[0] = "متوسط حداقل رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "سمبل روز" ;
        model.addRow(row);
        row[0] = "متوسط حداقل دما به فارنهایت" ;
        model.addRow(row);
        row[0] = "متوسط حداکثر رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "سمبل شب" ;
        model.addRow(row);
        row[0] = "نسبت روز به شب" ;
        model.addRow(row);
        row[0] = "گروه سمبل نسبت روز به شب" ;
        model.addRow(row);
        row[0] = "ضریب روز" ;
        model.addRow(row);
        row[0] = "احساس غالب روز" ;
        model.addRow(row);
        row[0] = "ضریب شب" ;
        model.addRow(row);
        row[0] = "احساس غالب شب" ;
        model.addRow(row);
    }

}
