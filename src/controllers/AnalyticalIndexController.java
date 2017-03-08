package controllers;

import facilities.SuperTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 1/21/17.
 */
public abstract class AnalyticalIndexController extends IndexController {

    private final JTable table1;
    private final JTable table2;
    private final JTabbedPane tabbedPane;

    public AnalyticalIndexController(){
        super();
        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        constTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
//        constTable.getColumnModel().getColumn(1).setPreferredWidth(300);

        for(int i = 0 ; i < 12 ; i++){
            int jolNo = (i<6?i*31 + 1 : 6*31 +1 + (i-6)*30 ) ;
            varTable.setValueAt(Integer.toString(jolNo),i,1);
        }

        String[] cols1 = new String[]{"ردیف","مدار میل","وارون ارتفاع خورشید","Lg","LA","R در شرایط آفتابی", "R در شرایط سایه"} ;
        String[] cols2 = concatArrays(new String[]{"ردیف"},getLocalResList()) ;

        table1 = new JTable(new String[400][cols1.length],cols1) ;
        table2 = new JTable(new String[400][cols2.length],cols2) ;


        JScrollPane jsp1 = createNewSubTable(table1);
        JScrollPane jsp2 = createNewSubTable(table2);

        tabbedPane = new JTabbedPane() ;

        tabbedPane.setSize(resJsp.getSize());
        tabbedPane.add("پارامترهای مشترک",jsp1) ;
        tabbedPane.add("پارامترهای مربوط به شاخص",jsp2) ;
        tabbedPane.setLocation(resJsp.getLocation());
        tabbedPane.setBorder(BorderFactory.createBevelBorder(0));
        tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        remove(resJsp);
        add(tabbedPane) ;

    }

    @Override
    protected String[][] getInitRowList(String[] list) {
        String[][] rows =  new String[400][list.length];
        rows[0][0] = "فروردین-مارس" ;
        rows[1][0] = "اردیبهشت-آوریل" ;
        rows[2][0] = "خرداد-می" ;

        rows[3][0] = "تیر-ژوئن" ;
        rows[4][0] = "مرداد-جولای" ;
        rows[5][0] = "شهریور-آگوست" ;

        rows[6][0] = "مهر-سپتامبر" ;
        rows[7][0] = "آبان-اکتبر" ;
        rows[8][0] = "آذر-نوامبر" ;

        rows[9][0] = "دی-دسامبر" ;
        rows[10][0] = "بهمن-ژانویه" ;
        rows[11][0] = "اسفند-فوریه" ;

        return rows;
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

        table1.setFillsViewportHeight(true);
        return jsp1;
    }

    protected abstract String[] getLocalResList() ;

    @Override
    public void solve(){
        super.solve();
        System.out.println(resTable.getColumnCount());
        for(int j = 0 ; j < resTable.getRowCount() ; j++) {
            for (int i = 0; i < 7; i++) {
                table1.setValueAt(resTable.getValueAt(j, i), j, i);
            }
            table2.setValueAt(resTable.getValueAt(j,0),j,0);
            for (int i = 7; i < 7 + getLocalResList().length; i++) {
                table2.setValueAt(resTable.getValueAt(j, i), j, i-6);
            }
        }

    }

    @Override
    protected void setResTable(TableModel resModel) {
        TableModel varModel = varTable.getModel() ;
        for (int i = 0 ; i < resModel.getRowCount() ; i ++)
        {
            if(varModel.getValueAt(i,0) != null && !varModel.getValueAt(i,0).equals("")) {
                for (int j = 0; j < resModel.getColumnCount() - 1; j++) {
                    if (indexOfBoundStrings.contains(new Integer(j))) {
                        double[] resInputs = new double[resModel.getColumnCount() - 1];
                        for (int ri = 1; ri < resModel.getColumnCount() - 1; ri++)
                            try {
                                resInputs[ri] = Double.parseDouble((String) resModel.getValueAt(i, ri));
                            }catch (Exception e){resInputs[ri] = 0 ;}
                        resModel.setValueAt(getBoundString(resInputs, j, (int)i), i, j + 1);
                    }
                    else
                        resModel.setValueAt(Double.toString(computeRes(i, j)), i, j + 1);
                }
            }
        }
    }

