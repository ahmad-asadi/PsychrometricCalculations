package controllers;

import javax.swing.*;
import java.util.Vector;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class IndexController extends JTable {
    public IndexController(){


    }

    public IndexController(String[] cols){
        super(new String[3][3], cols) ;
    }

}
