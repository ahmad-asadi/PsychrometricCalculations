package controllers;

import uiElements.ThermalChartFrame;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class PMVController extends IndexController {
    private double PMVval ;
    private double PPDval ;
    public PMVController(){
        super();
        setCols();
        numberOfRes = 2 ;
        numberOfVars = 6 ;
        setBoundStrings = false ;
        callIndexedComputeRes = true ;
    }

    @Override
    public void solve(){
        super.solve();
        new ThermalChartFrame("pmv", new double[][]{{PMVval, PPDval}},null) ;
    }

    @Override
    protected double computeRes(double[] input, int index){
        double CLO = input[0] ;
        double MET = input[1] ;
        double TEQ = input[2] ;
        double REL = input[3] ;
        double Tg = input[4] ;
        double V = input[5] ;

        double TA = Math.pow(Math.pow((Tg + 273.15),4) + ((Math.pow((1.335 + V),0.71) + (Tg - TEQ))/(0.95*Math.pow(0.15,0.4))) *100000000,0.25) - 273.15 ;

        double FNSP = Math.exp(16.6536 - 4030.183 / (TEQ + 235)) ;
        double PA = REL * 10 * FNSP ;
        double ICL = 0.155 * CLO ;
        double TAA = TEQ + 273 ;
        double TRA = TA + 273 ;
        double M = 58.15 * MET ;
        double FCL ;

        if (ICL < 0.078)
                FCL = 1 + 1.29 * ICL ;
        else
                FCL = 1.05 + 0.645 * ICL ;

        double TCLA = TAA + (35.5 - TEQ) / (3.5 * (6.45 * ICL + 0.1)) ;
        double P1 = ICL * FCL ;
        double P2 = P1 * 3.96 ;
        double P3 = P1 * 100 ;
        double P4 = P1 * TAA ;
        double P5 = 308.7 - 0.028 * M + P2 * Math.pow((TRA / 100), 4) ;
        double XN = TCLA / 100 ;
        double XF = XN ;
        double EPS = 0.0015 ;

        double HC = 0;
        for (int N = 1 ; N <= 150 ; N++) {
            XF = (XF + XN) / 2;
            HC = 2.38 * Math.pow(Math.abs(100 * XF - TAA), 0.25);
            XN = (P5 + P4 * HC - P2 * Math.pow(XF, 4)) / (100 + P3 * HC);
        }
        double TCL = 100 * XN - 273 ;

        double HL1 = 3.05 * 0.001 * (5733 - 6.99 * M - PA) ;

        double HL2 ;
        if (M > 58.15)
                HL2 = 0.42 * (M - 58.15) ;
        else
                HL2 = 0 ;

        double HL3 = 1.7 * 0.00001 * M * (5867 - PA) ;

        double HL4 = 0.0014 * M * (34 - TEQ) ;

        double HL5 = 3.96 * FCL * (Math.pow(XN, 4) - Math.pow((TRA / 100), 4) );

        double HL6 = FCL * HC * (TCL - TEQ) ;
        double TS = 0.303 * Math.exp(-0.036 * M) + 0.028 ;
        PMVval = TS * (M - HL1 - HL2 - HL3 - HL4 - HL5 - HL6) ;
        PPDval = 100 - 95 * Math.exp(-0.3353 * Math.pow(PMVval, 4) - 0.2179 * Math.pow(PMVval, 2)) ;

        switch (index){
            case 1:
                return PPDval ;
            default:
                return PMVval ;
        }

    }

    protected void setCols(){
        super.setCols();
        String[] row = new String[cols.length];
        row[0] = "رنگ لباس" ;
        model.addRow(row);
        row[0] = "متابولیسم" ;
        model.addRow(row);
        row[0] = "دمای هوا" ;
        model.addRow(row);
        row[0] = "رطوبت نسبی" ;
        model.addRow(row);
        row[0] = "دمای کروی" ;
        model.addRow(row);
        row[0] = "سرعت باد" ;
        model.addRow(row);

        row[0] = "شاخص PPD" ;
        model.addRow(row);
        row[0] = "شاخص PMV" ;
        model.addRow(row);

    }

}
