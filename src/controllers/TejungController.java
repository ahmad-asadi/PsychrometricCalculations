package controllers;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class TejungController extends IndexController {

    public TejungController(){
        super();
        setCols();
        numberOfVars = 5 ;
        numberOfRes = 16 ;
        indexOfStringField = 19 ;
        setBoundStrings = true ;
    }

    @Override
    protected double computeRes(double[] input , int index){

        return  0 ;
    }

     

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "MEAN" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد به نات" ;
        model.addRow(row);
        row[0] = "عرض جغرافیایی" ;
        model.addRow(row);
        row[0] = "میانگین حداقل دما" ;
        model.addRow(row);
        row[0] = "میانگین حداکثر دما" ;
        model.addRow(row);





        row[0] = "میانگین ساعت واقعی آفتاب روزانه" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد به متر بر ثانیه" ;
        model.addRow(row);
        row[0] = "مدار میل خورشید" ;
        model.addRow(row);
        row[0] = "WS(+24)" ;
        model.addRow(row);
        row[0] = "WS(-24)" ;
        model.addRow(row);
        row[0] = "طول روز" ;
        model.addRow(row);
        row[0] = "ضریب تاثیر باد در روز" ;
        model.addRow(row);
        row[0] = "میزان دفع انرژی شبانه" ;
        model.addRow(row);
        row[0] = "انرژی خورشیدی روزانه" ;
        model.addRow(row);
        row[0] = "قدرت خنک‌کنندگی خالص باد در روز" ;
        model.addRow(row);
        row[0] = "منفی‌دار کردن پاسخ" ;
        model.addRow(row);
        row[0] = "مقدار دفع انرژی" ;
        model.addRow(row);
        row[0] = "سمبل روز" ;
        model.addRow(row);
        row[0] = "سمبل شب" ;
        model.addRow(row);
        row[0] = "احساس غالب در روز" ;
        model.addRow(row);
        row[0] = "احساس غالب در شب" ;
        model.addRow(row);
    }

    @Override
    protected void setBoundStrings(double[] res){
        //TODO
    }

}
