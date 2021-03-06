package controllers;

import javax.swing.table.DefaultTableModel;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class ASVController extends IndexController {

    public ASVController(){
        super();
        setCols();
        coeffs = new double[]{0.049, 0.051, 0.001,0.00014,0,0,0,0};
        numberOfRes = 2 ;
        numberOfVars = 4 ;
        indexOfStringField = 5 ;
        bounds = new double[]{1,2,3,4,5,7,Double.MAX_VALUE} ;
        boundStrings = new String[]{"معتدل","شرایط تقریبا سخت","شرایط تا حدی سخت","شرایط سخت","شرایط خیلی سخت","شرایط بشدت سخت","شرایط خیلی سخت"} ;
        setBoundStrings = true ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای ماهیانه" ;
        model.addRow(row);
        row[0] = "میانگین  سرعت باد" ;
        model.addRow(row);
        row[0] = "میانگین تابش مستقیم بر افق" ;
        model.addRow(row);
        row[0] = "رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "ASV" ;
        model.addRow(row);
        row[0] = "شرایط زیست‌-اقلیمی" ;
        model.addRow(row);

    }

}
