package uiElements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This class is created by Ahmad Asadi on 1/25/17.
 */
public class ThermalChartFrame extends JFrame {

    private int width = 800 ;
    private int height = 600 ;

    protected JPanel sidePanel ;
    private JButton saveBtn ;

    protected Chart chart;

    public ThermalChartFrame(String indexName , double[][] data, String[] pointNames){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(width, height);
        setLayout(null);
        sidePanel = createSidePanel() ;

        saveBtn = new JButton("ذخیره نمودار") ;
        saveBtn.setSize(sidePanel.getWidth() - 10, 40);
        saveBtn.setLocation(5,5);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser() ;
                fileChooser.setCurrentDirectory(new File("./"));
                fileChooser.showSaveDialog(ThermalChartFrame.this) ;
                File file = fileChooser.getSelectedFile() ;
                chart.saveImage(file.getAbsolutePath());
            }
        });

        sidePanel.add(saveBtn) ;

        getContentPane().add(sidePanel) ;

        setVisible(true);

        setChartPanel(indexName,data, pointNames);

    }

    public void setChartMap(String[][] map){
        JTable table = new JTable(map, new String[]{"نقطه روی نمودار","نام"}) ;
        table.setSize(sidePanel.getWidth(), sidePanel.getHeight() - 50);
        table.setLocation(0,50);
        JScrollPane jsp = new JScrollPane(table) ;
        table.setFillsViewportHeight(true);
        jsp.setSize(table.getSize());
        jsp.setLocation(0,50);
        sidePanel.add(jsp);
        repaint();
    }

    private JPanel createSidePanel() {
        JPanel panel = new JPanel() ;
        panel.setSize(width - 600 , height);
        panel.setLocation(605, 5);
        panel.setBorder(BorderFactory.createSoftBevelBorder(0));
        panel.setLayout(null);
        return panel ;
    }

    protected void setChartPanel(String indexName,double[][] data,String[] pointNames){
        switch (indexName){
            case "olgay" :
                chart = new OlgayChartPanel(this,data,pointNames) ;
                break;
            default:
                chart = null ;
        }
        getContentPane().add(chart) ;
    }

    public static void main(String[] args){
        new ThermalChartFrame("olgay", null,null) ;
    }
}
