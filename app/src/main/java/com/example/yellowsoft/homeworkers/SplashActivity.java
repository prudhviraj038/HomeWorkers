package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by info on 13-05-2018.
 */

public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        get_words();
    }

    public void get_words(){
        Ion.with(this)
                .load(Session.SERVER_URL+"words-json.php")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            Session.SetEnWords(SplashActivity.this, result.get("en").getAsJsonObject().toString());
                            Session.SetArWords(SplashActivity.this, result.get("ar").getAsJsonObject().toString());
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
//                            SharedPreferences sharedPreferences = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor=sharedPreferences.edit();
//                            boolean  firstTime=sharedPreferences.getBoolean("first", true);
//                            if(firstTime) {
//                                editor.putBoolean("first", false);
//                                editor.commit();
//                                editor.apply();
//                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

}
