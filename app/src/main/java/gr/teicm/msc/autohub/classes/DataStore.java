package gr.teicm.msc.autohub.classes;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import gr.teicm.msc.autohub.R;

public class DataStore {
    public static String KEY_POSITION = "POSITION";
    public static String KEY_ID = "ID";
    public static String KEY_CAR = "TITLE";
    public static String KEY_BRAND = "AUTHOR";
    public static String KEY_TYPE = "GENREID";
    public static String KEY_TYPENAME = "GENRENAME";
    public static String KEY_AMAZONURL = "AMAZONURL";
    public static String KEY_COVERURL = "COVERURL";

    public static Context AppContext = null;
    public static Resources AppResources = null;
    public static String[] Genres = null;
    public static ArrayList<HashMap<String, Object>> Cars = new ArrayList<HashMap<String, Object>>();

    public static void Init(Context context){
        AppContext = context;
        AppResources = AppContext.getResources();
        Genres = AppResources.getStringArray(R.array.car_types);
    }

    public static void LoadCars(String filterAuthor, String filterTitle, int filterGenreId) {
        DataStore.Cars.clear();
        String contents = AssetsUtils.getFileContentsFromAssets(AppContext, "cars.json");
        //String urlString = String.format("http://informatics.teicm.gr/msc/android/books_sample.json.txt?author=%s&title=%s&genreid=%d", filterAuthor, filterTitle, filterGenreId);
        //String contents = NetworkUtils.getFileContentsFromFromUrl(urlString);
        JSONObject json = JsonParser.getJsonObject(contents);
        JSONArray jCars = json.optJSONArray("Cars");
        if (jCars == null) return;
        int nCars = jCars.length();
        for (int i=0; i<nCars; i++){
            JSONObject jCurCars = jCars.optJSONObject(i);
            int carID = jCurCars.optInt(DataStore.KEY_ID, 0);
            String carName = jCurCars.optString(DataStore.KEY_CAR);
            String carBrand = jCurCars.optString(DataStore.KEY_BRAND);
            int carType = jCurCars.optInt(DataStore.KEY_TYPE, 0);
            String carAmazonUrl = jCurCars.optString(DataStore.KEY_AMAZONURL);
            String carCoverUrl = jCurCars.optString(DataStore.KEY_COVERURL);

            //get Genre name by ID
            String carTypeName = DataStore.Genres[carType];

            // hold each book in a HashMap (Associative Array)
            HashMap<String, Object> car = new HashMap<String, Object>();
            car.put(DataStore.KEY_ID, carID);
            car.put(DataStore.KEY_CAR, carName);
            car.put(DataStore.KEY_BRAND, carBrand);
            car.put(DataStore.KEY_TYPE, carType);
            car.put(DataStore.KEY_TYPENAME, carTypeName);
            car.put(DataStore.KEY_AMAZONURL, carAmazonUrl);
            car.put(DataStore.KEY_COVERURL, carCoverUrl);

            DataStore.Cars.add(car);
        }
    }

}
