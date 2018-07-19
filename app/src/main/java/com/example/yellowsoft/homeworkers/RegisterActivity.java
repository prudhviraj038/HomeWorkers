package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by info on 17-04-2018.
 */

public class RegisterActivity extends Activity {
    ViewFlipper flipper;
    LinearLayout login_btn,signin_btn,submit_btn,submit;
    EditText email,password,reg_fname,reg_lname,reg_email,reg_password,phone;
    TextView fp_btn,register;
    TextView st_email,st_password,login_submit,register_ll,st_fname,st_lname,st_email_add,st_phone,st_pass;
    ImageView back_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        Session.forceRTLIfSupported(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        login_btn = (LinearLayout) findViewById(R.id.login_btn);
        signin_btn = (LinearLayout) findViewById(R.id.signin_btn);
        submit_btn = (LinearLayout) findViewById(R.id.submit_btn);
        submit = (LinearLayout) findViewById(R.id.submit);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        reg_fname = (EditText) findViewById(R.id.reg_fname);
        reg_lname = (EditText) findViewById(R.id.reg_lname);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_password = (EditText) findViewById(R.id.reg_password);
        phone = (EditText) findViewById(R.id.phone);
        register = (TextView) findViewById(R.id.register);
        fp_btn = (TextView) findViewById(R.id.fp_btn);
        final TextView login = (TextView) findViewById(R.id.login);
        st_email = (TextView) findViewById(R.id.st_email);
        st_password = (TextView) findViewById(R.id.st_password);
        login_submit = (TextView) findViewById(R.id.login_submit);
        register_ll = (TextView) findViewById(R.id.register_ll);
        st_fname = (TextView) findViewById(R.id.st_fname);
        st_lname = (TextView) findViewById(R.id.st_lname);
        st_email_add = (TextView) findViewById(R.id.st_email_add);
        st_phone = (TextView) findViewById(R.id.st_phone);
        st_pass = (TextView) findViewById(R.id.st_pass);
        back_btn = (ImageView) findViewById(R.id.back_btn);

        st_email.setText(Session.GetWord(this,"Email Address"));
        st_password.setText(Session.GetWord(this,"Password"));
        fp_btn.setText(Session.GetWord(this,"Forgot Password"));
        login_submit.setText(Session.GetWord(this,"Submit"));
        st_fname.setText(Session.GetWord(this,"First Name"));
        st_lname.setText(Session.GetWord(this,"Last Name"));
        st_pass.setText(Session.GetWord(this,"Password"));
        st_email_add.setText(Session.GetWord(this,"Email Address"));
        st_phone.setText(Session.GetWord(this,"Mobile Number"));
        register_ll.setText(Session.GetWord(this,"Register"));


        login_btn.setBackgroundColor(getResources().getColor(R.color.registerbackground));
        register.setTextColor(getResources().getColor(R.color.white));
        signin_btn.setBackgroundColor(getResources().getColor(R.color.black));
        flipper.setDisplayedChild(0);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_btn.setBackgroundColor(getResources().getColor(R.color.registerbackground));
                register.setTextColor(getResources().getColor(R.color.white));
                signin_btn.setBackgroundColor(getResources().getColor(R.color.black));
                flipper.setDisplayedChild(0);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin_btn.setBackgroundColor(getResources().getColor(R.color.registerbackground));
                login_btn.setBackgroundColor(getResources().getColor(R.color.black));
                login.setTextColor(getResources().getColor(R.color.white));
                flipper.setDisplayedChild(1);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        fp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.onBackPressed();
            }
        });
    }

    public void login(){

        String email_string = email.getText().toString();
        String password_string = password.getText().toString();
        if (email_string.equals("")){
            Toast.makeText(RegisterActivity.this,"Please Enter Email Address",Toast.LENGTH_SHORT).show();
            email.requestFocus();
        }else if (password_string.equals("")){
            Toast.makeText(RegisterActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            password.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(RegisterActivity.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVER_URL + "login.php")
                    .setBodyParameter("email", email_string)
                    .setBodyParameter("password", password_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("login", result.toString());

                                if (result.get("status").getAsString().equals("Success")) {
                                    Session.SetUserId(RegisterActivity.this, result.get("member_id").getAsString());
                                    Toast.makeText(RegisterActivity.this, result.get("name").getAsString(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, result.get("name").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }
    }


    public void register(){

        String fname_string = reg_fname.getText().toString();
        String lname_string = reg_lname.getText().toString();
        String email_string = reg_email.getText().toString();
        String password_string = reg_password.getText().toString();
        String phone_string = phone.getText().toString();
        if (fname_string.equals("")){
            Toast.makeText(RegisterActivity.this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
            reg_fname.requestFocus();
        }else if (lname_string.equals("")){
            Toast.makeText(RegisterActivity.this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
            reg_lname.requestFocus();
        }else if (email_string.equals("")){
            Toast.makeText(RegisterActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            reg_email.requestFocus();
        }else if (password_string.equals("")){
            Toast.makeText(RegisterActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            reg_password.requestFocus();
        }else if(phone_string.equals("")){
            Toast.makeText(RegisterActivity.this,"Please Enter Phone",Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(RegisterActivity.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVER_URL + "add-member.php")
                    .setBodyParameter("fname", fname_string)
                    .setBodyParameter("lname", lname_string)
                    .setBodyParameter("email", email_string)
                    .setBodyParameter("password", password_string)
                    .setBodyParameter("phone",phone_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("register", result.toString());

                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(RegisterActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    login_btn.setBackgroundColor(getResources().getColor(R.color.registerdeselect));
                                    register.setTextColor(getResources().getColor(R.color.registerdeselect));
                                    flipper.setDisplayedChild(0);
                                } else {
                                    Toast.makeText(RegisterActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }

    }
}
