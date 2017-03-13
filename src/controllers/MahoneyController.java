package controllers;

import facilities.ExcelAdapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class MahoneyController extends IndexController {

    private final double[] bounds;
    private final String[] boundStrings;
    private final JTable table1;
    private final JTable table2;
    private final JTable table3;
    private JTabbedPane tabbedPane;
    private double AMT = 0;
    private double AMR = 0;
    private double AH23 = Double.MIN_VALUE;
    private double AH24 = Double.MAX_VALUE;

    public MahoneyController(){
        super();
        numberOfVars = 6 ;
        numberOfRes = 9 ;
        bounds = new double[]{90,105,130,Double.MAX_VALUE} ;
        boundStrings = new String[]{"احتیاط","احتیاط بسیار","خطرناک","بسیار خطرناک"} ;
        indexOfBoundStrings = new ArrayList<>() ;
        indexOfBoundStrings.add(6);
        indexOfBoundStrings.add(7);
//        indexOfBoundStrings.add(8);
//        indexOfBoundStrings.add(9);
//        indexOfBoundStrings.add(10);
//        indexOfBoundStrings.add(11);
//        indexOfBoundStrings.add(12);
//        indexOfBoundStrings.add(13);

        constTable.setValueAt("میانگین دمای سالیانه" , 0 , 0);

        String[] cols1 = new String[]{"ردیف","نوسان دمای ماهانه","گروه رطوبت","حداکثر آسایش روز","حداقل آسایش روز","حداکثر آسایش شب","حداقل آسایش شب","تنش حرارتی روز","تنش حرارتی شب"} ;
        String[] cols2 = new String[]{"ردیف","شاخص A1","شاخص A2","شاخص A3","شاخص H1","شاخص H2","شاخص H3"}  ;
        String[] cols3 = new String[]{"لیست توصیه‌ها"}  ;

        table1 = new JTable(new String[400][cols1.length],cols1) ;
        table2 = new JTable(new String[400][cols2.length],cols2) ;
        table3 = new JTable(new String[400][cols3.length],cols3) ;


        JScrollPane jsp1 = createNewSubTable(table1);
        JScrollPane jsp2 = createNewSubTable(table2);
        JScrollPane jsp3 = createNewSubTable(table3);

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for(int i = 0 ; i < table1.getColumnCount() ; i ++)
            table1.getColumnModel().getColumn(i).setPreferredWidth(200);

        table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table3.getColumnModel().getColumn(0).setPreferredWidth(1000);

        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for(int i = 0 ; i < varTable.getColumnCount() ; i ++)
            varTable.getColumnModel().getColumn(i).setPreferredWidth(200);

        tabbedPane = new JTabbedPane() ;

        tabbedPane.setSize(resJsp.getSize());
        tabbedPane.add("تنش حرارتی",jsp1) ;
        tabbedPane.add("شاخص‌ها",jsp2) ;
        tabbedPane.add("توصیه‌ها",jsp3) ;
//        tabbedPane.add("شرایط گرمایی",jsp5) ;
        tabbedPane.setLocation(resJsp.getLocation());
        tabbedPane.setBorder(BorderFactory.createBevelBorder(0));
        tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        remove(resJsp);
        add(tabbedPane) ;

    }

    private JScrollPane createNewSubTable(JTable table1) {
        JScrollPane jsp1 = new JScrollPane(table1) ;
        jsp1.setSize(table1.getSize());
        jsp1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        table1.getColumnModel().getColumn(0).setPreferredWidth(100);
        for(int i = 1 ; i < table1.getColumnCount() ; i++)
            table1.getColumnModel().getColumn(i).setPreferredWidth(180);



        ExcelAdapter adapter = new ExcelAdapter(table1) ;
        table1.addMouseListener( new MouseAdapter()
        {
            public void mousePressed(MouseEvent e){
                if (e.isPopupTrigger())
                    doPop(e);
            }

            public void mouseReleased(MouseEvent e){
                if (e.isPopupTrigger())
                    doPop(e);
            }

            private void doPop(MouseEvent e){
                PopUp menu = new PopUp(adapter);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });


        table1.setFillsViewportHeight(true);
        table1.setShowHorizontalLines(true);
        table1.setShowVerticalLines(true);

        return jsp1;
    }

    @Override
    protected void setResTable(TableModel resModel) {
        double[][] inputData = getTableData(varTable);
        TableModel varModel = varTable.getModel() ;

        for (int i = 0 ; i < resModel.getRowCount() ; i ++)
        {
            if(varModel.getValueAt(i,0) != null && !varModel.getValueAt(i,0).equals("")) {

                resModel.setValueAt(varModel.getValueAt(i,0),i,0);

                double[] inputs = new double[varModel.getColumnCount() - 1];
                for (int ri = 0; ri < varModel.getColumnCount() - 1; ri++)
                    inputs[ri] = inputData[i][ri];

                for (int j = 0; j < resModel.getColumnCount() - 1; j++) {
                    resModel.setValueAt(Double.toString(computeRes(inputs, j,i)), i, j + 1);
                    if (indexOfBoundStrings.contains(new Integer(j))) {
                        double[] resInputs = new double[resModel.getColumnCount() - 1];
                        for (int ri = 1; ri < resModel.getColumnCount() - 1; ri++)
                            try {
                                resInputs[ri] = Double.parseDouble((String) resModel.getValueAt(i, ri));
                            }catch (Exception e){resInputs[ri] = 0 ;}
                        resModel.setValueAt(getBoundString(resInputs, j, (int)i), i, j + 1);
                    }
                }
            }
        }
    }



    @Override
    public void solve(){
        super.solve();

        int[] sums = new int[]{0,0,0,0,0,0} ;

        for(int j = 0 ; j < resTable.getRowCount() ; j++) {
            for (int i = 0; i < 9; i++) {
                table1.setValueAt(resTable.getValueAt(j, i), j, i);
            }


            table2.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 9; i < 15; i++) {
                System.out.println(resTable.getValueAt(j,i));
                table2.setValueAt(resTable.getValueAt(j, i), j, i-8);
                sums[i-9] += getCellData(resTable, j, i) ;
            }
        }


        table2.setValueAt("مجموع",13,0);
        table2.setValueAt(Integer.toString(sums[0]),13,1);
        table2.setValueAt(Integer.toString(sums[1]),13,2);
        table2.setValueAt(Integer.toString(sums[2]),13,3);
        table2.setValueAt(Integer.toString(sums[3]),13,4);
        table2.setValueAt(Integer.toString(sums[4]),13,5);
        table2.setValueAt(Integer.toString(sums[5]),13,6);


        //////////////////////////////   JANAMAEI
        int index = 0 ;
        if(sums[0] <= 10 || sums[2] >= 5 )
            table3.setValueAt("جانمایی: ساختمان‌ها بر محور شرقی-غربی جهت‌گیری کنند تا حتی‌الامکان در معرض خورشید نباشند.",index++ , 0);

        if(sums[0] >= 11 && sums[2]<=4)
            table3.setValueAt("جانمایی: طراحی فشرده حول یک حیاط مرکزی" , index++ , 0);


        //////////////////////////////   FAZASAZI
        if(sums[3]>=11)
            table3.setValueAt("فضاسازی: فضاسازی باز برای نفوذ باد",index++ , 0);

        if(sums[3]>=2 && sums[3] <= 10)
            table3.setValueAt("فضاسازی: فضاسازی باز برای نفوذ باد، اما از باد سرد/داغ جلوگیری شود", index++ , 0);

        if(sums[3]>=0 && sums[3] <= 1)
            table3.setValueAt("فضاسازی: طراحی فشرده", index++ , 0);


        //////////////////////////////   JABEJAEI E HAVA
        if(sums[3]>=3 ||sums[0]>=5)
            table3.setValueAt("جابجایی هوا: اتاق‌های یک جداره، شرایط دائمی برای جابجایی هوا", index++ , 0);
        if((sums[3]>=1 && sums[3]<=2 && sums[0]>=6 && sums[0]<=12) || (sums[3]== 0 && sums[4]>=2))
            table3.setValueAt("جابجایی هوا: اتاق‌های دوجداره با شرایط موقتی برای جابجایی هوا",index++ , 0);
        if(sums[3]== 0 && sums[4]<=1)
            table3.setValueAt(" جابجایی هوا: هیچ جابجایی هوایی نیاز نیست", index++ , 0);

        //////////////////////////////   BAZSHO
        if(sums[2] == 0 && sums[0] <=1)
            table3.setValueAt("بازشوها: بازشوهای بزرگ، 40 تا 80 درصد از دیوارهای شمالی و جنوبی", index++ , 0);
        else if(sums[2] <= 1 && sums[0] >= 11)
            table3.setValueAt("بازشو‌ها: بازشوهای بسیار کوچک، 10 تا 20 درصد",index++ , 0);
        else
            table3.setValueAt("بازشوها: بازشو‌های متوسط، ۲۰ تا ۴۰ درصد",index++ , 0);

        //////////////////////////////   DIVARHA
        if(sums[0] <=2 )
            table3.setValueAt("دیوارها: دیوارهای سبک، زمان تاخیر کوتاه",index++ , 0);
        else
            table3.setValueAt("دیوارها: دیوارهای سنگین داخلی و خارجی",index++ , 0);

        //////////////////////////////   SAGHFHA
        if(sums[0] <= 5)
            table3.setValueAt("سقف‌ها: سقف‌های عایق‌کاری‌شده سبک",index++ , 0);
        else
            table3.setValueAt("سقف‌ها: سقف‌های سنگین، بیش از ۸ ساعت زمان تاخیر",index++ , 0);

        //////////////////////////////   KHAB E BIROON
        if(sums[1] >= 2 )
            table3.setValueAt("خواب بیرون: فضایی برای خواب بیرون مورد نیاز است",index++,0);

        //////////////////////////////   MOHAFEZAT AZ BARAN
        if(sums[5] >= 3)
            table3.setValueAt("محافظت از باران: محافظت در برابر باران سنگین مورد نیاز است",index++ , 0);

        //////////////////////////////   ROZANE
        if(sums[0] <= 1 && sums[2] == 0)
            table3.setValueAt("روزنه‌، نورگیر و پنجره وسیع: 40 تا 80 درصد دیواره‌های شمالی و جنوبی",index++ , 0);
        if(sums[2] >= 1 || (sums[0] >= 2 && sums[0]<=5))
            table3.setValueAt("روزنه، نورگیر، پنجره متوسط: 25 تا 40 درصد مساحت دیوار",index++ , 0);
        if(sums[0]>=6 && sums[0]<=10)
            table3.setValueAt("روزنه، نورگیر، پنجره کوچک: 15 تا 25 درصد مساحت دیوار",index++ , 0);
        if(sums[2] <= 3 || sums[0] >= 11)
            table3.setValueAt("روزنه، نورگیر و پنجره بسیار کوچک: 10 تا 20 درصد مساحت دیوار",index++ , 0);
        if(sums[0] >= 11 && sums[2] >= 4)
            table3.setValueAt("روزنه، نورگیر و پنجره متوسط: 25 تا 45 درصد مساحت دیوار",index++ , 0);

        //////////////////////////////   MAHALE ROZANE
        if(sums[0] <= 5 || sums[3] >= 3)
            table3.setValueAt("محل روزنه: در دیوار‌های شمالی و جنوبی، بدن انسان رو به باد و nر ارتفاع بدن انسان",index++ , 0);
        if(sums[0] >= 6 || sums[4] >= 2)
            table3.setValueAt("محل روزنه: در دیوار\u200Cهای شمالی و جنوبی، بدن انسان رو به باد و nر ارتفاع بدن انسان همین‌طور روزنه در دیوارهای داخلی نیز تعبیه شود",index++ , 0);

        //////////////////////////////   HEFAZATE ROZANE
        if(sums[2] <= 2)
            table3.setValueAt("حفاظت روزنه: روزنه از اشعه مستقیم آفتاب حفاظت شود",index++ , 0);
        if(sums[5] >= 2)
            table3.setValueAt("حفاظت روزنه: روزنه در مقابل باران حفاظت شود",index++ , 0);

        //////////////////////////////   DIVARHA VA KAFHA
        if(sums[0] <= 2)
            table3.setValueAt("دیوارها و کف‌ها: ظرفیت گرمایی کم و سبک",index++ , 0);
        else
            table3.setValueAt("دیوارها و کف‌ها: سنگین، زمان تاخیر بیش از ۸ ساعت",index++ , 0);

        //////////////////////////////   SAGHFHA VA FAZAHAYE KHAREJI
        if(sums[3] >= 10){
            if(sums[0] <= 2)
                table3.setValueAt("سقف‌ها: سبک، سطح منعکس‌کننده، دوجداره",index++ , 0);
            else
                table3.setValueAt("سقف‌ها: سبک، عایق سدی خوب",index++ , 0);
        }
        else
        {
            if(sums[0] <= 5)
                table3.setValueAt("سقف‌ها سبک، عایق سدی خوب",index++ , 0);
            else
                table3.setValueAt("سقف‌ها سنگین، زمان تاخیر بیش از ۸ ساعت",index++ , 0);
        }


        if(sums[1] >= 1)
            table3.setValueAt("فضای خارجی: فضا برای خوابیدن در فضای آزاد", index ++ , 0);
        if(sums[5] >= 1)
            table3.setValueAt("فضای خارجی: تدارکات کافی برای رد‌کردن آب باران",index++ , 0);




    }


    @Override
    protected double computeRes(double[] inputs, int index){
        double B10 = inputs[0] ;
        double B11 = inputs[1] ;

        if(AH23 == Double.MIN_VALUE) {
            for (int i = 0; i < varTable.getRowCount(); i++) {
                if (varTable.getValueAt(i, 0) == null || varTable.getValueAt(i, 0).equals(" ") || varTable.getValueAt(i, 0).equals(""))
                    continue;
                else {
                    AH23 = Math.max(AH23, getCellData(varTable, i, 1));
                    AH24 = Math.min(AH24, getCellData(varTable, i, 1));
                }
            }

            AMT = getCellData(constTable,0,1) ;
            AMR = AH24-AH23 ;
        }


        double C8 = inputs[2];
        int C9 ;
        if(C8<30)
            C9 = 1;
        else if(C8>=30&&C8<50)
            C9 = 2;
        else if(C8>=50&&C8<70)
            C9 = 3;
        else
            C9 = 4 ;

        double maxDayComf = 0 ;
        double minDayComf = 0;
        double maxNightComf = 0;
        double minNightComf = 0;

        switch (C9){
            case 1:
                if(AMT > 20)
                {
                    maxDayComf = 34 ;
                    minDayComf = 26 ;
                    maxNightComf = 25 ;
                    minNightComf = 17 ;
                }
                else if(AMT > 15)
                {
                    maxDayComf = 32 ;
                    minDayComf = 23 ;
                    maxNightComf = 23 ;
                    minNightComf = 14 ;
                }
                else
                {
                    maxDayComf = 30 ;
                    minDayComf = 21 ;
                    maxNightComf = 21 ;
                    minNightComf = 12 ;
                }
                break;
            case 2:
                if(AMT > 20)
                {
                    maxDayComf = 31 ;
                    minDayComf = 25 ;
                    maxNightComf = 24 ;
                    minNightComf = 17 ;
                }
                else if(AMT > 15)
                {
                    maxDayComf = 30 ;
                    minDayComf = 22 ;
                    maxNightComf = 22 ;
                    minNightComf = 14 ;
                }
                else
                {
                    maxDayComf = 27 ;
                    minDayComf = 20 ;
                    maxNightComf = 20 ;
                    minNightComf = 12 ;
                }
                break;
            case 3:
                if(AMT > 20)
                {
                    maxDayComf = 29 ;
                    minDayComf = 23 ;
                    maxNightComf = 23 ;
                    minNightComf = 17 ;
                }
                else if(AMT > 15)
                {
                    maxDayComf = 28 ;
                    minDayComf = 21 ;
                    maxNightComf = 21 ;
                    minNightComf = 14 ;
                }
                else
                {
                    maxDayComf = 26 ;
                    minDayComf = 19 ;
                    maxNightComf = 19 ;
                    minNightComf = 12 ;
                }
                break;
            case 4:
                if(AMT > 20)
                {
                    maxDayComf = 27 ;
                    minDayComf = 22 ;
                    maxNightComf = 21 ;
                    minNightComf = 17 ;
                }
                else if(AMT > 15)
                {
                    maxDayComf = 25 ;
                    minDayComf = 20 ;
                    maxNightComf = 20 ;
                    minNightComf = 14 ;
                }
                else
                {
                    maxDayComf = 24 ;
                    minDayComf = 18 ;
                    maxNightComf = 18 ;
                    minNightComf = 12 ;
                }
                break;
        }

        double dayComfort = 0 ;
        if(B10 > maxDayComf)
            dayComfort = 1 ;
        else if(B10 < minDayComf)
            dayComfort = -1 ;

        double nightComfort = 0 ;
        if(B11 > maxNightComf)
            nightComfort = 1 ;
        else if(B11 < minNightComf)
            nightComfort = -1 ;

        double A1 = 0 ;
        double A2 = 0 ;
        double A3 = 0 ;
        double H1 = 0 ;
        double H2 = 0 ;
        double H3 = 0 ;

        if(C9 <= 3 && Math.abs(B10 - B11) >= 10 &&  dayComfort == 1)
            A1 = 1 ;
        if((nightComfort == 1 && C9 <=2)||(nightComfort == 0 && B11 >26.5 && C9 <= 2 && Math.abs(B11-B10) <= 10))
            A2 = 1 ;
        if(nightComfort == -1 && dayComfort == -1)
            A3 = 1 ;

        if((C9 == 4 && dayComfort == 1)||(dayComfort == 1 && Math.abs(B11 - B10) < 10 && C9 >= 2 && C9 <= 3))
            H1 = 1 ;
        if(C9 == 4 && dayComfort == 0 && nightComfort == 0)
            H2 = 1 ;
        if(inputs[3] > 200)
            H3 = 1 ;


        switch (index) {
            case 0:
                return Math.abs(B11 - B10) ;
            case 1:
                return C9 ;
            case 2:
                return maxDayComf ;
            case 3:
                return minDayComf ;
            case 4:
                return maxNightComf ;
            case 5:
                return minNightComf ;
            case 6:
                return dayComfort ;
            case 7:
                return nightComfort ;
            case 8:
                return A1 ;
            case 9:
                return A2 ;
            case 10:
                return A3 ;
            case 11:
                return H1 ;
            case 12:
                return H2 ;
            case 13:
                return H3 ;
            default:
                return 0;
        }

    }

    @Override
    protected String[] getVarList() {
        return new String[]{"میانگین دمای حداکثر ماهانه","میانگین دمای حداقل ماهانه","میانگین رطوبت نسبی","بارندگی ماهیانه","بالاترین فراوانی باد نخستین باد غالب","بالاترین فراوانی دومین اوج جهت باد فرعي", "میانگین حداقل رطوبت نسبی","میانگین حداکثر رطوبت نسبی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"نوسان دمای ماهانه","گروه رطوبت","حداکثر آسایش روز","حداقل آسایش روز","حداکثر آسایش شب","حداقل آسایش شب","تنش حرارتی روز","تنش حرارتی شب","شاخص A1","شاخص A2","شاخص A3","شاخص H1","شاخص H2","شاخص H3"};
    }

    @Override
    protected String[] getConstList() {
        return new String[]{"نام پارامتر" , "مقدار"};
    }

    @Override
    protected boolean hasConstList() {
        return true;
    }

    @Override
    protected String getBoundString(double[] resInput, int i) {
        if(i < 8) {
            double field = resInput[i+1];
            switch ((int) field) {
                case 1:
                    return "H";
                case 0:
                    return "-";
                default:
                    return "C";
            }
        }
        else
        {
            return "" ;
        }
    }
}
