package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by info on 18-04-2018.
 */

public class AboutPage extends Activity {
    ImageView back_btn;
    TextView about;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        Session.forceRTLIfSupported(this);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        about = (TextView) findViewById(R.id.about);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutPage.this.onBackPressed();
            }
        });

        JsonParser jsonParser = new JsonParser();
        if(!Session.GetSettings(AboutPage.this).equals("-1")) {
            JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(AboutPage.this));
             about.setText(Html.fromHtml(parse.get("about").getAsString()));
        }
    }
}
