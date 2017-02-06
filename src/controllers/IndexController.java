package controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public abstract class IndexController extends JPanel {
    protected int numberOfRes;
    protected int numberOfVars;
    protected int numberOfBoundStrings;
    protected double[][] bounds;
    protected String[][] boundStrings;
    private Font headerFont ;
    private Font colFont ;
    protected JTable varTable ;
    protected JTable resTable ;
    protected JTable boundTable ;
    protected JTable constTable ;
    private JScrollPane varJsp ;
    private JScrollPane resJsp ;
    private JScrollPane boundJsp ;
    private JScrollPane constJsp ;

    public IndexController(int width , int height){
        setSize(width, height);
        setLayout(null);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        varTable = new JTable() ;
        resTable = new JTable() ;
        boundTable = new JTable() ;
        varJsp = createJSP(varTable);
        resJsp = createJSP(resTable);
        boundJsp = createJSP(boundTable);

        


        add(varJsp) ;
        add(resJsp) ;
        add(boundJsp) ;


    }

    private JScrollPane createJSP(JTable table) {
        JScrollPane jsp = new JScrollPane(table) ;
        jsp.setSize(table.getSize());
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return jsp ;
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
                if(numberOfRes == 2 && !callIndexedComputeRes)
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

}
