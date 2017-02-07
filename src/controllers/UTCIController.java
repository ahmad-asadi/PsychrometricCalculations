//package controllers;
//
//import javax.swing.table.DefaultTableModel;
//
///**
// * This class is created by Ahmad Asadi on 1/17/17.
// */
//public class UTCIController extends AnalyticalIndexController {
//    public UTCIController(){
//        indexOfStringField = numberOfVars + 3 ;
//        numberOfRes = 7 ;
//        bounds = new double[]{-40,-27,-13,0,9,26,32,38,46,Double.MAX_VALUE} ;
//        boundStrings = new String[]{"تنش سرمایی شدید","تنش سرمایی خیلی زیاد","تنش سرمایی زیاد","تنش سرمایی متوسط","تنش سرمایی اندک","بدون تنش حرارتی","تنش گرمایی متوسط","تنش گرمایی زیاد","تنش گرمایی بسیار زیاد","تنش گرمایی شدید"} ;
//        setBoundStrings = true ;
//    }
//
//    @Override
//    protected void setCols(){
//        super.setCols();
//        String[] row = new String[cols.length];
//
//        for(int i = 1 ; i < row.length ; i ++)
//            row[i] = "0" ;
//
//        row[0] = "فشار بخار آب e" ;
//        model.addRow(row);
//        row[0] = "میانگین دمای تابشی در شرایط آفتابی" ;
//        model.addRow(row);
//        row[0] = "شاخص UTCI در شرایط آفتابی" ;
//        model.addRow(row);
//        row[0] = "شرایط زیست-اقلیمی در شرایط آفتابی" ;
//        model.addRow(row);
//        row[0] = "میانگین دمای تابشی در شرایط سایه" ;
//        model.addRow(row);
//        row[0] = "شاخص UTCI در شرایط سایه" ;
//        model.addRow(row);
//        row[0] = "شرایط زیست-اقلیمی در شرایط سایه" ;
//        model.addRow(row);
//
//    }
//
//    protected void setBoundStrings(double[] res){
//        super.setBoundStrings(res);
//        model = (DefaultTableModel) getModel();
//
//        for (int i = 0; i < res.length; i++) {
//            res[i] = Double.parseDouble((String) model.getValueAt(numberOfVars + numberOfRes -1 , i+1));
//            System.out.println(res[i]);
//        }
//
//        String[] strings = new String[cols.length] ;
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < bounds.length; j++) {
//                if(res[i] <= bounds[j])
//                {
//                    strings[i] = boundStrings[j] ;
//                    break;
//                }
//            }
//        }
//        for(int j = 1 ; j < cols.length ; j++)
//            model.setValueAt(strings[j-1],numberOfVars + numberOfRes - 1,j);
//
//    }
//
//}
