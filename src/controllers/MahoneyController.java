package controllers;

import javax.swing.table.DefaultTableModel;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class MahoneyController extends IndexController {

    public MahoneyController(){
        super();
        setCols();
        numberOfVars = 8 ;
        numberOfRes = 9 ;
        indexOfStringField = 16 ;
        bounds = new double[]{90,105,130,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احتیاط","احتیاط بسیار","خطرناک","بسیار خطرناک"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double B10 = inputs[0] ;
        double B11 = inputs[1] ;
        double B12 = B10 - B11 ;
        double[][] tableData = getTableData() ;
        double maxMean = 0 ;
        double minMean = 0 ;
        for(int i = 0 ; i < tableData[0].length ; i++) {
            maxMean += tableData[0][i] ;
            minMean += tableData[1][i] ;
        }
        maxMean /= tableData[0].length ;
        minMean /= tableData[0].length ;

        double AMT = (maxMean + minMean)/2 ;
        double AMR = (maxMean - minMean) ;

        double C8 = inputs[2] ;

        double C9 ;
        if(C8 < 30)
            C9 = 1 ;
        else if(C8 < 50)
            C9 = 2 ;
        else if(C8 < 70)
            C9 = 3 ;
        else
            C9 = 4 ;

        double D13 = 24 - C9;
        double D14 ;
        if(C8 < 30)
            D14 = 32 ;
        else if(C8 < 50)
            D14 = 30 ;
        else if(C8 < 70)
            D14 = 28 ;
        else
            D14 = 25 ;

        double D16 = 14 ;
        double D17 = D13 ;
        double D19 = (B10 > D13) ? 1 : ((B10 < D14)? -1 : 0) ;
        double D20 = (B11 > D16) ? 1 : ((B11 < D17)? -1 : 0) ;

        switch (index){
            case 0 :
                return B12;
            case 1 :
                return C9;
            case 2 :
                return D13;
            case 3 :
                return D14;
            case 4 :
                return D16;
            case 5 :
                return D17;
            case 6 :
                return D19;
            case 7 :
                return D20;
            default:
                return 0 ;

        }


    }

    @Override
    protected void setBoundStrings(double[] res)
    {
        double[][] tableData = getTableData() ;
        for (int i = 14 ; i < 16 ; i++) {
            for (int j = 1; j < cols.length - 1; j++) {
                if (tableData[14][j] == 1)
                    model.setValueAt("H" , i , j);
                else if  (tableData[14][j] == 0)
                    model.setValueAt("-" , i , j);
                else
                    model.setValueAt("C" , i , j);
            }
        }
    }

    @Override
    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای حداکثر ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین دمای حداقل ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "میانگین حداکثر رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "میانگین حداقل رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "بالاترین فراوانی باد نخستین باد غالب" ;
        model.addRow(row);
        row[0] = "بالاترین فراوانی دومین اوج   جهت باد فرعي" ;
        model.addRow(row);
        row[0] = "بارندگی ماهیانه" ;
        model.addRow(row);


        row[0] = "نوسان دمای ماهانه" ;
        model.addRow(row);
        row[0] = "گروه رطوبت" ;
        model.addRow(row);
        row[0] = "حداکثر آسایش روز" ;
        model.addRow(row);
        row[0] = "حداقل آسایش روز" ;
        model.addRow(row);
        row[0] = "حداکثر آسایش شب" ;
        model.addRow(row);
        row[0] = "حداقل آسایش شب" ;
        model.addRow(row);
        row[0] = "تنش حرارتی روز" ;
        model.addRow(row);
        row[0] = "تنش حرارتی شب" ;
        model.addRow(row);
        row[0] = "مشخصات اقلیم شاخص" ;
        model.addRow(row);
    }
}
