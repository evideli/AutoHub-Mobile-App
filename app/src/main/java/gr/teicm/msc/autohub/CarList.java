package gr.teicm.msc.autohub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import gr.teicm.msc.autohub.classes.DataStore;

public class CarList extends AppCompatActivity {

    GridView mgridCars;
    ImageButton buttonBack;

    private void findViews() {
        mgridCars = (GridView) findViewById(R.id.gridViewCars);
        buttonBack = (ImageButton) findViewById(R.id.imageButtonBack);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);


        //get user filters from Intent
        Intent intent = getIntent();
        String filterAuthor = intent.getStringExtra("AUTHOR");
        String filterTitle = intent.getStringExtra("TITLE");
        int filterGenreId = intent.getIntExtra("GENREID", 0);

        findViews();

        DataStore.LoadCars(filterAuthor, filterTitle, filterGenreId);

        //COMPLEX OBJECT BINDING
        ListAdapter carsAdapter = new SimpleAdapter(
                this,
                DataStore.Cars,
                R.layout.list_item,
                new String[]{DataStore.KEY_TITLE, DataStore.KEY_AUTHOR, DataStore.KEY_GENRENAME},
                new int[]{R.id.car_item_title, R.id.car_item_author, R.id.car_item_genre}
        );
        mgridCars.setAdapter(carsAdapter);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                // Code will run on Button Click
                //
                Intent intent = new Intent(CarList.this, HomePage.class);
                startActivity(intent);
            }
        });

    }
}
