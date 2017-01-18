package controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class IndexController extends JTable {
    protected double[] coeffs ;
    protected int numberOfRes;
    protected int numberOfVars;
    private Font headerFont ;
    private Font colFont ;
    protected DefaultTableModel model ;
    protected String[] cols = {"نام متغیر", "فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"};

    public IndexController(){
        super(new DefaultTableModel(new Object[]{"نام متغیر", "فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"},0)) ;
        headerFont = new Font("B Titr" , 14, 14) ;
        headerFont = new Font("B Nazanin" , 14, 14) ;
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    protected void setCols(){
        model = (DefaultTableModel) getModel();
    }

    public void solve(){
        double[][] tableData = getTableData();
        double[][] res = new double[numberOfRes][cols.length-1];
        System.out.printf("table data size: %d , %d\n" ,tableData.length , tableData[0].length);
        System.out.printf("coeffs size: %d \n" ,coeffs.length);
        System.out.printf("numberOfVars: %d \n" ,numberOfVars);
        System.out.printf("numberOfRes: %d \n" ,numberOfRes);

        for (int resIndex = numberOfVars; resIndex < numberOfRes + numberOfVars ; resIndex ++)
        {
            for(int i = 0 ; i < resIndex ; i++)
            {
                for(int j = 0 ; j < cols.length - 1; j++) {
                    res[resIndex-numberOfVars][j] += coeffs[i] * tableData[i][j];
                    System.out.printf("res at %d, %d is %f\n" ,resIndex,j, res[resIndex-numberOfVars][j]);
                }
            }
            for(int j = 1 ; j < cols.length ; j++)
                model.setValueAt(Double.toString(res[resIndex-numberOfVars][j-1]),resIndex,j);
        }

    }

    private double[][] getTableData() {
        model = (DefaultTableModel) getModel();
        int nRow = model.getRowCount(), nCol = model.getColumnCount();
        double[][] tableData = new double[nRow-1][nCol-1];
        for (int i = 0 ; i < numberOfVars; i++)
            for (int j = 1 ; j < nCol ; j++) {
                System.out.printf("data at %d , %d is %s\n", i, j, model.getValueAt(i, j));
                tableData[i][j - 1] = Double.parseDouble((String) model.getValueAt(i, j));
            }
        return tableData;
    }

}
