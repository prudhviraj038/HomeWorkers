package com.example.yellowsoft.homeworkers;

import android.app.Activity;
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
 * Created by info on 18-04-2018.
 */

public class EditProfilePage extends Activity {
    EditText fname,lname,email,phone;
    ImageView back_btn;
    LinearLayout submit_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_page);
        Session.forceRTLIfSupported(this);
        fname = (EditText) findViewById(R.id.comments_edit);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        submit_btn = (LinearLayout) findViewById(R.id.submit_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfilePage.this.onBackPressed();
            }
        });

    }

    public void edit_profile(){

        String fname_string = fname.getText().toString();
        String lname_string = lname.getText().toString();
        String email_string = email.getText().toString();
        String phone_string = phone.getText().toString();
        if (fname_string.equals("")){
            Toast.makeText(EditProfilePage.this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
            fname.requestFocus();
        }else if (lname_string.equals("")){
            Toast.makeText(EditProfilePage.this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
            lname.requestFocus();
        }else if (email_string.equals("")){
            Toast.makeText(EditProfilePage.this,"Please Enter Email Address",Toast.LENGTH_SHORT).show();
            email.requestFocus();
        }else if (phone_string.equals("")){
            Toast.makeText(EditProfilePage.this,"Please Enter Phone Number",Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(EditProfilePage.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVER_URL + "edit-member.php")
                    .setBodyParameter("member_id",Session.GetUserId(this))
                    .setBodyParameter("fname", fname_string)
                    .setBodyParameter("lname", lname_string)
                    .setBodyParameter("email", email_string)
                    .setBodyParameter("password", phone_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                Log.e("editresponse", result.toString());
                                hud.dismiss();
                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(EditProfilePage.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    EditProfilePage.this.onBackPressed();
                                } else {
                                    Toast.makeText(EditProfilePage.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }
    }
}
