package ru.kev163.trueorfalse;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;

public class RateActivity extends Activity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_rate);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        TextView textViewExit = (TextView) findViewById(R.id.textViewExit);

        textViewExit.setOnClickListener(RateActivity.this);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratingForApp();
            }});
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ratingBar:
                ratingForApp();
                finish();
                System.exit(0);
                break;
            case R.id.textViewExit:
                finish();
                System.exit(0);
                break;
        }
    }

    private void ratingForApp(){

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("market://details?id=ru.kev163rus.trueorfalse"));
        this.startActivity(i);

    }
}
