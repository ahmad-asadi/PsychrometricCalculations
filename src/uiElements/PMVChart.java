package uiElements;

import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 1/26/17.
 */
public class PMVChart extends Chart {

    private Point point ;
    public PMVChart(ThermalChartFrame parent, Point point) {
        super(parent);
        this.point = point ;
        mainChartImage = "./Imgs/pmvBG.jpg" ;
        setMainChart();
        drawChart() ;
        repaint();
    }

    private void drawChart(){
        addPoint(point , "نتیجه محاسبات");
    }

    @Override
    protected Color setPointColor(int i){
        return new Color(255,0,0) ;
    }
}
