package controllers;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class ASVController extends IndexController {

    private final double[][] bounds;
    private final String[][] boundStrings;

    public ASVController(){
        super();
        numberOfRes = 2 ;
        numberOfVars = 4 ;
        bounds = new double[][]{{-1.5,-0.5,0.5,1.5,Double.MAX_VALUE}} ;
        boundStrings = new String[][]{{"خیلی سرد","سرد","آسایش","گرم","بسیار گرم"}} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما", "میانگین سرعت باد به متر بر ثانیه", "میانگین تابش مستقیم بر افق", "رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"ASV" , "شرایط زیست-اقلیمی"};
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
        return getBoundString(boundStrings[0] , bounds[0] , resInput[i]);
    }

    @Override
    protected double computeRes(double[] inputs, int resIndex) {
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        double B6 = inputs[2] ;
        double B11 = inputs[3]/100 ;

        return (0.049*B2)+(0.001*B6)+(0.051*B4)+(0.014*B11) ;
    }
}
