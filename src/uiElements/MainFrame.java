package uiElements;

import controllers.Facade;
import controllers.IndexController;
import lang.LanguageDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class is created by Ahmad Asadi on 1/10/17.
 */
public class MainFrame extends JFrame {

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

    public MainFrame()
    {
        facade = new Facade();

        titleFont = new Font("Tahoma",20,18) ;
        normalFont = new Font("Tahoma",20,12) ;
        width = 1000;
        height = 600;
        sidePanelWidth = width - 20 ;
        sidePanelHeight = 200 ;
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
        sidePanel.add(indexLabel);
        sidePanel.add(indexName);
        sidePanel.add(solveBtn);
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

        graphPanel.add(jScrollPane,BorderLayout.CENTER) ;
        graphPanel.revalidate();
        graphPanel.repaint();
        revalidate();
        repaint() ;
    }
}
