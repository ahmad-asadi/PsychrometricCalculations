package controllers;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.geom.Arc2D;


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
        varTable = new JTable(new String[0][0], getVarList()) ;
        resTable = new JTable(new String[0][0], getResList()) ;
        boundTable = new JTable(new String[0][0], getBoundStringsList()) ;
        varJsp = createJSP(varTable);
        resJsp = createJSP(resTable);
        boundJsp = createJSP(boundTable);
        if(hasConstList()) {
            constTable = new JTable(new String[0][0], getConstList());
            constJsp = createJSP(boundTable);
        }




        add(varJsp) ;
        add(resJsp) ;
        add(boundJsp) ;


    }

    protected abstract  String[] getVarList();
    protected abstract  String[] getResList();
    protected abstract  String[] getConstList();
    protected abstract  String[] getBoundStringsList();
    protected abstract  boolean hasConstList();
    protected abstract  boolean hasBoundStrings();

    private JScrollPane createJSP(JTable table) {
        JScrollPane jsp = new JScrollPane(table) ;
        jsp.setSize(table.getSize());
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return jsp ;
    }

    public void solve(){
        TableModel resModel = resTable.getModel() ;

        setResTable(resModel);

        if(hasBoundStrings())
        {
            setBoundStrings(resModel);
        }
    }

    private void setResTable(TableModel resModel) {
        double[][] inputData = getTableData(varTable);
        TableModel varModel = varTable.getModel() ;

        for (int i = 0 ; i < resModel.getRowCount() ; i ++)
        {
            double[] inputs = new double[varModel.getColumnCount() - 1] ;
            for(int ri = 0 ; ri < varModel.getColumnCount() - 1 ; ri ++)
                inputs[ri] = inputData[i][ri] ;

            for(int j = 0 ; j < resModel.getColumnCount() - 1 ; j++) {
                resModel.setValueAt(Double.toString(computeRes(inputs, j)) , i , j+1);
            }
        }
    }

    private void setBoundStrings(TableModel resModel) {
        TableModel boundModel = boundTable.getModel() ;
        for(int i = 0 ; i < boundModel.getRowCount() ; i++){
            double[] resInput = new double[resModel.getColumnCount() - 1] ;
            for(int j = 0 ; j < resInput.length ; j++)
                resInput[j] = Double.parseDouble((String)resModel.getValueAt(i,j+1)) ;

            for(int j = 0 ; j < boundModel.getColumnCount() - 1 ; j++){
                boundModel.setValueAt(getBoundString(resInput, i),i,j+1);
            }
        }
    }

    protected abstract String getBoundString(double[] resInput, int i);

    private double[][] getTableData(JTable table) {
        TableModel model = table.getModel() ;

        double[][] data = new double[model.getRowCount()][model.getColumnCount() - 1] ;
        for(int i = 0 ; i < data.length ; i++){
            for (int j = 1 ; j < data[i].length ; j++)
                data[i][j-1] = Double.parseDouble((String)model.getValueAt(i,j)) ;
        }

        return data ;
    }

    protected abstract double computeRes(double[] inputs, int resIndex);


}
