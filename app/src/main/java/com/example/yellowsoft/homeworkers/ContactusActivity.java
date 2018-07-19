package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by info on 12-05-2018.
 */

public class ContactusActivity extends Activity {
    ImageView back_btn;
    EditText name,email,phone,message;
    TextView send_btn;
    TextView st_name,st_email,st_phone,st_message;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus_page);
        Session.forceRTLIfSupported(this);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        message = (EditText) findViewById(R.id.message);
        send_btn = (TextView) findViewById(R.id.send_btn);
//        st_name = (TextView) findViewById(R.id.st_name);
//        st_email = (TextView) findViewById(R.id.st_email);
//        st_phone = (TextView) findViewById(R.id.st_phone);
//        st_message  = (TextView) findViewById(R.id.st_message);

//        st_name.setText(Session.GetWord(this,"Name"));
//        st_email.setText(Session.GetWord(this,"Email"));
//        st_phone.setText(Session.GetWord(this,"Phone"));
//        st_message.setText(Session.GetWord(this,"message"));
        send_btn.setText(Session.GetWord(this,"Send"));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactusActivity.this.onBackPressed();
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactusform();
            }
        });

    }

    public void contactusform(){
        String name_string = name.getText().toString();
        String email_string = email.getText().toString();
        String phone_string = phone.getText().toString();
        String message_string = message.getText().toString();
        if (name_string.equals("")){
            Toast.makeText(ContactusActivity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
            name.requestFocus();
        }else if (email_string.equals("")){
            Toast.makeText(ContactusActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            email.requestFocus();
        }else if (phone_string.equals("")){
            Toast.makeText(ContactusActivity.this,"Please Enter Mobile Number",Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else if (message_string.equals("")){
            Toast.makeText(ContactusActivity.this,"Please Enter Message",Toast.LENGTH_SHORT).show();
            message.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
            Ion.with(this)
                    .load(Session.SERVER_URL + "contact-us.php")
                    .setBodyParameter("name",name_string)
                    .setBodyParameter("email",email_string)
                    .setBodyParameter("phone",phone_string)
                    .setBodyParameter("message",message_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                if (result.get("status").getAsString().equals("Success")){
                                    Toast.makeText(ContactusActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT);
                                }else {
                                    Toast.makeText(ContactusActivity.this,result.get("message").getAsString(),Toast.LENGTH_SHORT);
                                }
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }


                        }
                    });
        }
    }
}
