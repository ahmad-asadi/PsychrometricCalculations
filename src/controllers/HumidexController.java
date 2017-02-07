//package controllers;
//
///**
// * This class is created by Ahmad Asadi on 1/17/17.
// */
//public class HumidexController extends IndexController {
//
//    public HumidexController(){
//        super();
//        setCols();
//        numberOfVars = 2 ;
//        numberOfRes = 4 ;
//        indexOfStringField = 4 ;
//        bounds = new double[]{30,40,55,Double.MAX_VALUE} ;
//        boundStrings = new String[]{"بی‌خطر","خطرناک","بسیار خطرناک","بسیار خطرناک"} ;
//        setBoundStrings = true ;
//    }
//
//    @Override
//    protected double computeRes(double[] inputs, int index){
//        double D3 = inputs[0] ;
//        double D5 = inputs[1] ;
//        double D7 = (6.11*(Math.pow(2.71,((54.70753*(1/273.16)*((1/273.16)+D5)))))) ;
//        if(index == 0)
//            return D7;
//        else
//            return (D3+(0.555*(D7-10))) ;
//    }
//
//    @Override
//    protected void setBoundStrings(double[] res){
//        super.setBoundStrings(res);
//        String[] secondBoundStrings = new String[]{"کمی آزار دهنده،درصورت طولانی شدن فعالیت امکان خستگی و کوفتگی", "اغلب آزار دهنده،گرمای آزار دهنده و خسته کننده و در صورت ادامه ی فعالیت گرفتگی عضلات", "محیط بسیار آزار دهنده .باید از فعالیت دوری کرد.احتمال گرفتگی عضلات و کوفتگی و درصورت ادامه فعالیت گرمازدگی حادث می شود", "گرمازدگی قریب الوقوع"};
//        String[] strings = new String[cols.length-1] ;
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < bounds.length; j++) {
//                if(res[i] <= bounds[j])
//                {
//                    strings[i] = secondBoundStrings[j] ;
//                    break;
//                }
//            }
//        }
//        for(int j = 1 ; j < cols.length ; j++)
//            model.setValueAt(strings[j-1],indexOfStringField + 1,j);
//
//    }
//
//    protected void setCols(){
//        super.setCols();
//        String[] row = new String[cols.length];
//        row[0] = "میانگین دما" ;
//        model.addRow(row);
//        row[0] = "دمای نقطه شبنم" ;
//        model.addRow(row);
//        row[0] = "VP" ;
//        model.addRow(row);
//        row[0] = "Humidex 2" ;
//        model.addRow(row);
//        row[0] = "سطح خطر" ;
//        model.addRow(row);
//        row[0] = "علائم" ;
//        model.addRow(row);
//    }
//
//}
