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
    public static String KEY_ID = "id";
    public static String KEY_CAR = "title";
    public static String KEY_BRAND = "brand";
    public static String KEY_BRANDNAME = "brand_name";
    public static String KEY_TYPE = "car_type";
    public static String KEY_TYPENAME = "car_type_name";

    public static String KEY_FUEL = "fuel";
    public static String KEY_TRANSMISSON = "transmission";
    public static String KEY_YEAR = "year";
    public static String KEY_COUNTRY = "country";
    public static String KEY_PRICE = "price";
    public static String KEY_DESCRIPTION = "description";

    public static String KEY_COVERURL = "cover";
    public static String KEY_COVER1URL = "cover1";
    public static String KEY_VIDEO = "video";


    public static Context AppContext = null;
    public static Resources AppResources = null;
    public static String[] Types = null;
    public static ArrayList<HashMap<String, Object>> Cars = new ArrayList<HashMap<String, Object>>();

    public static void Init(Context context) {
        AppContext = context;
        AppResources = AppContext.getResources();
        Types = AppResources.getStringArray(R.array.car_types);
    }

    public static void LoadCars(String filterTitle, int filterBrand, int filterCarType) {
        DataStore.Cars.clear();

        // Convert the Brand and CarType filters to String and handle the case of 0
        String filterBrandStr = String.valueOf(filterBrand);
        String filterCarTypeStr = String.valueOf(filterCarType);

        if (filterBrandStr.equals("0")) {
            filterBrandStr = "";
        }

        if (filterCarTypeStr.equals("0")) {
            filterCarTypeStr = "";
        }

        String urlString = "http://evideli92.pythonanywhere.com/cars/?title=" + filterTitle + "&brand=" + filterBrandStr + "&car_type=" + filterCarTypeStr;

        String contents = NetworkUtils.getFileContentsFromFromUrl(urlString);

        // Added a name to the jsonArray returned from the Django REST API
        // so that JsonParser works
        contents = "{\"Cars\":" + contents + "}";

        //

        JSONObject json = JsonParser.getJsonObject(contents);
        JSONArray jCars = json.optJSONArray("Cars");


        if (jCars == null) return;
        int nCars = jCars.length();
        for (int i = 0; i < nCars; i++) {
            JSONObject jCurCars = jCars.optJSONObject(i);
            int carID = jCurCars.optInt(DataStore.KEY_ID, 0);
            String carTitle = jCurCars.optString(DataStore.KEY_CAR);

            int carBrand = jCurCars.optInt(DataStore.KEY_BRAND);
            String carBrandName = jCurCars.optString(DataStore.KEY_BRANDNAME);

            int carType = jCurCars.optInt(DataStore.KEY_TYPE, 0);
            String carTypeName = jCurCars.optString(DataStore.KEY_TYPENAME);

            String carFuel = jCurCars.optString(DataStore.KEY_FUEL);
            String carTransmission = jCurCars.optString(DataStore.KEY_TRANSMISSON);
            String carYear = jCurCars.optString(DataStore.KEY_YEAR);
            String carCountry = jCurCars.optString(DataStore.KEY_COUNTRY);
            String carPrice = jCurCars.optString(DataStore.KEY_PRICE);
            String carDescription = jCurCars.optString(DataStore.KEY_DESCRIPTION);

            String carCover1Url = jCurCars.optString(DataStore.KEY_COVER1URL);
            String carCoverUrl = jCurCars.optString(DataStore.KEY_COVERURL);
            String carVideo = jCurCars.optString(DataStore.KEY_VIDEO);


            HashMap<String, Object> car = new HashMap<String, Object>();
            car.put(DataStore.KEY_ID, carID);
            car.put(DataStore.KEY_CAR, carTitle);
            car.put(DataStore.KEY_BRAND, carBrand);
            car.put(DataStore.KEY_BRANDNAME, carBrandName);
            car.put(DataStore.KEY_TYPE, carType);
            car.put(DataStore.KEY_TYPENAME, carTypeName);

            car.put(DataStore.KEY_FUEL, carFuel);
            car.put(DataStore.KEY_TRANSMISSON, carTransmission);
            car.put(DataStore.KEY_YEAR, carYear);
            car.put(DataStore.KEY_COUNTRY, carCountry);
            car.put(DataStore.KEY_PRICE, carPrice);
            car.put(DataStore.KEY_DESCRIPTION, carDescription);


            car.put(DataStore.KEY_COVER1URL, carCover1Url);
            car.put(DataStore.KEY_COVERURL, carCoverUrl);
            car.put(DataStore.KEY_VIDEO, carVideo);


            DataStore.Cars.add(car);
        }
    }

}
