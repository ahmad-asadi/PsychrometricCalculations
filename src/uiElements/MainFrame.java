package uiElements;

import controllers.Facade;
import controllers.IndexController;
import facilities.ExcelAdapter;
import lang.LanguageDictionary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is created by Ahmad Asadi on 1/10/17.
 */
public class MainFrame extends JFrame {

    private ExcelAdapter adapter ;
    private Facade facade ;
    private Font normalFont;
    private Font titleFont ;
    private int graphPanelWidth;
    private int graphPanelHeight;
    private Point sidePanelLocation ;
    private Point graphPanelLocation ;
    private int sidePanelHeight;
    private int width , height ;
    private String title ;
    private String locale = "fa" ;
    private JPanel sidePanel ;
    private JPanel graphPanel ;
    private JComboBox<String> indexName ;
    private int sidePanelWidth ;
    private int sidePanelElementHeight;
    private int sidePanelElementWidth;
    private LanguageDictionary languageDictionary;
    private JLabel indexLabel;
    private JScrollPane jScrollPane ;
    private IndexController indexController;
    private JButton solveBtn;
    private JButton resetBtn;
    private JButton addColBtn;
    private JButton removeColBtn;
    private JButton printBtn;
    private JButton saveBtn;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel holLabel;
    private JTextField dayField;
    private JTextField jolField;
    private JTextField monthField;
    private JLabel deltaLabel;
    private JTextField deltaField;
    private JButton cosineBtn;

    public MainFrame()
    {
        facade = new Facade();

        titleFont = new Font("Tahoma",20,18) ;
        normalFont = new Font("Tahoma",20,12) ;
        width = 1000;
        height = 600;
        sidePanelWidth = width - 20 ;
        sidePanelHeight = 150 ;
        sidePanelLocation = new Point(10, 20) ;
        graphPanelWidth = width - 20;
        graphPanelHeight = height - sidePanelHeight - 50 ;
        graphPanelLocation = new Point(10, 20 + sidePanelHeight + 10) ;
        sidePanelElementHeight = 40 ;
        sidePanelElementWidth = sidePanelWidth / 4 ;
        title = getString("applicationTitle");

        languageDictionary = LanguageDictionary.getInstance("fa") ;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        changeLookAndFeel() ;

        initializeFrame() ;
    }

    private String getString(String label) {
        return LanguageDictionary.getInstance(locale).getString(label) ;
    }

    private void changeLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeFrame(){
        setSize(width, height);
        setTitle(title);
        setLayout(null);
        setVisible(true);

        sidePanel = getSidePanel() ;
        graphPanel = getGraphPanel() ;

        createSidePanelComponents() ;


        getContentPane().add(sidePanel) ;
        getContentPane().add(graphPanel) ;



        repaint();
    }

    private void createSidePanelComponents() {
        indexLabel = createIndexLabel() ;
        indexName = createIndexCombo() ;
        solveBtn = createSolveBtn() ;
        resetBtn = createResetBtn() ;
        addColBtn = createAddColBtn() ;
        removeColBtn = createRemoveColBtn() ;
        printBtn = createPrintBtn() ;
        saveBtn = createSaveBtn() ;

        dayLabel = createDayLabel() ;
        monthLabel = createMonthLabel() ;
        holLabel = createHourLabel() ;

        dayField = createDayField() ;
        monthField = createMonthField() ;
        jolField = createJolField() ;

        deltaLabel = createDeltaLabel() ;
        deltaField = createDeltaField() ;

        cosineBtn = createCosineBtn() ;


        sidePanel.add(indexLabel);
        sidePanel.add(indexName);
        sidePanel.add(solveBtn);
        sidePanel.add(resetBtn);
        sidePanel.add(addColBtn);
        sidePanel.add(saveBtn);
        sidePanel.add(printBtn);
        sidePanel.add(removeColBtn);

//        sidePanel.add(dayLabel);
//        sidePanel.add(monthLabel);
//        sidePanel.add(holLabel);
//        sidePanel.add(dayField);
//        sidePanel.add(monthField);
//        sidePanel.add(jolField);
//
//        sidePanel.add(deltaLabel);
//        sidePanel.add(deltaField);
        sidePanel.add(cosineBtn);
    }

