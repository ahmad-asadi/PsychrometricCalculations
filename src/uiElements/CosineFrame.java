package uiElements;

import com.sun.xml.internal.ws.server.ServerRtException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * This class is created by Ahmad Asadi on 1/30/17.
 */
public class CosineFrame extends JFrame{

    private final JTable table;

    public CosineFrame(){
        setSize(800,800) ;
        setLocation(0,0);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(550,60);
        panel.setLocation((getWidth() - panel.getWidth()) / 2 , 30);
        panel.setBorder(BorderFactory.createSoftBevelBorder(0));

        JPanel talbePanel = new JPanel();
        talbePanel.setLayout(null);
        talbePanel.setSize(getWidth() - 20 , getHeight() - panel.getHeight() - panel.getY() - 50);
        talbePanel.setLocation(10 , panel.getY() + panel.getHeight() + 10);
        talbePanel.setBorder(BorderFactory.createSoftBevelBorder(0));

        JLabel lat = new JLabel("عرض جغرافیایی");
        lat.setFont(new Font("Tahoma",12,18));
        lat.setSize(120,40);
        lat.setLocation(panel.getWidth() - lat.getWidth() - 20 , 10);

        JTextField latField = new JTextField() ;
        latField.setSize(120,40);
        latField.setLocation(lat.getX() - latField.getWidth() - 10,lat.getY());

        JButton solve = new JButton("محاسبه") ;
        solve.setSize(100,40);
        solve.setLocation(latField.getX() - solve.getWidth() - 10 , latField.getY());
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] rowData = solve(latField);
                TableModel model = table.getModel();
                for(int i = 0 ; i < rowData.length ; i++)
                    for(int j = 0 ; j < rowData[i].length ; j++)
                        model.setValueAt(rowData[i][j],i,j);

            }
        });

        JButton save = new JButton("ذخیره") ;
        save.setSize(100,40);
        save.setLocation(solve.getX() - solve.getWidth() - 10 , latField.getY());
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(new File("./")) ;
                fileChooser.showSaveDialog(CosineFrame.this) ;
                File file = fileChooser.getSelectedFile() ;
                if(!file.isFile())
                    try {
                        file.createNewFile() ;
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(CosineFrame.this, "قادر به ایجاد فایل مذکور نیستیم. لطفا دوباره سعی نمایید.");
                        return;
                    }

                TableModel model = table.getModel() ;
                try {
                    PrintStream ps = new PrintStream(file) ;
                    for (int i = 0 ; i < table.getRowCount() ; i++){
                        for (int j = 0 ; j < table.getColumnCount() ; j++){
                            ps.printf("%s,", model.getValueAt(i,j));
                        }
                        ps.println();
                    }
                    ps.flush();
                    ps.close();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(CosineFrame.this, "قادر به نوشتن داده‌ها در فایل مورد نظر نیستیم. لطفا فایل دیگری را امتحان نمایید.");
                    return;
                }


            }
        });

        panel.add(solve) ;
        panel.add(save) ;
        panel.add(lat) ;
        panel.add(latField) ;


        String[][] rowData = solve(latField);


        String[] cols = new String[]{"ستون اول","ستون دوم","ستون سوم","ستون چهارم","ستون پنجم","ستون ششم","ستون هفتم","ستون هشتم","ستون نهم","ستون دهم","ستون یازدهم","ستون دوازدهم","ستون سیزدهم","ستون چهاردهم","ستون پانزدهم"} ;

        table = new JTable(rowData ,cols) ;
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JScrollPane jsp = new JScrollPane(table) ;
        jsp.setSize(talbePanel.getSize());
        jsp.setLocation(0,0);
        jsp.setBorder(BorderFactory.createSoftBevelBorder(0));
        jsp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);




        talbePanel.add(jsp) ;

        getContentPane().add(talbePanel) ;
        getContentPane().add(panel) ;

        setVisible(true);
    }

    private String[][] solve(JTextField latField) {
        String[][] rowData = new String[55][15] ;
        rowData[0] = new String[]{"","ساعت","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"} ;
        rowData[1] = new String[]{"","ساعت h","-90","-75","-60","-45","-30","-15","0","-15","-30","-45","-60","-75","-90"} ;
        rowData[2] = new String[]{"","ساعت h","90","75","60","45","30","15","0","15","30","45","60","75","90"} ;

        rowData[3][1] = "ارتفاع خورشید میلادی" ;
        rowData[4][1] = "وارون ارتفاع خورشید میلادی" ;
        rowData[5][1] = "کسینوس ارتفاع خورشید" ;
        rowData[6][1] = "جهت تابش با سمت نسبت به جنوب" ;
        double E5 = 0 ;
        E5 = getLat(latField);
        for(int i = 0 ; i < 13 ; i++) {
            double O2 = 9.96588881328322 ;
            double D15 = Double.parseDouble(rowData[2][2+i]) ;

            double D16 = (((Math.cos(Math.toRadians(E5)))*(Math.cos(Math.toRadians(O2))*(Math.cos(Math.toRadians(Double.parseDouble(rowData[1][2 + i]))))))+((Math.sin(Math.toRadians(E5)))*(Math.sin(Math.toRadians(O2))))) ;
            rowData[3][2 + i] = Double.toString(D16) ;
            double D17 = Math.toDegrees(Math.asin(D16)) ;
            rowData[4][2 + i] = Double.toString(D17) ;
            double D19 = Math.cos(Math.toRadians(Double.parseDouble(rowData[4][i+2]))) ;
            rowData[5][2 + i] = Double.toString(D19) ;
            double D20 = 180-(Math.toDegrees(Math.asin(((Math.cos(Math.toRadians(O2))*Math.sin(Math.toRadians(D15)))/Math.cos(Math.toRadians(D17)))))) ;
            rowData[6][2 + i] = Double.toString(D20) ;
        }

        rowData[7] = new String[]{"","ساعت","ساعت 6","ساعت 7","ساعت 8","ساعت 9","ساعت 10","ساعت 11","ساعت 12","ساعت 13","ساعت 14","ساعت 15","ساعت 16","ساعت 17","ساعت 18"} ;

        rowData[8][0] = "زاویه دیوار" ;
        rowData[8][1] = "0" ;

        rowData[9][0] = "زاویه دیوار" ;
        rowData[9][1] = "15" ;

        rowData[10][0] = "زاویه دیوار" ;
        rowData[10][1] = "30" ;

        rowData[11][0] = "زاویه دیوار" ;
        rowData[11][1] = "45" ;

        rowData[12][0] = "زاویه دیوار" ;
        rowData[12][1] = "60" ;

        rowData[13][0] = "زاویه دیوار" ;
        rowData[13][1] = "75" ;

        rowData[14][0] = "زاویه دیوار" ;
        rowData[14][1] = "90" ;

        rowData[15][0] = "زاویه دیوار" ;
        rowData[15][1] = "105" ;

        rowData[16][0] = "زاویه دیوار" ;
        rowData[16][1] = "120" ;

        rowData[17][0] = "زاویه دیوار" ;
        rowData[17][1] = "135" ;

        rowData[18][0] = "زاویه دیوار" ;
        rowData[18][1] = "150" ;

        rowData[19][0] = "زاویه دیوار" ;
        rowData[19][1] = "165" ;

        rowData[20][0] = "زاویه دیوار" ;
        rowData[20][1] = "180" ;


        rowData[21] = new String[]{"","ساعت","ساعت 6","ساعت 7","ساعت 8","ساعت 9","ساعت 10","ساعت 11","ساعت 12","ساعت 13","ساعت 14","ساعت 15","ساعت 16","ساعت 17","ساعت 18"} ; ;


        for(int i = 0 ; i < 13 ; i++){
            for (int j = 0 ; j < 13 ; j++){
                double Cj = Double.parseDouble(rowData[j + 8][1]) ;
                double D19 = Math.cos(Math.toRadians(Double.parseDouble(rowData[4][i+2]))) ;
                double O2 = 9.96588881328322 ;
                double D15 = Double.parseDouble(rowData[2][2+i]) ;
                double D16 = (((Math.cos(Math.toRadians(E5)))*(Math.cos(Math.toRadians(O2))*(Math.cos(Math.toRadians(Double.parseDouble(rowData[1][2 + i]))))))+((Math.sin(Math.toRadians(E5)))*(Math.sin(Math.toRadians(O2))))) ;
                double D17 = Math.toDegrees(Math.asin(D16)) ;
                double D20 = 180-(Math.toDegrees(Math.asin(((Math.cos(Math.toRadians(O2))*Math.sin(Math.toRadians(D15)))/Math.cos(Math.toRadians(D17)))))) ;

                double D22 = (D19*(Math.cos(Math.toRadians(D20-Cj)))) ;

                rowData[j+ 8][i + 2] = Double.toString(D22) ;
                rowData[j+ 8 + 14][i + 2] = Double.toString(Math.max(0,D22)) ;

            }
        }

        rowData[35] = new String[] {"","ساعت","06","07","08","09","10","11","12","13","14","15","16","17","18"} ;
        rowData[36][1] = "وارون ارتفاع خورشید میلادی" ;
        rowData[37][1] = "وارون sin" ;
        rowData[38][1] = "" ;
        rowData[39][1] = "EXP" ;
        rowData[40][1] = "ln" ;
        rowData[41] = new String[] {"","ساعت","06","07","08","09","10","11","12","13","14","15","16","17","18"} ;

        rowData[42][0] = "زاویه دیوار" ;
        rowData[42][1] = "0" ;

        rowData[43][0] = "زاویه دیوار" ;
        rowData[43][1] = "15" ;

        rowData[44][0] = "زاویه دیوار" ;
        rowData[44][1] = "30" ;

        rowData[45][0] = "زاویه دیوار" ;
        rowData[45][1] = "45" ;

        rowData[46][0] = "زاویه دیوار" ;
        rowData[46][1] = "60" ;

        rowData[47][0] = "زاویه دیوار" ;
        rowData[47][1] = "75" ;

        rowData[48][0] = "زاویه دیوار" ;
        rowData[48][1] = "90" ;

        rowData[49][0] = "زاویه دیوار" ;
        rowData[49][1] = "105" ;

        rowData[50][0] = "زاویه دیوار" ;
        rowData[50][1] = "120" ;

        rowData[51][0] = "زاویه دیوار" ;
        rowData[51][1] = "135" ;

        rowData[52][0] = "زاویه دیوار" ;
        rowData[52][1] = "150" ;

        rowData[53][0] = "زاویه دیوار" ;
        rowData[53][1] = "165" ;

        rowData[54][0] = "زاویه دیوار" ;
        rowData[54][1] = "180" ;

        double C54 = 0.196 ;
        double C55 = 360 ;

        for(int i = 0 ; i < 13 ; i++)
        {
            double O2 = 9.96588881328322 ;
            double D16 = (((Math.cos(Math.toRadians(E5)))*(Math.cos(Math.toRadians(O2))*(Math.cos(Math.toRadians(Double.parseDouble(rowData[1][2 + i]))))))+((Math.sin(Math.toRadians(E5)))*(Math.sin(Math.toRadians(O2))))) ;
            double D17 = Math.toDegrees(Math.asin(D16)) ;

            double D57 = D17 ;
            double D58 = Math.sin(Math.toRadians(D57)) ;
            double D59 = C54/D58 ;
            double D60 = Math.exp(D59) ;
            double D61 = C55/D60 ;


            rowData[36][2+i] = Double.toString(D57) ;
            rowData[37][2+i] = Double.toString(D58) ;
            rowData[38][2+i] = Double.toString(D59) ;
            rowData[39][2+i] = Double.toString(D60) ;
            rowData[40][2+i] = Double.toString(D61) ;

            for(int j = 0 ; j < 13 ; j++){
                double D37 = Double.parseDouble(rowData[22+j][2+i]) ;
                double D64 = D61 * D37 ;
                rowData[42+j][2+i] = Double.toString(D64) ;
            }

        }



        return rowData;
    }

    private double getLat(JTextField latField) {
        double e5 = 0 ;
        try{
            e5 = Double.parseDouble(latField.getText()) ;
        }
        catch (Exception e){}
        return e5;
    }


}
