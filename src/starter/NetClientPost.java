package starter;

import JSON.JSONArray;
import JSON.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

/**
 * Created by root on 3/15/17.
 *
 */
public class NetClientPost {

    public NetClientPost(String license) throws Exception {

            URL url = new URL("http://softtcic.com/activation");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"license\":"+license+"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

//            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
//            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            String jsonString = "" ;
            while ((output = br.readLine()) != null) {
                jsonString += output ;
            }

            JSONObject jsonObject = new JSONObject(jsonString) ;
            int status = (int) jsonObject.get("status");
            int remaining_activations = (int)jsonObject.get("remaining_activations") ;
            String activation_permission = (String) jsonObject.get("activation_permission");

            if(status == -1)
            {
                JOptionPane.showMessageDialog(null, "شماره سریال وارد شده معتبر نمی‌باشد. لطفا شماره سریال را دوباره بررسی بفرمایید یا با تیم پشتیبانی تماس حاصل نمایید.");
                throw new Exception("invalid license");
            }
            else if(status == -2)
            {
                JOptionPane.showMessageDialog(null, "شماره سریال وارد شده قبلا به تعداد مجاز استفاده شده است و معتبر نیست. لطفا از شماره سریال مخصوص خود استفاده نمایید.");
                throw new Exception("no activation times");
            }
            else {
                Long serial = Long.parseLong(license) ;
                serial += remaining_activations ;
                serial += new Long("1234856998756325") ;
                MessageDigest md = MessageDigest.getInstance("MD5") ;
                md.update(serial.toString().getBytes());

                byte byteData[] = md.digest();

                //convert the byte to hex format method 1
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }

                if(!sb.toString().equals(activation_permission))
                {
                    JOptionPane.showMessageDialog(null, "شماره سریال وارد شده معتبر نمی‌باشد. لطفا شماره سریال را دوباره بررسی بفرمایید یا با تیم پشتیبانی تماس حاصل نمایید.");
                    throw new Exception("activation not permitted");
                }
            }



            conn.disconnect();



    }

}
