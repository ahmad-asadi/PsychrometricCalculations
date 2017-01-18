package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HK0Controller extends IndexController {

    public HK0Controller(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "شاخص سرماباد H" ;
        model.addRow(row);
        row[0] = " H شرایط زیست-اقلیمی" ;
        model.addRow(row);
        row[0] = "سمبل" ;
        model.addRow(row);
        row[0] = "احساس غالب" ;
        model.addRow(row);
        row[0] = "میزان دفع انرژی" ;
        model.addRow(row);
        row[0] = "شاخص سرماباد K0" ;
        model.addRow(row);
        row[0] = "K0 شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
