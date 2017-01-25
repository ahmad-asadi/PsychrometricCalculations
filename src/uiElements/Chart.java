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
public class Chart extends JPanel{

    private Image mainChart ;
    protected String mainChartImage ;
    protected int xMargin = 33  ;
    protected int yMargin = 127 ;
    protected double xStride = 5.04 ;
    protected double yStride = 8.8 ;
    protected int pointRadius = 12 ;
    protected ThermalChartFrame parent;

    private Vector<Point> points ;
    private Vector<String> pointStrings ;
    private Vector<Point> lines ;

    public Chart(ThermalChartFrame parent){
        this.parent = parent ;
        setSize(590 , 590);
        setLocation(5,5);
        setBorder(BorderFactory.createSoftBevelBorder(0));
        points = new Vector<>() ;
        pointStrings = new Vector<>() ;
        lines = new Vector<>() ;
        setLayout(null);
    }

    protected void setMappings(String[] pointNames){
        String[][] maps = new String[pointNames.length][2] ;
        for (int i = 0; i < pointNames.length; i++) {
            maps[i][0] = Integer.toString(i) ;
            maps[i][1] = pointNames[i] ;
        }
        parent.setChartMap(maps);
    }

    protected void setMainChart(){
        try {
            mainChart = ImageIO.read(new FileInputStream(mainChartImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void addPoint(Point p, String text){
        System.out.printf("p.x: %f, p.y: %f\n" , p.getX(), p.getY());
        points.add(p);
        pointStrings.add(text);
    }

    protected void addPointToLine(Point p){
        lines.add(p);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(mainChart, 0,0,getHeight(),getWidth(),this) ;

        Graphics2D g2d = (Graphics2D)(g);
        g2d.setFont(new Font("B Titr",10,18));
        for (int i = 0; i < points.size(); i++) {
            g2d.setColor(setPointColor(i));
            Point p = points.elementAt(i);
//            g2d.setColor(Color.WHITE);
//            g2d.fillRect(getPointX(p.getX()) ,getPointY(p.getY()) - 2*pointRadius,pointRadius*4,pointRadius*3);
            g2d.fillOval(getPointX(p.getX()) , getPointY(p.getY()) ,pointRadius,pointRadius);
//            g2d.setColor(Color.BLUE);
            g2d.drawString(pointStrings.elementAt(i), getPointX(p.getX()) - pointRadius/2,getPointY(p.getY()) - pointRadius/2);
        }

        for (int i = 1; i < lines.size(); i++) {
            Point p1 = lines.elementAt(i-1);
            Point p2 = lines.elementAt(i);
            g2d.drawLine(getPointX(p1.getX()),getPointY(p1.getY()) , getPointX(p2.getX()) , getPointY(p2.getY()));
        }
    }


    protected Color setPointColor(int i) {
        return new Color(0,0,255);

    }

    private int getPointX(double x){
        return (int) (x * xStride + xMargin) ;
    }

    private int getPointY(double y){
        return (int) (getHeight() - y * yStride - yMargin) ;
    }

    public void saveImage(String fileName){
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        this.paint(g);
        g.dispose();
        try{
            File file = new File(fileName) ;
            if(!file.isFile())
                file.createNewFile() ;

            ImageIO.write(bi,"png",file);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
