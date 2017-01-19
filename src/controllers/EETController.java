package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class EETController extends IndexController {

    public EETController(){
        super();
        setCols();
        numberOfVars = 3 ;
        numberOfRes = 2 ;
        indexOfStringField = 4 ;
        bounds = new double[]{8,17,23,Double.MAX_VALUE} ;
        boundStrings = new String[]{"سرد","خنک","ملایم و مطبوع","داغ"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        return (inputs[0]*(1-(0.003*(100-inputs[1])))-(0.365*(Math.pow(inputs[2],0.59)))*( (36.6-inputs[0])+(0.622*((inputs[2]-1))))+((0.0015*inputs[2])+0.008)*(((36.6-inputs[0])- 0.0167))*(100-inputs[1])) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "رطوبت" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "EET" ;
        model.addRow(row);
        row[0] = "شرایط محیطی" ;
        model.addRow(row);
    }

}
