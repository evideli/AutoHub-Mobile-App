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

    HashMap<String, Object> book = null;

    private void findViews() {
        textViewTitle = (TextView) findViewById(R.id.car_details_title);
        textViewAuthor = (TextView) findViewById(R.id.car_details_author);
        textViewGenre = (TextView) findViewById(R.id.car_details_genre);
        buttonVisitWebsite = (Button) findViewById(R.id.buttonVisitWebsite);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        //Animation when this Activity appears


        findViews();

        Intent intent = getIntent();
        int bookPosition = intent.getIntExtra(DataStore.KEY_POSITION, 0);

        book = DataStore.Cars.get(bookPosition);
        String bookTitle = (String) book.get(DataStore.KEY_TITLE);
        String bookAuthor = (String) book.get(DataStore.KEY_AUTHOR);
        String bookGenreName = (String) book.get(DataStore.KEY_GENRENAME);
        textViewTitle.setText(bookTitle);
        textViewAuthor.setText(bookAuthor);
        textViewGenre.setText(bookGenreName);

        buttonVisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookAmazonUrl = (String) book.get(DataStore.KEY_AMAZONURL);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bookAmazonUrl));
                startActivity(browserIntent);
            }
        });
    }

}
