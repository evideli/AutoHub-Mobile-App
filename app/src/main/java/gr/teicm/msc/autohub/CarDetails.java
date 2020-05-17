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
    TextView textViewAuthor;
    TextView textViewGenre;
    Button buttonVisitWebsite;
    ImageView imageViewCover;
    ImageLoader imageLoader;

    HashMap<String, Object> car = null;

    private void findViews() {
        textViewTitle = (TextView) findViewById(R.id.car_details_car);
        textViewAuthor = (TextView) findViewById(R.id.car_details_brand);
        textViewGenre = (TextView) findViewById(R.id.car_details_type);
        buttonVisitWebsite = (Button) findViewById(R.id.buttonVisitWebsite);
        imageViewCover = (ImageView) findViewById(R.id.imageViewCover);
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
        String carBrand = (String) car.get(DataStore.KEY_BRANDNAME);
        String carType = (String) car.get(DataStore.KEY_TYPENAME);
        String carCoverUrl = (String) car.get(DataStore.KEY_COVERURL);
        textViewTitle.setText(carName);
        textViewAuthor.setText(carBrand);
        textViewGenre.setText(carType);
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
