package facilities;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuperTable extends JTable {

    private final JComboBox gender;
    private JComboBox clothesColor ;

    public double ac = 0 ;
    public int g = 1;

    public SuperTable(String[][] rowData, String[] colData){
        super(rowData, colData);
        createCloComboBox();

        gender = new JComboBox<>();
        gender.addItem("مرد");
        gender.addItem("زن");

        gender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = setSelectedGender(gender.getSelectedIndex()) ;
            }
        });

    }

    private int setSelectedGender(int selectedIndex) {
        int res = 0 ;
        switch (selectedIndex){
            case 0:
                res = 1;
                break ;
            case 1:
                res = 2;
                break ;
       }

        return res;
    }

    private void createCloComboBox() {
        clothesColor = new JComboBox() ;
        clothesColor.addItem("سفید") ;
        clothesColor.addItem("طبیعی") ;
        clothesColor.addItem("تاریک") ;
        clothesColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ac = setSelectedColor(clothesColor.getSelectedIndex()) ;
            }
        });
    }

    public TableCellEditor getCellEditor(int row, int column) {
        if (row == 2 & column == 1) {
            return new DefaultCellEditor(clothesColor);
        } else if(row == 3 & column == 1){
            return new DefaultCellEditor(gender) ;
        } else {
            return super.getCellEditor(row, column);
        }
    }

    public double setSelectedColor(int selectedIndex) {
        double res = 0 ;
        switch (selectedIndex){
            case 0:
                res = 1 ;
                break;
            case 1:
                res = 2 ;
                break;
            case 2:
                res = 3 ;
                break;
//            case 3:
//                res = 0.96 ;
//                break;
//            case 4:
//                res = 1.14 ;
//                break;
//            case 5:
//                res = 1.01 ;
//                break;
//            case 6:
//                res = 1.30 ;
//                break;
//            case 7:
//                res = 0.74 ;
//                break;
//            case 8:
//                res = 0.96 ;
//                break;
//            case 9:
//                res = 0.54 ;
//                break;
//            case 10:
//                res = 0.67 ;
//                break;
//            case 11:
//                res = 1.10 ;
//                break;
//            case 12:
//                res = 1.04 ;
//                break;
//            case 13:
//                res = 1.10 ;
//                break;
//            case 14:
//                res = 0.72 ;
//                break;
//            case 15:
//                res = 0.89 ;
//                break;
//            case 16:
//                res = 1.37 ;
//                break;
        }

        return res ;
    }
}