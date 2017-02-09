package controllers;

import java.util.ArrayList;
import java.util.Vector;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HK0Controller extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;
    private Vector K0 ;

    public HK0Controller(){
        super();
        K0 = new Vector<>() ;
        numberOfVars = 2 ;
        numberOfRes = 4 ;
        bounds = new double[]{-1400,-1200,-1000,-800,-600,-300,-200,-50,80,120,160,Double.MAX_VALUE} ;
        boundStrings = new String[]{"گوشت در معرض این دما و باد منجمد می‌شود","فوق‌العاده سرد","بسیار سرد","سرد","بسیار خنک","خنک","مطبوع","گرمای مطبوع","گرم","احساس گرما روی پوست بدن","احساس گرمای نامطبوع اضافی","احساس گرمای بسیار نامطوبع اضافی"};
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
        indexOfBoundStrings.add(3);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای ماهانه","میانگین سرعت باد"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص سرماباد H", "شرایط زیست-اقلیمی H" ,"شاخص سرماباد K0" , "شرایط زیست-اقلیمی K0"};
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
        double[] secondBounds = new double[]{0,58.3,116.4,232.7,581.5,930.4,1628.2,2326,Double.MAX_VALUE} ;
        String[] secondBoundStrings = new String[]{"به‌شدت داغ","بسیار داغ","داغ","گرم","آسایش","خنک","سرد","یخ‌زدگی","نهایت یخ‌زدگی"};
        switch (i){
            case 1 :
                return getBoundString(boundStrings, bounds ,resInput[i]) ;
            case 3:
                return getBoundString(secondBoundStrings, secondBounds ,resInput[i]) ;
        }
        return "ناشناخته" ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double B2 = inputs[0] ;
        double B4 = inputs[1] ;
        if(index == 0 || index == 1)
            return -(10.45+(10*(Math.sqrt(B4)))-B4)*(33-B2) ;
        else if(index == 2 || index == 3) {
            double k0 = (((11.62 * (Math.sqrt(B4))) + 16.86 - (1.162 * B4))) * (33 - B2) ;
            K0.add(k0);
            return k0;
        }
        else
            return -1 ;
    }

}
