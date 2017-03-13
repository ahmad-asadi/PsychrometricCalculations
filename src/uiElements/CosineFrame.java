package uiElements;

import com.sun.xml.internal.ws.server.ServerRtException;
import controllers.*;
import facilities.ExcelAdapter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;

/**
 * This class is created by Ahmad Asadi on 1/30/17.
 */
public class CosineFrame extends JFrame{

    private final JTable medarMillTable;
    private JTable sunHoursTable ;
    private JTextField lat ;
    private JPanel panel ;
    private double[][] data;
    private JTabbedPane tabbedPane;
    private double[] medars ;

    public CosineFrame() {
        data = new double[12][13] ;
        medars = new double[12] ;

        setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20 , (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50);
        setLocation(10,20);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        String[][] rowData = new String[1][13] ;
        rowData[0][0] = "میانگین ساعت آفتابی روزانه";

        String[] cols = new String[]{"ماه","فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"};

        String[][] rowData1 = new String[1][13] ;
        rowData1[0][0] = "مدارهای میل";
        rowData1[0][1] = "9.96588881328322";
        rowData1[0][2] = "19.2636251749416";
        rowData1[0][3] = "23.2546178057485";
        rowData1[0][4] = "21.1836935645139";
        rowData1[0][5] = "13.1223682035186";
        rowData1[0][6] = "1.61335714612628";
        rowData1[0][7] = "-10.3301654930191";
        rowData1[0][8] = "-19.4902292262213";
        rowData1[0][9] = "-23.2416248006465";
        rowData1[0][10] = "-21.0963438933451";
        rowData1[0][11] = "-13.2891561850267";
        rowData1[0][12] = "-2.01587453039313";

        String[] cols1 = new String[]{"ماه","فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"};

        medarMillTable = new JTable(rowData1, cols1) ;
        medarMillTable.setSize(getWidth() - 20 , 50);
        medarMillTable.setLocation(0,50);
        medarMillTable.setRowHeight(30);
        for(int i = 0 ; i < medarMillTable.getColumnCount() ; i ++)
            medarMillTable.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer());

        sunHoursTable = new JTable(rowData, cols) ;
        sunHoursTable.setSize(getWidth() - 20 , 50);
        sunHoursTable.setLocation(0,50);
        sunHoursTable.setRowHeight(30);
        for(int i = 0 ; i < sunHoursTable.getColumnCount() ; i ++)
            sunHoursTable.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer());

        JScrollPane jsp = new JScrollPane(sunHoursTable) ;
        jsp.setSize(sunHoursTable.getWidth(), sunHoursTable.getHeight() + 40);
        jsp.setLocation(10 , 120);
        jsp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jsp.setBorder(BorderFactory.createTitledBorder("میانگین ساعت آفتابی روزانه"));
        sunHoursTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sunHoursTable.setFillsViewportHeight(true);
        sunHoursTable.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        ExcelAdapter adapter = new ExcelAdapter(sunHoursTable) ;

        JScrollPane jsp1 = new JScrollPane(medarMillTable) ;
        jsp1.setSize(medarMillTable.getWidth(), medarMillTable.getHeight() + 40);
        jsp1.setLocation(10 , 20);
        jsp1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jsp1.setBorder(BorderFactory.createTitledBorder("مدارهای میل مورد استفاده"));
        medarMillTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        medarMillTable.setFillsViewportHeight(true);
        medarMillTable.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        ExcelAdapter adapter2 = new ExcelAdapter(medarMillTable) ;


        JLabel label = new JLabel("عرض جغرافیایی");
        label.setSize(150,50);
        label.setFont(new Font("B Titr",14,14));
        label.setLocation(getWidth()*4/5 , jsp.getY() + jsp.getHeight() + 20);

        lat = new JTextField() ;
        lat.setSize(150,40);
        lat.setLocation(label.getX() - 180, label.getY());
        lat.addActionListener(e -> btn3Handle());
        lat.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                btn3Handle();
            }
        });

//        JButton btn1 = createButton("مجموع ساعات نظری ماه‌ها",getWidth()*2/5 + 20 , label.getY()) ;
//        JButton btn2 = createButton("مجموع ساعات واقعی با احتساب ساعات نظری ماه‌ها",getWidth()/5 + 20, label.getY()) ;
        JButton btn3 = createButton("محاسبه مراحل برای تک‌تک ماه‌ها",20 , label.getY()) ;

