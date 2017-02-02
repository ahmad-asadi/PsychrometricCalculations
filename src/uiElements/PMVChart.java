package uiElements;

import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 1/26/17.
 */
public class PMVChart extends Chart {

    private double[][] data;
    private String[] colNames;
    public PMVChart(ThermalChartFrame parent, double[][] data, String[] colNames) {
        super(parent);

        xMargin = 315  ;
        yMargin = 160 ;
        xStride = 11.8 ;
        yStride = 2.9 ;

        this.data = data ;
        this.colNames = colNames ;
        mainChartImage = "./Imgs/pmvBG.jpg" ;
        setMainChart();
        setMappings(colNames);
        drawChart() ;
        repaint();
    }

    private void drawChart(){
        for(int i = 0 ; i < data.length ; i++) {
            addPoint(new Point((int)data[i][0]*10 , (int)data[i][1]), colNames[i]);
        }
    }

    @Override
    protected Color setPointColor(int i){
        return new Color(255,0,0) ;
    }
}
