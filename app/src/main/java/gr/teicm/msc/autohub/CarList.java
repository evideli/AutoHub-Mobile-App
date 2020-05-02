package gr.teicm.msc.autohub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import gr.teicm.msc.autohub.classes.DataStore;

public class CarList extends AppCompatActivity {

    ListView mListCars;
    ImageButton buttonBack;

    private void findViews() {
        mListCars = (ListView) findViewById(R.id.listViewCars);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);


        //get user filters from Intent

        Intent intent = getIntent();
        String filterTitle = intent.getStringExtra("title");
        int filterBrand = intent.getIntExtra("brand", 0);
        int filterCar_Type = intent.getIntExtra("car_type", 0);

        findViews();

        DataStore.LoadCars(filterTitle , filterBrand , filterCar_Type);

        //COMPLEX OBJECT BINDING
        ListAdapter carsAdapter = new SimpleAdapter(
                this,
                DataStore.Cars,
                R.layout.list_item,
                new String[]{DataStore.KEY_CAR,DataStore.KEY_PRICE ,DataStore.KEY_BRANDNAME, DataStore.KEY_TYPENAME},
                new int[]{R.id.car_item_car,R.id.car_item_price, R.id.car_item_brand, R.id.car_item_type}
        );
        mListCars.setAdapter(carsAdapter);

        mListCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(CarList.this, CarDetails.class);
                detailsIntent.putExtra(DataStore.KEY_POSITION, position);
                startActivity(detailsIntent);
            }
        });

    }
}
