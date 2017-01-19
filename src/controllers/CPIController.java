package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class CPIController extends IndexController {

    public CPIController(){
        super();
        setCols();
        numberOfRes = 2 ;
        numberOfVars = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{210,420,630,840,1260,1680,2100,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی داغ","داغ","طبیعی","تا حدی خنک","خنک","سرد","خیلی سرد","فوق‌العاده سرد، طوفانی"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        return (36.5 - inputs[0]) * (0.13 + (0.47*Math.sqrt(inputs[1]))) * 41.868 ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین  سرعت باد" ;
        model.addRow(row);
        row[0] = "CPI" ;
        model.addRow(row);
        row[0] = "شرایط زیست‌-اقلیمی" ;
        model.addRow(row);
    }

}
