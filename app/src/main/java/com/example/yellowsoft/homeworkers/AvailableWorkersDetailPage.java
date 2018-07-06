package com.example.yellowsoft.homeworkers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

/**
 * Created by info on 11-04-2018.
 */

public class AvailableWorkersDetailPage extends Activity {
    ImageView back_btn;
    AvailableWorkers availableWorkers;
    TextView call_btn,email_btn;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE;
    TextView st_exp;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availableworkers_detailpage);
        availableWorkers = (AvailableWorkers) getIntent().getSerializableExtra("aw");
        back_btn = (ImageView) findViewById(R.id.back_btn);
        ImageView imageView = (ImageView) findViewById(R.id.aw_image);
        TextView aw_id = (TextView) findViewById(R.id.aw_app_id);
        TextView aw_age = (TextView) findViewById(R.id.aw_age);
        TextView aw_nationality = (TextView) findViewById(R.id.aw_nationality);
        TextView aw_religion = (TextView) findViewById(R.id.aw_religion);
        TextView aw_sal = (TextView) findViewById(R.id.aw_sal);
        TextView aw_amt = (TextView) findViewById(R.id.aw_amt);
        TextView name = (TextView) findViewById(R.id.name);
        TextView st_id = (TextView) findViewById(R.id.st_name);
        TextView st_age = (TextView) findViewById(R.id.st_age);
        TextView st_nationality = (TextView) findViewById(R.id.st_nationality);
        TextView st_religion = (TextView) findViewById(R.id.st_religion);
        TextView st_salary = (TextView) findViewById(R.id.st_salary);
        TextView st_amount = (TextView) findViewById(R.id.st_amt);
        st_exp = (TextView) findViewById(R.id.st_exp);
        call_btn = (TextView) findViewById(R.id.call_btn);
        email_btn = (TextView) findViewById(R.id.email_btn);
        Picasso.with(this).load(availableWorkers.image).into(imageView);
        aw_id.setText(availableWorkers.name);
        aw_age.setText(availableWorkers.age);
        aw_nationality.setText(availableWorkers.nationality_title);
        aw_religion.setText(availableWorkers.religion_title);
        aw_sal.setText(availableWorkers.salary + " KD ");
        aw_amt.setText(availableWorkers.amount + " KD ");
        name.setText("Applicant Name,"+" "+ availableWorkers.name);


        st_id.setText(Session.GetWord(this,"Applicant ID"));
        st_age.setText(Session.GetWord(this,"Age"));
        st_nationality.setText(Session.GetWord(this,"Nationality"));
        st_religion.setText(Session.GetWord(this,"Religion"));
        st_salary.setText(Session.GetWord(this,"Salary"));
        st_amount.setText(Session.GetWord(this,"Amount"));
        call_btn.setText(Session.GetWord(this,"Call"));
        email_btn.setText(Session.GetWord(this,"Email"));
        st_exp.setText(Session.GetWord(this,"Experience"));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AvailableWorkersDetailPage.this.onBackPressed();
            }
        });

        call_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                JsonParser jsonParser = new JsonParser();
                if(!Session.GetSettings(AvailableWorkersDetailPage.this).equals("-1")) {
                    JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(AvailableWorkersDetailPage.this));
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + parse.get("phone").getAsString()));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (ActivityCompat.checkSelfPermission(AvailableWorkersDetailPage.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        return;
                    }

                    startActivity(callIntent);
                }
            }
        });

        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonParser jsonParser = new JsonParser();
                if(!Session.GetSettings(AvailableWorkersDetailPage.this).equals("-1")) {
                    JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(AvailableWorkersDetailPage.this));
                    Intent Email = new Intent(Intent.ACTION_SEND);
                    Email.setType("text/email");
                    Email.putExtra(Intent.EXTRA_EMAIL, new String[]{parse.get("email").getAsString()});
                    Email.putExtra(Intent.EXTRA_SUBJECT, "Add your Subject");
                    Email.putExtra(Intent.EXTRA_TEXT, "message");
                    startActivity(Intent.createChooser(Email, "Send Feedback:"));
                }
            }
        });
    }
}
