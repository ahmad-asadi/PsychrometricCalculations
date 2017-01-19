package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WCTController extends IndexController {

    public WCTController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        indexOfStringField = 3 ;
        bounds = new double[]{-60,-45,-25,-10,Double.MAX_VALUE} ;
        boundStrings = new String[]{"گرمای متوسط","خیلی سرد","سرد","ملایم و متوسط","کم"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double D5 = inputs[1] ;
        double D3 = inputs[0] ;
        return 13.12+(0.6215*D3)-(11.37*(Math.pow((1.5*D5),0.16)))+(0.3965*D3*((Math.pow((1.5*D5),0.16)))) ;
    }


    protected void setCols(){
        super.setCols();
        System.out.println("set cols in WCT Controller");
        String[] row = new String[cols.length];
        row[0] = "میانگین دما" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "WCT" ;
        model.addRow(row);
        row[0] = "اثرات عمومی روی انسان" ;
        model.addRow(row);
    }

}
