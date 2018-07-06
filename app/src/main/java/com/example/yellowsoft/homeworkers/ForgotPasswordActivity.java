package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by info on 17-04-2018.
 */

public class ForgotPasswordActivity extends Activity {
    ImageView back_btn;
    EditText email;
    LinearLayout submit;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        email = (EditText) findViewById(R.id.email);
        submit = (LinearLayout) findViewById(R.id.submit);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPasswordActivity.this.onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot_password();
            }
        });
    }


    public void forgot_password(){

        String email_string = email.getText().toString();
        if (email_string.equals("")){
            Toast.makeText(ForgotPasswordActivity.this,"Please Enter Email.",Toast.LENGTH_SHORT).show();
            email.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(ForgotPasswordActivity.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVER_URL + "forget-password.php.php")
                    .setBodyParameter("email", email_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("login", result.toString());
                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(ForgotPasswordActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgotPasswordActivity.this, RegisterActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ForgotPasswordActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }
    }
}
