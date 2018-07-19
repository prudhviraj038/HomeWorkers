package com.example.yellowsoft.homeworkers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
 * Created by info on 17-04-2018.
 */

public class AddAvailableWorkersActivity extends Activity {
    ImageView back_btn;
    EditText name,age,sal,amount,experience;
    String nationality_id,religion_id;
    ArrayList<Nationality> nationalitiesfrom_api;
    ArrayList<Religions> religionsfrom_api;
    LinearLayout submit;
    TextView nationality,religion;
    NationalityAdapter nationalityAdapter;
    ReligionAdapter religionAdapter;
    LinearLayout nationality_popup,religion_popup;
    ImageView nationality_close_btn,religion_close_btn;
    ListView nationality_list,religion_list;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_available_worker);
        Session.forceRTLIfSupported(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        nationalitiesfrom_api = new ArrayList<>();
        religionsfrom_api = new ArrayList<>();
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        nationality = (TextView) findViewById(R.id.nationality);
        religion = (TextView) findViewById(R.id.religion);
        sal = (EditText) findViewById(R.id.sal);
        amount = (EditText) findViewById(R.id.amount);
        experience = (EditText) findViewById(R.id.experience);
        submit= (LinearLayout) findViewById(R.id.submit);
        nationality_popup = (LinearLayout) findViewById(R.id.nationality_popup);
        religion_popup = (LinearLayout) findViewById(R.id.religion_popup);
        nationality_close_btn = (ImageView) findViewById(R.id.nationality_close_btn);
        religion_close_btn = (ImageView) findViewById(R.id.religion_close_btn);
        nationality_list = (ListView) findViewById(R.id.nationality_list);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        religion_list = (ListView) findViewById(R.id.religion_list);
        nationalityAdapter = new NationalityAdapter(this,nationalitiesfrom_api);
        nationality_list.setAdapter(nationalityAdapter);
        religionAdapter = new ReligionAdapter(this,religionsfrom_api);
        religion_list.setAdapter(religionAdapter);

        nationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(AddAvailableWorkersActivity.this, R.anim.myanim);
                nationality_popup.startAnimation(anim);
//                Dialog dialog = onNationalityChoice();
//                dialog.show();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAvailableWorkersActivity.this.onBackPressed();
            }
        });

        nationality_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nationality_popup.setVisibility(View.GONE);
            }
        });

        religion_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religion_popup.setVisibility(View.GONE);
            }
        });

        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religion_popup.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(AddAvailableWorkersActivity.this, R.anim.myanim);
                religion_popup.startAnimation(anim);
//                Dialog dialog = onReligionChoice();
//                dialog.show();
            }
        });

        nationality_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nationality_popup.setVisibility(View.GONE);
                nationality.setText(nationalitiesfrom_api.get(i).title);
            }
        });

        religion_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                religion_popup.setVisibility(View.GONE);
                religion.setText(religionsfrom_api.get(i).title);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_available_workers();
            }
        });
        get_nationalities();
        get_religions();

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
                                Nationality nationality = new Nationality(result.get(i).getAsJsonObject(), AddAvailableWorkersActivity.this);
                                nationalitiesfrom_api.add(nationality);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
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
                                Religions religions = new Religions(result.get(i).getAsJsonObject(), AddAvailableWorkersActivity.this);
                                religionsfrom_api.add(religions);
                            }
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }
                });
    }


    public Dialog onNationalityChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] array = new CharSequence[nationalitiesfrom_api.size()];
        for(int i=0;i<nationalitiesfrom_api.size();i++){

            array[i] = nationalitiesfrom_api.get(i).title;
        }

        builder.setTitle("Select Nationality")
                .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String selectedItem = array[i].toString();
                        Log.e("select",selectedItem);
                        nationality.setText(selectedItem);
                        nationality_id = nationalitiesfrom_api.get(i).id;


                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    public Dialog onReligionChoice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] array = new CharSequence[religionsfrom_api.size()];
        for(int i=0;i<religionsfrom_api.size();i++){

            array[i] = religionsfrom_api.get(i).title;
        }

        builder.setTitle("Select Religion")
                .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String selectedItem = array[i].toString();
                        Log.e("select",selectedItem);
                        religion.setText(selectedItem);
                        religion_id = religionsfrom_api.get(i).id;


                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }



    public void add_available_workers(){
        String fname_string = name.getText().toString();
        String age_string = age.getText().toString();
        String sal_string = sal.getText().toString();
        String amt_string = amount.getText().toString();
        String experience_string = experience.getText().toString();
        String nationality_string = nationality.getText().toString();
        String religion_string = religion.getText().toString();
        if (fname_string.equals("")){
            Toast.makeText(AddAvailableWorkersActivity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
            name.requestFocus();
        }else if (age_string.equals("")){
            Toast.makeText(AddAvailableWorkersActivity.this,"Please Enter Age",Toast.LENGTH_SHORT).show();
            age.requestFocus();
        }else if (nationality_string==""){
            Toast.makeText(AddAvailableWorkersActivity.this,"Please Enter Nationality",Toast.LENGTH_SHORT).show();
            nationality.requestFocus();
        }else if (religion_string == ""){
            Toast.makeText(AddAvailableWorkersActivity.this,"Please Enter Religion",Toast.LENGTH_SHORT).show();
            religion.requestFocus();
        }else if (sal_string.equals("")){
            Toast.makeText(AddAvailableWorkersActivity.this,"Please Enter Salary",Toast.LENGTH_SHORT).show();
            sal.requestFocus();
        }else if (amt_string.equals("")){
            Toast.makeText(AddAvailableWorkersActivity.this,"Please Enter Amount",Toast.LENGTH_SHORT).show();
            amount.requestFocus();
        }else if (experience_string.equals("")){
            Toast.makeText(AddAvailableWorkersActivity.this,"Please Enter Experience",Toast.LENGTH_SHORT).show();
            experience.requestFocus();
        }else {
            final KProgressHUD hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setCancellable(true).setMaxProgress(100).show();

            Ion.with(this)
                    .load(Session.SERVER_URL + "add-availworker.php")
                    .setBodyParameter("member_id", Session.GetUserId(this))
                    .setBodyParameter("name", fname_string)
                    .setBodyParameter("age", age_string)
                    .setBodyParameter("nationality", nationality_string)
                    .setBodyParameter("religion", religion_string)
                    .setBodyParameter("salary", sal_string)
                    .setBodyParameter("amount", amt_string)
                    .setBodyParameter("experience", experience_string)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            try {
                                hud.dismiss();
                                Log.e("response", result.toString());
                                if (result.get("status").getAsString().equals("Success")) {
                                    Toast.makeText(AddAvailableWorkersActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                    AddAvailableWorkersActivity.this.onBackPressed();
                                } else {
                                    Toast.makeText(AddAvailableWorkersActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();

                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }
    }



}
