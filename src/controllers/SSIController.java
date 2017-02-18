package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SSIController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;
    private final String[] boundStrings2;

    public SSIController(){
        super();
        numberOfVars = 3 ;
        numberOfRes = 3 ;
        bounds = new double[]{77,83,91,100,112,125,150,Double.MAX_VALUE} ;
        boundStrings = new String[]{"افراد بسیار احساس راحتی با کمی گرمی دارند","تمامی افراد در این شرایط احساس راحتی می‌کنند","افراد بسیاری احساس راحتی با کمی گرمی دارند","افزایش حرارت که به همراه احساس ناراحتی می‌باشد","احساس ناراحتی همراه با خطر گرمازدگی","ناراحتی زیاد و افزایش خطر گرمازدگی","حداکثر ناراحتی توام با خطر گرمازدگی زیاد","تعریف نشده"};
        boundStrings2 = new String[]{"احساس خنکی","احساس آرامش","آسایش با گرمای کم","گرمای کم","گرمای متوسط","گرمای زیاد","داغ","تعریف نشده"};
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
        indexOfBoundStrings.add(2);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای خشک","میانگین دمای خشک به فارنهایت","میانگین رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"شاخص داغی تابستان","اثرات","طبقه"};
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
        System.out.println(resInput[1]);
        switch (i){
            case 1:
                return getBoundString(boundStrings, bounds, resInput[1]);
            case 2:
                return getBoundString(boundStrings2, bounds, resInput[1]);
        }
        return "ناشناخته";
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double B8 = inputs[1] ;
        double B10 = inputs[2] ;
        return ((1.98*(B8-((0.55-(0.0055*(B10))*(B8-58)))))-56.83) ;
    }
}
