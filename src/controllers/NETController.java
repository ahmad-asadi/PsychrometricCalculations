package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class NETController extends IndexController {

    public NETController(){
        super();
        setCols();
        numberOfVars = 3 ;
        numberOfRes = 2 ;
        indexOfStringField = 4 ;
        bounds = new double[]{1,9,17,21,23,27,Double.MAX_VALUE} ;
        boundStrings = new String[]{"خیلی سرد","سرد","خنک","خوب","راحت","گرم","خیلی گرم"};
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double D3 = inputs[0] ;
        double D5 = inputs[1] ;
        double D7 = inputs[2] ;
        return (37-((37-D3)/(0.68-(0.0014*D7)+(1/(1.76+(1.4*(Math.pow(D5,0.75)))))))-((0.29*D3)*(1-(0.01*D7)))) ;
    }


    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "TE" ;
        model.addRow(row);
        row[0] = "اثرات عمومی روی انسان" ;
        model.addRow(row);
    }

}
