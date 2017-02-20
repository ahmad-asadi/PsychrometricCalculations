package uiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

/**
 * This class is created by Ahmad Asadi on 1/25/17.
 */
public class ThermalChartFrame extends JFrame {

    private int width = 800 ;
    private int height = 600 ;

    private String[][] map ;
    protected JPanel sidePanel ;
    private JButton saveBtn ;

    protected Chart chart;
    private JComboBox penwardenCombo;
    private JScrollPane jsp;

    public ThermalChartFrame(String indexName , double[][] data, String[] pointNames){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(width, height);
        setLayout(null);
        sidePanel = createSidePanel() ;
        setResizable(false);

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
        this.map = map ;
        JTable table = new JTable(map, new String[]{"نقطه روی نمودار","نام"}) ;
        if(penwardenCombo != null)
            table.setSize(sidePanel.getWidth(), sidePanel.getHeight() - penwardenCombo.getHeight() - penwardenCombo.getY() - 10);
        else
            table.setSize(sidePanel.getWidth(), sidePanel.getHeight() - saveBtn.getHeight() - saveBtn.getY() - 10);

        table.setLocation(0,0);
        jsp = new JScrollPane(table) ;
        table.setFillsViewportHeight(true);
        jsp.setSize(table.getSize());
        if(penwardenCombo != null)
            jsp.setLocation(0,penwardenCombo.getY() + penwardenCombo.getHeight() + 10);
        else
            jsp.setLocation(0,saveBtn.getY() + saveBtn.getHeight() + 10);
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
            case "pmv" :
                chart = new PMVChart(this,data, pointNames) ;
                break;
            case "penwarden" :
                addPenComboToSidePanel() ;
                chart = new PenwardenChart(this,data, pointNames) ;
                break;
            case "terjung" :
                chart = new TerjungChart(this,data, pointNames) ;
                break;
            case "givoni" :
                chart = new GivoniChart(this,data, pointNames) ;
                break;
            default:
                chart = null ;
        }
        getContentPane().add(chart) ;
    }

    private void addPenComboToSidePanel() {
        penwardenCombo = new JComboBox() ;
        penwardenCombo.addItem("بهمن - فروردین در آفتاب") ;
        penwardenCombo.addItem("بهمن - فروردین در سایه") ;
        penwardenCombo.addItem("اردیبهشت - تیر در آفتاب") ;
        penwardenCombo.addItem("اردیبهشت - تیر در سایه") ;
        penwardenCombo.addItem("مرداد - مهر در آفتاب") ;
        penwardenCombo.addItem("مرداد - مهر در سایه") ;
        penwardenCombo.addItem("آبان - دی در آفتاب") ;
        penwardenCombo.addItem("آبان - دی در سایه") ;
        penwardenCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chart.selectChart(penwardenCombo.getSelectedIndex());
            }
        });
        penwardenCombo.setSize(saveBtn.getSize());
        penwardenCombo.setLocation(saveBtn.getX() , saveBtn.getHeight() + saveBtn.getY() + 10) ;
        sidePanel.add(penwardenCombo) ;
//        sidePanel.remove(jsp);
//        setChartMap(map);
        sidePanel.repaint();
        repaint();
    }

    public static void main(String[] args){
        new ThermalChartFrame("olgay", null,null) ;
    }
}
