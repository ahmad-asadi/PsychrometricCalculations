package controllers;

import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class WCTController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;
    private final String[] boundStrings2;

    public WCTController(){
        super();
        numberOfVars = 2 ;
        numberOfRes = 2 ;
        bounds = new double[]{-60,-45,-25,-10,Double.MAX_VALUE} ;
        boundStrings = new String[]{"گرمای متوسط","خیلی سرد","سرد","ملایم و متوسط","کم"} ;
        boundStrings2 = new String[]{"سطح خطر، شرایط در فضای باز خطرناک هستند، پوست در معرض یخ‌زدن در ۲ دقیقه","هشدار سطح،‌ در معرض یخ پوست در دقیقه، خطر جدی از هیپوترمی","خطر یخ‌زدگی، بررسی صورت و اندام‌ها‌(به عنوان مثال انگشتان پا و گوش) برای بی‌حسی و سفیدی، خطر ابتلا به هیپوترمی","ناراحت‌کننده، پوست در معرض احساس سرد، خطر ابتلا به هیپوترمی بدون حفاظت کافی","افزایش اندک در ناراحتی"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(1);
        indexOfBoundStrings.add(2);
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دما","دمای تر","سرعت باد به متر بر ثانیه","دمای کروی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"WCT","اثرات عمومی روی بدن انسان","شرایط زیست-اقلیمی"};
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
        switch (i){
            case 1:
                return getBoundString(boundStrings,bounds,resInput[i]);
            default:
                return getBoundString(boundStrings2, bounds, resInput[i]);
        }
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double D5 = inputs[1] ;
        double D3 = inputs[0] ;
        return 13.12+(0.6215*D3)-(11.37*(Math.pow((1.5*D5),0.16)))+(0.3965*D3*((Math.pow((1.5*D5),0.16)))) ;
    }
}
