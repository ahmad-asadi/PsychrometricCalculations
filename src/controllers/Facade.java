package controllers;

import uiElements.MainFrame;

import javax.swing.*;
import java.util.HashMap;

/**
 * This class is created by Ahmad Asadi on 1/17/17.
 */
public class Facade {
    private MainFrame frame;
    private IndexController indexController = new HK0Controller();
    public void getCorrespondingTable(String selectedItem, MainFrame frame)
    {
        this.frame = frame;
        generateCorrespondingIndexController(selectedItem) ;
        frame.addNewIndexController(indexController);
    }

    private void generateCorrespondingIndexController(String selectedItem) {
        String itemKey = frame.getLanguageDictionary().getKey(selectedItem);
        System.out.println(itemKey);
        switch (itemKey){
            case "Evanz":
                indexController = new EvanzController() ;
                break;
            case "Olgyay":
                indexController = new OlgyayController() ;
                break;
            case "Becker":
                indexController = new BeckerController() ;
                break;
            case "ke(Wind cool Index)":
                indexController = new KeController() ;
                break;
            case "Penwarden":
                indexController = new PenwardenController() ;
                break;
            case "Tek(Equivalent temperature)":
                indexController = new TEKController() ;
                break;
            case "DI (Term hygrometric Index)":
                indexController = new HygrometricController() ;
                break;
            case "Terjung ":
                indexController = new TejungController() ;
                break;
            case "(WCT) Wind chill Temperature":
                indexController = new WCTController() ;
                break;
            case "(WCI) Wind chill index ":
                indexController = new WCIController() ;
                break;
            case "SET(New Standard Effective Temperature)":
                indexController = new SETController() ;
                break;
            case "NET(net effective temperature)":
                indexController = new NETController() ;
                break;
            case "SSI (Summer Simmer Index)":
                indexController = new SSIController() ;
                break;
            case "WBGT(Wet Bulb Globe Temperature)":
                indexController = new WBGTController() ;
                break;
            case "AT(Apparent Temperature)":
                indexController = new ATController() ;
                break;
            case "Thi1(Temperature -Humidity Index)":
                indexController = new THI1Controller() ;
                break;
            case "Thi2(Thom index)":
                indexController = new THI2Controller() ;
                break;
            case "H&K0(wind-chill index)":
                indexController = new HK0Controller() ;
                break;
            case "Sb(Bodman's weather severity index)":
                indexController = new SBController() ;
                break;
            case "D(sultry intensity index)":
                indexController = new DController() ;
                break;
            case "Di(Discomfort Index)":
                indexController = new DIController() ;
                break;
            case "Rsi(Relative Strain Index)":
                indexController = new RSIController() ;
                break;
            case "CI(neurotic pressure index)":
                indexController = new CIController() ;
                break;
            case "Cpi(Cooling Power Index)":
                indexController = new CPIController() ;
                break;
            case "Givoni":
                indexController = new GivoniController() ;
                break;
            case "Mahoney":
                indexController = new MahoneyController() ;
                break;
            case "Humidex":
                indexController = new HumidexController() ;
                break;
            case "HI(Heat Index)":
                indexController = new HIController() ;
                break;
            case "EET (Equivalent effective temperature)":
                indexController = new EETController() ;
                break;
            case "ET (Equivalent temperature)":
                indexController = new ETController() ;
                break;
            case "pphs(Approximated physiological strain index)":
                indexController = new PPHSController() ;
                break;
            case "TS(Thermal Sensation)":
                indexController = new TSController() ;
                break;
            case "ASV(Actual Sensation Vote)":
                indexController = new ASVController() ;
                break;
            case "PMV (predicted mean vote)":
                indexController = new PMVController() ;
                break;
//            case "PET(Physiological Equivalent Temperature)":
//                indexController = new PETController() ;
//                break;
            case "UTCI(Universal Thermal Climate Index)":
                indexController = new UTCIController() ;
                break;
            case "HSI(heat stress index)":
                indexController = new HSIController() ;
                break;
            case "PHS(Physiological strain index)":
                indexController = new PHSController() ;
                break;
            case "STI(Subjective temperature index)":
                indexController = new STIController() ;
                break;
            case "SST(Still shade temperature)":
                indexController = new SSTController() ;
                break;
            case "PST(Physiological subjective temperature)":
                indexController = new PSTController() ;
                break;
            default:
                indexController = null ;
        }
    }
}
