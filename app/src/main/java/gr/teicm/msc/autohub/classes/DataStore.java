package gr.teicm.msc.autohub.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import gr.teicm.msc.autohub.R;

public class DataStore {
    public static String KEY_POSITION = "POSITION";
    public static String KEY_ID = "id";
    public static String KEY_CAR = "title";
    public static String KEY_BRAND = "brand";
    public static String KEY_TYPE = "car_type";
    public static String KEY_TYPENAME = "car_typeName";
    public static String KEY_COVERURL = "cover";
    public static String KEY_COVER1URL = "cover1";


    public static Context AppContext = null;
    public static Resources AppResources = null;
    public static String[] Types = null;
    public static ArrayList<HashMap<String, Object>> Cars = new ArrayList<HashMap<String, Object>>();

    public static void Init(Context context){
        AppContext = context;
        AppResources = AppContext.getResources();
        Types = AppResources.getStringArray(R.array.car_types);
    }

    public static void LoadCars(String filterBrand, String filterTitle, int filterCar_Type) {
        DataStore.Cars.clear();
        String contents = AssetsUtils.getFileContentsFromAssets(AppContext, "cars.json");
        //@SuppressLint("DefaultLocale") String urlString = String.format(" http://127.0.0.1:8000/cars/?title=%s&brand=%s&car_type=%d", filterTitle, filterBrand, filterCar_Type);
        //String contents = NetworkUtils.getFileContentsFromFromUrl(urlString);
        JSONObject json = JsonParser.getJsonObject(contents);
        JSONArray jCars = json.optJSONArray("Cars");
        if (jCars == null) return;
        int nCars = jCars.length();
        for (int i=0; i<nCars; i++){
            JSONObject jCurCars = jCars.optJSONObject(i);
            int carID = jCurCars.optInt(DataStore.KEY_ID, 0);
            String carTitle = jCurCars.optString(DataStore.KEY_CAR);
            String carBrand = jCurCars.optString(DataStore.KEY_BRAND);
            int carType = jCurCars.optInt(DataStore.KEY_TYPE, 0);
            String carCover1Url = jCurCars.optString(DataStore.KEY_COVER1URL);
            String carCoverUrl = jCurCars.optString(DataStore.KEY_COVERURL);

            String carTypeName = DataStore.Types[carType];

            HashMap<String, Object> car = new HashMap<String, Object>();
            car.put(DataStore.KEY_ID, carID);
            car.put(DataStore.KEY_CAR, carTitle);
            car.put(DataStore.KEY_BRAND, carBrand);
            car.put(DataStore.KEY_TYPE, carType);
            car.put(DataStore.KEY_TYPENAME, carTypeName);
            car.put(DataStore.KEY_COVER1URL, carCover1Url);
            car.put(DataStore.KEY_COVERURL, carCoverUrl);

            DataStore.Cars.add(car);
        }
    }

}
