package gr.teicm.msc.autohub.classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import gr.teicm.msc.autohub.R;

public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, Object>> data;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<HashMap<String, Object>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item, null);

        TextView name = (TextView) vi.findViewById(R.id.car_item_car);
        TextView brand = (TextView) vi.findViewById(R.id.car_item_brand);
        TextView price = (TextView) vi.findViewById(R.id.car_item_price);
        TextView type_car = (TextView) vi.findViewById(R.id.car_item_type);
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.car_item_cover);

        HashMap<String, Object> car = new HashMap<String, Object>();
        car = data.get(position);

        // Setting all values in listview
        name.setText((String) car.get(DataStore.KEY_CAR));
        brand.setText((String) car.get(DataStore.KEY_BRANDNAME));
        price.setText((String) car.get(DataStore.KEY_PRICE));
        type_car.setText((String) car.get(DataStore.KEY_TYPENAME));
        imageLoader.DisplayImage((String) car.get(DataStore.KEY_COVERURL), thumb_image);
        return vi;
    }
}
