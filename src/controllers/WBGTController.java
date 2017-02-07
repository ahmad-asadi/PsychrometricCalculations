//package controllers;
//
//import javax.swing.table.DefaultTableModel;
//
///**
// * This class is created by Ahmad Asadi on 1/17/17.
// */
//public class WBGTController extends IndexController {
//
//    public WBGTController(){
//        super();
//        setCols();
//        numberOfVars = 3 ;
//        numberOfRes = 5 ;
//        indexOfStringField = 6 ;
//        bounds = new double[]{13.5,17,18.5,22,23.5,28,Double.MAX_VALUE} ;
//        boundStrings = new String[]{"بسیار سرد","سرد","خنک","آسایش","گرم","داغ","بسیار داغ"} ;
//        setBoundStrings = true ;
//    }
//
//    @Override
//    protected double computeRes(double[] inputs, int index){
//        double D3 = inputs[0] ;
//        double D7 = inputs[1] ;
//        double D9 = inputs[2] ;
//
//        double D11 =((D3+(2.35*D3*(Math.sqrt(D9))))/(1+(2.35*(Math.sqrt(D9))))) ;
//        double D13 =((0.7*D7)+(0.2*D11)+((0.1*D3))) ;
//        double D15 = (0.7*D7)+(0.3*D11) ;
//
//        switch (index){
//            case 0:
//                return D11 ;
//            case 1:
//                return D13 ;
//            default:
//                return D15 ;
//        }
//
//    }
//
//    @Override
//    protected void setBoundStrings(double[] res){
//        super.setBoundStrings(res);
//        model = (DefaultTableModel) getModel();
//
//        for(int i = 1 ; i < cols.length ; i++)
//            res[i-1] = Double.parseDouble((String) model.getValueAt(numberOfVars + numberOfRes -1 , i)) ;
//
//        String[] strings = new String[cols.length-1] ;
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
//            model.setValueAt(strings[j-1],numberOfVars + numberOfRes - 1 , j);
//
//    }
//
//
//    protected void setCols(){
//        super.setCols();
//        String[] row = new String[cols.length];
//        row[0] = "میانگین دما" ;
//        model.addRow(row);
//        row[0] = "دمای تر" ;
//        model.addRow(row);
//        row[0] = "سرعت باد" ;
//        model.addRow(row);
//        row[0] = "محاسبه دمای کروی" ;
//        model.addRow(row);
//        row[0] = "WBGT در صورت انرژی مستقیم خورشید" ;
//        model.addRow(row);
//        row[0] = "WBGT در صورت عدم انرژی مستقیم خورشید" ;
//        model.addRow(row);
//        row[0] = "احساس حرارتی در تابش مستقیم" ;
//        model.addRow(row);
//        row[0] = "احساس حرارتی در عدم تابش مستقیم" ;
//        model.addRow(row);
//    }
//
//}
