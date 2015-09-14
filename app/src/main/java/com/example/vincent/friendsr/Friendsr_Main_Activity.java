package com.example.vincent.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Friendsr_Main_Activity extends AppCompatActivity {

    private TextView textViewChandler, textViewJoey, textViewMonica, textViewPhoebe, textViewRachel, textViewRoss;
    private String[] friendNames, friendDetails; //friendFullNames
    private float ratingChandler, ratingJoey, ratingMonica, ratingPhoebe, ratingRachel, ratingRoss;

    final int result = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate in Friendsr_Main_Activity...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendsr_main_activity);

        textViewChandler = (TextView) findViewById(R.id.text_view_chandler);
        textViewJoey = (TextView) findViewById(R.id.text_view_joey);
        textViewMonica = (TextView) findViewById(R.id.text_view_monica);
        textViewPhoebe = (TextView) findViewById(R.id.text_view_phoebe);
        textViewRachel = (TextView) findViewById(R.id.text_view_rachel);
        textViewRoss = (TextView) findViewById(R.id.text_view_ross);

        friendNames = getResources().getStringArray(R.array.friend_names);
        //friendFullNames = getResources().getStringArray(R.array.friend_full_names);
        friendDetails = getResources().getStringArray(R.array.friend_details);

        setFriendNames();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.friendsr_main_menu, menu);
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

    private void setFriendNames() {
        textViewChandler.setText(friendNames[0]);
        textViewJoey.setText(friendNames[1]);
        textViewMonica.setText(friendNames[2]);
        textViewPhoebe.setText(friendNames[3]);
        textViewRachel.setText(friendNames[4]);
        textViewRoss.setText(friendNames[5]);
    }

    public void onClickChandler(View view) {
        showDetails("Chandler", friendDetails[0]);
    }

    public void onClickJoey(View view) {
        showDetails("Joey", friendDetails[1]);
    }

    public void onClickMonica(View view) {
        showDetails("Monica", friendDetails[2]);
    }

    public void onClickPhoebe(View view) {
        showDetails("Phoebe", friendDetails[3]);
    }

    public void onClickRachel(View view) {
        showDetails("Rachel", friendDetails[4]);
    }

    public void onClickRoss(View view) {
        showDetails("Ross", friendDetails[5]);
    }

    private void showDetails(String name, String details) {
        Intent goToDetails = new Intent(this, Friendsr_Details_Activity.class);
        goToDetails.putExtra("name", name);
        goToDetails.putExtra("details", details);
        startActivityForResult(goToDetails, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        float rating = data.getFloatExtra("rating", (float) -100);
        Toast.makeText(this, getString(R.string.text_rating_toast) + rating, Toast.LENGTH_SHORT).show();

        String name = data.getStringExtra("name");
        setAndWriteRating(name, rating);
    }

    private void setAndWriteRating(String name, float rating) {
        switch(name){
            case "Chandler":
                ratingChandler = rating;
                textViewChandler.setText(name + " (" + ratingChandler + ")");
                break;
            case "Joey":
                ratingJoey = rating;
                textViewJoey.setText(name + " (" + ratingJoey + ")");
                break;
            case "Monica":
                ratingMonica = rating;
                textViewMonica.setText(name + " (" + ratingMonica + ")");
                break;
            case "Phoebe":
                ratingPhoebe = rating;
                textViewPhoebe.setText(name + " (" + ratingPhoebe + ")");
                break;
            case "Rachel":
                ratingRachel = rating;
                textViewRachel.setText(name + " (" + ratingRachel + ")");
                break;
            case "Ross":
                ratingRoss = rating;
                textViewRoss.setText(name + " (" + ratingRoss + ")");
                break;
        }
    }

}
