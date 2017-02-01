package uiElements;

import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 2/1/17.
 */
public class TerjungChart extends Chart{

    private double[][] data;
    public TerjungChart(ThermalChartFrame parent, double[][] data, String[] pointNames){
        super(parent);
        this.data = data ;
        mainChartImage = "./Imgs/TerjungBG.png" ;
        setMainChart();
        drawChart() ;
        setMappings(pointNames) ;
        repaint();
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
