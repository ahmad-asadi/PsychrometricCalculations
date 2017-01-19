package controllers;

import java.util.Vector;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class HK0Controller extends IndexController {

    private int resIndex = 0 ;
    private Vector K0 ;
    public HK0Controller(){
        super();
        setCols();
        K0 = new Vector<>() ;
        numberOfVars = 2 ;
        numberOfRes = 4 ;
        indexOfStringField = 3 ;
        bounds = new double[]{-1400,-1200,-1000,-800,-600,-300,-200,-50,80,120,160,Double.MAX_VALUE} ;
        boundStrings = new String[]{"گوشت در معرض این دما و باد منجمد می‌شود","فوق‌العاده سرد","بسیار سرد","سرد","بسیار خنک","خنک","مطبوع","گرمای مطبوع","گرم","احساس گرما روی پوست بدن","احساس گرمای نامطبوع اضافی","احساس گرمای بسیار نامطوبع اضافی"};
        setBoundStrings = true ;
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

    @Override
    protected void setBoundStrings(double[] res){
        super.setBoundStrings(res);

        for (int i = 0; i < res.length; i++) {
            res[i] = (double) K0.elementAt(i);

        }
        double[] secondBounds = new double[]{0,58.3,116.4,232.7,581.5,930.4,1628.2,2326,Double.MAX_VALUE} ;
        String[] secondBoundStrings = new String[]{"به‌شدت داغ","بسیار داغ","داغ","گرم","آسایش","خنک","سرد","یخ‌زدگی","نهایت یخ‌زدگی"};
        String[] strings = new String[cols.length - 1];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < secondBounds.length; j++) {
                if (res[i] <= secondBounds[j]) {
                    strings[i] = secondBoundStrings[j];
                    break;
                }
            }
        }
        for (int j = 1; j < cols.length; j++)
            model.setValueAt(strings[j - 1], indexOfStringField + 2, j);
    }

    @Override
    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "میانگین دمای ماهانه" ;
        model.addRow(row);
        row[0] = "میانگین سرعت باد" ;
        model.addRow(row);
        row[0] = "شاخص سرماباد H" ;
        model.addRow(row);
        row[0] = " H شرایط زیست-اقلیمی" ;
        model.addRow(row);
        row[0] = "شاخص سرماباد K0" ;
        model.addRow(row);
        row[0] = "K0 شرایط زیست-اقلیمی" ;
        model.addRow(row);
    }

}
