//package controllers;
//
///**
// * This class is created by Ahmad Asadi on 1/17/17.
// */
//public class RSIController extends IndexController {
//
//    public RSIController(){
//        super();
//        setCols();
//        numberOfVars = 4 ;
//        numberOfRes = 5 ;
//        indexOfStringField = 6 ;
//        bounds = new double[]{0.1,0.3,0.5,Double.MAX_VALUE} ;
//        boundStrings = new String[]{"آسایش","عدم آسایش","اضطراب","ناتوانی"} ;
//        setBoundStrings = true ;
//    }
//
//    @Override
//    protected double computeRes(double[] inputs, int index){
//        double B2 = inputs[0] ;
//        double B4 = inputs[1] ;
//        double B7 = 0.254*B4*(Math.pow((0.00739*B2)+0.807,8)) ;
//        if(index == 0)
//            return B7 ;
//        else
//            return (10.7+(0.74*(B2-35)))/(44-B7) ;
//
//    }
//
//    @Override
//    protected void setBoundStrings(double[] res)
//    {
//        super.setBoundStrings(res);
//        double[] secondBounds = new double[]{0.3,0.5,1,Double.MAX_VALUE} ;
//        String[] strings = new String[cols.length-1] ;
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < secondBounds.length; j++) {
//                if(res[i] <= secondBounds[j])
//                {
//                    strings[i] = boundStrings[j] ;
//                    break;
//                }
//            }
//        }
//        for(int j = 1 ; j < cols.length ; j++)
//            model.setValueAt(strings[j-1],indexOfStringField + 1,j);
//
//        secondBounds = new double[]{0.1,0.3,0.4,Double.MAX_VALUE} ;
//        strings = new String[cols.length-1] ;
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < secondBounds.length; j++) {
//                if(res[i] <= secondBounds[j])
//                {
//                    strings[i] = boundStrings[j] ;
//                    break;
//                }
//            }
//        }
//        for(int j = 1 ; j < cols.length ; j++)
//            model.setValueAt(strings[j-1],indexOfStringField + 2,j);
//    }
//
//    protected void setCols(){
//        super.setCols();
//        String[] row = new String[cols.length];
//        row[0] = "میانگین دما" ;
//        model.addRow(row);
//        row[0] = "میانگین رطوبت" ;
//        model.addRow(row);
//        row[0] = "باد" ;
//        model.addRow(row);
//        row[0] = "نقطه شبنم" ;
//        model.addRow(row);
//        row[0] = "فشار بخار به میلی بار e" ;
//        model.addRow(row);
//        row[0] = "شاخص فشار نسبی" ;
//        model.addRow(row);
//        row[0] = "شخص متوسط" ;
//        model.addRow(row);
//        row[0] = "شخص سازگار" ;
//        model.addRow(row);
//        row[0] = "شخص سال‌خورده" ;
//        model.addRow(row);
//    }
//}