    private JButton createCosineBtn() {
        JButton btn = new JButton("محاسبه قانون کسینوس") ;
        btn.setSize(saveBtn.getSize());
        btn.setLocation(removeColBtn.getX() + removeColBtn.getWidth() + 30 , removeColBtn.getY());
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CosineFrame();
            }
        });

        btn.setFont(normalFont);
        return btn;
    }

    private JTextField createDeltaField() {
        JTextField field = new JTextField("محاسبه نشده") ;
        field.setEditable(false);
        field.setSize(indexName.getWidth() - 20 , sidePanelElementHeight - 5);
        field.setLocation(indexName.getX() + 10, deltaLabel.getY());
        field.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        field.setBorder(BorderFactory.createSoftBevelBorder(1));
        return field;
    }

    private JLabel createDeltaLabel() {
        JLabel label = new JLabel("زاویه میل خورشید:") ;
        label.setSize(indexLabel.getSize());
        label.setFont(titleFont);
        label.setLocation(indexLabel.getX() , indexLabel.getY() + 2* sidePanelElementHeight + 15);
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return label ;
    }

    private JTextField createDayField() {
        JTextField field = new JTextField("0") ;
        field.setSize(sidePanelElementWidth/4 , sidePanelElementHeight - 5);
        field.setLocation(dayLabel.getX() - field.getWidth() - 5 , dayLabel.getY());
        field.setFont(titleFont);
        field.setBorder(BorderFactory.createSoftBevelBorder(1));
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                computeDelta();
            }
        });
        return field ;
    }

    private void computeDelta() {
        int day = Integer.parseInt(dayField.getText());
        int month = Integer.parseInt(monthField.getText());

        if (month > 12)
            monthField.setText("12");

        if(month <= 6 && day > 31)
            dayField.setText("31");
        else if(day > 30)
            dayField.setText("30");

        day = Integer.parseInt(dayField.getText());
        month = Integer.parseInt(monthField.getText());

        int monthDayCount = (month <= 6 ? (31 * (month - 1)) : (31 * 6) + 30 * (month - 7)) ;
        int jolNumber = monthDayCount + day;

        double mil = 23.45*Math.sin(Math.toRadians(360*((284+jolNumber)/365))) ;

        deltaField.setText(Double.toString(mil));
        jolField.setText(Integer.toString(jolNumber));
    }

    private JTextField createMonthField() {
        JTextField field = new JTextField("0") ;
        field.setSize(sidePanelElementWidth/4 , sidePanelElementHeight - 5);
        field.setLocation(monthLabel.getX() - field.getWidth() - 5 , monthLabel.getY());
        field.setFont(titleFont);
        field.setBorder(BorderFactory.createSoftBevelBorder(1));
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                computeDelta();
            }
        });
        return field ;
    }

    private JTextField createJolField() {
        JTextField field = new JTextField("0") ;
        field.setSize(sidePanelElementWidth/4  , sidePanelElementHeight - 5);
        field.setLocation(holLabel.getX() - field.getWidth() - 5 , holLabel.getY());
        field.setFont(titleFont);
        field.setBorder(BorderFactory.createSoftBevelBorder(1));
        field.setEditable(false);
        return field ;
    }

    private JLabel createHourLabel() {
        JLabel label = new JLabel("شماره ژولیوسی:") ;
        label.setFont(titleFont);
        label.setLocation(indexLabel.getX() - indexLabel.getWidth()*2/3 , indexLabel.getY() + indexLabel.getHeight() + 10);
        label.setSize(sidePanelElementWidth/3 - 10 , sidePanelElementHeight );
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return label;
    }

    private JLabel createMonthLabel() {
        JLabel label = new JLabel("ماه:") ;
        label.setFont(titleFont);
        label.setLocation(indexLabel.getX() + indexLabel.getWidth()/4 - 50 , indexLabel.getY() + indexLabel.getHeight() + 10);
        label.setSize(sidePanelElementWidth/3 - 20 , sidePanelElementHeight );
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return label;
    }

    private JLabel createDayLabel() {
        JLabel label = new JLabel("روز:") ;
        label.setSize(sidePanelElementWidth/3 - 10 , sidePanelElementHeight);
        label.setLocation(indexLabel.getX() + indexLabel.getWidth()*2/3 , indexLabel.getY() + indexLabel.getHeight() + 10);
        label.setFont(titleFont);
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return label ;
    }

    private JButton createSaveBtn() {
        JButton btn = createSidePanelBtn("ذخیره نتایج جدول",removeColBtn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        return btn;

    }

    private JButton createPrintBtn() {
        JButton btn = createSidePanelBtn("چاپ نتایج جدول",addColBtn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        return btn;

    }

    private JButton createRemoveColBtn() {
        JButton btn = createSidePanelBtn("حذف آخرین ستون",resetBtn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        return btn;

    }

    private JButton createAddColBtn() {
        JButton btn = createSidePanelBtn("اضافه کردن ستون جدید",solveBtn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        return btn;
    }

    private JButton createSidePanelBtn(String text, JButton upperBtn) {
        JButton btn = new JButton(text) ;
        btn.setLocation(upperBtn.getX() , upperBtn.getHeight() + upperBtn.getY() + 5);
        btn.setSize(upperBtn.getSize());
        return btn ;
    }

    private JButton createSolveBtn() {
        JButton button = new JButton() ;
        button.setSize(sidePanelElementWidth-20, sidePanelElementHeight);
        button.setLocation(15,10);
        button.setText("انجام محاسبه") ;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    indexController.solve();
                }catch (Exception ex){ex.printStackTrace();}
            }
        });
        button.setFont(normalFont);
        return button ;
    }


    private JButton createResetBtn() {
        JButton button = new JButton() ;
        button.setSize(sidePanelElementWidth-20, sidePanelElementHeight);
        button.setLocation(15+button.getWidth() + 5,10);
        button.setText("پاک کردن جدول") ;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        button.setFont(normalFont);
        return button ;
    }

    private JLabel createIndexLabel() {
        JLabel label = new JLabel() ;
        label.setText(languageDictionary.getString("indexLabel"));
        label.setSize(sidePanelElementWidth - 20, sidePanelElementHeight);
        label.setLocation(sidePanelWidth - sidePanelElementWidth - 10 , 10);
        label.setFont(titleFont);
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return label;
    }

    private JComboBox<String> createIndexCombo() {
        JComboBox<String> combo = new JComboBox<>(languageDictionary.getStrings()) ;
        combo.removeItem("انتخاب شاخص: ");
        combo.setSize(sidePanelElementWidth + 20 , sidePanelElementHeight);
        combo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        combo.setLocation(sidePanelWidth - 2*sidePanelElementWidth  , 10);
        combo.setFont(normalFont);
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if(state == ItemEvent.SELECTED) {
                    processNewIndex((String) itemEvent.getItem());
                }
            }
        };
        combo.addItemListener(itemListener);
        return combo;
    }

    private void processNewIndex(String item) {
        facade.getCorrespondingTable(item, this);
    }

    private JPanel getGraphPanel() {
        JPanel panel = new JPanel() ;
        panel.setSize(graphPanelWidth , graphPanelHeight);
        panel.setLocation(graphPanelLocation);
        panel.setBorder(BorderFactory.createSoftBevelBorder(0));
        panel.setLayout(new BorderLayout());
        panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        return panel;
    }

    private JPanel getSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setSize(sidePanelWidth , sidePanelHeight);
        sidePanel.setLocation(sidePanelLocation);
        sidePanel.setBorder(BorderFactory.createSoftBevelBorder(0));
        sidePanel.setLayout(null);
        return sidePanel;
    }

    public LanguageDictionary getLanguageDictionary()
    {
        return languageDictionary;
    }

    public void addNewIndexController(IndexController indexController) {
        this.indexController = indexController;
        if(indexController == null)
            return;

        if(jScrollPane != null)
            graphPanel.remove(jScrollPane);

        jScrollPane = new JScrollPane(indexController, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) ;
        jScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        indexController.setFillsViewportHeight(true);
        indexController.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        adapter = new ExcelAdapter(indexController) ;

        graphPanel.add(jScrollPane,BorderLayout.CENTER) ;
        graphPanel.revalidate();
        graphPanel.repaint();
        revalidate();
        repaint() ;
    }
}
