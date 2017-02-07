//package controllers;
//
//import javax.swing.table.DefaultTableModel;
//
///**
// * This class is created by Ahmad Asadi on 1/21/17.
// */
//public class AnalyticalIndexController extends IndexController {
//    protected int augmentedParametersCount = 9 ;
//    public AnalyticalIndexController()
//    {
//        numberOfVars = 15 ;
//        setBoundStrings = true ;
//        setCols();
//    }
//
//    @Override
//    protected double computeRes(double[] inputs , int index)
//    {
//        double B5 = inputs[augmentedParametersCount] ;
//        double Ta = B5 ;
//        double B6 = inputs[augmentedParametersCount + 2] ;
//        double O1 = 6.112*(Math.pow(10,7.5*B5/237.7+B5)) * 0.01 * B6 ; // pheshare bokhare ab e
//        double Tg = inputs[augmentedParametersCount + 3] ;
//        double lg = 0.5*0.97*0.0000000567*Math.pow((273+Tg),4) ;
//        double la = 0.5*(0.97*0.0000000567*Math.pow((273+Ta),4)*(0.82-0.25*Math.pow(10,-0.094*0.75*O1))) ;
//        double color = inputs[augmentedParametersCount + 5] ;
//        double ac = color == 1 ? 60 : (color == 2 ? 30 : 5) ;
//        double h = getH(inputs) ;
//        double N = inputs[augmentedParametersCount + 4] ;
//        double RSunny = 0 ;
//
//        if(h>4){
//            if(N < 3)
//                RSunny = 1.4* (-100.428+73.981*Math.log(h))*(1-0.01*ac) ;
//            else if(N < 5)
//                RSunny = 1.4*Math.exp(5.383-16.072/h) * (1-0.01*ac) ;
//            else if(N < 7)
//                RSunny = 1.4*Math.exp(5.012-11.805/h) * (1-0.01*ac) ;
//            else
//                RSunny = 1.4*0.951*Math.pow(h,1.039) * (1-0.01*ac) ;
//        }
//
//        double Rshaded = 0 ;
//        if(h > 4){
//            Rshaded =  1.4*0.951*Math.pow(h,1.039) * (1-0.01*ac) ;
//        }
//
//
//        double TmrtSunny = Math.pow((RSunny+lg+la) / (0.095+5.667*Math.pow(10,-8)),0.25) - 273 ;
//        double TmrtShaded = Math.pow((Rshaded+lg+la) / (0.095+5.667*Math.pow(10,-8)),0.25) - 273 ;
//
//        double V = inputs[augmentedParametersCount+1] ;
//        double RH = inputs[augmentedParametersCount+2] ;
//
//        switch (index){
//            case 0:
//                return O1;
//            case 1:
//                return TmrtSunny;
//            case 2:
//            case 3:
//                return 3.21 + 0.872*Ta + 0.2459*TmrtSunny + (-2.5078*V) - 0.0176*RH ;
//            case 4:
//                return TmrtShaded;
//            case 5:
//            case 6:
//                return 3.21 + 0.872*Ta + 0.2459*TmrtShaded + (-2.5078*V) - 0.0176*RH ;
//            default:
//                return -1 ;
//        }
//
//    }
//
//    @Override
//    protected void setCols(){
//        super.setCols();
//        String[] row = new String[cols.length];
//
//        for(int i = 1 ; i < row.length ; i ++)
//            row[i] = "1" ;
//
//        row[0] = "روز" ;
//        model.addRow(row);
//
//        for(int i = 1 ; i < row.length ; i ++)
//            row[i] = Integer.toString(i) ;
//
//        row[0] = "ماه" ;
//        model.addRow(row);
//
//        for(int i = 1 ; i < row.length ; i ++)
//            row[i] = "12" ;
//
//        row[0] = "ساعت" ;
//        model.addRow(row);
//
//        for(int i = 1 ; i < row.length ; i ++)
//            row[i] = "0" ;
//
//        row[0] = "عرض جغرافیایی" ;
//        model.addRow(row);
//
//        row[0] = "شماره ژولیوسی" ;
//        model.addRow(row);
//        row[0] = "مدار میل خورشید" ;
//        model.addRow(row);
//        row[0] = "زاویه ساعت خورشیدی" ;
//        model.addRow(row);
//        row[0] = "ارتفاع خورشید" ;
//        model.addRow(row);
//        row[0] = "وارون ارتفاع خورشید" ;
//        model.addRow(row);
//        for(int i = 1 ; i < row.length ; i ++)
//            row[i] = "0" ;
//
//        row[0] = "دمای هوا" ;
//        model.addRow(row);
//        row[0] = "سرعت باد" ;
//        model.addRow(row);
//        row[0] = "رطوبت نسبی" ;
//        model.addRow(row);
//        row[0] = "دمای کروی" ;
//        model.addRow(row);
//        row[0] = "میزان ابرناکی" ;
//        model.addRow(row);
//        row[0] = "رنگ لباس" ;
//        model.addRow(row);
//
//
//    }
//
//    protected void fillMedarMil(){
//        model = (DefaultTableModel) getModel();
//        double[][] tableData = getTableData();
//        String[][] res = new String[5][tableData[0].length] ;
//        for(int i = 0 ; i < tableData[0].length ; i++)
//        {
//            int day = (int) tableData[0][i];
//            int month = (int) tableData[1][i];
//            int hour = (int) tableData[2][i];
//            double lat = tableData[3][i] ;
//            if (month > 12)
//                month = 12 ;
//
//            if(month <= 6 && day > 31)
//                day = 31 ;
//            else if(day > 30)
//                day = 30 ;
//
//            int monthDayCount = (month <= 6 ? (31 * (month - 1)) : (31 * 6) + 30 * (month - 7)) ;
//            int jolNumber = monthDayCount + day;
//
//            double mil = 23.45*Math.sin(Math.toRadians(360*(((double)(284+jolNumber))/365))) ;
//
//            double SHA = 0.25*(12-hour)*60 ;
//            double h = (((Math.cos(Math.toRadians(mil)))*(Math.cos(Math.toRadians(lat))*(Math.cos(Math.toRadians(SHA)))))+((Math.sin(Math.toRadians(mil)))*(Math.sin(Math.toRadians(lat))))) ;
//            double hInverse = Math.toDegrees(Math.asin(h)) ;
//
//            res[0][i] = Integer.toString(jolNumber) ;
//            res[1][i] = Double.toString(mil) ;
//            res[2][i] = Double.toString(SHA) ;
//            res[3][i] = Double.toString(h) ;
//            res[4][i] = Double.toString(hInverse) ;
//        }
//
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < res[i].length; j++) {
//                String value = res[i][j];
//                model.setValueAt(value , i+ 4, j+1);
//            }
//        }
//
//    }
//
//    protected double getH(double[] inputs) {
//        int day = (int) inputs[0];
//        int month = (int) inputs[1];
//        int hour = (int) inputs[2];
//        double lat = inputs[3] ;
//
//        if (month > 12)
//            month = 12 ;
//
//        if(month <= 6 && day > 31)
//            day = 31 ;
//        else if(day > 30)
//            day = 30 ;
//
//        int monthDayCount = (month <= 6 ? (31 * (month - 1)) : (31 * 6) + 30 * (month - 7)) ;
//        int jolNumber = monthDayCount + day;
//
//        double mil = 23.45*Math.sin(Math.toRadians(360*(((double)(284+jolNumber))/365))) ;
//
//        double SHA = 0.25*(12-hour)*60 ;
//        double h = (((Math.cos(Math.toRadians(mil)))*(Math.cos(Math.toRadians(lat))*(Math.cos(Math.toRadians(SHA)))))+((Math.sin(Math.toRadians(mil)))*(Math.sin(Math.toRadians(lat))))) ;
//        return Math.toDegrees(Math.asin(h)) ;
//    }
//
//
//    @Override
//    public void solve(){
//        fillMedarMil();
//
//        super.solve();
//    }
//
//    protected double getAc(double[] input){
//        double color = input[augmentedParametersCount + 5] ;
//        return color == 1 ? 60 : (color == 2 ? 30 : 5) ;
//    }
//
//    protected double getT(double[] input) {
//        return input[augmentedParametersCount];
//    }
//
//    protected double getRH(double[] input) {
//        return input[augmentedParametersCount+2];
//    }
//
//    protected double getV(double[] input) {
//        return input[augmentedParametersCount + 1] ;
//    }
//
//    protected double getVp(double[] input) {
//        double B5 = input[augmentedParametersCount] ;
//        double B6 = input[augmentedParametersCount + 2] ;
//        return 6.112*(Math.pow(10,7.5*B5/237.7+B5)) * 0.01 * B6 ; // pheshare bokhare ab e
//    }
//
//    protected double getLa(double[] input) {
//        double B5 = input[augmentedParametersCount] ;
//        double Ta = B5 ;
//        double B6 = input[augmentedParametersCount + 2] ;
//        double O1 = 6.112*(Math.pow(10,7.5*B5/237.7+B5)) * 0.01 * B6 ; // pheshare bokhare ab e
//        double Tg = input[augmentedParametersCount + 3] ;
//        return 0.5*(0.97*0.0000000567*Math.pow((273+Ta),4)*(0.82-0.25*Math.pow(10,-0.094*0.75*O1))) ;
//    }
//
//    protected double getLg(double[] input) {
//        double Tg = input[augmentedParametersCount + 3] ;
//        return 0.5*0.97*0.0000000567*Math.pow((273+Tg),4) ;
//    }
//
//    protected double getRPrime(double[] input) {
//        double h = getH(input) ;
//        double N = input[augmentedParametersCount + 4] ;
//        double color = input[augmentedParametersCount + 5] ;
//        double ac = color == 1 ? 60 : (color == 2 ? 30 : 5) ;
//        double RSunny = 0 ;
//
//        if(h>4){
//            if(N < 3)
//                RSunny = 1.4* (-100.428+73.981*Math.log(h))*(1-0.01*ac) ;
//            else if(N < 5)
//                RSunny = 1.4*Math.exp(5.383-16.072/h) * (1-0.01*ac) ;
//            else if(N < 7)
//                RSunny = 1.4*Math.exp(5.012-11.805/h) * (1-0.01*ac) ;
//            else
//                RSunny = 1.4*0.951*Math.pow(h,1.039) * (1-0.01*ac) ;
//        }
//
//        return RSunny ;
//    }
//}
