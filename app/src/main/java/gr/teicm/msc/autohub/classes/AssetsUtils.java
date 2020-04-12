package gr.teicm.msc.autohub.classes;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class AssetsUtils {
    public static String getFileContentsFromAssets(Context ctx, String filename) {
        String xml = null;

        try {
            AssetManager am = ctx.getAssets();
            InputStream st = am.open(filename);

            InputStreamReader isr = new InputStreamReader(st);

            StringBuilder sb=new StringBuilder();

            BufferedReader br = new BufferedReader(isr);
            String line;

            while((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            isr.close();
            st.close();

            xml = sb.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        // return XML
        return xml;
    }
}