//        btn1.addActionListener(e -> btn1Handle());
//
//        btn2.addActionListener(e -> btn2Handle());

        btn3.addActionListener(e -> btn3Handle());

        panel = new JPanel() ;
        panel.setSize(getWidth() - 40 , getHeight() - label.getY() - label.getHeight() - 50);
        panel.setLocation(20,label.getY() + label.getHeight() + 20);
        panel.setBorder(BorderFactory.createSoftBevelBorder(0));
        panel.setLayout(null);

        getContentPane().add(panel) ;
        getContentPane().add(jsp1) ;
        getContentPane().add(jsp) ;
        getContentPane().add(label) ;
        getContentPane().add(lat) ;
//        getContentPane().add(btn1) ;
//        getContentPane().add(btn2) ;
        getContentPane().add(btn3) ;

        btn3Handle();

        setVisible(true);
    }

    public double getMedarMill(int monthNo){
        try{
            return Double.parseDouble((String) medarMillTable.getValueAt(0,monthNo+1)) ;
        }catch (Exception e){
            return 0 ;
        }
    }

    private JScrollPane createJSP(JTable table){
        JScrollPane jsp = new JScrollPane(table) ;
        jsp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jsp.setSize(table.getSize());
        jsp.setLocation(0,0);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        return jsp;
    }

    private void btn3Handle() {
        if(tabbedPane != null)
            panel.remove(tabbedPane);
        tabbedPane = new JTabbedPane() ;
        tabbedPane.setSize(panel.getSize());
        tabbedPane.setLocation(0,0);


        tabbedPane.addTab("فروردین",createJSP(new FarvardinCosineTable(this)));
        tabbedPane.addTab("اردیبهشت",createJSP(new OrdibeheshtCosineTable(this)));
        tabbedPane.addTab("خرداد",createJSP(new KhordadCosineController(this)));

        tabbedPane.addTab("تیر",createJSP(new TirCosineController(this)));
        tabbedPane.addTab("مرداد",createJSP(new MordadCosineController(this)));
        tabbedPane.addTab("شهریور",createJSP(new ShahrivarCosineController(this)));

        tabbedPane.addTab("مهر",createJSP(new MehrCosineTable(this)));
        tabbedPane.addTab("آبان",createJSP(new AbanCosineController(this)));
        tabbedPane.addTab("آذر",createJSP(new AzarCosineController(this)));

        tabbedPane.addTab("دی",createJSP(new DeyCosineController(this)));
        tabbedPane.addTab("بهمن",createJSP(new BahmanCosineController(this)));
        tabbedPane.addTab("اسفند",createJSP(new EsfandCosineController(this)));

        tabbedPane.addTab("مجموع ساعات نظری ماه‌ها",createJSP(new TheoreticalSumMonthCosineTable(this)));
        tabbedPane.addTab("مجموع ساعات واقعی با احتساب ساعت آفتابی",createJSP(new SecondCosineReportTable(this)));



        tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tabbedPane.setFont(new Font("B Nazanin",14,14));

        panel.add(tabbedPane) ;
        panel.repaint();
    }

    private void btn2Handle() {
        //TODO
    }

    private void btn1Handle() {
        //TODO
    }

    private JButton createButton(String text , int x , int y){
        JButton btn = new JButton(text) ;
        btn.setLocation(x, y);
        btn.setSize(250,40);
        btn.setFont(new Font("B Nazanin",14,14));
        return btn ;
    }

    public double getLat(){
        try{
            return Double.parseDouble(lat.getText()) ;
        }
        catch (Exception e){
            return 0 ;
        }
    }

    public void setMonthSummary(int monthNo, double[] hours) {
        data[monthNo] = hours ;
    }

    public double[][] getData() {
        return data;
    }

    public double getSunHour(int col) {
        try{
           return Double.parseDouble((String) sunHoursTable.getValueAt(0,col + 1 )) ;
        }catch (Exception e){
            return 0 ;
        }
    }

    public void setMedar(int monthNo, double medar){
        medars[monthNo] = medar ;
    }

    public double getMedar(int monthNo) {
        return medars[monthNo];
    }
}

/**
 * @author suhas
 *
 */
class CustomCellRenderer extends DefaultTableCellRenderer {

    /* (non-Javadoc)
     * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        if(column%2 == 0) {
            rendererComp.setBackground(new Color(200, 200, 200));
            rendererComp.setForeground(Color.black);
        }
        else {
            rendererComp.setBackground(Color.WHITE);
            rendererComp.setForeground(Color.black);
        }

        return rendererComp ;
    }

}

