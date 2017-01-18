package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class BeckerController extends IndexController {

    public BeckerController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای روزانه" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "CP" ;
        model.addRow(row);
        row[0] = "شرایط محیطی" ;
        model.addRow(row);
        row[0] = "شرایط بیوکلیماتولوژی" ;
        model.addRow(row);
        row[0] = "علائم" ;
        model.addRow(row);
    }

}
