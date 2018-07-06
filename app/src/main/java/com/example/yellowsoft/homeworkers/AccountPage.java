package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by info on 18-04-2018.
 */

public class AccountPage extends Activity {
    ImageView back_btn;
    TextView edit_profile,change_password;
    LinearLayout logout_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        edit_profile = (TextView) findViewById(R.id.edit_profile);
        change_password = (TextView) findViewById(R.id.change_password);
        logout_btn = (LinearLayout) findViewById(R.id.logout_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountPage.this.onBackPressed();
            }
        });
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountPage.this,EditProfilePage.class);
                startActivity(intent);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session.SetUserId(AccountPage.this,"-1");
                Intent intent = new Intent(AccountPage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
