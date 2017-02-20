package controllers;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WBGTController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;

    public WBGTController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 4 ;
        bounds = new double[]{13.5,17,18.5,22,23.5,28,Double.MAX_VALUE} ;
        boundStrings = new String[]{"بسیار سرد","سرد","خنک","آسایش","گرم","داغ","بسیار داغ"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(2);
        indexOfBoundStrings.add(3);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","دمای تر","دمای کروی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"WBGT در صورت انرژی مستقیم خورشید","WBGT در صورت عدم انرژی مستقیم خورشید","احساس حرارتی در تابش مستقیم خورشید","احساس حرارتی در عدم تابش مستقیم خورشید"};
    }

    @Override
    protected String[] getConstList() {
        return new String[0];
    }

    @Override
    protected boolean hasConstList() {
        return false;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        return getBoundString(boundStrings, bounds, resInput[i]);
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double D3 = inputs[0] ;
        double D7 = inputs[1] ;
        double D9 = inputs[2] ;

        double D11 =((D3+(2.35*D3*(Math.sqrt(D9))))/(1+(2.35*(Math.sqrt(D9))))) ;
        double D13 =((0.7*D7)+(0.2*D11)+((0.1*D3))) ;
        double D15 = (0.7*D7)+(0.3*D11) ;

        switch (index){
            case 0:
                return D11 ;
            case 1:
                return D13 ;
            default:
                return D15 ;
        }

    }
}
