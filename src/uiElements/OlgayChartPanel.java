package uiElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * This class is created by Ahmad Asadi on 1/25/17.
 */
public class OlgayChartPanel extends Chart{

    private double [][] data ;
    public OlgayChartPanel(ThermalChartFrame parent , double[][] data, String[] pointNames){
        super(parent);
        this.data = data ;
        mainChartImage = "./Imgs/OlgayBG.png" ;
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
        if(i < data.length/2)
            return new Color(0,0,255) ;
        else
            return new Color(255,0,0) ;
    }
}
