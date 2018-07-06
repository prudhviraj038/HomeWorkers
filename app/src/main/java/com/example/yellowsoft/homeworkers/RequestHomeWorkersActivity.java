package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by info on 04-07-2018.
 */

public class RequestHomeWorkersActivity extends Activity {
    LinearLayout male,female,select_religion,select_nationality,yes,no,select_service;
    ImageView male_checkbox,female_checkbox,yes_checkbox,no_checkbox;
    TextView male_option,female_option,yes_option,no_option,service_option,submit_btn,religion_option,nationality_option;
    EditText age,phone;
    ImageView nationality_close_btn,service_close_btn,close_btn;
    ListView religion_list,services_list,nationality_list;
    LinearLayout religion_popup,service_popup,nationality_popup;
    ReligionAdapter religionAdapter;
    NationalityAdapter nationalityAdapter;
    ServiceAdapter servicesAdapter;
    ArrayList<Nationality> nationalitiesfrom_api;
    ArrayList<Religions> religionsfrom_api;
    ArrayList<Services> servicesfrom_api;
    String type,exp,amount,msg;
    String request_id;
    ImageView back_btn;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeworker_request_form);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        nationalitiesfrom_api = new ArrayList<>();
        religionsfrom_api = new ArrayList<>();
        servicesfrom_api = new ArrayList<>();
        back_btn = (ImageView) findViewById(R.id.back_btn);
        male = (LinearLayout) findViewById(R.id.male);
        female = (LinearLayout) findViewById(R.id.female);
        select_nationality = (LinearLayout) findViewById(R.id.select_nationality);
        select_religion = (LinearLayout) findViewById(R.id.select_religion);
        yes = (LinearLayout) findViewById(R.id.yes);
        no = (LinearLayout) findViewById(R.id.no);
        select_service = (LinearLayout) findViewById(R.id.select_service);
        male_checkbox = (ImageView) findViewById(R.id.male_checkbox);
        female_checkbox  = (ImageView) findViewById(R.id.female_checkbox);
        yes_checkbox = (ImageView) findViewById(R.id.yes_checkbox);
        no_checkbox = (ImageView) findViewById(R.id.no_checkbox);
        male_option = (TextView) findViewById(R.id.male_option);
        female_option = (TextView) findViewById(R.id.female_option);
        yes_option = (TextView) findViewById(R.id.yes_option);
        no_option = (TextView) findViewById(R.id.no_option);
        service_option = (TextView) findViewById(R.id.service_option);
        religion_option = (TextView) findViewById(R.id.religion_option);
        nationality_option = (TextView) findViewById(R.id.nationality_option);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);
        submit_btn = (TextView) findViewById(R.id.submit_btn);
        nationality_close_btn = (ImageView) findViewById(R.id.nationality_close_btn);
        service_close_btn = (ImageView) findViewById(R.id.service_close_btn);
        close_btn = (ImageView) findViewById(R.id.close_btn);
        religion_list = (ListView) findViewById(R.id.religion_list);
        services_list = (ListView) findViewById(R.id.services_list);
        nationality_list = (ListView) findViewById(R.id.nationality_list);
        religion_popup = (LinearLayout) findViewById(R.id.religion_popup);
        service_popup = (LinearLayout) findViewById(R.id.service_popup);
        nationality_popup = (LinearLayout) findViewById(R.id.nationality_popup);
        back_btn = (ImageView) findViewById(R.id.back_btn);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religion_popup.setVisibility(View.GONE);
            }
        });

        nationality_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.GONE);
            }
        });

        service_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_popup.setVisibility(View.GONE);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestHomeWorkersActivity.this.onBackPressed();
            }
        });

        religionAdapter = new ReligionAdapter(this,religionsfrom_api);
        religion_list.setAdapter(religionAdapter);

        nationalityAdapter = new NationalityAdapter(this,nationalitiesfrom_api);
        nationality_list.setAdapter(nationalityAdapter);

        servicesAdapter = new ServiceAdapter(this,servicesfrom_api);
        services_list.setAdapter(servicesAdapter);

        select_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_popup.setVisibility(View.VISIBLE);
            }
        });

        select_religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religion_popup.setVisibility(View.VISIBLE);
            }
        });

        select_nationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.VISIBLE);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_requests();
            }
        });

        services_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                service_popup.setVisibility(View.GONE);
                service_option.setText(servicesfrom_api.get(i).title);
            }
        });

        religion_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                religion_popup.setVisibility(View.GONE);
                religion_option.setText(religionsfrom_api.get(i).title);
            }
        });

        nationality_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nationality_popup.setVisibility(View.GONE);
                nationality_option.setText(nationalitiesfrom_api.get(i).title);

            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (male_checkbox.isEnabled()){
                    male_checkbox.setImageResource(R.drawable.checked);
                    female_checkbox.setImageResource(R.drawable.unchecked);
                    type="male";
                }else {
                    male_checkbox.setImageResource(R.drawable.unchecked);
                }
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (female_checkbox.isEnabled()){
                    female_checkbox.setImageResource(R.drawable.checked);
                    male_checkbox.setImageResource(R.drawable.unchecked);
                    type="female";
                }else {
                    female_checkbox.setImageResource(R.drawable.unchecked);

                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yes_checkbox.isEnabled()){
                    yes_checkbox.setImageResource(R.drawable.checked);
                    no_checkbox.setImageResource(R.drawable.unchecked);
                    exp = "yes";

                }else {
                    yes_checkbox.setImageResource(R.drawable.unchecked);
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (no_checkbox.isEnabled()){
                    no_checkbox.setImageResource(R.drawable.checked);
                    yes_checkbox.setImageResource(R.drawable.unchecked);
                    exp="no";
                }else {
                    no_checkbox.setImageResource(R.drawable.unchecked);
                }
            }
        });
        get_services();
        get_nationalities();
        get_religions();
        get_settings();

    }

    public void get_requests(){
        String type_string = type;
        String religion_string = religion_option.getText().toString();
        String nationality_string = nationality_option.getText().toString();
        String age_string = age.getText().toString();
        String exp_string= exp;
        String service_string = service_option.getText().toString();
        String phone_string = phone.getText().toString();
        if (type == ""){
            Toast.makeText(RequestHomeWorkersActivity.this,"Please Pass Type male/female",Toast.LENGTH_SHORT).show();
        }else if (nationality_string.equals("")){
            Toast.makeText(RequestHomeWorkersActivity.this,"Please Enter Nationality",Toast.LENGTH_SHORT).show();
            nationality_option.requestFocus();
        }else if (religion_string.equals("")){
            Toast.makeText(RequestHomeWorkersActivity.this,"Please Enter Religion",Toast.LENGTH_SHORT).show();
            religion_option.requestFocus();
        }else if (age_string.equals("")){
            Toast.makeText(RequestHomeWorkersActivity.this,"Please Enter Age",Toast.LENGTH_SHORT).show();
            age.requestFocus();
        }else if (exp == ""){
            Toast.makeText(RequestHomeWorkersActivity.this,"Please Enter experience in kuwait",Toast.LENGTH_SHORT).show();
        }else if (service_string == ""){
            Toast.makeText(RequestHomeWorkersActivity.this,"Please Enter Services",Toast.LENGTH_SHORT).show();
            service_option.requestFocus();
        }else if (phone_string.equals("")){
            Toast.makeText(RequestHomeWorkersActivity.this,"Please Enter Phone",Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(RequestHomeWorkersActivity.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait")
                    .setCancellable(true)
                    .setMaxProgress(100)
                    .show();
            Ion.with(this)
                    .load(Session.SERVER_URL + "homew_reqs.php")
                    .setBodyParameter("type",type)
                    .setBodyParameter("age",age_string)
                    .setBodyParameter("nationality", nationality_string)
                    .setBodyParameter("religion", religion_string)
                    .setBodyParameter("exp_kuwait", exp)
                    .setBodyParameter("member_id", Session.GetUserId(this))
                    .setBodyParameter("services",service_string)
                    .setBodyParameter("phone",phone_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                Log.e("editresponse", result.toString());
                                hud.dismiss();
                                if (result.get("status").getAsString().equals("Success")) {
                                    request_id = result.get("id").getAsString();
                                    Toast.makeText(RequestHomeWorkersActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RequestHomeWorkersActivity.this, PaymentPage.class);
                                    intent.putExtra("amount", amount);
                                    RequestHomeWorkersActivity.this.startActivityForResult(intent, 1);
                                } else {
                                    Toast.makeText(RequestHomeWorkersActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });


        }

    }


    public void get_services(){
        Ion.with(this)
                .load(Session.SERVER_URL+"services.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            for (int i = 0; i < result.size(); i++) {
                                Services services = new Services(result.get(i).getAsJsonObject(), RequestHomeWorkersActivity.this);
                                servicesfrom_api.add(services);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void get_nationalities(){
        Ion.with(this)
                .load(Session.SERVER_URL+"nationality.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            for (int i = 0; i < result.size(); i++) {
                                Nationality nationality = new Nationality(result.get(i).getAsJsonObject(), RequestHomeWorkersActivity.this);
                                nationalitiesfrom_api.add(nationality);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 1) {
            return;
        }
        if (resultCode == -1) {
            if (data != null && data.hasExtra("message")) {
                msg = data.getExtras().getString("message");
                Log.e("toast", msg);
                if (this.msg.equals("success")) {
                    Toast.makeText(this, "Payment done Successfully", Toast.LENGTH_SHORT).show();
                    update_payment();
                } else if (this.msg.equals("failure")) {
                    Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (resultCode != 0) {
        }
    }

    public void get_religions(){
        Ion.with(this)
                .load(Session.SERVER_URL+"religions.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        try {
                            for (int i = 0; i < result.size(); i++) {
                                Religions religions = new Religions(result.get(i).getAsJsonObject(), RequestHomeWorkersActivity.this);
                                religionsfrom_api.add(religions);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }

    public void  get_settings(){
        Ion.with(this)
                .load(Session.SERVER_URL+"settings.php")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                         amount = result.get("home_req_amount").getAsString();
                    }
                });
    }

    public void update_payment(){
        final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();
        Ion.with(this)
                .load(Session.SERVER_URL+"update_homereq_payment.php.php")
                .setBodyParameter("req_id",request_id)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        hud.dismiss();
                    }
                });
    }
}
