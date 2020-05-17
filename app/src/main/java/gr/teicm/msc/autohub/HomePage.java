package gr.teicm.msc.autohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.view.MenuInflater;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import gr.teicm.msc.autohub.classes.DataStore;

public class HomePage extends AppCompatActivity {

    private Spinner spinnerBrand;
    private EditText textCar;
    private Spinner spinnerCarType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        spinnerBrand = (Spinner) findViewById(R.id.spinner_Brand);
        textCar = (EditText) findViewById(R.id.editTextCar);
        spinnerCarType = (Spinner) findViewById(R.id.action_bar_spinnerCarType);
        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);

        DataStore.Init(getApplicationContext());

        ArrayAdapter<CharSequence> brandAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.brand_types,
                android.R.layout.simple_spinner_item
        );
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBrand.setAdapter(brandAdapter);


        ArrayAdapter<CharSequence> car_typeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.car_types,
                android.R.layout.simple_spinner_item
        );
        car_typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCarType.setAdapter(car_typeAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String car = textCar.getText().toString();
                int brand = spinnerBrand.getSelectedItemPosition();
                int filter_car_type = spinnerCarType.getSelectedItemPosition();

                Intent intent = new Intent(HomePage.this, CarList.class);
                intent.putExtra("brand", brand);
                intent.putExtra("title", car);
                intent.putExtra("car_type", filter_car_type);
                startActivity(intent);
            }
        });
    }
};