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

        String macAddress = GetNetworkAddress.GetAddress("mac");
        File testFile = new File(".dll") ;
        Authenticated authenticated = new Authenticated();
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
       try {
            if(authenticated == null || !authenticated.registered || !macAddress.equals(authenticated.macAddress)){
                serverAuthentication(authenticated);
            }
        } catch (Exception e) {
            authenticated = serverAuthentication(authenticated);
        }
        if(authenticated.registered) {
            try {
                System.out.println("writting");
                File file = new File("./.dll");

                ObjectOutputStream outputStream= new ObjectOutputStream(new FileOutputStream(file)) ;
                authenticated.macAddress = macAddress ;
                outputStream.writeObject(authenticated);
                outputStream.flush();
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
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
        authenticated.registered = new Boolean(true) ;
        authenticated.license = license ;
        return authenticated ;
    }


    public static void main(String[] args) {
        try {
            new Starter();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getStackTrace());
            System.exit(0);
        }
    }
}
