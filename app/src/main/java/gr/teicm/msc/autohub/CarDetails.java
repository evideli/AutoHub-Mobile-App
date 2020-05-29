package gr.teicm.msc.autohub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import gr.teicm.msc.autohub.classes.DataStore;
import gr.teicm.msc.autohub.classes.ImageLoader;

public class CarDetails extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewBrand;
    TextView textViewDescription;
    TextView textViewType;
    TextView textViewYear;
    TextView textViewFuel;
    TextView textViewPrice;
    TextView textViewTransmission;
    Button buttonVisitWebsite;
    ImageView imageViewCover;
    ImageLoader imageLoader;

    HashMap<String, Object> car = null;

    private void findViews() {
        textViewTitle = (TextView) findViewById(R.id.car_details_model);
        textViewBrand = (TextView) findViewById(R.id.car_details_brand);
        textViewDescription = (TextView) findViewById(R.id.car_details_description);
        textViewType = (TextView) findViewById(R.id.car_details_type);
        textViewYear = (TextView) findViewById(R.id.car_details_year);
        textViewFuel = (TextView) findViewById(R.id.car_details_fuel);
        textViewPrice = (TextView) findViewById(R.id.car_details_price);
        textViewTransmission = (TextView) findViewById(R.id.car_details_transmission);
        buttonVisitWebsite = (Button) findViewById(R.id.buttonVisitWebsite);
        imageViewCover = (ImageView) findViewById(R.id.imageViewCover);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        findViews();

        Intent intent = getIntent();
        int carPosition = intent.getIntExtra(DataStore.KEY_POSITION, 0);

        car = DataStore.Cars.get(carPosition);
        String carName = (String) car.get(DataStore.KEY_CAR);
        String carBrand = (String) car.get(DataStore.KEY_BRANDNAME);
        String carType = (String) car.get(DataStore.KEY_TYPENAME);
        String carYear = (String) car.get(DataStore.KEY_YEAR);
        String carFuel = (String) car.get(DataStore.KEY_FUEL);
        String carPrice = (String) car.get(DataStore.KEY_PRICE);
        String carTransmission = (String) car.get(DataStore.KEY_TRANSMISSON);
        String carDescription = (String) car.get(DataStore.KEY_DESCRIPTION);
        String carCoverUrl = (String) car.get(DataStore.KEY_COVERURL);

        textViewTitle.setText(carName);
        textViewType.setText(carType);
        textViewBrand.setText(carBrand);
        textViewDescription.setText(carDescription);
        textViewYear.setText(carYear);
        textViewFuel.setText(carFuel);
        textViewPrice.setText(carPrice);
        textViewTransmission.setText(carTransmission);

        imageLoader = new ImageLoader(this);
        imageLoader.DisplayImage(carCoverUrl, imageViewCover);

        buttonVisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carVideo = (String) car.get(DataStore.KEY_VIDEO);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(carVideo));
                startActivity(browserIntent);
            }
        });
    }
}
