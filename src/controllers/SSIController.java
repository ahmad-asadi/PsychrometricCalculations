package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class SSIController extends IndexController {

    public SSIController(){
        super();
        setCols();
        numberOfVars = 3 ;
        numberOfRes = 3 ;
        indexOfStringField = 4 ;
        bounds = new double[]{70,77,83,91,100,112,125,150,Double.MAX_VALUE} ;
        boundStrings = new String[]{"تعریف نشده","افراد بسیار احساس راحتی با کمی گرمی دارند","تمامی افراد در این شرایط احساس راحتی می‌کنند","افراد بسیاری احساس راحتی با کمی گرمی دارند","افزایش حرارت که به همراه احساس ناراحتی می‌باشد","احساس ناراحتی همراه با خطر گرمازدگی","ناراحتی زیاد و افزایش خطر گرمازدگی","حداکثر ناراحتی توام با خطر گرمازدگی زیاد","تعریف نشده"};
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs){
        double B8 = inputs[1] ;
        double B10 = inputs[2] ;
        return ((1.98*(B8-((0.55-(0.0055*(B10))*(B8-58)))))-56.83) ;
    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای خشک" ;
        model.addRow(row);
        row[0] = "میانگین دمای خشک به فارنهایت" ;
        model.addRow(row);
        row[0] = "میانگین رطوبت به درصد" ;
        model.addRow(row);
        row[0] = "شاخص داغی تابستان" ;
        model.addRow(row);
        row[0] = "اثرات" ;
        model.addRow(row);
        row[0] = "طبقه" ;
        model.addRow(row);
    }

}
