package controllers;

import javax.swing.table.DefaultTableModel;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class ATController extends IndexController {

    public ATController(){
        super();
        setCols();
        numberOfVars = 3 ;
        numberOfRes = 3 ;
        indexOfStringField = 5 ;
        bounds = new double[]{32,41,54,Double.MAX_VALUE} ;
        boundStrings = new String[]{"امکان خستگی با فعالیت ممتد فیزیکی","آفتاب‌زدگی و انقباض حرارتی","امکان گرمازدگی با فعالیت فیزیکی","خطر گرمازدگی،‌ آفتاب‌زدگی و شوک جدی"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double D3 = inputs[0] ;
        double D5 = inputs[1] ;
        double D7 = inputs[2] ;
        double D9 = 6.11*Math.pow(2.71,((54.70753*(1/273.16)*((1/273.16)+D7)))) ;
        double D11 = D3+(0.32*D9)-(0.7*D5)-4 ;

        switch (index){
            case 0:
                return D9 ;
            default:
                return D11 ;
        }

    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = " دمای نقطه شبنم" ;
        model.addRow(row);
        row[0] = "VP" ;
        model.addRow(row);
        row[0] = "AT" ;
        model.addRow(row);
        row[0] = "اثرات عمومی روی انسان" ;
        model.addRow(row);
    }

}
