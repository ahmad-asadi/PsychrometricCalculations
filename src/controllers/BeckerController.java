package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class BeckerController extends IndexController {

    public BeckerController(){
        super();
        setCols();
        numberOfVars = 2 ;
        numberOfRes = 4 ;
        indexOfStringField = 3 ;
        bounds = new double[]{5,10,20,30,40,50,60,Double.MAX_VALUE} ;
        boundStrings = new String[]{"داغ، گرم، شرجی و نامطبوع","گرم قابل تحمل","ملایم و مطبوع","خنک","سرد و کمی فشار دهنده","خیلی سرد","فوق العاده سرد، نامطبوع","تعریف نشده"} ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        double C4 = inputs[1] ;
        double C3 = inputs[0] ;
        return ((0.26+(0.34*(Math.pow(C4,0.672))))*(36.5-C3)) ;
    }

    @Override
    protected void setBoundStrings(double[] res){
        super.setBoundStrings(res);
        String[] secondBoundStrings = new String[]{"فشار زیست-اقلیمی", "آسایش زیست-اقلیمی", "آسایش زیست-اقلیمی", "تحریک ملایم", "تحریک متوسط تا شدید", "نسبتا آزاردهنده", "به شدت آزار دهنده" ,"تعریف نشده"};
        String[] strings = new String[cols.length-1] ;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < bounds.length; j++) {
                if(res[i] <= bounds[j])
                {
                    strings[i] = secondBoundStrings[j] ;
                    break;
                }
            }
        }
        for(int j = 1 ; j < cols.length ; j++)
            model.setValueAt(strings[j-1],indexOfStringField + 1,j);

        secondBoundStrings = new String[]{"A", "B", "C", "D", "E", "F" ,"G", "H"};
        strings = new String[cols.length-1] ;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < bounds.length; j++) {
                if(res[i] <= bounds[j])
                {
                    strings[i] = secondBoundStrings[j] ;
                    break;
                }
            }
        }
        for(int j = 1 ; j < cols.length ; j++)
            model.setValueAt(strings[j-1],indexOfStringField + 2,j);

    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای روزانه" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "CP" ;
        model.addRow(row);
        row[0] = "شرایط محیطی" ;
        model.addRow(row);
        row[0] = "شرایط بیوکلیماتولوژی" ;
        model.addRow(row);
        row[0] = "علائم" ;
        model.addRow(row);
    }

}
