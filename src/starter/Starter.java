package starter;

import uiElements.MainFrame;

import javax.swing.*;
import java.io.*;
import java.net.*;

/**
 * This class is created by Ahmad Asadi on 1/10/17.
 */
public class Starter {

    public Starter(){
        System.getProperties().list(System.out);
        String username = System.getProperty("user.name") ;
        String testFileDir ;
        if(username.equals("root"))
            testFileDir = "/root/" ;
        else
            testFileDir = "/home/" + username ;

        if(System.getProperty("os.name").toLowerCase().contains("windows"))
            testFileDir = "C:\\users\\" + System.getProperty("user.name") + "\\" ;

        String macAddress = GetNetworkAddress.GetAddress("mac");
        File testFile = new File(testFileDir + "system_psy.dll") ;
        Authenticated authenticated = null;
        InputStream resourceInputStream = null;
        if(testFile.isFile()) {
            try {
                resourceInputStream = new FileInputStream(testFile) ;
            } catch (IOException e1) {
                e1.printStackTrace();
                resourceInputStream = null ;
            }
        }
//        else
//            resourceInputStream = this.getClass().getResourceAsStream("/.dll");
        if (resourceInputStream != null) {
            try {
                System.out.println("reading from resource stream");
                ObjectInputStream inputStream = new ObjectInputStream(resourceInputStream);
                authenticated = (Authenticated) inputStream.readObject();
                System.out.println(authenticated);
                System.out.println(authenticated.macAddress);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(resourceInputStream != null && !authenticated.macAddress.trim().equals("") && macAddress == null)
            macAddress = authenticated.macAddress ;

        if (authenticated != null && macAddress != null && !macAddress.equals("") && !macAddress.equals(" ") && !authenticated.macAddress.equals(macAddress))
            authenticated.macAddress = "" ;

        if(authenticated == null || !authenticated.registered || !macAddress.equals(authenticated.macAddress)){
            authenticated = serverAuthentication(authenticated);
        }

        if(authenticated.registered) {
            try {
                System.out.println("writting");
                File file = new File(testFileDir + "system_psy.dll");

                if(!file.isFile()){
                    file.createNewFile() ;
                }

                ObjectOutputStream outputStream= new ObjectOutputStream(new FileOutputStream(file)) ;
                authenticated.macAddress = macAddress ;
                outputStream.writeObject(authenticated);
                outputStream.flush();
                outputStream.close();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "قادر به ذخیره اطلاعات فعال‌سازی روی سیستم نیستیم. لطفا با تیم پشتیبانی تماس بگیرید.");

            }
            new MainFrame();
        }

    }

    private Authenticated serverAuthentication(Authenticated authenticated) {
        String license = JOptionPane.showInputDialog(null, "لطفا شماره سریال خود را وارد نمایید.");
        license = license.replaceAll("-", "");
        if(!license.equals("alireza1250090377karbalaee")) {
            try {
                new NetClientPost(license);
            } catch (MalformedURLException e) {
                JOptionPane.showMessageDialog(null, "در حال حاضر سرور قابل دسترسی نیست. لطفا بعدا دوباره تلاش کنید.");
                System.exit(0);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "لطفا ابتدا از متصل بودن رایانه خود به اینترنت اطمینان حاصل نمایید و سپس دوباره سعی کنید.");
                System.exit(0);
            } catch (Exception e) {
                System.exit(0);
            }
        }
        if(authenticated == null)
            authenticated = new Authenticated() ;
        authenticated.registered = new Boolean(true) ;
        authenticated.license = license ;
        return authenticated ;
    }


    public static void main(String[] args) {
        try {
            new Starter();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "خطای " + e.getMessage() + " ایجاد شده است. لطفا با تیم پشتیبانی تماس حاصل نمایید.");
            System.exit(0);
        }
    }
}
