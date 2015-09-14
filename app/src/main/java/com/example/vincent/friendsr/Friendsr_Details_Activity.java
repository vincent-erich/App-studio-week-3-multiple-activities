package com.example.vincent.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Friendsr_Details_Activity extends AppCompatActivity {

    private RatingBar ratingBar;
    private ImageView detailedPicture;
    private TextView detailedInformation;

    private String friendName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate in Friendsr_Details_Activity...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendsr_details_activity);

        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        detailedPicture = (ImageView) findViewById(R.id.picture);
        detailedInformation = (TextView) findViewById(R.id.details);

        Intent activityThatCalled = getIntent();
        friendName = activityThatCalled.getExtras().getString("name");
        String friendDetails = activityThatCalled.getExtras().getString("details");

        showPictureAndDetails(friendDetails);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.friendsr_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showPictureAndDetails(String details) {
        switch (friendName) {
            case "Chandler":
                detailedPicture.setImageResource(R.drawable.chandler);
                break;
            case "Joey":
                detailedPicture.setImageResource(R.drawable.joey);
                break;
            case "Monica":
                detailedPicture.setImageResource(R.drawable.monica);
                break;
            case "Phoebe":
                detailedPicture.setImageResource(R.drawable.phoebe);
                break;
            case "Rachel":
                detailedPicture.setImageResource(R.drawable.rachel);
                break;
            case "Ross":
                detailedPicture.setImageResource(R.drawable.ross);
                break;
        }
        detailedInformation.setText(details);
    }

    public void onRateButtonClick(View view) {
        float rating = ratingBar.getRating();
        Intent sendRating = new Intent();
        sendRating.putExtra("rating", rating);
        sendRating.putExtra("name", friendName);
        setResult(RESULT_OK, sendRating);
        finish();
    }
}
