package facilities;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is created by Ahmad Asadi on 2/20/17.
 */
public class PMVTable extends JTable{
    private JComboBox clothesColor ;
    private JComboBox metabolism ;

    public double ac = 0 ;
    public double m = 135 ;

    public PMVTable(String[][] rowData, String[] colData) {
        super(rowData, colData);
        createCloComboBox();

        metabolism = new JComboBox() ;
        metabolism.addItem("استراحت");
        metabolism.addItem("خوابیده");
        metabolism.addItem("درحال تکیه");
        metabolism.addItem("نشسته، ساکت");
        metabolism.addItem("ایستاده آرام");
        metabolism.addItem("قدم‌زدن روی سطح صاف با سرعت 0.55 m/s");
        metabolism.addItem("قدم‌زدن روی سطح صاف با سرعت 0.88 m/s");
        metabolism.addItem("قدم‌زدن روی سطح صاف با سرعت 1.34 m/s");
        metabolism.addItem("قدم‌زدن روی سطح صاف با سرعت 1.80 m/s");
        metabolism.addItem("فعالیت دفتری در حال خواندن، نشسته");
        metabolism.addItem("فعالیت دفتری در حال نوشتن");
        metabolism.addItem("فعالیت دفتری در حال تایپ");
        metabolism.addItem("فعالیت دفتری در حال نقشه‌کشی");
        metabolism.addItem("فعالیت دفتری ایستاده در حال انجام کار سبک مثل کتاب‌داری");
        metabolism.addItem("بایگانی‌کردن نشسته");
        metabolism.addItem("بایگانی‌کردن ایستاده");
        metabolism.addItem("قدم‌زدن در محیط");
        metabolism.addItem("بلندکردن، دسته‌بندی");
        metabolism.addItem("رانندگی اتوموبیل");
        metabolism.addItem("خلبانی هواپیما به طور معمول");
        metabolism.addItem("فرود‌آمدن هواپیما با استفاده از سیستم کنترل");
        metabolism.addItem("خلبانی هواپیمای جنگی");
        metabolism.addItem("رانندگی خودرو سنگین");
        metabolism.addItem("پخت و پز");
        metabolism.addItem("نظافت منزل");
        metabolism.addItem("نشسته، حرکت عضو سنگین بدن");
        metabolism.addItem("اره‌کردن روی میز");
        metabolism.addItem("صنایع الکترونیکی");
        metabolism.addItem("کار ماشینی سنگین");
        metabolism.addItem("حمل کیف");
        metabolism.addItem("کار با بیل و کلنگ");
        metabolism.addItem("خرید کردن");
        metabolism.addItem("ژیمناستیک");
        metabolism.addItem("رقصیدن معمولی");
        metabolism.addItem("نرمش تمرینی");
        metabolism.addItem("تنیس فردی");
        metabolism.addItem("بسکتبال");
        metabolism.addItem("کشتی رقابتی");
        metabolism.addItem("شمشیربازی");
        metabolism.addItem("اسکواش");

        metabolism.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m = setSelectedMetabolism(metabolism.getSelectedIndex()) ;
            }
        });
    }

    private double setSelectedMetabolism(int selectedIndex) {
        double res = 0;
        switch (selectedIndex) {
            case 0:
                res = 0;
                break;
            case 1:
                res = 0.7;
                break;
            case 2:
                res = 0.8;
                break ;
            case 3:
                res = 1;
                break ;
            case 4:
                res = 1.2;
                break ;
            case 5:
                res = 1.9;
                break ;
            case 6:
                res = 2;
                break ;
            case 7:
                res = 2.6;
                break ;
            case 8:
                res = 3.8;
                break ;
            case 9:
                res = 1;
                break ;
            case 10:
                res = 1;
                break ;
            case 11:
                res = 1.1;
                break ;
            case 12:
                res = 1.2;
                break ;
            case 13:
                res = 1.6;
                break ;
            case 14:
                res = 1.2;
                break ;
            case 15:
                res = 1.4;
                break ;
            case 16:
                res = 1.7;
                break ;
            case 17:
                res = 2.1;
                break ;
            case 18:
                res = 1.5;
                break ;
            case 19:
                res = 1.2;
                break ;
            case 20:
                res = 1.8;
                break ;
            case 21:
                res = 2.4;
                break ;
            case 22:
                res = 2.2;
                break ;
            case 23:
                res = 1.8;
                break ;
            case 24:
                res = 2.7;
                break ;
            case 25:
                res = 2.2;
                break;
            case 26:
                res = 1.8;
                break;
            case 27:
                res = 2.2;
                break;
            case 28:
                res = 4;
                break ;
            case 29:
                res = 4;
                break ;
            case 30:
                res = 4.4;
                break ;
            case 31:
                res = 1.6;
                break ;
            case 32:
                res = 3.5;
                break ;
            case 33:
                res = 3.4;
                break ;
            case 34:
                res = 3.5;
                break ;
            case 35:
                res = 3.8;
                break ;
            case 36:
                res = 6.3;
                break ;
            case 37:
                res = 7.85;
                break ;
            case 38:
                res = 7;
                break ;
            case 39:
                res = 7.2;
                break ;
        }

        return res;
    }

    private void createCloComboBox() {
        clothesColor = new JComboBox();
        clothesColor.addItem("شلوارک پیاده‌روی، پیراهن آستین‌کوتاه") ;
        clothesColor.addItem("شلوار، پیراهن آستین‌کوتاه") ;
        clothesColor.addItem("شلوار، پیراهن آستین‌بلند") ;
        clothesColor.addItem("شلوار، پیراهن آستین‌بلند، ژاکت مناسب") ;
        clothesColor.addItem("شلوار،پیراهن‌ آستین‌بلند، ژاکت مناسب، زیرپوش بلند") ;
        clothesColor.addItem("شلوار، پیراهن آستین‌بلند، پلیور آستین‌بلند، تی‌شرت") ;
        clothesColor.addItem("مانند بالا به‌اضافه ژاکت مناسب و زیرپوش بلند") ;
        clothesColor.addItem("شلوار ورزشی، عرق‌گیر") ;
        clothesColor.addItem("لباس‌خواب آستین‌بلند، شلوار پیژامه‌ای بلند، ردای کوتاه با آستین سه‌چهارم، کفش راحتی بدون جوراب") ;
        clothesColor.addItem("دامن بلند‌تر از زانو، پیراهن آستین‌کوتاه، جوراب تنگ، صندل") ;
        clothesColor.addItem("دامن بلند‌تر از زانو، پیراهن آستین‌بلند، زیرپیراهنی نیمه، جوراب تنگ") ;
        clothesColor.addItem("مانند بالا به اضافه پلیور آستین‌بلند") ;
        clothesColor.addItem("مانند بالا به جای پلیور ژاکت مناسب") ;
        clothesColor.addItem("دامن بلند تا قوزک پا، پیراهن آستین‌بلند، ژاکت مناسب، جوراب تنگ") ;
        clothesColor.addItem("بالاپوش آستین‌بلند، تی‌شرت") ;
        clothesColor.addItem("لباس کار، پیراهن آستین‌بلند، تی‌شرت") ;
        clothesColor.addItem("بالاپوش ضد آب، زیرپوش گرمایشی آستین‌بلند، زیرپوش بلند") ;
        clothesColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ac = setSelectedColor(clothesColor.getSelectedIndex());
            }
        });
    }

    public TableCellEditor getCellEditor(int row, int column) {
        if (row == 1 & column == 1) {
            return new DefaultCellEditor(clothesColor);
        } else if (row == 0 & column == 1) {
            return new DefaultCellEditor(metabolism);
        } else {
            return super.getCellEditor(row, column);
        }
    }

    public double setSelectedColor(int selectedIndex) {
        double res = 0;
        switch (selectedIndex) {
            case 0:
                res = 1;
                break;
            case 1:
                res = 2;
                break;
            case 2:
                res = 3;
                break;
            case 3:
                res = 0.96 ;
                break;
            case 4:
                res = 1.14 ;
                break;
            case 5:
                res = 1.01 ;
                break;
            case 6:
                res = 1.30 ;
                break;
            case 7:
                res = 0.74 ;
                break;
            case 8:
                res = 0.96 ;
                break;
            case 9:
                res = 0.54 ;
                break;
            case 10:
                res = 0.67 ;
                break;
            case 11:
                res = 1.10 ;
                break;
            case 12:
                res = 1.04 ;
                break;
            case 13:
                res = 1.10 ;
                break;
            case 14:
                res = 0.72 ;
                break;
            case 15:
                res = 0.89 ;
                break;
            case 16:
                res = 1.37 ;
                break;
        }

        return res;
    }
}