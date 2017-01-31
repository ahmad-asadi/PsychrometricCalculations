package uiElements;

import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 1/31/17.
 */
public class PenwardenChart extends Chart {

    private double[][] data ;
    private String[] pointNames ;
    public PenwardenChart(ThermalChartFrame parent, double[][] data, String[] pointNames) {
        super(parent);
        this.data = data ;
        mainChartImage = "./Imgs/penwardenBG.jpg" ;
        setMainChart();
        drawChart() ;
        setMappings(pointNames) ;
        repaint();
    }

    private void drawChart(){
        System.out.println(data.length);
        System.out.println(data[0].length);
        for (int i = 0; i < data.length; i++) {
            addPoint(new Point((int)data[i][1],(int)data[i][0]), Integer.toString(i));
        }
    }

    @Override
    protected Color setPointColor(int i){
        return new Color(200,0,128) ;
    }

}
