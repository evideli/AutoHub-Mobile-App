package gr.teicm.msc.autohub.classes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
    public static JSONObject getJsonObject(String jsonString){
        JSONObject jObj = null;

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            jObj = null;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jObj;
    }
}
