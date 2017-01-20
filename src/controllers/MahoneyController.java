package controllers;

import javax.swing.table.DefaultTableModel;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class MahoneyController extends IndexController {

    public MahoneyController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 1 ;
        indexOfStringField = 2 ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        return inputs[0] - inputs[1] ;
    }

    @Override
    protected void setBoundStrings(double[] res)
    {
        double[][] tableData = getTableData() ;
        double maxMean = 0 ;
        double minMean = 0 ;
        for(int i = 0 ; i < tableData[0].length ; i++) {
            maxMean += tableData[0][i] ;
            minMean += tableData[1][i] ;
        }
        maxMean /= tableData[0].length ;
        minMean /= tableData[0].length ;

        String[] row = new String[cols.length] ;
        row[0] = "AMT" ;
        row[1] = Double.toString((maxMean + minMean)/2) ;
        row[2] = "AMR" ;
        row[3] = Double.toString(maxMean - minMean) ;
        model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

    @Override
    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای حداکثر ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین دمای حداقل ماهانه" ;
        model.addRow(row);
        row[0] = "نوسان دمای ماهانه" ;
        model.addRow(row);
    }
}
