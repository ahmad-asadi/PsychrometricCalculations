package uiElements;

import controllers.Facade;
import controllers.IndexController;
import lang.LanguageDictionary;

import javax.swing.*;
import java.awt.*;
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

    public MainFrame()
    {
        facade = new Facade();

        titleFont = new Font("Tahoma",20,18) ;
        normalFont = new Font("Tahoma",20,12) ;
        width = 1000;
        height = 600;
        sidePanelWidth = 2*width/3;
        sidePanelHeight = 60 ;
        sidePanelLocation = new Point(width/6, 20) ;
        graphPanelWidth = width - 20;
        graphPanelHeight = height - sidePanelHeight - 50 ;
        graphPanelLocation = new Point(10, 20 + sidePanelHeight + 10) ;
        sidePanelElementHeight = sidePanelHeight - 20 ;
        sidePanelElementWidth = sidePanelWidth / 2 ;
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
        sidePanel.add(indexLabel);
        sidePanel.add(indexName);
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
        combo.setLocation(5, 10);
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
        panel.setLayout(null);
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
        if(jScrollPane != null)
            graphPanel.remove(jScrollPane);

        jScrollPane = new JScrollPane(indexController) ;
        indexController.setFillsViewportHeight(true);
        jScrollPane.setBorder(BorderFactory.createSoftBevelBorder(1));
        jScrollPane.add(indexController);
        graphPanel.add(jScrollPane) ;
        graphPanel.repaint();
        repaint() ;
    }
}
