package uiElements;

import java.awt.*;
import java.util.Vector;

/**
 * This class is created by Ahmad Asadi on 1/31/17.
 */
public class PenwardenChart extends Chart {

    private int chartIndex = 0 ;
    private double[][] data ;
//    private double[] xStrides = {36,37,34.5,36,30,30,35,31.2} ;
//    private double[] yStrides = {12.2,10.8,12.2,11.8,13,11.3,12.2,13} ;
//    private int[] xMargins = {65,75,90,80,80,100,65,80} ;
//    private int[] yMargins = {175,155,145,160,160,170,175,160} ;
    private double[] xStrides = {15,15.5} ;
    private double[] yStrides = {12.2,12.5} ;
    private int[] xMargins = {110,48} ;
    private int[] yMargins = {170,182} ;
    public PenwardenChart(ThermalChartFrame parent, double[][] data, String[] pointNames) {
        super(parent);
        this.data = data ;
        selectChart(0);
        drawChart() ;
        setMappings(pointNames) ;
        repaint();
    }

    private void drawChart(){
        points = new Vector<>();
        pointStrings = new Vector<>();
        for (int i = 0; i < data.length; i++) {
            addPoint(new Point((int)data[i][1],(int)data[i][0]), Integer.toString(i));
            int offset = 20 ;
            if(chartIndex == 1)
                offset = 19 ;
            addPoint(new Point((int)data[i][1] + offset,(int)data[i][0]), Integer.toString(i));
        }
    }

    @Override
    public void paint(Graphics g) {
        drawChart();
        super.paint(g);
    }

        @Override
    public void selectChart(int index){
        chartIndex = index ;
        mainChartImage = "/Imgs/penwardenbg"+Integer.toString(index+1)+".jpg" ;
        xStride = xStrides[index] ;
        yStride = yStrides[index] ;
        xMargin = xMargins[index] ;
        yMargin = yMargins[index] ;
        setMainChart();
        repaint();
    }


    @Override
    protected Color setPointColor(int i){
        if(i % 2 == 0)
            return new Color(200,100,100) ;
        else
            return new Color(200,100,0) ;
    }

}
