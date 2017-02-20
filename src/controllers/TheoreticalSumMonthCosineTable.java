package controllers;

import uiElements.CosineFrame;

import javax.swing.*;
import java.awt.*;

/**
 * This class is created by Ahmad Asadi on 2/18/17.
 */
public class TheoreticalSumMonthCosineTable extends JTable {

    public TheoreticalSumMonthCosineTable(CosineFrame cosineFrame) {
        super(new String[24][13], new String[]{"","فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"});

        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setSize(cosineFrame.getWidth(), cosineFrame.getHeight() - 50);
        setLocation(0,0);

        setValueAt("شمال",0,0);
        setValueAt("15",1,0);
        setValueAt("30",2,0);
        setValueAt("45",3,0);
        setValueAt("60",4,0);
        setValueAt("75",5,0);
        setValueAt("90",6,0);
        setValueAt("105",7,0);
        setValueAt("120",8,0);
        setValueAt("135",9,0);
        setValueAt("150",10,0);
        setValueAt("165",11,0);
        setValueAt("جنوب",12,0);
        setValueAt("-165",13,0);
        setValueAt("-150",14,0);
        setValueAt("-135",15,0);
        setValueAt("-120",16,0);
        setValueAt("-105",17,0);
        setValueAt("-90",18,0);
        setValueAt("-75",19,0);
        setValueAt("-60",20,0);
        setValueAt("-45",21,0);
        setValueAt("-30",22,0);
        setValueAt("-15",23,0);
        double[][] data = cosineFrame.getData() ;
        for(int col = 0 ; col < data.length ; col++){
            for(int row = 0; row < 24 ; row ++){
                if(row == 12)
                    setValueAt(Double.toString(data[col][0]), row , col + 1);
                else if (row > 12)
                    setValueAt(Double.toString(data[col][24-row]), row , col + 1);
                else if (row < 12)
                    setValueAt(Double.toString(data[col][row]),row,col+1);
            }
        }
    }
}

