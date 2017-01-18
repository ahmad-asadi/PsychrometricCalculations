package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WBGTController extends IndexController {

    public WBGTController(){
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
        row[0] = "دمای تر" ;
        model.addRow(row);
        row[0] = "سرعت باد" ;
        model.addRow(row);
        row[0] = "محاسبه دمای کروی" ;
        model.addRow(row);
        row[0] = "WBGT در صورت انرژی مستقیم خورشید" ;
        model.addRow(row);
        row[0] = "WBGT در صورت عدم انرژی مستقیم خورشید" ;
        model.addRow(row);
        row[0] = "احساس حرارتی در تابش مستقیم" ;
        model.addRow(row);
        row[0] = "احساس حرارتی در عدم تابش مستقیم" ;
        model.addRow(row);
    }

}
