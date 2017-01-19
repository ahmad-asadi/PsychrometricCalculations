package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HIController extends IndexController {

    public HIController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 3 ;
        indexOfStringField = 3 ;
        bounds = new double[]{90,105,130,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احتیاط","احتیاط بسیار","خطرناک","بسیار خطرناک"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        return -24.379+(2.04901523*inputs[0])+(10.14333127*inputs[1])-(0.22475541*inputs[0]*inputs[1])-(0.00683783*inputs[0]*inputs[0])-(0.05481717*inputs[1]*inputs[1])+(0.001228*inputs[0]*inputs[0]*inputs[1])+(0.00085282*inputs[0]*inputs[1]*inputs[1])-(0.00000199*inputs[0]*inputs[0]*inputs[1]*inputs[1]) ;
    }

    @Override
    protected void setBoundStrings(double[] res){
        super.setBoundStrings(res);
        String[] secondBoundStrings = new String[]{"بسیار گرم", "داغ", "بسیار داغ", "خطرناک"};;
        String[] strings = new String[cols.length-1] ;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < bounds.length; j++) {
                if(res[i] <= bounds[j])
                {
                    strings[i] = secondBoundStrings[j] ;
                    break;
                }
            }
        }
        for(int j = 1 ; j < cols.length ; j++)
            model.setValueAt(strings[j-1],indexOfStringField + 1,j);

    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما به فارنهایت" ;
        model.addRow(row);
        row[0] = "رطوبت" ;
        model.addRow(row);
        row[0] = "HI" ;
        model.addRow(row);
        row[0] = "طبقه خطر" ;
        model.addRow(row);
        row[0] = "حساسیت حرارتی" ;
        model.addRow(row);
    }

}
