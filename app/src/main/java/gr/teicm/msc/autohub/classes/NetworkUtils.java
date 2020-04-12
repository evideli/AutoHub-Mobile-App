package gr.teicm.msc.autohub.classes;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    public static String getFileContentsFromFromUrl(String address) {
        //
        //Necessary in order to be able to execute Network Calls on main Thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String contents = null;

        try {
            // defaultHttpClient
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is, "UTF-8"));
            contents = "";
            String data="";

            //read data line-by-line
            while ((data = reader.readLine()) != null){
                contents += data + "\n";
            }

            conn.disconnect();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        // return Contents
        return contents;
    }
}
