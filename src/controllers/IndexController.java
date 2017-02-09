package controllers;

import facilities.ExcelAdapter;
import facilities.SuperTable;
import uiElements.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;


/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public abstract class IndexController extends JPanel {

    private JLabel varLabel;
    private JLabel resLabel;
    private JLabel constLabel;

    protected int numberOfRes;
    protected int numberOfVars;

    protected JTable varTable ;
    protected JTable resTable ;
    protected SuperTable constTable ;
    private JScrollPane varJsp ;
    private JScrollPane resJsp ;
    private JScrollPane constJsp ;
    protected ArrayList<Integer> indexOfBoundStrings ;

    public IndexController(){
        setLayout(null);
        setBorder(BorderFactory.createSoftBevelBorder(0));
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        if(!hasConstList())
            varLabel = createTableLabel("ورودی‌های مورد نیاز" , MainFrame.graphPanelWidth  / 2 + 20 , 20);
        else
            varLabel = createTableLabel("ورودی‌های مورد نیاز" , MainFrame.graphPanelWidth  * 2 / 3 + 20 , 20);

        varTable = new JTable(getInitRowList(getFinalVarList()), getFinalVarList()) ;

        setCellRenderer(varTable);

        resLabel = createTableLabel("نتایج و شرایط زیست-اقلیمی" ,  20 , 20);
        resTable = new JTable(getInitRowList(getFinalResList()) , getFinalResList()) ;

        setCellRenderer(resTable);

        varJsp = createJSP(varTable,varLabel);
        resJsp = createJSP(resTable,resLabel);
        if(hasConstList()) {
            createConstTable();
        }


        ExcelAdapter varAdapter = new ExcelAdapter(varTable) ;
        ExcelAdapter resAdapter = new ExcelAdapter(resTable) ;




//        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        add(varLabel) ;
        add(varJsp) ;
        add(resLabel) ;
        add(resJsp) ;
//        add(boundLabel) ;
//        add(boundJsp) ;


    }

    private void createConstTable() {
        constLabel = createTableLabel("پارامترهای عمومی و ثوابت" , MainFrame.graphPanelWidth / 3 + 20 , 20);
        constTable = new SuperTable(getConstRowList(getFinalConstList()) , getFinalConstList());
        constJsp = createJSP(constTable,constLabel);
        add(constLabel) ;
        add(constJsp) ;
    }

    protected String[][] getConstRowList(String[] list) {
        String[][] rows =  new String[400][list.length];

        return rows;
    }

    private void setCellRenderer(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private String[][] getInitRowList(String[] list) {
        String[][] rows =  new String[400][list.length];
        rows[0][0] = "فروردین" ;
        rows[1][0] = "اردیبهشت" ;
        rows[2][0] = "خرداد" ;

        rows[3][0] = "تیر" ;
        rows[4][0] = "مرداد" ;
        rows[5][0] = "شهریور" ;

        rows[6][0] = "مهر" ;
        rows[7][0] = "آبان" ;
        rows[8][0] = "آذر" ;

        rows[9][0] = "دی" ;
        rows[10][0] = "بهمن" ;
        rows[11][0] = "اسفند" ;

        return rows;
    }

    private JLabel createTableLabel(String text,int x , int y) {
        JLabel label = new JLabel(text) ;

        if(!hasConstList())
            label.setSize(MainFrame.graphPanelWidth/2 - 40,40);
        else
            label.setSize(MainFrame.graphPanelWidth/3 - 40,40);

        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label.setFont(new Font("B Titr",12,14));
        label.setLocation(x,y);
        return label;
    }

    protected abstract  String[] getVarList();
    protected abstract  String[] getResList();
    protected abstract  String[] getConstList();
    protected abstract  boolean hasConstList();

    protected String[] getFinalVarList(){
        String[] list = getVarList();
        String[] strings = createFinalList(list);
        return strings ;
    }

    private String[] createFinalList(String[] list) {
        String[] strings = new String[list.length + 1] ;
        strings[0] = "ردیف" ;
        for (int i = 0; i < list.length; i++) {
            strings[i+1] = list[i] ;
        }
        return strings;
    }

    protected String[] getFinalResList(){
        String[] list = getResList();
        String[] strings = createFinalList(list);
        return strings ;
    }
    protected String[] getFinalConstList(){
        return getConstList();
    }

    private JScrollPane createJSP(JTable table, JLabel label) {
        JScrollPane jsp = new JScrollPane(table) ;
        jsp.setSize(label.getWidth(), MainFrame.graphPanelHeight - 100);
        jsp.setLocation(label.getX() , label.getY() + label.getHeight() + 10);
        jsp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setFillsViewportHeight(true);
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        return jsp ;
    }

    public void solve(){
        TableModel resModel = resTable.getModel() ;

        setResTable(resModel);

    }

    protected void setResTable(TableModel resModel) {
        double[][] inputData = getTableData(varTable);
        TableModel varModel = varTable.getModel() ;

        for (int i = 0 ; i < resModel.getRowCount() ; i ++)
        {
            if(varModel.getValueAt(i,0) != null && !varModel.getValueAt(i,0).equals("")) {

                resModel.setValueAt(varModel.getValueAt(i,0),i,0);

                double[] inputs = new double[varModel.getColumnCount() - 1];
                for (int ri = 0; ri < varModel.getColumnCount() - 1; ri++)
                    inputs[ri] = inputData[i][ri];

                for (int j = 0; j < resModel.getColumnCount() - 1; j++) {
                    if (indexOfBoundStrings.contains(new Integer(j))) {
                        double[] resInputs = new double[resModel.getColumnCount() - 1];
                        for (int ri = 1; ri < resModel.getColumnCount() - 1; ri++)
                            resInputs[ri] = Double.parseDouble((String) resModel.getValueAt(i,ri));
                        resModel.setValueAt(getBoundString(resInputs, j), i, j + 1);
                    }
                    else
                        resModel.setValueAt(Double.toString(computeRes(inputs, j)), i, j + 1);
                }
            }
        }
    }

    protected abstract String getBoundString(double[] resInput, int i);

    protected double[][] getTableData(JTable table) {
        TableModel model = table.getModel() ;

        double[][] data = new double[model.getRowCount()][model.getColumnCount() - 1] ;
        for(int i = 0 ; i < data.length ; i++){
            for (int j = 0 ; j < data[i].length ; j++) {
                double value = 0 ;
                try{
                    value = Double.parseDouble((String) model.getValueAt(i, j + 1));
                }
                catch (Exception e){
                    value = 0 ;
                }
                data[i][j] = value ;
            }
        }

        return data ;
    }

    protected abstract double computeRes(double[] inputs, int resIndex);

    protected String getBoundString(String[] boundStrings , double[] bounds, double input){
        String result = boundStrings[0] ;
        for (int i = 0; i < bounds.length; i++) {
            if(input > bounds[i])
                result = boundStrings[i+1] ;
        }
        return result ;
    }

}
