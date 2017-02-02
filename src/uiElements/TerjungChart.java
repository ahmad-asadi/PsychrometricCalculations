package uiElements;

import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 2/1/17.
 */
public class TerjungChart extends Chart{

    private double[][] data;
    public TerjungChart(ThermalChartFrame parent, double[][] data, String[] pointNames){
        super(parent);

        xMargin = 150  ;
        yMargin = 75 ;
        xStride = 6.1 ;
        yStride = 6.8 ;

        this.data = data ;
        mainChartImage = "./Imgs/terjungBG.jpg" ;
        setMainChart();
        drawChart() ;
        setMappings(pointNames) ;
        repaint();
    }

    @Override
    protected double getYStride(double x){
        if(x >= 60)
            return 6.8;
        else if(x >= 50)
            return calculateStride(x , 50, 5.2, 60,6.8) ;
        else if(x >= 40)
            return calculateStride(x, 40,3.7,50,5.2) ;
        else if(x >= 30)
            return calculateStride(x, 30,2.7,40,3.7) ;
        else if(x >= 20)
            return calculateStride(x, 20,1.9,30,2.7) ;
        else if(x >= 10)
            return calculateStride(x, 10,1.3,20,1.9) ;
        else if(x >= 0)
            return calculateStride(x, 0,0.9,10,1.3) ;
        else
            return 0.9 ;
    }

    private double calculateStride(double x , double x1, double y1, double x2 , double y2){
        return y1 + ((y2 - y1)/(x2 - x1))*x - ((y2-y1)/(x2-x1))*x1 ;
    }

    private void drawChart(){
        for (int i = 0; i < data.length; i++) {
            addPoint(new Point((int)data[i][0]-40,(int)data[i][1]), Integer.toString(i));
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
