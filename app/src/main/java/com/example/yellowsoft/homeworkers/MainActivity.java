package com.example.yellowsoft.homeworkers;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    MainActivityAdapter adapter;
    ArrayList<String> titles;
    ArrayList<String> images;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    LinearLayout drawerView;
    RelativeLayout mainView;
    TextView login_btn,profile_btn;
    ImageView menu_btn,lang_btn;
    LinearLayout about_btn,contact_btn,settings_btn;
    int MY_PERMISSIONS_REQUEST_CALL_PHONE;
    TextView st_title,st_contact,st_about,st_settings;
    private static long back_pressed;
    LinearLayout corporate_popup;
    TextView corporaterequest_btn,employeerequest_btn,cancel_btn;

    private void openNavigation(){

        if(mDrawerLayout.isDrawerOpen(GravityCompat.START))

            mDrawerLayout.closeDrawer(GravityCompat.START,true);
        else
            mDrawerLayout.openDrawer(GravityCompat.START,true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Session.forceRTLIfSupported(this);
        titles = new ArrayList<>();
        images = new ArrayList<>();
        titles.add("Home Workers");
        titles.add("Part-Time Workers");
        titles.add("Available Workers");
        titles.add("CORPORATE");
//        images.add(R.drawable.homeworkers);
//        images.add(R.drawable.parttimeworkers);
//        images.add(R.drawable.availableworkers);
//        images.add(R.drawable.availableworkers);
        try {
            JsonParser jsonParser = new JsonParser();
            if (!Session.GetSettings(MainActivity.this).equals("-1")) {
                JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(MainActivity.this));
                images.add(parse.get("image1").getAsString());
                Log.e("image",parse.get("image1").getAsString());
                images.add(parse.get("image2").getAsString());
                images.add(parse.get("image3").getAsString());
                images.add(parse.get("image4").getAsString());
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        drawerView = (LinearLayout) findViewById(R.id.drawerView);
        mainView = (RelativeLayout) findViewById(R.id.mainView);
        profile_btn = (TextView) findViewById(R.id.profile_btn);
        login_btn = (TextView) findViewById(R.id.login_btn);
        menu_btn = (ImageView) findViewById(R.id.menu_btn);
        lang_btn = (ImageView) findViewById(R.id.lang_btn);
        about_btn = (LinearLayout) findViewById(R.id.about_btn);
        contact_btn = (LinearLayout) findViewById(R.id.contact_btn);
        settings_btn = (LinearLayout) findViewById(R.id.settings_btn);
        st_title = (TextView)  findViewById(R.id.st_title);
        st_contact = (TextView) findViewById(R.id.st_contact);
        st_about = (TextView) findViewById(R.id.st_about);
        st_settings = (TextView) findViewById(R.id.st_settings);
        corporate_popup = (LinearLayout) findViewById(R.id.corporate_popup);
        corporaterequest_btn = (TextView) findViewById(R.id.corporaterequest_btn);
        employeerequest_btn = (TextView) findViewById(R.id.employeerequest_btn);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNavigation();
            }
        });
       // st_title.setText(Session.GetWord(MainActivity.this,"Main"));
        st_contact.setText(Session.GetWord(this,"CONTACT US"));
        st_about.setText(Session.GetWord(this,"ABOUT US"));
        st_settings.setText(Session.GetWord(this,"SETTINGS"));

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AccountPage.class);
                startActivity(intent);
            }
        });

        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AboutPage.class);
                startActivity(intent);
            }
        });

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Session.GetUserId(MainActivity.this).equals("-1")){
                    Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                }
            }
        });

        contact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ContactusActivity.class);
                startActivity(intent);
            }
        });

        lang_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Session.GetLang(MainActivity.this).equals("en")) {
                    Session.SetLang(MainActivity.this, "ar");
                    setLocale(Session.GetLang(MainActivity.this));
                    lang_btn.setImageResource(R.drawable.english_icon);
                }else {
                    Session.SetLang(MainActivity.this, "en");

                    setLocale(Session.GetLang(MainActivity.this));

//                    arabic_btn.setText(Session.GetWord(MainActivity.this,"ARABIC"));
                }
            }
        });

//        contact_btn.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view) {
//                JsonParser jsonParser = new JsonParser();
//                if(!Session.GetSettings(MainActivity.this).equals("-1")) {
//                    JsonObject parse = (JsonObject) jsonParser.parse(Session.GetSettings(MainActivity.this));
//                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.parse("tel:" + parse.get("phone").getAsString()));
//                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
//                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
//                        return;
//                    }
//
//                    startActivity(callIntent);
//                }
//            }
//        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();

            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainView.setTranslationX(slideOffset * drawerView.getWidth());
//                if (Session.GetLang(MainActivity.this).equals("en")){
//                    mainView.setTranslationX(slideOffset * drawerView.getWidth());
//                }else {
//                    mainView.setTranslationX(-slideOffset * drawerView.getWidth());
//                }
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();


            }
        };


        listView = (ListView) findViewById(R.id.list);
        adapter = new MainActivityAdapter(this,titles,images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (titles.get(i).equals("Home Workers")){
                    Intent intent = new Intent(MainActivity.this,HomeWorkersActivity.class);
                    startActivity(intent);
                }else if (titles.get(i).equals("Part-Time Workers")){
                    Intent intent = new Intent(MainActivity.this,PartTimeWorkersActivity.class);
                    startActivity(intent);

                }else if (titles.get(i).equals("Available Workers")){
                    Intent intent = new Intent(MainActivity.this,AvailableWorkersActivity.class);
                    startActivity(intent);
                }else if (titles.get(i).equals("CORPORATE")){
                   corporate_popup.setVisibility(View.VISIBLE);
                }
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corporate_popup.setVisibility(View.GONE);

            }
        });

        corporaterequest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent =  new Intent(MainActivity.this,CorporateRequestActivity.class);
                  startActivity(intent);
            }
        });

        employeerequest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,EmployeeRequestActivity.class);
                    startActivity(intent);
            }
        });

        get_settings();
    }


    public void get_settings(){
        Ion.with(this)
                .load(Session.SERVER_URL+"settings.php")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.e("settings",result.toString());
                        Session.SetSettings(MainActivity.this,result.toString());

                    }
                });
    }


    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }



    @Override
    public void onBackPressed()
    {
        // code here to show dialog

        if (back_pressed + 10000 > System.currentTimeMillis()){
            super.onBackPressed();}
        else{
            back_pressed = System.currentTimeMillis();
        }
    }

}
