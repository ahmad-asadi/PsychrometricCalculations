package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WCIController extends IndexController {

    public WCIController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{58.3,116.3,232.6,581.5,930.3,1628.2,2326,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی داغ","داغ","گرم","راحت","خنک","سرد","یخ‌زده","تعریف نشده"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double D5 = inputs[1] ;
        double D3 = inputs[0] ;
        return (((10*(Math.sqrt(D5)))+10.45-D5)*(33-D3)*1.163) ;
    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "WCI" ;
        model.addRow(row);
        row[0] = "اثرات عمومی روی انسان" ;
        model.addRow(row);
    }

}
