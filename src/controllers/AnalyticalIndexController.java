package controllers;

import facilities.SuperTable;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * This class is created by Ahmad Asadi on 1/21/17.
 */
public abstract class AnalyticalIndexController extends IndexController {

    public AnalyticalIndexController(){
        super();
        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        constTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
//        constTable.getColumnModel().getColumn(1).setPreferredWidth(300);

        for(int i = 0 ; i < 12 ; i++){
            int jolNo = (i<6?i*31 + 1 : 6*31 +1 + (i-6)*30 ) ;
            varTable.setValueAt(Integer.toString(jolNo),i,1);
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

    @Override
    protected double computeRes(double[] inputs, int index){
        return 0; //UNUSED HERE
    }

    protected abstract double computeRes(int rowIndex , int resIndex);

    @Override
    protected String[] getVarList() {
        return new String[]{"شماره ژولیوسی","میانگین دمای هوا","سرعت باد به متر بر ثانیه","رطوبت نسبی","دمای کروی","میزان ابرناکی"};
    }

    @Override
    protected String[] getResList() {
        return new String[]{"مدار میل","ارتفاع خورشید","Lg","LA","R در شرایط آفتابی", "R در شرایط سایه", "فشار بخار آب e"} ;
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
        return 23.45*Math.sin(Math.toRadians(360*(((double)(284+jolNumber))/365))) ;
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
        double N13 = getRPrime(rowIndex) ;
        double O13 = getLg(rowIndex) ;
        double P13 = getLa(rowIndex) ;
        return Math.pow(((N13+O13+P13)/(0.95*0.0000000567)),0.25)-273 ;
    }

    protected double getHInverse(int rowIndex){
        double H2 = getH(rowIndex) ;
        return Math.toDegrees(Math.asin(H2)) ;
    }

    protected double getRPrime(int rowIndex) {

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
        return getInput(rowIndex, 5) ;
    }

    protected double getTmrtShaded(int rowIndex){
        double P14 = getLa(rowIndex) ;
        double O14 = getLg(rowIndex) ;
        double N14 = getRPrime(rowIndex) ;
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
