package controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;


/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class IndexController extends JTable {
    protected double[] coeffs ;
    protected int numberOfRes;
    protected int numberOfVars;
    protected int indexOfStringField;
    protected double[] bounds;
    protected String[] boundStrings;
    protected boolean setBoundStrings;
    private Font headerFont ;
    private Font colFont ;
    protected DefaultTableModel model ;
    protected String[] cols = {"نام متغیر", "فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"};

    public IndexController(){
        super(new DefaultTableModel(new Object[]{"نام متغیر", "فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"},0)) ;
        headerFont = new Font("B Titr" , 14, 14) ;
        headerFont = new Font("B Nazanin" , 14, 14) ;
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        getColumnModel().getColumn(0).setWidth(100);
    }

    protected void setCols(){
        model = (DefaultTableModel) getModel();
    }

    public void solve(){
        double[][] tableData = getTableData();
        double[][] res = new double[numberOfRes][cols.length-1];

        for (int resIndex = numberOfVars; resIndex < numberOfRes + numberOfVars ; resIndex ++)
        {
            for(int j = 0 ; j < cols.length - 1; j++) {
                double[] inputs = new double[tableData.length] ;
                for (int i = 0; i < tableData.length; i++) {
                    inputs[i] = tableData[i][j];
                }
                if(numberOfRes == 2)
                    res[resIndex-numberOfVars][j] = computeRes(inputs) ;
                else
                    res[resIndex-numberOfVars][j] = computeRes(inputs,resIndex - numberOfVars) ;

            }
            for(int j = 1 ; j < cols.length ; j++)
                model.setValueAt(Double.toString(res[resIndex-numberOfVars][j-1]),resIndex,j);
        }

        if(setBoundStrings)
            setBoundStrings(res[indexOfStringField - numberOfVars]);
    }

    protected double computeRes(double[] inputs) {
        double res = 0 ;
        for (int i = 0 ; i < numberOfVars ; i++)
            res += coeffs[i] * inputs[i] ;
        return res ;
    }

    protected double computeRes(double[] inputs, int resIndex) {
        return 0;
    }

    protected void setBoundStrings(double[] res) {
        String[] strings = new String[cols.length-1] ;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < bounds.length; j++) {
                if(res[i] <= bounds[j])
                {
                    strings[i] = boundStrings[j] ;
                    break;
                }
            }
        }
        for(int j = 1 ; j < cols.length ; j++)
            model.setValueAt(strings[j-1],indexOfStringField,j);

    }

    protected double[][] getTableData() {
        model = (DefaultTableModel) getModel();
        int nRow = model.getRowCount(), nCol = model.getColumnCount();
        double[][] tableData = new double[nRow-1][nCol-1];
        for (int i = 0 ; i < numberOfVars; i++)
            for (int j = 1 ; j < nCol ; j++) {
                try {
                    tableData[i][j - 1] = Double.parseDouble((String) model.getValueAt(i, j));
                }catch (NumberFormatException e)
                {
                    tableData[i][j - 1] = 0 ;
                }
            }
        return tableData;
    }

}
