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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Set;

/**
 * Created by info on 12-05-2018.
 */

public class SettingsActivity extends Activity {
    ImageView back_btn,call_btn,email_btn;
    LinearLayout lang_btn,hwrequests_btn,available_hw_btn,parttime_request_btn,contact_btn,about_btn,corporate_request_btn,employee_request_btn;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE;
    TextView st_name,st_lang,st_settings,st_profile,st_logout,st_support,st_contact,st_about,st_contactus,logout_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        Session.forceRTLIfSupported(this);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        lang_btn = (LinearLayout) findViewById(R.id.lang_btn);
        hwrequests_btn = (LinearLayout) findViewById(R.id.hw_requests_btn);
        available_hw_btn = (LinearLayout) findViewById(R.id.available_hw_btn);
        parttime_request_btn = (LinearLayout) findViewById(R.id.parttime_request_btn);
        logout_btn = (TextView) findViewById(R.id.logout_btn);
        contact_btn = (LinearLayout) findViewById(R.id.contact_btn);
        about_btn = (LinearLayout) findViewById(R.id.about_btn);
        corporate_request_btn = (LinearLayout) findViewById(R.id.corporate_request_btn);
        employee_request_btn = (LinearLayout) findViewById(R.id.employee_request_btn);
        about_btn = (LinearLayout) findViewById(R.id.about_btn);
       // call_btn = (ImageView) findViewById(R.id.call_btn);
        //email_btn = (ImageView) findViewById(R.id.email_btn);
        st_name = (TextView) findViewById(R.id.st_name);
        st_lang = (TextView) findViewById(R.id.st_lang);
        st_settings = (TextView) findViewById(R.id.st_settings);
        st_profile = (TextView) findViewById(R.id.st_profile);
        st_logout = (TextView) findViewById(R.id.st_logout);
        st_support = (TextView) findViewById(R.id.st_support);
        st_contact = (TextView) findViewById(R.id.st_contact);
        st_about = (TextView) findViewById(R.id.st_about);
        //st_contactus = (TextView) findViewById(R.id.st_contactus);
        st_name.setText(Session.GetWord(this,"Settings"));
        st_lang.setText(Session.GetWord(this,"LANGUAGE"));
        st_settings.setText(Session.GetWord(this,"MY SETTINGS"));
        st_profile.setText(Session.GetWord(this,"Edit Profile"));
        st_logout.setText(Session.GetWord(this,"LogOut"));
        st_support.setText(Session.GetWord(this,"SUPPORT"));
        st_contact.setText(Session.GetWord(this,"CONTACT US"));
        st_about.setText(Session.GetWord(this,"ABOUT US"));
       // st_contactus.setText(Session.GetWord(this,"CONTACT US"));
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity.this.onBackPressed();
            }
        });

        hwrequests_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,HomeWorkerRequestsList.class);
                startActivity(intent);
            }
        });

        available_hw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,AvailableWorkersListActivity.class);
                startActivity(intent);
            }
        });

        parttime_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,PartTimeWorkersListActivity.class);
                startActivity(intent);
            }
        });

        corporate_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,CorporateListActivity.class);
                startActivity(intent);
            }
        });

        employee_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,EmployeeRequestListActivity.class);
                startActivity(intent);
            }
        });



//        call_btn.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view) {
//                JsonParser jsonParser = new JsonParser();
//                if(!Session.GetSettings(SettingsActivity.this).equals("-1")) {
//                    JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(SettingsActivity.this));
//                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.parse("tel:" + parse.get("phone").getAsString()));
//                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    if (ActivityCompat.checkSelfPermission(SettingsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
//                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
//                        return;
//                    }
//
//                    startActivity(callIntent);
//                }
//            }
//        });
//
//        email_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JsonParser jsonParser = new JsonParser();
//                if(!Session.GetSettings(SettingsActivity.this).equals("-1")) {
//                    JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(SettingsActivity.this));
//                    Intent Email = new Intent(Intent.ACTION_SEND);
//                    Email.setType("text/email");
//                    Email.putExtra(Intent.EXTRA_EMAIL, new String[]{parse.get("email").getAsString()});
//                    Email.putExtra(Intent.EXTRA_SUBJECT, "Add your Subject");
//                    Email.putExtra(Intent.EXTRA_TEXT, "message");
//                    startActivity(Intent.createChooser(Email, "Send Feedback:"));
//                }
//            }
//        });

//        profile_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SettingsActivity.this,EditProfilePage.class);
//                startActivity(intent);
//            }
//        });

        if (Session.GetUserId(SettingsActivity.this).equals("-1")){
            logout_btn.setVisibility(View.GONE);
        }else {
            logout_btn.setVisibility(View.VISIBLE);
        }

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Session.SetUserId(SettingsActivity.this, "-1");
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
            }
        });

//        contact_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SettingsActivity.this,ContactusActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        about_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =  new Intent(SettingsActivity.this,AboutPage.class);
//                startActivity(intent);
//            }
//        });



    }
}
