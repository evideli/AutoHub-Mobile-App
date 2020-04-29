package gr.teicm.msc.autohub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import gr.teicm.msc.autohub.classes.DataStore;

public class CarDetails extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewAuthor;
    TextView textViewGenre;
    Button buttonVisitWebsite;

    HashMap<String, Object> car = null;

    private void findViews() {
        textViewTitle = (TextView) findViewById(R.id.car_details_car);
        textViewAuthor = (TextView) findViewById(R.id.car_details_brand);
        textViewGenre = (TextView) findViewById(R.id.car_details_type);
        buttonVisitWebsite = (Button) findViewById(R.id.buttonVisitWebsite);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        //Animation when this Activity appears


        findViews();

        Intent intent = getIntent();
        int carPosition = intent.getIntExtra(DataStore.KEY_POSITION, 0);

        car = DataStore.Cars.get(carPosition);
        String carName = (String) car.get(DataStore.KEY_CAR);
        String carBrand = (String) car.get(DataStore.KEY_BRAND);
        String carType = (String) car.get(DataStore.KEY_TYPENAME);
        textViewTitle.setText(carName);
        textViewAuthor.setText(carBrand);
        textViewGenre.setText(carType);

    //    buttonVisitWebsite.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View v) {
     //           String carAmazonUrl = (String) car.get(DataStore.KEY_COVER1URL);
     //           Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(carAmazonUrl));
      //          startActivity(browserIntent);
     //       }
      //  });
    }

}
