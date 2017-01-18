package controllers;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EvanzController extends IndexController{

    public EvanzController(){
        super();
        setCols();
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای حداقل" ;
        model.addRow(row);
        row[0] = "میانگین دمای حداکثر" ;
        model.addRow(row);
        row[0] = "میانگین حداکثر رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "میانگین حداقل رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "مقیاس الف منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس الف منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "میانگین حداکثر رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "میانگین حداقل رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "مقیاس الف منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت روز" ;
        model.addRow(row);
        row[0] = "مقیاس الف منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ب منطقه راحت شب" ;
        model.addRow(row);
        row[0] = "مقیاس ج منطقه راحت شب" ;
        model.addRow(row);
    }

}
