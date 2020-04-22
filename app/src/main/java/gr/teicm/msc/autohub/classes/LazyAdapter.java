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
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<HashMap<String, Object>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
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
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_item, null);

        TextView title = (TextView)vi.findViewById(R.id.car_item_title);
        TextView author = (TextView)vi.findViewById(R.id.car_item_author);
        TextView genre = (TextView)vi.findViewById(R.id.car_item_genre);
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.car_item_cover);

        HashMap<String, Object> book = new HashMap<String, Object>();
        book = data.get(position);

        // Setting all values in listview
        title.setText((String)book.get(DataStore.KEY_TITLE));
        author.setText((String)book.get(DataStore.KEY_AUTHOR));
        genre.setText((String)book.get(DataStore.KEY_GENRENAME));
        //imageLoader.DisplayImage((String)book.get(DataStore.KEY_COVERURL), thumb_image);
        return vi;
    }
}
