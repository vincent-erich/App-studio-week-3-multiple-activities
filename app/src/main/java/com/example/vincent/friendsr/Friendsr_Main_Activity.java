package com.example.vincent.friendsr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Friendsr_Main_Activity extends AppCompatActivity {

    private TextView textViewChandler, textViewJoey, textViewMonica, textViewPhoebe, textViewRachel, textViewRoss;
    private String[] friendNames, friendDetails;
    private float ratingChandler, ratingJoey, ratingMonica, ratingPhoebe, ratingRachel, ratingRoss;
    private SharedPreferences prefs;

    private final String ratingChandlerKey = "ratingChandlerKey";
    private final String ratingJoeyKey = "ratingJoeyKey";
    private final String ratingMonicaKey = "ratingMonicaKey";
    private final String ratingPhoebeKey = "ratingPhoebeKey";
    private final String ratingRachelKey = "ratingRachelKey";
    private final String ratingRossKey = "ratingRossKey";

    public static final float initialRating = (float) -1;
    public static final float ratingErrorSwitchingActivities = (float) -100;
    public static final String prefsName = "myPREFERENCES";
    public static final String nameKey = "nameKey";
    public static final String detailsKey = "detailsKey";
    public static final String ratingKey = "ratingKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendsr_main_activity);

        textViewChandler = (TextView) findViewById(R.id.text_view_chandler);
        textViewJoey = (TextView) findViewById(R.id.text_view_joey);
        textViewMonica = (TextView) findViewById(R.id.text_view_monica);
        textViewPhoebe = (TextView) findViewById(R.id.text_view_phoebe);
        textViewRachel = (TextView) findViewById(R.id.text_view_rachel);
        textViewRoss = (TextView) findViewById(R.id.text_view_ross);

        friendNames = getResources().getStringArray(R.array.friend_names);
        friendDetails = getResources().getStringArray(R.array.friend_details);

        prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE);

        getPreferences();
        showFriendNamesAndRatings();
    }

    //-----------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.friendsr_main_menu, menu);
        return true;
    }

    //-----------------------------------------------------------------------------

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

    //-----------------------------------------------------------------------------

    private void getPreferences() {
        ratingChandler = prefs.getFloat(ratingChandlerKey, initialRating);
        ratingJoey = prefs.getFloat(ratingJoeyKey, initialRating);
        ratingMonica = prefs.getFloat(ratingMonicaKey, initialRating);
        ratingPhoebe = prefs.getFloat(ratingPhoebeKey, initialRating);
        ratingRachel = prefs.getFloat(ratingRachelKey, initialRating);
        ratingRoss = prefs.getFloat(ratingRossKey, initialRating);
    }

    //-----------------------------------------------------------------------------

    private void showFriendNamesAndRatings() {
        if (ratingChandler != initialRating) {
            textViewChandler.setText(friendNames[0] + " (" + ratingChandler + ")");
        } else {
            textViewChandler.setText(friendNames[0] + " " + getString(R.string.text_not_rated));
        }

        if (ratingJoey != initialRating) {
            textViewJoey.setText(friendNames[1] + " (" + ratingJoey + ")");
        } else {
            textViewJoey.setText(friendNames[1] + " " + getString(R.string.text_not_rated));
        }

        if (ratingMonica != initialRating) {
            textViewMonica.setText(friendNames[2] + " (" + ratingMonica + ")");
        } else {
            textViewMonica.setText(friendNames[2] + " " + getString(R.string.text_not_rated));
        }

        if (ratingPhoebe != initialRating) {
            textViewPhoebe.setText(friendNames[3] + " (" + ratingPhoebe + ")");
        } else {
            textViewPhoebe.setText(friendNames[3] + " " + getString(R.string.text_not_rated));
        }

        if (ratingRachel != initialRating) {
            textViewRachel.setText(friendNames[4] + " (" + ratingRachel + ")");
        } else {
            textViewRachel.setText(friendNames[4] + " " + getString(R.string.text_not_rated));
        }

        if (ratingRoss != initialRating) {
            textViewRoss.setText(friendNames[5] + " (" + ratingRoss + ")");
        } else {
            textViewRoss.setText(friendNames[5] + " " + getString(R.string.text_not_rated));
        }
    }

    //-----------------------------------------------------------------------------

    public void onClickChandler(View view) {
        showDetails(friendNames[0], friendDetails[0], ratingChandler);
    }

    public void onClickJoey(View view) {
        showDetails(friendNames[1], friendDetails[1], ratingJoey);
    }

    public void onClickMonica(View view) {
        showDetails(friendNames[2], friendDetails[2], ratingMonica);
    }

    public void onClickPhoebe(View view) {
        showDetails(friendNames[3], friendDetails[3], ratingPhoebe);
    }

    public void onClickRachel(View view) {
        showDetails(friendNames[4], friendDetails[4], ratingRachel);
    }

    public void onClickRoss(View view) {
        showDetails(friendNames[5], friendDetails[5], ratingRoss);
    }

    //-----------------------------------------------------------------------------

    private void showDetails(String name, String details, float currentRating) {
        Intent goToDetails = new Intent(this, Friendsr_Details_Activity.class);
        goToDetails.putExtra(nameKey, name);
        goToDetails.putExtra(detailsKey, details);
        goToDetails.putExtra(ratingKey, currentRating);
        int result = 1;
        startActivityForResult(goToDetails, result);
    }

    //-----------------------------------------------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            Toast.makeText(this, R.string.text_no_rating_toast, Toast.LENGTH_SHORT).show();
        } else {
            float rating = data.getFloatExtra(ratingKey, ratingErrorSwitchingActivities);

            if(rating == ratingErrorSwitchingActivities) {
                Toast.makeText(this, R.string.text_error_switching_activities, Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(this, getString(R.string.text_rating_toast) + " " + rating, Toast.LENGTH_SHORT).show();
                String name = data.getStringExtra(nameKey);
                setFriendRating(name, rating);
                saveRatingToPreferences(name, rating);
                showFriendNamesAndRatings();
            }
        }
    }

    //-----------------------------------------------------------------------------

    private void setFriendRating(String name, float rating) {
        switch(name) {
            case "Chandler":
                ratingChandler = rating;
                break;
            case "Joey":
                ratingJoey = rating;
                break;
            case "Monica":
                ratingMonica = rating;
                break;
            case "Phoebe":
                ratingPhoebe = rating;
                break;
            case "Rachel":
                ratingRachel = rating;
                break;
            case "Ross":
                ratingRoss = rating;
                break;
        }
    }

    //-----------------------------------------------------------------------------

    private void saveRatingToPreferences(String name, float rating) {
        SharedPreferences.Editor editor = prefs.edit();
        switch (name) {
            case "Chandler":
                editor.putFloat(ratingChandlerKey, rating);
                break;
            case "Joey":
                editor.putFloat(ratingJoeyKey, rating);
                break;
            case "Monica":
                editor.putFloat(ratingMonicaKey, rating);
                break;
            case "Phoebe":
                editor.putFloat(ratingPhoebeKey, rating);
                break;
            case "Rachel":
                editor.putFloat(ratingRachelKey, rating);
                break;
            case "Ross":
                editor.putFloat(ratingRossKey, rating);
                break;
        }
        editor.apply();
    }

    //-----------------------------------------------------------------------------

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putFloat(ratingChandlerKey, ratingChandler);
        outState.putFloat(ratingJoeyKey, ratingJoey);
        outState.putFloat(ratingMonicaKey, ratingMonica);
        outState.putFloat(ratingPhoebeKey, ratingPhoebe);
        outState.putFloat(ratingRachelKey, ratingRachel);
        outState.putFloat(ratingRossKey, ratingRoss);
    }

    //-----------------------------------------------------------------------------

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle inState) {
        super.onRestoreInstanceState(inState);

        ratingChandler = inState.getFloat(ratingChandlerKey);
        ratingJoey = inState.getFloat(ratingJoeyKey);
        ratingMonica = inState.getFloat(ratingMonicaKey);
        ratingPhoebe = inState.getFloat(ratingPhoebeKey);
        ratingRachel = inState.getFloat(ratingRachelKey);
        ratingRoss = inState.getFloat(ratingRossKey);
        showFriendNamesAndRatings();
    }

    //-----------------------------------------------------------------------------

}
