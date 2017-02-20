package uiElements;

import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 1/31/17.
 */
public class PenwardenChart extends Chart {

    private double[][] data ;
    private double[] xStrides = {36,37,34.5,36,30,30,35,31.2} ;
    private double[] yStrides = {12.2,10.8,12.2,11.8,13,11.3,12.2,13} ;
    private int[] xMargins = {65,75,90,80,80,100,65,80} ;
    private int[] yMargins = {175,155,145,160,160,170,175,160} ;
    public PenwardenChart(ThermalChartFrame parent, double[][] data, String[] pointNames) {
        super(parent);
        this.data = data ;
        selectChart(0);
        drawChart() ;
        setMappings(pointNames) ;
        repaint();
    }

    private void drawChart(){
        for (int i = 0; i < data.length/2; i++) {
            addPoint(new Point((int)data[i][1],(int)data[i][0]), Integer.toString(i));
            addPoint(new Point((int)data[data.length/2 + i][1] + 1,(int)data[data.length/2 + i][0]), Integer.toString(data.length/2 + i));
        }
    }

    @Override
    public void selectChart(int index){
        mainChartImage = "/Imgs/penwardenBG"+Integer.toString(index)+".jpg" ;
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
            return new Color(200,0,128) ;
        else
            return new Color(128,0,200) ;
    }

}
