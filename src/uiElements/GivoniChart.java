package uiElements;

import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 2/1/17.
 */
public class GivoniChart extends Chart {

    private double[][] data ;
    public GivoniChart(ThermalChartFrame parent, double[][] data, String[] pointNames) {
        super(parent);

        xMargin = 140  ;
        yMargin = 60 ;
        xStride = 8.8 ;
        yStride = 0.2 ;

        this.data = data ;
        mainChartImage = "./Imgs/givoniBG.jpg" ;
        setMainChart();
        drawChart() ;
        setMappings(pointNames) ;
        repaint();
    }

    @Override
    protected double getYStride(double x){
        if(x >= 45)
            return 13.5;
        else if(x >= 40)
            return calculateStride(x , 40, 11, 45,13.5) ;
        else if(x >= 30)
            return calculateStride(x, 30,6,40,11) ;
        else if(x >= 20)
            return calculateStride(x, 20,3.2,30,6) ;
        else if(x >= 10)
            return calculateStride(x, 10,1.7,20,3.2) ;
        else if(x >= 0)
            return calculateStride(x, 0,0.8,10,1.7) ;
        else if(x >= -5)
            return calculateStride(x, -5,0.5,0,0.8) ;
        else if(x >= -10)
            return calculateStride(x, -10,0.3,-5,0.5) ;
        else
            return 0.15 ;
    }

    private double calculateStride(double x , double x1, double y1, double x2 , double y2){
        return y1 + ((y2 - y1)/(x2 - x1))*x - ((y2-y1)/(x2-x1))*x1 ;
    }

    private void drawChart(){
        for (int i = 0; i < data.length; i++) {
            addPoint(new Point((int)data[i][1],(int)data[i][0]), Integer.toString(i));
        }
    }

    @Override
    protected Color setPointColor(int i){
        if(i < data.length/2)
            return new Color(0,0,255) ;
        else
            return new Color(255,0,0) ;
    }
}
