package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;

import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import static com.willy.ratingbar.BaseRatingBar.TAG;

/**
 * Created by info on 24-04-2018.
 */

public class ExampleActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,  boolean fromUser) {
                Log.e("message",String.valueOf(rating));
                Log.e("mess", String.valueOf(ratingBar.getRating()));

            }
        });

}

}