    protected String[] concatArrays(String[] str1 , String[] str2){
        String[] res = new String[str1.length + str2.length] ;
        int index = 0 ;
        for (int i = 0; i < str1.length; i++)
            res[index ++] = str1[i] ;
        for (int i = 0; i < str2.length; i++)
            res[index ++] = str2[i] ;
        return res ;
    }

    @Override
    protected double computeRes(double[] inputs, int index, int row){
        return 0 ;
    }

    @Override
    protected double computeRes(double[] inputs, int index){
        return 0 ;
    }

    protected double computeRes(int row , int resIndex){
        switch (resIndex){
            case 0:
                return getMedarMil(row) ;
            case 1:
                return getHInverse(row) ;
            case 2:
                return getLg(row) ;
            case 3:
                return getLa(row) ;
            case 4:
                return getRSunny(row) ;
            case 5:
                return getRShaded(row) ;
            default:
                return -1;
        }
    }

    @Override
    protected String[] getVarList() {
        return new String[]{"شماره ژولیوسی","میانگین دمای هوا","سرعت باد به متر بر ثانیه","رطوبت نسبی","دمای کروی","میزان ابرناکی"};
    }

    @Override
    protected String[] getResList() {
        return concatArrays(new String[]{"مدار میل","ارتفاع خورشید","Lg","LA","R در شرایط آفتابی", "R در شرایط سایه"},getLocalResList()) ;
    }

    @Override
    protected String[] getConstList() {
        return new String[]{"نام پارامتر","مقدار"};
    }

    @Override
    protected boolean hasConstList() {
        return true;
    }

    protected double getLat(){
        try {
            TableModel model = constTable.getModel();
            return Double.parseDouble((String) model.getValueAt(0, 1));
        }catch (Exception e)
        {
            return 0 ;
        }
    }

    protected double getH(int rowIndex){
        double mil = getMedarMil(rowIndex) ;
        double lat = getLat() ;
        double SHA = getSHA(rowIndex) ;
        return  Math.abs((((Math.cos(Math.toRadians(mil)))*(Math.cos(Math.toRadians(lat))*(Math.cos(Math.toRadians(SHA)))))+((Math.sin(Math.toRadians(mil)))*(Math.sin(Math.toRadians(lat)))))) ;
    }

    protected double getSHA(int rowIndex){
        double hour = getCellData(constTable, 4,1);

        return  0.25*(12-hour)*60 ;
    }

    protected double getMedarMil(int rowIndex){
        int jolNumber = (int)getInput(rowIndex,1) ;
        return -23.45*Math.cos(Math.toRadians(360*(((double)(jolNumber+10))/365))) ;
    }

    protected int getJolNumber(int rowIndex){
        double[][] tableData = getTableData(varTable) ;
        int day = (int) tableData[rowIndex][1];
        int month = (int) tableData[rowIndex][2];
        if (month > 12)
            month = 12 ;

        if(month <= 6 && day > 31)
            day = 31 ;
        else if(day > 30)
            day = 30 ;

        int monthDayCount = (month <= 6 ? (31 * (month - 1)) : (31 * 6) + 30 * (month - 7)) ;

        return monthDayCount + day ;
    }

    protected double getAc(){
        int color =  ((SuperTable)constTable).ac ;
        switch (color){
            case 1:
                return 60 ;
            case 2:
                return 30 ;
            default:
                return 5 ;
        }
    }

    protected double getM(){
        return getCellData(constTable,1,1) ;
    }

    protected double getT(int rowIndex) {
        return getInput(rowIndex,2);
    }

    private double getInput(int rowIndex, int column) {
        try{
            return Double.parseDouble((String) varTable.getModel().getValueAt(rowIndex,column));
        }catch (Exception e){
            return 0 ;
        }
    }

    protected double getRH(int rowIndex) {
        return getInput(rowIndex, 4);
    }

    protected double getV(int rowIndex) {
        return getInput(rowIndex, 3);
    }

    protected double getVp(int rowIndex) {
        double B5 = getT(rowIndex) ;
        double B6 = getRH(rowIndex) ;

        return 6.112*Math.pow(10,(7.5*B5/(237.7+B5)))*0.01*B6 ;
    }

    protected double getHV() {
        return 1.1;
    }

    protected double getGender() {
        return ((SuperTable)constTable).g ;
    }

    private double getConstant(int row, int col) {
        try {
            return Double.parseDouble((String) constTable.getModel().getValueAt(row, col));
        }
        catch (Exception e){
            return 0 ;
        }
    }

    protected double getTmrtSunny(int rowIndex) {
        double N13 = getRPrimeSunny(rowIndex) ;
        double O13 = getLg(rowIndex) ;
        double P13 = getLa(rowIndex) ;
        return Math.pow(((N13+O13+P13)/(0.95*0.0000000567)),0.25)-273 ;
    }

    protected double getHInverse(int rowIndex){
        double H2 = getH(rowIndex) ;
        return Math.toDegrees(Math.asin(H2)) ;
    }

    protected double getRPrimeShaded(int rowIndex) {

        double H3 = getHInverse(rowIndex) ;
        return H3<=0?0:getRShaded(rowIndex) ;
    }

    protected double getRPrimeSunny(int rowIndex) {

        double H3 = getHInverse(rowIndex) ;
        return H3<=0?0:getRSunny(rowIndex) ;
    }

    protected double getAp(int rowIndex) {
        return 1000 ;
    }

    protected double getLa(int rowIndex) {
        double Ta = getT(rowIndex) ;
        double O1 = getVp(rowIndex) ;
        return 0.5*(0.97*0.0000000567*Math.pow((273+Ta),4)*(0.82-0.25*Math.pow(10,-0.094*0.75*O1))) ;
    }

    protected double getLg(int rowIndex) {
        double Tg = getInput(rowIndex , 5) ;
        return 0.5*0.97*0.0000000567*Math.pow((273+Tg),4) ;
    }

    protected double getRShaded(int rowIndex){
        double h = getHInverse(rowIndex) ;
        double N = getCloud(rowIndex) ;

        return 1.4*0.951*Math.pow(h,1.039) * (1-0.01*N) ;
    }

    protected double getRSunny(int rowIndex){
        double h = getHInverse(rowIndex) ;
        double N = getInput(rowIndex, 6) ;
        double ac = getAc() ;
        double RSunny = 0 ;

        if(h>4){
            if(N < 3)
                RSunny = 1.4* (-100.428+73.981*Math.log(h))*(1-0.01*ac) ;
            else if(N < 5)
                RSunny = 1.4*Math.exp(5.383-16.072/h) * (1-0.01*ac) ;
            else if(N < 7)
                RSunny = 1.4*Math.exp(5.012-11.805/h) * (1-0.01*ac) ;
            else
                RSunny = 1.4*0.951*Math.pow(h,1.039) * (1-0.01*ac) ;
        }

        return RSunny ;
    }

    protected double getCloud(int rowIndex){
        return getInput(rowIndex, 6) ;
    }

    protected double getTmrtShaded(int rowIndex){
        double P14 = getLa(rowIndex) ;
        double O14 = getLg(rowIndex) ;
        double N14 = getRPrimeShaded(rowIndex) ;
        return Math.pow(((N14+O14+P14)/(0.95*0.0000000567)),0.25)-273 ;
    }

    @Override
    protected String[][] getConstRowList(String[] list) {
        String[][] rows = new String[400][list.length] ;
        rows[0][0] = "عرض جغرافیایی" ;
        rows[1][0] = "متابولیسم" ;
        rows[1][1] = "135" ;
        rows[2][0] = "رنگ لباس" ;
        rows[3][0] = "جنسیت" ;
        rows[3][1] = "1" ;
        rows[4][0] = "ساعت (جهت محاسبه زاویه ساعتی)" ;
        return rows ;
    }


}
